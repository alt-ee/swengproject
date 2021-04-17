package parser;

import datastorage.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXParseException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;

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
    void testDefaults() throws Exception{

        BufferedInputStream bufferedInputStream = getBufferedInputStreamFromFilepath("src/test/resources/defaults_test.xml");

        Slideshow parsedSlideShow;

        parser.parse(bufferedInputStream, handler);

        parsedSlideShow = handler.getSlideshow();

        SlideDataStorage parsedSlide = parsedSlideShow.getSlides().get(0);
        TextDataStorage parsedText = parsedSlide.textIterator().next();
        LineDataStorage parsedLine = parsedSlide.lineIterator().next();
        ShapeDataStorage parsedShape = parsedSlide.shapeIterator().next();

        assertEquals(parsedSlideShow.getDefaultTextColour(), parsedText.getColour());
        assertEquals(parsedSlideShow.getDefaultFont(), parsedText.getFont());
        assertEquals(parsedSlideShow.getDefaultFontSize(), parsedText.getFontSize());
        assertEquals(parsedSlideShow.getDefaultLineColour(), parsedLine.getColour());
        assertEquals(parsedSlideShow.getDefaultShapeColour(), parsedShape.getColour());
    }

    @Test
    void testUnsetStartTimes() throws Exception {

        BufferedInputStream bufferedInputStream = getBufferedInputStreamFromFilepath("src/test/resources/unset_start_times.xml");

        Slideshow parsedSlideShow;

        parser.parse(bufferedInputStream, handler);

        parsedSlideShow = handler.getSlideshow();

        SlideDataStorage parsedSlide = parsedSlideShow.getSlides().get(0);

        AudioDataStorage parsedAudio = parsedSlide.audioIterator().next();
        VideoDataStorage parsedVideo = parsedSlide.videoIterator().next();

        assertEquals(0, parsedAudio.getStartTime());
        assertEquals(0, parsedVideo.getStartTime());
    }

    @Test
    void testUnexpectedElement() throws Exception {

        BufferedInputStream bufferedInputStream = getBufferedInputStreamFromFilepath("src/test/resources/unexpected_element.xml");

        Assertions.assertDoesNotThrow(() -> parser.parse(bufferedInputStream, handler));
        Assertions.assertDoesNotThrow(() -> handler.getSlideshow());
    }
}