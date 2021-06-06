package survivalapp;

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
        view.newWindow(1280, 720);

        // Image test
        try {
            URL url1 = new URL("file:///src/test/resources/image0.jpg");
            ImageDataStorage image1 = new ImageDataStorage(20, 20, url1, 300, 300, 0);
            URL url2 = new URL("file:///src/test/resources/hulk.JPEG");
            ImageDataStorage image2 = new ImageDataStorage(30, 400, url2, 300, 300, 0);
            view.drawImage(image1);
            view.drawImage(image2);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Shape test
        ShapeDataStorage shape1 = new ShapeDataStorage(500, 20, 100, 100, Color.BLUE, 0, ShapeDataStorage.Shapes.Oval);
        view.drawShape(shape1);

        ShaderDataStorage shader = new ShaderDataStorage(500, 300, 550, 400, Color.red, Color.green, false);
        ShapeDataStorage shape2 = new ShapeDataStorage(500, 300, 50, 100, Color.cyan, 0, ShapeDataStorage.Shapes.Rectangle);
        shape2.setShader(shader);
        view.drawShape(shape2);

        // Line test
        LineDataStorage line1 = new LineDataStorage(800, 20, 850, 400, Color.green, 0);
        LineDataStorage line2 = new LineDataStorage(850, 20, 900, 400, Color.blue, 2);
        view.drawLine(line1);
        view.drawLine(line2);

        // Text test
        String textString = "Normal text <b>bold text</b>\n<i>italic text</i>";
        TextDataStorage text1 = new TextDataStorage(500, 500, textString, "Calibri", 16, Color.BLACK, 0);
        view.drawText(text1);

        // Audio test
        try {
        /*
        URL url1 = new URL("file:/src/test/resources/bird.wav");
        AudioDataStorage audio1 = new AudioDataStorage(url1, 0, true);
        view.playAudio(audio1);
        */

            URL url2 = new URL("file:///src/test/resources/bird2.wav");
            AudioDataStorage audio2 = new AudioDataStorage(url2, 2, true);
            view.playAudio(audio2);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Video test
        try {
            URL url = new URL("file:///src/test/resources/testVideo.mp4");
            VideoDataStorage video = new VideoDataStorage(900, 400, url, 0, false);

            view.drawVideo(video);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

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
