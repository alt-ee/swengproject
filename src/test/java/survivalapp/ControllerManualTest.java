package survivalapp;

import datastorage.*;

import java.awt.*;

public class ControllerManualTest {

    public static void main(String[] args) {

        Slideshow slideshow = new Slideshow();

        SlideDataStorage slide1 = new SlideDataStorage("slide1", 0);
        SlideDataStorage slide2 = new SlideDataStorage("slide2", 0);

        TextDataStorage text1 = new TextDataStorage(450, 100, "This is Slide 1", "Calibri", 16, Color.BLACK, 0);
        TextDataStorage text2 = new TextDataStorage(450, 100, "This is Slide 2", "Calibri", 16, Color.BLUE, 0);

        ButtonDataStorage button1 = new TextButton(450, 250, 100, 50, "slide2", "Go to slide 2", "Calibri", 16, Color.BLUE, ButtonDataStorage.Target.slide);
        ButtonDataStorage button2 = new TextButton(450, 250, 100, 50, "slide1", "Go to slide 1", "Calibri", 16, Color.BLACK, ButtonDataStorage.Target.slide);

        slide1.addText(text1);
        slide1.addButton(button1);

        slide2.addText(text2);
        slide2.addButton(button2);

        slideshow.addSlide(slide1);
        slideshow.addSlide(slide2);
        slideshow.setCurrentSlide("slide1");

        Controller controller = new Controller(slideshow);

        controller.drawCurrentSlide();

    }

}
