package audiohandler;

import org.junit.jupiter.api.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AudioPlayerTest {

    @Test
    void throwIOException() {
        AudioPlayer myPlayer = new AudioPlayer();
        String input = "src/main/resources/RedKite100.wav";
        assertThrows(IOException.class , ()-> myPlayer.loadClip(input));
    }

    @Test
    void throwUnsupportedException() {
        AudioPlayer myPlayer = new AudioPlayer();
        String input = "src/main/resources/Common_Blackbird.mp3";
        assertThrows(UnsupportedAudioFileException.class , ()-> myPlayer.loadClip(input));
        }

    @Test
    void soundQuality() throws InterruptedException {
        AudioPlayer myPlayer = new AudioPlayer();
        String input = "src/main/resources/RedKite1.wav";
        try {
            myPlayer.loadClip(input);
        } catch (IOException e) {
            System.out.println("File not found");
        } catch (UnsupportedAudioFileException e) {
            System.out.println("File is of incorrect format");
        } catch (LineUnavailableException e) {
            System.out.println("Line unavailable");
        }
        long clipLength = myPlayer.audioClip.getMicrosecondLength()/1000;
        myPlayer.playClip(false);

        Thread.sleep(clipLength);
    }
}