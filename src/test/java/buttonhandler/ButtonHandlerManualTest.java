package buttonhandler;

import junit.framework.TestListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

public class ButtonHandlerManualTest {

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(null);
        frame.add(panel);

        ButtonHandler buttonHandler = new ButtonHandler(panel);

        frame.setVisible(true);

        buttonHandler.addTextButton(100, 100, 100, 20, "testid", "testButton", "Calibri", 14, Color.BLUE, new TestListener());

        try {
            URL imageURL = new URL("file:///src/test/resources/image0.jpg");
            buttonHandler.addImageButton(100, 200, 100, 100, "testid2", imageURL, new TestListener());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        panel.repaint();
    }

    static class TestListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
        }
    }
}
