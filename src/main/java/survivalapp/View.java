package survivalapp;

import datastorage.ImageDataStorage;
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

    /***
     * Wrapper method for image handler to use our image type
     *
     * @param image
     */
    public void drawImage(ImageDataStorage image) {

        String url = image.getFileLocation().getFile().substring(1);
        System.out.println(url);
        int xPos = image.getXPos();
        int yPos = image.getYPos();
        int width = image.getWidth();
        int height = image.getHeight();

        ImageType imageType = new ImageType(url, xPos, yPos, width, height, panel);
        imageType.drawImageWithScale();
        window.repaint();
    }
}
