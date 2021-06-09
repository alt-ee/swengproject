package survivalapp;

import datastorage.*;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class ControllerManualTest {

    public void runTest() {

        Slideshow slideshow = new Slideshow();

        SlideDataStorage slide1 = new SlideDataStorage("slide1", 0);
        SlideDataStorage slide2 = new SlideDataStorage("slide2", 0);

        TextDataStorage text1 = new TextDataStorage(450, 100, "This is Slide 1", "Calibri", 16, Color.BLACK, 0);
        TextDataStorage text2 = new TextDataStorage(450, 100, "This is Slide 2", "Calibri", 16, Color.BLUE, 0);

        ButtonDataStorage button1 = new TextButton(450, 250, 100, 50, "slide2", "Go to slide 2", "Calibri", 16, Color.BLUE, ButtonDataStorage.Target.slide);
        ButtonDataStorage button2 = new TextButton(450, 250, 100, 50, "slide1", "Go to slide 1", "Calibri", 16, Color.BLACK, ButtonDataStorage.Target.slide);
        ButtonDataStorage button3 = new TextButton(450, 450, 100, 50, "audio1", "Play audio", "Calibri", 16, Color.BLACK, ButtonDataStorage.Target.media);
        ButtonDataStorage button4 = new TextButton(450, 450, 100, 50, "video1", "Play video", "Calibri", 16, Color.BLACK, ButtonDataStorage.Target.media);

        slide1.addText(text1);
        slide1.addButton(button1);
        try {
            URL url2 = new URL("file:///src/test/resources/bird2.wav");
            AudioDataStorage audio2 = new AudioDataStorage(url2, "audio1", true);
            slide1.addAudio(audio2);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        slide1.addButton(button3);

        slide2.addText(text2);
        slide2.addButton(button2);

        try {
            URL url = new URL("file:///src/test/resources/testVideo.mp4");
            VideoDataStorage video = new VideoDataStorage(900, 400, url,"video1", false);

            slide2.addVideo(video);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        slide2.addButton(button4);

        slideshow.addSlide(slide1);
        slideshow.addSlide(slide2);
        slideshow.setCurrentSlide("slide1");

        Controller controller = new Controller(slideshow);

        controller.run();
    }

}
