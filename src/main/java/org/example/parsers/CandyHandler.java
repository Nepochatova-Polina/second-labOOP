package org.example.parsers;

import org.example.classes.Candy;
import org.example.classes.Type;
import org.example.classes.CandyBox;
import org.example.classes.Ingredients;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.util.List;

public class CandyHandler extends DefaultHandler {
    private static final String CANDYBOX = "Candybox";
    private static final String CANDY = "Candy";
    private static final String ID = "Id";
    private static final String NAME = "Name";
    private static final String COMPANY = "Company";
    private static final String INGREDIENTS = "Ingredients";
    private static final String TYPE = "Type";
    private static final String MILK = "Milk";
    private static final String SUGAR = "Sugar";
    private static final String CACAO = "Cacao";
    private static final String VANILLA = "Vanilla";
    private static final String FRUCTOSE = "Fructose";

    private CandyBox candyList = new CandyBox();

    private String elementValue;

    private final String nodeName = "Candy";

    public void setElementValue(String elementValue) {
        this.elementValue = elementValue;
    }

    public String getName() {
        return nodeName;
    }

    @Override
    public void characters(char[] ch, int start, int length){
        elementValue = new String(ch, start, length);
    }

    @Override
    public void startDocument() {
        candyList = new CandyBox();
    }

    @Override
    public void startElement(String uri, String lName, String qName, Attributes attr){
        switch (qName) {
            case CANDY:
                Candy candy = new Candy();
                candyList.getcandyList().add(candy);
                break;
            case INGREDIENTS:
                lastestCandy().setIngredients(new Ingredients());
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName){
        setField(qName);
    }

    public Candy lastestCandy() {
        List<Candy> gunList = candyList.getcandyList();
        int latestArticleIndex = gunList.size() - 1;
        return gunList.get(latestArticleIndex);
    }

    public CandyBox getCandyBox() {
        return candyList;
    }

    public void setField(String qName) {
        switch (qName) {
            case ID:
                lastestCandy().setId(Integer.parseInt(elementValue));
                break;
            case NAME:
                lastestCandy().setName(elementValue);
                break;
            case COMPANY:
                lastestCandy().setCompanyName(elementValue);
                break;
            case TYPE:
                lastestCandy().setType(Type.valueOf(elementValue));
                break;
            case MILK:
                lastestCandy().getIngredients().setMilk(Double.parseDouble(elementValue));
                break;
            case SUGAR:
                lastestCandy().getIngredients().setSugar(Double.parseDouble(elementValue));
                break;
            case CACAO:
                lastestCandy().getIngredients().setCacao(Double.parseDouble(elementValue));
                break;
            case VANILLA:
                lastestCandy().getIngredients().setVanilla(Double.parseDouble(elementValue));
                break;
            case FRUCTOSE:
                lastestCandy().getIngredients().setFructose(Double.parseDouble(elementValue));
                break;
            default:
                break;
        }
    }

    public void setField(String qName, String attribute){
        switch (qName) {
            case CANDY:
                Candy candy = new Candy();
                candyList.getcandyList().add(candy);
                break;
            case INGREDIENTS:
                lastestCandy().setIngredients(new Ingredients());
                break;
            default:
                setField(qName);
                break;
        }
    }
}
