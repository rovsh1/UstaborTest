package utils.mailApi.client;

import utils.WaitHelper;
import utils.XmlParser;
import utils.mailApi.client.factory.GuerrillaMailClientFactory;
import utils.mailApi.client.model.request.AddressRequest;
import utils.mailApi.client.model.request.EmailRequest;
import utils.mailApi.client.model.response.AddressResponse;
import utils.mailApi.client.model.response.EmailsResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

import static utils.mailApi.client.model.request.EmailsRequest.check;
import static utils.mailApi.client.model.request.EmailsRequest.emails;

public class EmailSingleton {

    private final GuerrillaMailClient guerrillaMailClient;
    private final AddressResponse addressResponse;

    public static final EmailSingleton INSTANCE = new EmailSingleton();

    private EmailSingleton() {
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

    public String getForgotPasswordUrl() {
        return null;
    }
}
