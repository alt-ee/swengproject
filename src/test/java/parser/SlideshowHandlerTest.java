package parser;

import datastorage.Slideshow;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class SlideshowHandlerTest {

    @Test
    void testSlides() {

        String filePath = "src/test/resources/defaults_test.xml";
        File file = new File(filePath).getAbsoluteFile();
        URI testFileLocation = file.toURI();

        Slideshow parsedSlideShow;
        Slideshow expectedSlideshow = new Slideshow();

        Color expectedBackgroundColour = Color.decode("#faf347");
        String expectedFont = "Calibri";
        int expectedFontSize = 16;
        Color expectedTextColour = Color.decode("#855797");
        Color expectedLineColour = Color.decode("#fa44dd");
        Color expectedShapeColour = Color.decode("#88cd02");

        expectedSlideshow.setDefaults(expectedBackgroundColour, expectedFont, expectedFontSize, expectedTextColour, expectedLineColour, expectedShapeColour);

        try {
            parsedSlideShow = Parser.parse(testFileLocation);
            assertTrue(expectedSlideshow.equals(parsedSlideShow));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}