package utils;

import org.seimicrawler.xpath.JXDocument;
import java.util.regex.Pattern;

public class NewXmlParser {

    private final JXDocument document;

    public NewXmlParser(String html) {
        document = JXDocument.create(html);
    }

    public String getSmsCode(String phoneNumber) {
        var sms = getSmsText(phoneNumber, XmlParser.getTextByKey("SmsAuthCode"));

        var result = Pattern.compile("\\d{2,}").matcher(sms);
        if (result.find()) {
            return result.group(0);
        } else {
            return "";
        }
    }

    public String getSmsPassword(String phoneNumber, String text) {
        var sms = getSmsText(phoneNumber, text);
        return sms.substring(sms.lastIndexOf(':') + 2, sms.lastIndexOf('<'));
    }

    public String getSmsText(String phoneNumber, String text) {
        String textXpath = "//tr[./td[contains(text(), '%s')]]//td[contains(text(), '%s')]";
        String xpath = String.format(textXpath, phoneNumber, text);
        var node = document.selNOne(xpath);
        return node.toString();
    }
}
