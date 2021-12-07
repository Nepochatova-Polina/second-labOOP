package org.example.utility;

import org.example.classes.Candy;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XMLCreator {
    private static final Logger log = Logger.getLogger(XMLCreator.class.getName());

    public void buildXML(List<Candy> candyList, String xmlFilePath) {
    try {
        File file = new File(xmlFilePath);
        if (file.createNewFile()) {
            log.info("File created: " + file.getName());
        } else {
            log.info(String.format("File %s already exists.", xmlFilePath));
        }
    } catch (IOException e) {
        log.log(Level.SEVERE, "Exception: ", e);
    }

    Collections.sort(candyList);

    try {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        Element root = document.createElement("CandyBox");
        document.appendChild(root);
        for (Candy candy : candyList) {
            Element drugElement = document.createElement("Candy");
            root.appendChild(drugElement);
            Element id = document.createElement("Id");
            id.appendChild(document.createTextNode(String.valueOf(candy.getId())));
            drugElement.appendChild(id);
            Element name = document.createElement("Name");
            name.appendChild(document.createTextNode(candy.getName()));
            drugElement.appendChild(name);
            Element pharm = document.createElement("Company");
            pharm.appendChild(document.createTextNode(candy.getCompanyName()));
            drugElement.appendChild(pharm);
            Element group = document.createElement("Type");
            group.appendChild(document.createTextNode(String.valueOf(candy.getType())));
            drugElement.appendChild(group);
            Element Ingredients = document.createElement("Ingredients");
            drugElement.appendChild(Ingredients);
            Element type = document.createElement("Milk");
            type.appendChild(document.createTextNode(String.valueOf(candy.getIngredients().getMilk())));
            Ingredients.appendChild(type);
            Element sugar = document.createElement("Sugar");
            sugar.appendChild(document.createTextNode(String.valueOf(candy.getIngredients().getSugar())));
            Ingredients.appendChild(sugar);
            Element cacao = document.createElement("Cacao");
            cacao.appendChild(document.createTextNode(String.valueOf(candy.getIngredients().getCacao())));
            Ingredients.appendChild(cacao);
            Element vanilla = document.createElement("Vanilla");
            vanilla.appendChild(document.createTextNode(String.valueOf(candy.getIngredients().getVanilla())));
            Ingredients.appendChild(vanilla);
            Element fructose = document.createElement("Fructose");
            fructose.appendChild(document.createTextNode(String.valueOf(candy.getIngredients().getFructose())));
            Ingredients.appendChild(fructose);
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(xmlFilePath));
        transformer.transform(domSource, streamResult);
        } catch (ParserConfigurationException | TransformerException e) {
            log.log(Level.SEVERE, "Exception: ", e);
        }
    }
}
