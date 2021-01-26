package utils;

import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class Admin {

    private static final Logger logger = LoggerFactory.getLogger(Admin.class);

    private Executor executor;
    private final String baseUrl;

    public Admin() {
        try {
            executor = Executor.newInstance(
                    HttpClientBuilder
                            .create()
                            .setRedirectStrategy(new LaxRedirectStrategy())
                            .setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build())
                            .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                            .build());
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            e.printStackTrace();
        }
        baseUrl = Config.getAdminUrl();
        login();
    }

    public void login() {
        var url = baseUrl + "account/auth/";

        try {
            var result = executor.execute(Request.Post(url)
                .bodyForm(Form.form()
                        .add("data[login]", Config.getUsers().getAdmin().getEmail())
                        .add("data[password]", Config.getUsers().getAdmin().getPassword())
                        .build()))
                    .returnResponse()
                    .getStatusLine()
                    .getStatusCode();

            if (result != 200) {
                logger.info("Admin login failed");
                throw new HttpResponseException(result, "Login failed");
            }
            logger.info("Admin login successfully");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteMaster(String id) {
        // http://ka8rms.vtaminka.com/master/user/delete/65820/
        var url = baseUrl + String.format("master/user/delete/%s/", id);

        try {
            var result = executor.execute(Request.Get(url))
                    .returnResponse()
                    .getStatusLine()
                    .getStatusCode();

            if (result != 200) {
                logger.info("Delete master request failed");
                throw new HttpResponseException(result, "Delete master request failed");
            }
            logger.info("Master profile deleted. Id: {}", id);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteCategory(String id) {
        var url = baseUrl + String.format("category/delete/%s/", id);

        try {
            var result = executor.execute(Request.Get(url))
                    .returnResponse()
                    .getStatusLine()
                    .getStatusCode();

            if (result != 200) {
                logger.info("Delete category request failed");
                throw new HttpResponseException(result, "Delete category request failed");
            }
            logger.info("Category deleted. Id: {}", id);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getSmsCode(String phoneNumber) {
        var url = baseUrl + "log/sms/";
        String code = "N/A";

        try {
            var html = executor.execute(Request.Get(url)).returnContent().asString();
            code = NewXmlParser.getSmsCode(html, phoneNumber);
            logger.info("Get SMS code {} for phone number: {}", code, phoneNumber);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }

    public String getSmsPassword(String phoneNumber) {
        var url = baseUrl + "log/sms/";
        String password = null;

        try {
            var html = executor.execute(Request.Get(url)).returnContent().asString();
            password = NewXmlParser.getSmsPassword(html, phoneNumber, XmlParser.getTextByKey("SmsRegistration"));
            logger.info("Get SMS password {} for phone number: {}", password, phoneNumber);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return password;
    }

    public String getSmsByText(String phoneNumber, String sms) {
        var url = baseUrl + "log/sms/";
        String smsText = null;

        try {
            var html = executor.execute(Request.Get(url)).returnContent().asString();
            smsText = NewXmlParser.getSmsByText(html, phoneNumber, sms);
            logger.info("Get SMS by piece of text for phone number: {}", phoneNumber);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return smsText;
    }

    public void deleteCustomer(String customerId) {
        var url = baseUrl + String.format("customer/delete/%s/", customerId);

        try {
            var result = executor.execute(Request.Get(url))
                    .returnResponse()
                    .getStatusLine()
                    .getStatusCode();

            if (result != 200) {
                logger.info("Delete customer request failed");
                throw new HttpResponseException(result, "Delete customer request failed");
            }
            logger.info("Customer deleted. Id: {}", customerId);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
