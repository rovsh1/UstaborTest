package utils;

import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;

import java.io.IOException;
import java.util.Map;

public class AdminApi {

    private static AdminApi instance;

    private Executor executor;
    private String baseUrl;

    private AdminApi() {
        executor = Executor.newInstance(
                HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy())
                        .build());
        baseUrl = Config.getAdminUrl();
    }

    public static AdminApi getInstance() {
        if (instance == null) {
            instance = new AdminApi();
        }
        return instance;
    }

    public void login() {
        var url = baseUrl + "account/auth/";

        try {
            var result = executor.execute(Request.Post(url)
                .bodyForm(Form.form()
                        .add("auth[login]", Config.getUsers().getAdmin().getLogin())
                        .add("auth[password]", Config.getUsers().getAdmin().getPassword())
                        .build()))
                    .returnResponse()
                    .getStatusLine()
                    .getStatusCode();

            if (result != 200) {
                throw new HttpResponseException(result, "Login failed");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteMaster(String id) {
        var url = baseUrl + "master/delete/" + id;

        try {
            var result = executor.execute(Request.Get(url))
                    .returnResponse()
                    .getStatusLine()
                    .getStatusCode();

            if (result != 200) {
                throw new HttpResponseException(result, "Delete master request failed");
            }

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

    public void enablePromotion(String countryId, String categoryId, int minPrice, int maxPrice) {
        var url = baseUrl +  String.format("category/countryform/%s/?id=%s", categoryId, countryId);

        try {
            var result = executor.execute(Request.Post(url)
                    .bodyForm(Form.form()
                            .add("data[enabled]", "1")
                            .add("data[price_min]", String.valueOf(minPrice))
                            .add("data[price_max]", String.valueOf(maxPrice))
                            .build()))
                    .returnResponse();

            if (result.getStatusLine()
                    .getStatusCode() != 200) {
                throw new HttpResponseException(99, "Category enable request failed");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
