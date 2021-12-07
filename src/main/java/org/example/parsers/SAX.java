package org.example.parsers;

import org.example.utility.XMLCreator;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SAX extends ParserXML {
    private static final Logger log = Logger.getLogger(SAX.class.getName());

    public SAX(XMLCreator xmlCreator) {
        this.xmlCreator = xmlCreator;
    }

    @Override
    public void parse(String xmlPath){
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(xmlPath, candyHandler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            log.log(Level.SEVERE, "Exception: ", e);
        }
    }

    @Override
    public void createXML() {
        xmlCreator.buildXML(candyHandler.getCandyBox().getcandyList(),"src/main/resources/sax.xml");
    }
}
