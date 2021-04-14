package parser;

import datastorage.Slideshow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class SlideshowHandlerTest {

    private static SAXParser parser;
    private static SlideshowHandler handler;

    BufferedInputStream getBufferedInputStreamFromFilepath(String filePath) throws FileNotFoundException{
        File file = new File(filePath).getAbsoluteFile();

        FileInputStream inputStream = new FileInputStream(file);

        return new BufferedInputStream(inputStream);
    }

    @BeforeAll
    static void init() throws Exception{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        parser = factory.newSAXParser();
        handler = new SlideshowHandler();
    }

    @Test
    void testInvalidFile() throws  Exception{

        BufferedInputStream bufferedInputStream = getBufferedInputStreamFromFilepath("src/test/resources/not_an_xml_file.txt");

        Assertions.assertThrows(SAXParseException.class, () -> {
            parser.parse(bufferedInputStream, handler);
        });
    }

    @Test
    void testDefaults() throws Exception{


        BufferedInputStream bufferedInputStream = getBufferedInputStreamFromFilepath("src/test/resources/defaults_test.xml");

        Slideshow parsedSlideShow;
        Slideshow expectedSlideshow = new Slideshow();

        Color expectedBackgroundColour = Color.decode("#faf347");
        String expectedFont = "Calibri";
        int expectedFontSize = 16;
        Color expectedTextColour = Color.decode("#855797");
        Color expectedLineColour = Color.decode("#fa44dd");
        Color expectedShapeColour = Color.decode("#88cd02");

        expectedSlideshow.setDefaults(expectedBackgroundColour, expectedFont, expectedFontSize, expectedTextColour, expectedLineColour, expectedShapeColour);

        parser.parse(bufferedInputStream, handler);

        parsedSlideShow = handler.getSlideshow();
        assertEquals(parsedSlideShow, expectedSlideshow);
    }
}