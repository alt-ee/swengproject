package survivalapp;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import datastorage.Slideshow;
import org.xml.sax.SAXException;
import parser.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.xml.parsers.ParserConfigurationException;

public class SurvivalApp {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, URISyntaxException, LineUnavailableException, UnsupportedAudioFileException {

        File xmlFile = new File("src/main/resources/firstIterationSlide.xml");
        URI xmlFileLocation = xmlFile.toURI();

        Parser myParser = new Parser();
        Slideshow slideshow = myParser.parse(xmlFileLocation);

        ParserHandlerInterface audioGraphicsDemo = new ParserHandlerInterface();
        audioGraphicsDemo.toHandlers(slideshow);

    }


}
