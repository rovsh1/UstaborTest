package utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;

public class NewXmlParser {

    public static HashMap<String, String> getDataProperties(String html) {
        var data = new HashMap<String, String>();

        Document document = Jsoup.parse(html);

        Elements inputs = document.select("input[name^=data]");
        Elements selects = document.select("select[name^=data]");
        Elements textArea = document.select("textarea[name^=data]");

        for (Element prop: inputs) {
            data.put(prop.attr("name"), prop.attr("value"));
        }

        for (Element prop: selects) {
            var value = prop.select("option[selected]").attr("value");

            data.put(prop.attr("name"), value);
        }

        for (Element prop: textArea) {
            data.put(prop.attr("name"), prop.text());
        }

        return data;
    }
}
