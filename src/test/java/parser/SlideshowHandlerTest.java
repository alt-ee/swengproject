package parser;

import datastorage.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    void init() throws Exception{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        parser = factory.newSAXParser();
        handler = new SlideshowHandler("src/test/resources/");
    }

    @Test
    void testInvalidFile() throws Exception{

        BufferedInputStream bufferedInputStream = getBufferedInputStreamFromFilepath("src/test/resources/not_an_xml_file.txt");

        assertThrows(SAXParseException.class, () -> parser.parse(bufferedInputStream, handler));
    }

    @Test
    void testInvalidXMLFile() throws Exception {

        BufferedInputStream bufferedInputStream = getBufferedInputStreamFromFilepath( "src/test/resources/invalid_xml_file.xml");

        assertThrows(SAXParseException.class, () -> parser.parse(bufferedInputStream, handler));
    }

    @Test
    void testDefaults() throws Exception{

        BufferedInputStream bufferedInputStream = getBufferedInputStreamFromFilepath("src/test/resources/defaults_test.xml");

        Slideshow parsedSlideShow;

        parser.parse(bufferedInputStream, handler);

        parsedSlideShow = handler.getSlideshow();

        SlideDataStorage parsedSlide = parsedSlideShow.getCurrentSlide();
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

        SlideDataStorage parsedSlide = parsedSlideShow.getCurrentSlide();

        AudioDataStorage parsedAudio = parsedSlide.audioIterator().next();
        VideoDataStorage parsedVideo = parsedSlide.videoIterator().next();

        assertEquals(0, parsedAudio.getStartTime());
        assertEquals(0, parsedVideo.getStartTime());
    }

    @Test
    void testUnexpectedElement() throws Exception {

        BufferedInputStream bufferedInputStream = getBufferedInputStreamFromFilepath("src/test/resources/unexpected_element.xml");

        assertDoesNotThrow(() -> parser.parse(bufferedInputStream, handler));
        assertDoesNotThrow(() -> handler.getSlideshow());
    }

    @Test
    void testValidFile() throws Exception {

        BufferedInputStream bufferedInputStream = getBufferedInputStreamFromFilepath("src/test/resources/PWS_example.xml");

        Slideshow parsedSlideshow;

        // Constructing a SlideshowHandler that matches the desired output of the provided XML file

        Slideshow expectedSlideshow = new Slideshow();

        expectedSlideshow.setDefaults(Color.decode("#43675a"),
                "Calibri",
                16,
                Color.decode("#a6aeae"),
                Color.decode("#f2c95e"),
                Color.decode("#6dcbdb"));

        SlideDataStorage slide1 = new SlideDataStorage("slide01", 0);
        TextDataStorage text1 = new TextDataStorage(100,
                200,
                "<html>test text <b>some bold text</b> <i>some italic text</i> <b>some more bold text</b> some normal text</html>",
                expectedSlideshow.getDefaultFont(),
                expectedSlideshow.getDefaultFontSize(),
                expectedSlideshow.getDefaultTextColour(),
                0);
        LineDataStorage line1 = new LineDataStorage(40, 20, 80, 20,
                expectedSlideshow.getDefaultLineColour(),
                0);
        ShapeDataStorage shape1 = new ShapeDataStorage(10, 10, 20 ,34,
                expectedSlideshow.getDefaultShapeColour(),
                10,
                ShapeDataStorage.Shapes.Oval);
        ShaderDataStorage shader1 = new ShaderDataStorage(0, 0, 0, 0,
                Color.decode("#f2a54b"),
                Color.decode("#dd452a"),
                false);
        shape1.setShader(shader1);
        TextDataStorage text2 = new TextDataStorage(7,
                34,
                "<html>more test text with specified font</html>",
                "Joker",
                expectedSlideshow.getDefaultFontSize(),
                expectedSlideshow.getDefaultTextColour(),
                0);
        ImageDataStorage image1 = new ImageDataStorage(10, 10,
                new URL("file:///src/test/resources/resources/images/image.png"),
                5,
                6,
                0);
        ButtonDataStorage button1 = new TextButton(
                300,
                300,
                100,
                50,
                "slide02",
                "button1Text",
                "Calibri",
                16,
                Color.decode("#dd452a"),
                ButtonDataStorage.Target.slide);

        slide1.addText(text1);
        slide1.addText(text2);
        slide1.addLine(line1);
        slide1.addShape(shape1);
        slide1.addImage(image1);
        slide1.addButton(button1);

        SlideDataStorage slide2 = new SlideDataStorage("slide02", 100);

        VideoDataStorage video1 = new VideoDataStorage(20, 9,
                new URL("file:///src/test/resources/resources/videos/video.mp4"), "testVideo", true);
        AudioDataStorage audio1 = new AudioDataStorage(new URL("file:///src/test/resources/resources/audio/audio.wav"), 10, true);
        ButtonDataStorage button2 = new ImageButton(
                300,
                300,
                100,
                50,
                "media01",
                new URL("file:///src/test/resources/images/buttonimage.png"),
                ButtonDataStorage.Target.media);

        slide2.addVideo(video1);
        slide2.addAudio(audio1);
        slide2.addButton(button2);

        expectedSlideshow.addSlide(slide1);
        expectedSlideshow.addSlide(slide2);

        parser.parse(bufferedInputStream, handler);
        parsedSlideshow = handler.getSlideshow();

        assertEquals(expectedSlideshow, parsedSlideshow);
    }

}