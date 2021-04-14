package parser;

import datastorage.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXParseException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.awt.*;
import java.io.*;
import java.net.URL;

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
    void testInvalidFile() throws Exception{

        BufferedInputStream bufferedInputStream = getBufferedInputStreamFromFilepath("src/test/resources/not_an_xml_file.txt");

        Assertions.assertThrows(SAXParseException.class, () -> {
            parser.parse(bufferedInputStream, handler);
        });
    }

    @Test
    void testInvalidXMLFile() throws Exception {

        BufferedInputStream bufferedInputStream = getBufferedInputStreamFromFilepath( "src/test/resources/invalid_xml_file.xml");

        Assertions.assertThrows(SAXParseException.class, () -> {
            parser.parse(bufferedInputStream, handler);
        });
    }

    @Test
    void testMalformedURLs() throws Exception {

        BufferedInputStream bufferedInputStream = getBufferedInputStreamFromFilepath("src/test/resources/malformed_url_test.xml");

        Slideshow parsedSlideshow;
        Slideshow expectedSlideshow = new Slideshow();

        Color expectedBackgroundColour = Color.decode("#faf347");
        String expectedFont = "Calibri";
        int expectedFontSize = 16;
        Color expectedTextColour = Color.decode("#855797");
        Color expectedLineColour = Color.decode("#fa44dd");
        Color expectedShapeColour = Color.decode("#88cd02");

        expectedSlideshow.setDefaults(expectedBackgroundColour, expectedFont, expectedFontSize, expectedTextColour, expectedLineColour, expectedShapeColour);

        SlideDataStorage goodAudioSlide = new SlideDataStorage("goodAudio", 0);
        SlideDataStorage goodVideoSlide = new SlideDataStorage("goodVideo", 0);
        SlideDataStorage goodImageSlide = new SlideDataStorage("goodImage", 0);
        SlideDataStorage badAudioSlide = new SlideDataStorage("badAudio", 0);
        SlideDataStorage badVideoSlide = new SlideDataStorage("badVideo", 0);
        SlideDataStorage badImageSlide = new SlideDataStorage("badImage", 0);

        URL audioURL =  new URL("file://bird.wav");
        goodAudioSlide.addAudio(new AudioDataStorage(audioURL, 0, false));

        URL videoURL = new URL("file://bird.mov");
        goodVideoSlide.addVideo(new VideoDataStorage(100, 100, videoURL, 0, true));

        URL imageURL = new URL("file://bird.png");
        goodImageSlide.addImage(new ImageDataStorage(100, 100, imageURL, 10, 10, 0));

        expectedSlideshow.addSlide(goodAudioSlide);
        expectedSlideshow.addSlide(badAudioSlide);
        expectedSlideshow.addSlide(goodVideoSlide);
        expectedSlideshow.addSlide(badVideoSlide);
        expectedSlideshow.addSlide(goodImageSlide);
        expectedSlideshow.addSlide(badImageSlide);

        parser.parse(bufferedInputStream, handler);
        parsedSlideshow = handler.getSlideshow();

        assertEquals(parsedSlideshow, expectedSlideshow);
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