package survivalapp;

import datastorage.ImageDataStorage;

import java.net.MalformedURLException;
import java.net.URL;

public class ViewManualTest {

    public static void main(String[] args) {

        View view = new View();

        view.newWindow(1280, 720);

        try {
            URL url1 = new URL("file:///src/test/resources/image0.jpg");
            ImageDataStorage image1 = new ImageDataStorage(20, 20, url1, 300, 300, 0);

            URL url2 = new URL("file:///src/test/resources/hulk.JPEG");
            ImageDataStorage image2 = new ImageDataStorage(20, 400, url2, 300, 300, 0);

            view.drawImage(image1);
            view.drawImage(image2);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
