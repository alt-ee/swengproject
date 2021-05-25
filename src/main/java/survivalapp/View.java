package survivalapp;

import imagehandler.ImageType;

import javax.swing.*;

public class View {

    private JFrame window;
    private JPanel panel;

    public void newWindow(int width, int height) {
        window = new JFrame();

        window.setSize(width, height);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();

        window.add(panel);
    }

    public void drawImage(int xPos, int yPos, String url, int width, int height, int duration) {

        ImageType image = new ImageType(url, xPos, yPos, width, height, panel);
        image.drawImageWithScale();
        window.repaint();
    }
}
