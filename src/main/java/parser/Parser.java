package parser;

import datastorage.Slideshow;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;

public class Parser {

    public static Slideshow parse(File xmlFile) throws IOException, SAXException, ParserConfigurationException
    {
        System.out.println(xmlFile.getParent());
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        SlideshowHandler handler = new SlideshowHandler(xmlFile.getParent() + "/");

        FileInputStream inputStream = new FileInputStream(xmlFile);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

        saxParser.parse(bufferedInputStream, handler);

        return handler.getSlideshow();
    }

}
