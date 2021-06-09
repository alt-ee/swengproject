package survivalapp;

import datastorage.*;
import org.xml.sax.SAXException;
import parser.Parser;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class Controller {
    private Slideshow slideshow;
    private View view;
    private final String defaultPath = "src/main/resources/";


    public Controller(File slideshowFile) throws ParserConfigurationException, SAXException, IOException {
        slideshow = Parser.parse(slideshowFile);


        view = new View(new SlideListener(), new MediaListener());
        view.newWindow(406, 883);
    }

    public Controller(Slideshow slideshow) {
        this.slideshow = slideshow;

        view = new View(new SlideListener(), new MediaListener());
        view.newWindow(1280, 720);
    }

    public Controller() {
       view = new View(new SlideListener(), new MediaListener());
    }

    public void run() {
        if (slideshow == null) {
            loadSlideshow();
        }
        drawCurrentSlide();
    }

    private void loadSlideshow() {
        boolean successfulParse = false;
        FileChooser chooser = new FileChooser();

        while (!successfulParse) {
            chooser.openDialog(defaultPath);

            if (!chooser.hadChoseFile()) {
                System.exit(0);
            }

            File xmlFile = new File(chooser.getChoseFilePath());

            try {
                slideshow = Parser.parse(xmlFile);
                successfulParse = true;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                int choice = JOptionPane.showConfirmDialog(null,
                        "Error parsing XML file. Try again?",
                        "Parsing Error!",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.ERROR_MESSAGE);

                if (choice == JOptionPane.NO_OPTION) {
                    System.exit(0);
                }
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
        }
    }

    private void drawCurrentSlide() {
        view.clearPanel();
        SlideDataStorage slide = slideshow.getCurrentSlide();

        drawAllImages(slide);
        drawAllText(slide);
        drawAllShapes(slide);
        drawAllLines(slide);
        drawAllVideos(slide);
        setupAudio(slide);
        drawAllButtons(slide);

        view.repaintPanel();
    }

    private void drawAllImages(SlideDataStorage slide) {
        if (slide.hasImage()) {
            Iterator<ImageDataStorage> imageIter = slide.imageIterator();

            while (imageIter.hasNext()) {
                view.drawImage(imageIter.next());
            }
        }
    }

    private void drawAllText(SlideDataStorage slide) {
        if (slide.hasText()) {
            Iterator<TextDataStorage> textIter = slide.textIterator();


            while (textIter.hasNext()) {
                view.drawText(textIter.next());
            }
        }
    }

    private void drawAllShapes(SlideDataStorage slide) {
        if (slide.hasShape()) {
            Iterator<ShapeDataStorage> shapeIter = slide.shapeIterator();

            while (shapeIter.hasNext()) {
                view.drawShape(shapeIter.next());
            }
        }
    }

    private void drawAllLines(SlideDataStorage slide) {
        if (slide.hasLine()) {
            Iterator<LineDataStorage> lineIter = slide.lineIterator();

            while (lineIter.hasNext()) {
                view.drawLine(lineIter.next());
            }
        }
    }

    private void drawAllVideos(SlideDataStorage slide) {
        if (slide.hasVideo()) {
            Iterator<VideoDataStorage> videoIter = slide.videoIterator();

            while (videoIter.hasNext()) {
                view.drawVideo(videoIter.next());
            }
        }
    }

    private void setupAudio(SlideDataStorage slide) {

        if (slide.hasAudio()) {
            Iterator<AudioDataStorage> audioIter = slide.audioIterator();

            view.playAudio(audioIter.next());

            if (audioIter.hasNext()) {
                System.out.println("Multiple audio files in slide!? Playing first file");
            }

        }

    }

    private void drawAllButtons(SlideDataStorage slide) {
        if (slide.hasButton()) {
            Iterator<ButtonDataStorage> buttonIter = slide.buttonIterator();

            while (buttonIter.hasNext()) {
                view.drawButton(buttonIter.next());
            }
        }
    }

    private class SlideListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            String id = ((String)((JButton)ae.getSource()).getClientProperty("targetid"));

            slideshow.setCurrentSlide(id);
            drawCurrentSlide();
        }
    }

    private class MediaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            String id = ((String)((JButton)ae.getSource()).getClientProperty("targetid"));

            view.toggleMedia(id);
        }
    }
}
