package org.example.parsers;


import org.example.utility.XMLCreator;

public abstract class ParserXML {
    protected XMLCreator xmlCreator;

    CandyHandler candyHandler = new CandyHandler();

    public abstract void parse(String XMLFile);

    public abstract void createXML();
}
