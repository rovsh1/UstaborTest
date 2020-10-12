package utils;

import org.seimicrawler.xpath.JXDocument;
import org.seimicrawler.xpath.JXNode;
import java.util.regex.Pattern;

public class NewXmlParser {

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

    public static String getSmsPassword(String html, String phoneNumber, String text) {
        JXDocument document = JXDocument.create(html);
        String xpath = String.format("//tr[./td[contains(text(), '%s')]]//td[contains(text(), '%s')]",
                phoneNumber,
                text);

        JXNode node = document.selNOne(xpath);

        return node.toString().substring(node.toString().lastIndexOf(':') + 2, node.toString().lastIndexOf('<'));
    }

    public static String getSmsByText(String html, String phoneNumber, String text) {
        JXDocument document = JXDocument.create(html);
        String xpath = String.format("//tr[./td[contains(text(), '%s')]]//td[contains(text(), '%s')]",
                phoneNumber,
                text);

        JXNode node = document.selNOne(xpath);

        return node.toString();
    }
}
