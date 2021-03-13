package audiohandler;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AudioPlayer implements LineListener {

    boolean donePlaying;
    Clip audioClip;

    public void playClip(String clipURL, boolean loop) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        File clipFile = new File(clipURL);
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(clipFile);
        AudioFormat format = inputStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);

        audioClip = (Clip) AudioSystem.getLine(info);

        audioClip.addLineListener(this);

        audioClip.open(inputStream);

        donePlaying = false;

        if (loop) {
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            audioClip.start();
        }


        new Thread(() -> {
            while (!donePlaying) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }

            audioClip.close();
        });
    }

    public void stopClip() {
        System.out.println("trying to stop");
        if (audioClip.isActive()) {
            audioClip.stop();
        }
    }

    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();

        if (type == LineEvent.Type.START) {
            System.out.println("Started");
        } else if (type == LineEvent.Type.STOP) {
            donePlaying = true;
            System.out.println("Stopped");
        }
    }

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // Temporary test - not for final release
        String file = "src/main/resources/bird.wav";
        AudioPlayer player = new AudioPlayer();

        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            String input = scanner.nextLine();

            switch (input) {
                case "start":
                    player.playClip(file, false);
                    break;
                case "loop":
                    player.playClip(file, true);
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
