package audiohandler;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class AudioPlayer implements LineListener {

    boolean donePlaying;
    Clip audioClip;

    /**
     * Load a sound file
     *
     * @param clipURL location of the sound file to load
     * @throws IOException if there was an issue loading the file
     * @throws UnsupportedAudioFileException if the file is not a usable format
     * @throws LineUnavailableException if the line could not be opened
     */
    public void loadClip(String clipURL) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        File clipFile = new File(clipURL);

        AudioInputStream inputStream = AudioSystem.getAudioInputStream(clipFile);

        // Get info about the dataline to construct the clip from
        AudioFormat format = inputStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);

        if (audioClip != null) {
            if (audioClip.isOpen()) {
                audioClip.close();
            }
        }

        audioClip = (Clip) AudioSystem.getLine(info);
        audioClip.addLineListener(this);
        audioClip.open(inputStream);
    }

    /**
     * Start playback of the clip. If no clip is loaded then does nothing.
     *
     * @param loop whether or not to loop the clip. If true clip will loop until stopped with {@link #stopClip()}
     */
    public void playClip(boolean loop) {

        if (audioClip != null) {
            audioClip.setFramePosition(0);

            if (loop) {
                audioClip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                audioClip.start();
            }

            donePlaying = false;

            new Thread(() -> {
                while (!donePlaying) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                }
                audioClip.stop();
            }).start();
        }
    }

    /**
     * Stop playback of the clip. If no clip is loaded then does nothing.
     */
    public void stopClip() {
        if (audioClip != null) {
            if (audioClip.isActive()) {
                audioClip.stop();
            }
        }
    }

    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();
        if (type == LineEvent.Type.STOP) {
            donePlaying = true;
        }
    }

    public static void main(String[] args) {
        // Temporary test - not for final release
        AudioPlayer player = new AudioPlayer();

        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            String input = scanner.nextLine();

            switch (input) {
                case "load":
                    System.out.println("enter the location of the file");
                    input = scanner.nextLine();
                    try {
                        player.loadClip(input);
                    } catch (IOException e) {
                        System.out.println("File not found");
                    } catch (UnsupportedAudioFileException e) {
                        System.out.println("File is of incorrect format");
                    } catch (LineUnavailableException e) {
                        System.out.println("Line unavailable");
                    }
                    break;
                case "start":
                    try {
                        player.playClip(false);
                    } catch (NullPointerException e) {
                        System.out.println("No clip loaded");
                    }
                    break;
                case "loop":
                    try {
                        player.playClip(true);
                    } catch (NullPointerException e) {
                        System.out.println("No clip loaded");
                    }
                    break;
                case "stop":
                    player.stopClip();
                    break;
                case "exit":
                    player.stopClip();
                    running = false;
                    break;
            }
        }
    }
}
