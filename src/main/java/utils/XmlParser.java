package utils;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XmlParser {

    public static String getTextByKey(String attribute) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        String xml = null;

        try {
            builder = factory.newDocumentBuilder();

            xml = "files/strings.xml";

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

        } catch (SAXException | IOException | ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}
