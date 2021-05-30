package survivalapp;

import datastorage.*;
import graphicshandler.GraphicsPanel;
import imagehandler.ImageType;
import texthandler.WriteText;

import javax.swing.*;
import java.awt.*;

public class View {

    private JFrame window;
    private GraphicsPanel panel;
    private WriteText textHandler;

    public void newWindow(int width, int height) {
        window = new JFrame();

        window.setSize(width, height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        panel = new GraphicsPanel();
        panel.setLayout(null);
        textHandler = new WriteText();

        window.add(panel);
    }

    public void repaintPanel() {
        panel.repaint();
    }


    /***
     * Wrapper method for image handler to use our image type
     *
     * @param image
     */
    public void drawImage(ImageDataStorage image) {

        String url = image.getFileLocation().getFile().substring(1);
        int xPos = image.getXPos();
        int yPos = image.getYPos();
        int width = image.getWidth();
        int height = image.getHeight();

        ImageType imageType = new ImageType(url, xPos, yPos, width, height, panel);
        imageType.drawImageWithScale();
    }

    /***
     * Wrapper method for shape handler to use our shape type
     *
     * @param shape
     */
    public void drawShape(ShapeDataStorage shape) {

        int xPos = shape.getxPosition();
        int yPos = shape.getyPosition();
        int width = shape.getWidth();
        int height = shape.getHeight();
        String colour = shape.getColourInHex();
        ShaderDataStorage shader = shape.getShader();
        int duration = shape.getDuration();

        if (shader != null) {
            int shaderX1 = shader.getX1();
            int shaderY1 = shader.getY1();
            int shaderX2 = shader.getX2();
            int shaderY2 = shader.getY2();
            String shaderColour1 = shader.getColour1InHex();
            System.out.println(shaderColour1);
            String shaderColour2 = shader.getColour2InHex();
            boolean cyclic = shader.isCyclic();

            if (shape.isOval()) {
                panel.addShadedOval(height, width, xPos, yPos, shaderX1, shaderY1, shaderColour1, shaderX2, shaderY2, shaderColour2, cyclic, duration);
            } else if (shape.isRectangle()) {
                panel.addShadedRect(height, width, xPos, yPos, shaderX1, shaderY1, shaderColour1, shaderX2, shaderY2, shaderColour2, cyclic, duration);
            }
        } else {
            if (shape.isOval()) {
                panel.addOval(height, width, xPos, yPos, colour, true, duration);
            } else if (shape.isRectangle()) {
                panel.addRect(height, width, xPos, yPos, colour, true, duration);
            }
        }
    }

    /***
     * Wrapper method for line handler to use our line type
     *
     * @param line
     */
    public void drawLine(LineDataStorage line) {

        int x1 = line.getX1();
        int y1 = line.getY1();
        int x2 = line.getX2();
        int y2 = line.getY2();
        String colour = line.getColourInHex();
        int duration = line.getDuration();

        panel.addLine(x1, y1, x2, y2, colour, duration);
    }

    /***
     * Wrapper method for text handler to use our text type
     *
     * @param text
     */
    public void drawText(TextDataStorage text) {
        int xPos = text.getXPos();
        int yPos = text.getYPos();
        String textString = text.getText();
        String font = text.getFont();
        int fontSize = text.getFontSize();
        Color fontColour = text.getColour();
        int duration = text.getDuration();
        textHandler.addText(panel, xPos, yPos, textString, font, fontSize, fontColour, duration);
    }

}
