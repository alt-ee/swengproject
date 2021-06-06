package survivalapp;

import buttonhandler.ButtonHandlerManualTest;
import datastorage.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

public class ViewManualTest {

    public static void main(String[] args) {

        View view = new View(new SlideListener(), new MediaListener());

        // Window test
        view.newWindow(390, 844);

        // Image test
        try {
            URL url1 = new URL("file:///src/test/resources/Chaffinch.jpg");
            ImageDataStorage image1 = new ImageDataStorage(0, 30, url1, 390, 219, 0);
            view.drawImage(image1);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Shape test
        ShapeDataStorage notificationBar = new ShapeDataStorage(0, 0, 390, 30, Color.blue, 0, ShapeDataStorage.Shapes.Rectangle);
        view.drawShape(notificationBar);

        // Line test
//        LineDataStorage line1 = new LineDataStorage(800, 20, 850, 400, Color.green, 0);
//        LineDataStorage line2 = new LineDataStorage(850, 20, 900, 400, Color.blue, 2);
//        view.drawLine(line1);
//        view.drawLine(line2);

        // Text test
        String notificationBarText = "<b><i>Notification Bar</i></b>";
        String name = "<b>Chaffinch</b>";
        String latinName = "<i>Fringilla coelebs</i>";
        String description = "<b>Description:</b> The chaffinch is one of the most\n widespread and abundant bird in Britain and Ireland.\n Its patterned plumage helps it to blend in when \nfeeding on the ground and it becomes most obvious\n when it flies, revealing a flash of white on the wings\n and white outer tail feathers. It does not feed openly\n on bird feeders - it prefers to hop about under the\n bird table or under the hedge. You'll usually hear\n chaffinches before you see them, with their loud\n song and varied calls.";


        TextDataStorage text1 = new TextDataStorage(130, 10, notificationBarText, "Calibri", 16, Color.white, 0);
        TextDataStorage text2 = new TextDataStorage(15, 264, name, "Calibri", 24, Color.black, 0);
        TextDataStorage text3 = new TextDataStorage(15, 291, latinName, "Calibri", 20, Color.lightGray, 0);
        TextDataStorage text4 = new TextDataStorage(15, 318, description, "Calibri", 16, Color.black, 0);
        view.drawText(text1);
        view.drawText(text2);
        view.drawText(text3);
        view.drawText(text4);


        // Audio test
        try {
            URL url2 = new URL("file:///src/test/resources/bird2.wav");
            AudioDataStorage audio2 = new AudioDataStorage(url2, 2, true);
            view.playAudio(audio2);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Video test
//        try {
//            URL url = new URL("file:///src/test/resources/testVideo.mp4");
//            VideoDataStorage video = new VideoDataStorage(900, 400,  url, 0, false);
//
//            view.drawVideo(video);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }

        // Button test
        ButtonDataStorage textButton = new TextButton(100, 600, 100, 20, "testid", "testButton", "Calibri", 14, Color.BLUE, ButtonDataStorage.Target.slide);
        view.drawButton(textButton);
        try {
            URL buttonImageURL = new URL("file:///src/test/resources/image0.jpg");
            ButtonDataStorage imageButton = new ImageButton(100, 650, 100, 100, "testid2", buttonImageURL, ButtonDataStorage.Target.media);
            view.drawButton(imageButton);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        view.repaintPanel();
    }

    static private class SlideListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String id = ((String)((JButton)ae.getSource()).getClientProperty("targetid"));

            System.out.println("SlideListener called with targetid: " + id);
        }
    }

    static private class MediaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String id = ((String)((JButton)ae.getSource()).getClientProperty("targetid"));

            System.out.println("MediaListener called with targetid: " + id);
        }
    }
}
