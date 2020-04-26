package utils;

import com.refactorable.guerrillamail.api.client.GuerrillaMailClient;
import com.refactorable.guerrillamail.api.client.factory.GuerrillaMailClientFactory;
import com.refactorable.guerrillamail.api.client.model.request.AddressRequest;
import com.refactorable.guerrillamail.api.client.model.request.EmailRequest;
import com.refactorable.guerrillamail.api.client.model.response.AddressResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

import static com.refactorable.guerrillamail.api.client.model.request.AddressRequest.forget;
import static com.refactorable.guerrillamail.api.client.model.request.EmailsRequest.emails;

public class Email {

    private static GuerrillaMailClient guerrillaMailClient;
    private static AddressResponse addressResponse;

    public Email() {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://api.guerrillamail.com");
        guerrillaMailClient = GuerrillaMailClientFactory.defaultClient(webTarget);
        addressResponse = guerrillaMailClient.address( AddressRequest.initialize() );
    }

    public String getEmail() {
        return addressResponse.getAddress();
    }

    public String getAuthCode() throws TimeoutException {
        WaitHelper.pollingWait(5 * 60000, 5000, () ->
                guerrillaMailClient
                        .emails( emails( addressResponse.getSessionId(), 0L, 0) )
                        .getEmails()
                        .size() > 1
        );

        var response = guerrillaMailClient.emails( emails( addressResponse.getSessionId(), 0L, 0) );

        var email = guerrillaMailClient.email(
                new EmailRequest(
                        addressResponse.getSessionId(),
                        response.getEmails().get(0).getId()
                )
        );

        var pattern = Pattern.compile(String.format("(?<=%s).*?(?=</)", XmlParser.getTextByKey("AuthCode")));
        var matcher = pattern.matcher(email.getBody());
        if (matcher.find()) {
            return matcher.group(0);
        }
        return null;
    }

    public String getForgotPasswordUrl() throws TimeoutException {
        WaitHelper.pollingWait(5 * 60000, 5000, () ->
                guerrillaMailClient
                        .emails( emails( addressResponse.getSessionId(), 0L, 0) )
                        .getEmails()
                        .size() > 2
        );

        var response = guerrillaMailClient.emails( emails( addressResponse.getSessionId(), 0L, 0) );

        var email = guerrillaMailClient.email(
                new EmailRequest(
                        addressResponse.getSessionId(),
                        response.getEmails().get(0).getId()
                )
        );

        var pattern = Pattern.compile(String.format("(?<=<a href=\").*?(?=\">%s</a>)", XmlParser.getTextByKey("ForgotPassword")));
        var matcher = pattern.matcher(email.getBody());
        if (matcher.find()) {
            return matcher.group(0);
        }
        return null;
    }

    public void forgetEmail() {
        guerrillaMailClient.address( forget(addressResponse.getSessionId()) );
    }
}
