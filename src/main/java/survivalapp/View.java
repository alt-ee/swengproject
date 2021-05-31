package survivalapp;

import audiohandler.AudioPlayer;
import datastorage.*;
import graphicshandler.GraphicsPanel;
import imagehandler.ImageType;
import media.Video;
import texthandler.WriteText;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

public class View {

    private JFrame window;
    private JLayeredPane layeredPane;
    private GraphicsPanel panel;
    private JPanel videoPanel;
    private WriteText textHandler;
    private AudioPlayer audioPlayer;
    private boolean clipLoaded;

    public void newWindow(int width, int height) {
        window = new JFrame();

        window.setSize(width, height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textHandler = new WriteText();

        audioPlayer = new AudioPlayer();
        clipLoaded = false;

        panel = new GraphicsPanel();
        panel.setBounds(0, 0, width, height);
        panel.setLayout(null);

        videoPanel = new JPanel();
        videoPanel.setOpaque(true);
        videoPanel.setLayout(null);

        layeredPane = new JLayeredPane();
        layeredPane.add(panel, 0);
        layeredPane.add(videoPanel, 1);

        window.add(layeredPane);
        window.setVisible(true);
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

        String url = image.getFileLocation().getFile().substring(1); // Better way to do this?
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

    /***
     * Wrapper method for audio handler to use our audio type
     *
     * @param audio
     */

    public void playAudio(AudioDataStorage audio) {
        System.out.println("playAudio");
        String url = audio.getAudioLocation().getPath().substring(1);
        boolean loop = audio.isLoop();

        // TODO handle exceptions better
        try {
            audioPlayer.loadClip(url);
            clipLoaded = true;
        } catch (IOException e) {
            System.out.println("File not found");
        } catch (UnsupportedAudioFileException e) {
            System.out.println("File is of incorrect format");
        } catch (LineUnavailableException e) {
            System.out.println("Line unavailable");
        }

        if (clipLoaded) {
            System.out.println("Clip loaded");
            if (audio.hasStartTime()) {
                int startTime = audio.getStartTime();
                if (startTime == 0) {
                    System.out.println("playing");
                    audioPlayer.playClip(loop);
                } else {
                    // Trigger a timer to start the clip after startTime has elapsed
                    Timer timer = new Timer(startTime * 1000, e -> audioPlayer.playClip(loop));
                    timer.setRepeats(false);
                    timer.start();
                }
            }
        }
    }

    public void drawVideo(VideoDataStorage video) {

        int xPos = video.getXPos();
        int yPos = video.getYPos();
        String url = video.getLocation().getPath().substring(1);
        int startTime = video.getStartTime();
        boolean startWithButton = false;
        boolean loop = video.isLoop();

        if (startTime == -1) {
            startWithButton = true;
            startTime = 0;
        }

        // TODO Need to sort out width and height
        Video videoPlayer = new Video(videoPanel, url, startTime, loop, xPos, yPos, 200, 200, true, true);

        if (!startWithButton) {
            videoPlayer.startVideo();
        }

    }


}
