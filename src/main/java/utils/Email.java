package utils;

import com.refactorable.guerrillamail.api.client.GuerrillaMailClient;
import com.refactorable.guerrillamail.api.client.factory.GuerrillaMailClientFactory;
import com.refactorable.guerrillamail.api.client.model.request.AddressRequest;
import com.refactorable.guerrillamail.api.client.model.request.EmailRequest;
import com.refactorable.guerrillamail.api.client.model.response.AddressResponse;
import com.refactorable.guerrillamail.api.client.model.response.EmailResponse;
import com.refactorable.guerrillamail.api.client.model.response.EmailsResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

import static com.refactorable.guerrillamail.api.client.model.request.AddressRequest.*;
import static com.refactorable.guerrillamail.api.client.model.request.EmailRequest.*;
import static com.refactorable.guerrillamail.api.client.model.request.EmailsRequest.*;

public class Email {

    private static GuerrillaMailClient guerrillaMailClient;
    private static AddressResponse addressResponse;

    public Email() {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://api.guerrillamail.com");
        guerrillaMailClient = GuerrillaMailClientFactory.defaultClient(webTarget);
        addressResponse = guerrillaMailClient.address( AddressRequest.initialize() );
    }

    public String getEmailAddress() {
        return addressResponse.getAddress();
    }

    public String getAuthCode() throws TimeoutException {
        var email = getEmailBySubject(XmlParser.getTextByKey("CustomerRegEmailSubject"));

        var pattern = Pattern.compile(String.format("(?<=%s).*?(?=</)", XmlParser.getTextByKey("AuthCode")));
        var matcher = pattern.matcher(email.getBody());
        if (matcher.find()) {
            return matcher.group(0);
        }
        throw new NullPointerException("Getting email auth code failed");
    }

    public String getForgotPasswordUrl() throws TimeoutException {
        var email = getEmailBySubject(XmlParser.getTextByKey("ForgotPassEmailSubject"));

        var pattern = Pattern.compile(String.format("(?<=<a href=\").*?(?=\">%s</a>)", XmlParser.getTextByKey("ForgotPassword")));
        var matcher = pattern.matcher(email.getBody());
        if (matcher.find()) {
            return matcher.group(0);
        }
        throw new NullPointerException("Getting forgot password link is failed");
    }

    private EmailResponse getEmailBySubject(String subject) throws TimeoutException {
        final EmailResponse[] response = new EmailResponse[1];
        WaitHelper.pollingWait(5 * 60000, 5000, () -> {
            var emails = guerrillaMailClient.emails(
                    emails( addressResponse.getSessionId(), 0L, 0))
                    .getEmails();

            var email = emails.stream()
                    .filter(e -> e.getSubject().contains(subject))
                    .findFirst();
            email.ifPresent(emailResponse -> response[0] = emailResponse);

            return email.isPresent();
        });

        var email = guerrillaMailClient.email(
                fetch(addressResponse.getSessionId(), response[0].getId())
        );

        guerrillaMailClient.delete(
                delete(addressResponse.getSessionId(), email.getId())
        );

        return email;
    }
}
