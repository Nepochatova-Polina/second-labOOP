package org.example;

import org.apache.commons.io.FileUtils;
import org.example.parsers.*;
import org.example.utility.XMLCreator;
import org.example.utility.XMLValidator;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainTest {
    private static final Logger log = Logger.getLogger(MainTest.class.getName());

    @Test
    public void testMain(){
        XMLCreator xmlCreator = new XMLCreator();
        DOM dom = new DOM(xmlCreator);
        SAX sax = new SAX(xmlCreator);
        StAX stAX = new StAX(xmlCreator);

        String XML = "src/main/resources/input.xml";
        String XSD = "src/main/resources/input.xsd";
        if(XMLValidator.validateXML(XML, XSD)){
            log.info("XML is valid");
        }
        else log.info("XML is not valid");
        sax.parse(XML);
        sax.createXML();
        stAX.parse(XML);
        stAX.createXML();
        dom.parse(XML);
        dom.createXML();
        try {
            Assert.assertTrue(FileUtils.contentEquals(new File("src/main/resources/dom.xml"),
                    new File("src/main/resources/sax.xml")));
            Assert.assertTrue(FileUtils.contentEquals(new File("src/main/resources/sax.xml"),
                    new File("src/main/resources/stax.xml")));
        }catch (IOException e){
            log.log(Level.SEVERE, "Exception: ", e);
        }
    }
}