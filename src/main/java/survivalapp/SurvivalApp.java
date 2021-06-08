package survivalapp;

import java.awt.*;
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
    private static final String resourcesPath = "src/main/resources/";

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, URISyntaxException, LineUnavailableException, UnsupportedAudioFileException {
        FileChooser chooser = new FileChooser();
        chooser.openDialog(resourcesPath);

        File xmlFile = new File(chooser.hadChoseFile()?
                chooser.getChoseFilePath() :
                resourcesPath + "birds.xml");

        Controller controller = new Controller(xmlFile);
        controller.drawCurrentSlide();
    }


}
