package utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.seimicrawler.xpath.JXDocument;
import org.seimicrawler.xpath.JXNode;

import java.util.HashMap;
import java.util.regex.Pattern;

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

    public static String getSmsCode(String html, String phoneNumber) {
        JXDocument document = JXDocument.create(html);
        String xpath = String.format("//tr[./td[contains(text(), '%s')]]//td[4]", phoneNumber);

        JXNode node = document.selNOne(xpath);

        var result = Pattern.compile("\\d{2,}").matcher(node.toString());
        if (result.find()) {
            return result.group(0);
        }
        throw new NullPointerException("Getting email auth code failed");
    }
}
