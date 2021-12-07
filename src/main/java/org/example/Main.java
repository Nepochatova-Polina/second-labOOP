package org.example;

import org.example.parsers.*;
import org.example.utility.XMLCreator;
import org.example.utility.XMLValidator;
import java.util.logging.Logger;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        XMLCreator xmlCreator = new XMLCreator();
        DOM dom = new DOM(xmlCreator);
        SAX sax = new SAX(xmlCreator);
        StAX stAX = new StAX(xmlCreator);

        String XML = "src/main/resources/input.xml";
        String XSD = "src/main/resources/input.xsd";
        if(XMLValidator.validateXML(XML, XSD)){
            log.info("XML is valid");
        }
        else {
            log.info("XML is not valid");
        }
        sax.parse(XML);
        sax.createXML();
        stAX.parse(XML);
        stAX.createXML();
        dom.parse(XML);
        dom.createXML();
    }
}
