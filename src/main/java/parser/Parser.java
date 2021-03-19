package parser;

import datastorage.Slideshow;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.net.URI;

public class Parser {

    public static Slideshow parse(URI fileLocation) throws IOException, SAXException, ParserConfigurationException
    {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        SlideshowHandler handler = new SlideshowHandler();

        File xmlFile = new File(fileLocation);
        FileInputStream inputStream = new FileInputStream(xmlFile);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

        saxParser.parse(bufferedInputStream, handler);

        return handler.getSlideshow();
    }

}
