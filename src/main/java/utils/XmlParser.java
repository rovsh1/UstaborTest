package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {

    private static final Logger logger = LoggerFactory.getLogger(XmlParser.class);

    public static String getTextByKey(String attribute) {
//        System.setProperty("file.encoding","UTF-8");
        try {
//            Field charset = Charset.class.getDeclaredField("defaultCharset");
//            charset.setAccessible(true);
//            charset.set(null,null);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder;
            String xml = "files/strings.xml";

            builder = factory.newDocumentBuilder();

            Document document = builder.parse(new File(xml));
            NodeList nl = document.getElementsByTagName("string");

            for (int i = 0; i < nl.getLength(); i++) {
                Node node = nl.item(i);
                NamedNodeMap nm = node.getAttributes();
                if (nm != null) {
                    String name = nm.getNamedItem("name").getNodeValue();

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
