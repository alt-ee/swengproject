package survivalapp;

import datastorage.ImageDataStorage;

import java.net.URL;

public class ViewManualTest {

    public static void main(String[] args) {

        View view = new View();

        view.newWindow(1280, 720);


        int xPos1 = 20;
        int yPos1 = 20;
        String fileLocation1 = "/src/test/resources/image0.jpg";
        int width1 = 100;
        int height1 = 100;
        int duration1 = 0;

        view.drawImage(xPos1, yPos1, fileLocation1, width1, height1, duration1);

        int xPos2 = 20;
        int yPos2 = 200;
        String fileLocation2 = "/src/test/resources/hulk.JPEG";
        int width2 = 100;
        int height2 = 100;
        int duration2 = 0;

        view.drawImage(xPos2, yPos2, fileLocation2, width2, height2, duration2);
    }
}
