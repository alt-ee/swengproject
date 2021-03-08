package parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SlideshowHandler extends DefaultHandler {

    //TODO attriubtes for media elements and slides once classes are written

    private String elementValue;

    @Override
    public void characters(char [] chars, int start, int length) throws SAXException {
        elementValue = new String(chars, start, length);
    }

    @Override
    public void startDocument() throws SAXException {
        //TODO create new slideshow
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //TODO handle start elements
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //TODO handle end elements
    }

    //TODO getter for finished slideshow
}
