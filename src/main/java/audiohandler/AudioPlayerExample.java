package audiohandler;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Scanner;

public class AudioPlayerExample {

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
                case "close":
                    player.closeClip();
                    break;
                case "exit":
                    player.stopClip();
                    player.closeClip();
                    running = false;
                    break;
            }
        }
    }
}
