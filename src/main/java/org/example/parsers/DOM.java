package org.example.parsers;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.example.classes.Candy;
import org.example.classes.Ingredients;
import org.example.utility.XMLCreator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DOM extends ParserXML {
    private static final Logger log = Logger.getLogger(DOM.class.getName());

    public DOM() {}

    public DOM(XMLCreator xmlCreator) {
        this.xmlCreator = xmlCreator;
    }

    @Override
    public void parse(String xmlPath) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document doc = null;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(xmlPath);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            log.log(Level.SEVERE, "Exception: ", e);
        }

        Element root = doc.getDocumentElement();
        NodeList drugNodes = root.getElementsByTagName(candyHandler.getName());
        for (int i = 0; i < drugNodes.getLength(); i++) {
            Element candyElem = (Element) drugNodes.item(i);
            candyHandler.getCandyBox().getcandyList().add(new Candy());
            candyHandler.lastestCandy().setIngredients(new Ingredients());
            NodeList childNodes = candyElem.getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                    Element child = (Element) childNodes.item(j);
                    candyHandler.setElementValue(getChildValue(candyElem, child.getNodeName()));
                    candyHandler.setField(child.getNodeName());
                    NodeList childChildNodes = child.getChildNodes();
                    for (int k = 0; k < childChildNodes.getLength(); k++) {
                        if (childChildNodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
                            Element childChild = (Element) childChildNodes.item(k);
                            candyHandler.setElementValue(getChildValue(child, childChild.getNodeName()));
                            candyHandler.setField(childChild.getNodeName());
                        }
                    }
                }
            }
        }
    }

    @Override
    public void createXML() {
        xmlCreator.buildXML(candyHandler.getCandyBox().getcandyList(),"src/main/resources/dom.xml");
    }

    protected static String getChildValue(Element element, String name) {
        Element child = (Element) element.getElementsByTagName(name).item(0);
        if (child == null) {
            return "";
        }
        Node node = child.getFirstChild();
        return node.getNodeValue();
    }
}
