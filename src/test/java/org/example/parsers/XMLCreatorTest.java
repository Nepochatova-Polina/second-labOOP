package org.example.parsers;

import org.apache.commons.io.FileUtils;
import org.example.classes.Candy;
import org.example.classes.Type;
import org.example.classes.Ingredients;
import org.example.utility.XMLCreator;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class XMLCreatorTest {
    private static final Logger log = Logger.getLogger(XMLCreatorTest.class.getName());

    @Test
    public void createXML(){
        XMLCreator xmlCreator = new XMLCreator();
        List<Candy> candies = new ArrayList<>();
        Candy candy1 = new Candy();
        Candy candy2 = new Candy();
        candy1.setIngredients(new Ingredients());
        candy2.setIngredients(new Ingredients());

        candy1.setName("Cow");
        candy1.setId(13726);
        candy1.setCompanyName("Roshen");
        candy1.setType(Type.Iris);
        candy1.getIngredients().setMilk(12.2);
        candy1.getIngredients().setSugar(202);
        candy1.getIngredients().setCacao(100);
        candy1.getIngredients().setVanilla(1.5);
        candy1.getIngredients().setFructose(10.5);

        candy2.setName("Pink Pantera");
        candy2.setId(23973283);
        candy2.setCompanyName("AVK");
        candy2.setType(Type.Chocolate);
        candy2.getIngredients().setMilk(15.7);
        candy2.getIngredients().setSugar(204);
        candy2.getIngredients().setCacao(88);
        candy2.getIngredients().setVanilla(5.0);
        candy2.getIngredients().setFructose(15);

        candies.add(candy1);
        candies.add(candy2);
        xmlCreator.buildXML(candies,"src/test/resources/XMLCreatorTest.xml");
        try {
            Assert.assertTrue(FileUtils.contentEquals(new File("src/test/resources/XMLCreatorTest.xml"),
                    new File("src/test/resources/expected.xml")));
        }catch (IOException e){
            log.log(Level.SEVERE, "Exception: ", e);
        }
    }
}