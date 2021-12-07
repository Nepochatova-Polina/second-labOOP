package org.example.parsers;

import org.example.utility.XMLCreator;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StAX extends ParserXML {
    private static final Logger log = Logger.getLogger(StAX.class.getName());

    public StAX(XMLCreator xmlCreator) {
        this.xmlCreator = xmlCreator;
    }

    @Override
    public void parse(String XMLFile) {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader;
        try {
            reader = xmlInputFactory.createXMLEventReader(new FileInputStream(XMLFile));
            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    String attributeString = null;
                    nextEvent = reader.nextEvent();
                    String string = startElement.getName().getLocalPart();
                    if (nextEvent.isCharacters()) {
                        candyHandler.setElementValue(nextEvent.asCharacters().getData());
                        candyHandler.setField(string, attributeString);
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            log.log(Level.SEVERE, "Exception: ", e);
        }
    }

    @Override
    public void createXML() {
        xmlCreator.buildXML(candyHandler.getCandyBox().getcandyList(), "src/main/resources/stax.xml");
    }
}
