package survivalapp;

import audiohandler.AudioPlayer;
import buttonhandler.ButtonHandler;
import datastorage.*;
import graphicshandler.GraphicsPanel;
import imagehandler.ImageType;
import media.ToggleableVideo;
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
import java.util.ArrayList;
import java.util.HashMap;

public class View {

    private JFrame window;
    private JLayeredPane layeredPane;
    private GraphicsPanel panel;
    private JPanel videoPanel;
    private WriteText textHandler;
    private AudioPlayer audioPlayer;
    private ButtonHandler buttonHandler;
    private boolean clipLoaded;
    private ActionListener slideListener;
    private ActionListener mediaListener;
    private HashMap<String, Runnable> mediaTriggers;
    private ArrayList<ToggleableVideo> videos;


    public View(ActionListener slideListener, ActionListener mediaListener) {
        this.slideListener = slideListener;
        this.mediaListener = mediaListener;

        textHandler = new WriteText();

        audioPlayer = new AudioPlayer();
        clipLoaded = false;
        mediaTriggers = new HashMap<>();
        videos = new ArrayList<>();
    }

    public void newWindow(int width, int height) {
        window = new JFrame();

        window.setSize(width, height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new GraphicsPanel();
        panel.setBounds(0, 0, width, height);
        panel.setLayout(null);

        buttonHandler = new ButtonHandler(panel);

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
        videoPanel.repaint();
    }

    public void toggleMedia(String id) {
        if (mediaTriggers.containsKey(id)) {
            mediaTriggers.get(id).run();
        }
    }

    public void clearPanel() {
        panel.clearAll();
        panel.removeAll();
        for (ToggleableVideo video : videos) {
            video.destroyVideo();
        }
        videoPanel.removeAll();
        audioPlayer.stopClip();
        audioPlayer.closeClip();
        clipLoaded = false;
    }

    /***
     * Wrapper method for image handler to use our image type
     *
     * @param image the image to be displayed
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
     * @param shape the shape to be displayed
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
     * @param line the line to be displayed
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
     * @param text the text to be displayed
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
     * @param audio the audio to be played
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
            if (audio.hasStartTime()) {
                int startTime = audio.getStartTime();
                if (startTime == 0) {
                    audioPlayer.playClip(loop);
                } else {
                    // Trigger a timer to start the clip after startTime has elapsed
                    Timer timer = new Timer(startTime * 1000, e -> audioPlayer.playClip(loop));
                    timer.setRepeats(false);
                    timer.start();
                }
            } else {
                String id = audio.getId();
                mediaTriggers.put(id, () -> audioPlayer.togglePlayback(loop));
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

        if (!video.hasStartTime()) {
            startWithButton = true;
            startTime = 0;
        }

        // TODO Need to sort out width and height
        ToggleableVideo videoPlayer = new ToggleableVideo(videoPanel, url, startTime, loop, xPos, yPos, 200, 200, true, true);

        if (!startWithButton) {
            videoPlayer.startVideo();
        } else {
            String id = video.getId();
            mediaTriggers.put(id, videoPlayer::togglePlayback);
        }

        videos.add(videoPlayer);

    }

    public void drawButton(ButtonDataStorage button) {
        ActionListener listener;
        if (button.getTarget() == ButtonDataStorage.Target.slide) {
            listener = slideListener;
        } else if (button.getTarget() == ButtonDataStorage.Target.media) {
            listener = mediaListener;
        } else {
            return;
        }

        if (button.getClass() == TextButton.class) {
            drawTextButton((TextButton) button, listener);
        } else if (button.getClass() == ImageButton.class) {
            drawImageButton((ImageButton) button, listener);
        }
    }

    private void drawTextButton(TextButton button, ActionListener listener) {
        buttonHandler.addTextButton(
                button.getXPos(),
                button.getYPos(),
                button.getWidth(),
                button.getHeight(),
                button.getId(),
                button.getText(),
                button.getFont(),
                button.getFontsize(),
                button.getFontColour(),
                listener
        );
    }

    private void drawImageButton(ImageButton button, ActionListener listener) {
        String url = button.getFileLocation().getFile().substring(1);

        buttonHandler.addImageButton(
                button.getXPos(),
                button.getYPos(),
                button.getWidth(),
                button.getHeight(),
                button.getId(),
                url,
                listener
        );
    }



    public void incrementTimes(int currentTime) {
        panel.incrementCurrentTime(currentTime);
        textHandler.incrementCurrentTime(currentTime);
    }

    public void resetTimes() {
        panel.setCurrentTime(0);
        textHandler.setCurrentTime(0);
    }
}
