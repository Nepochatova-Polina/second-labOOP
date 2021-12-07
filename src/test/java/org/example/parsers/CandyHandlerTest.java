package org.example.parsers;

import org.example.classes.Candy;
import org.example.classes.Type;
import org.example.classes.Ingredients;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class CandyHandlerTest {
    private final List<Candy> candies = new ArrayList<>();

    @Before
    public void setUp(){
        Candy candy1 = new Candy();
        candy1.setIngredients(new Ingredients());
        candy1.setId(13726);
        candy1.setName("Cow");
        candy1.setCompanyName("Roshen");
        candy1.setType(Type.Iris);
        candy1.getIngredients().setMilk(12.2);
        candy1.getIngredients().setSugar(202);
        candy1.getIngredients().setCacao(100);
        candy1.getIngredients().setVanilla(1.5);
        candy1.getIngredients().setFructose(10.5);
        candies.add(candy1);
    }

    @Test
    public void testHandlerWithDOM(){
        DOM dom = new DOM();
        dom.parse("src/test/resources/XMLCreatorTest.xml");
        Assert.assertEquals(dom.candyHandler.getCandyBox().getcandyList().get(0).getId(), candies.get(0).getId());
        Assert.assertEquals(dom.candyHandler.getCandyBox().getcandyList().get(0).getName(), candies.get(0).getName());
        Assert.assertEquals(dom.candyHandler.getCandyBox().getcandyList().get(0).getCompanyName(), candies.get(0).getCompanyName());
        Assert.assertEquals(dom.candyHandler.getCandyBox().getcandyList().get(0).getType(), candies.get(0).getType());
        Assert.assertEquals(dom.candyHandler.getCandyBox().getcandyList().get(0).getIngredients(), candies.get(0).getIngredients());
    }
}