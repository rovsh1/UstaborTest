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
                        .add("auth[login]", Config.getUsers().getAdmin().getEmail())
                        .add("auth[password]", Config.getUsers().getAdmin().getPassword())
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
        var url = baseUrl + String.format("master/delete/%s/", id);

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

    public void addMoneyToMaster(String id, String amount) {
        var url = baseUrl +  "master/view/" + id + "/";

        try {
            var result = executor.execute(Request.Post(url)
                    .bodyForm(Form.form()
                            .add("balance[service]", "")
                            .add("balance[sum]", amount)
                            .build()))
                    .returnResponse();

            if (result.getStatusLine()
                    .getStatusCode() != 200) {
                throw new HttpResponseException(99, "Add money to master request failed");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addBadgesToMaster(String id) {
        var url = baseUrl + baseUrl +  "master/edit/" + id + "/";
        var data = NewXmlParser.getDataProperties(getEditMasterPage(id));

        var body = Form.form();

        for (Map.Entry<String, String> kvPair : data.entrySet()) {
            body.add(kvPair.getKey(), kvPair.getValue());
        }

        for (int i = 1; i < 7; i++) {
            body.add("data[badges][]", String.valueOf(i));
        }

        try {
            var result = executor.execute(Request.Post(url)
                    .bodyForm(body.build()))
                    .returnResponse();

            if (result.getStatusLine()
                    .getStatusCode() != 200) {
                throw new HttpResponseException(99, "Add bagdes to master request failed");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getEditMasterPage(String id) {
        var url = baseUrl +  "master/edit/" + id + "/";

        try {
            return executor.execute(Request.Get(url))
                    .returnContent().asString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
}
