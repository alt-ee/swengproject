package survivalapp;

import java.io.File;
import java.io.IOException;

import java.net.URISyntaxException;
import org.xml.sax.SAXException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.xml.parsers.ParserConfigurationException;

public class SurvivalApp {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, URISyntaxException, LineUnavailableException, UnsupportedAudioFileException {
        File xmlFile = new File("src/main/resources/trailblazer.xml");

        Controller controller = new Controller(xmlFile);
        controller.drawCurrentSlide();
    }


}
