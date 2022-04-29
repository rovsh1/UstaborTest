package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class XmlParser {

    private static final Logger logger = LoggerFactory.getLogger(XmlParser.class);

    public static String getTextByKey(String attribute) {
        try {
            String xml = "files/strings.xml";

            var inputStream = new FileInputStream(new File(xml));
            var reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            var is = new InputSource(reader);
            is.setEncoding("UTF-8");

            var dbFactory = DocumentBuilderFactory.newInstance();
            var builder = dbFactory.newDocumentBuilder();
            var document = builder.parse(is);
            var nl = document.getElementsByTagName("string");

            for (int i = 0; i < nl.getLength(); i++) {
                var node = nl.item(i);
                var nm = node.getAttributes();
                if (nm != null) {
                    var name = nm.getNamedItem("name").getNodeValue();

                    if (name.equals(attribute)) {
                        return node.getTextContent();
                    }
                }
            }

        } catch (Exception e) {
            logger.info(e.getLocalizedMessage());
        }

        return null;
    }
}
