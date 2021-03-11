package parser;

import DataStorage.ImageDataStorage;
import DataStorage.SlideDataStorage;
import DataStorage.TextDataStorage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ParserTest {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser saxParser = factory.newSAXParser();
    SlideshowHandler slideshowHandler = new SlideshowHandler();

    public ParserTest() throws ParserConfigurationException, SAXException {
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        ParserTest myTest = new ParserTest();
        myTest.saxParser.parse("src/main/resources/testXML.xml", myTest.slideshowHandler);

        ArrayList<SlideDataStorage> slideshow = myTest.slideshowHandler.getSlideshow();

        for (SlideDataStorage slide : slideshow) {
            for(Iterator<TextDataStorage> iter = slide.textIterator(); iter.hasNext();){
                TextDataStorage text = iter.next();
                System.out.println("Text: ");
                String xText = "x: " + text.getXPos();
                String yText = "y: " + text.getYPos();
                String fontText = "font: " + text.getFont();
                String fontSizeText = "font size: " + text.getFontSize();
                String fontColourText = "font colour: " + text.getColour().toString();
                String textText = "text: " + text.getText();
                System.out.println(xText);
                System.out.println(yText);
                System.out.println(fontText);
                System.out.println(fontSizeText);
                System.out.println(fontColourText);
                System.out.println(textText);
            }
            for(Iterator<ImageDataStorage> iter = slide.imageIterator(); iter.hasNext();) {
                ImageDataStorage image = iter.next();
                System.out.println("Image:");
                String xText = "x: " + image.getXPos();
                String yText = "y: " + image.getYPos();
                String widthText = "width: " + image.getWidth();
                String heightText = "height: " + image.getHeight();
                String urlText = "url: " + image.getFileLocation().toString();
                String durationText = "duration: " + image.getDuration();
                System.out.println(xText);
                System.out.println(yText);
                System.out.println(widthText);
                System.out.println(heightText);
                System.out.println(urlText);
                System.out.println(durationText);
            }
        }
    }
}
