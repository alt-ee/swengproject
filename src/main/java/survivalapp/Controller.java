package survivalapp;

import datastorage.*;
import org.xml.sax.SAXException;
import parser.Parser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class Controller {
    private Slideshow slideshow;
    private View view;

    // Temporary constructor for testing
    public Controller(File slideshowFile) throws ParserConfigurationException, SAXException, IOException {
        slideshow = Parser.parse(slideshowFile);

        view = new View();
        view.newWindow(390, 844);
    }

    public void drawCurrentSlide() {
        SlideDataStorage slide = slideshow.getCurrentSlide();

        drawAllImages(slide);
        drawAllText(slide);
        drawAllShapes(slide);
        drawAllLines(slide);
        drawAllVideos(slide);
        setupAudio(slide);

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

}
