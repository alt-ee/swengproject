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
    void noFileLoadedPlay() {
        AudioPlayer myPlayer = new AudioPlayer();
        myPlayer.playClip(false);
        assertNull(myPlayer.audioClip);
    }

    @Test
    void noFileLoadedStop() {
        AudioPlayer myPlayer = new AudioPlayer();
        myPlayer.stopClip();
        assertNull(myPlayer.audioClip);
    }

    @Test
    void soundQuality() {
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

        while (myPlayer.audioClip.getMicrosecondPosition()/1000 < clipLength) {
            if (clipLength == myPlayer.audioClip.getMicrosecondPosition()) {
                assertEquals(clipLength, myPlayer.audioClip.getMicrosecondPosition());
            }
            else {
                continue;
            }
        }
    }

    @Test
    void interaction() {
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

        long counter = 0;
        while (myPlayer.audioClip.getMicrosecondPosition()/1000 < clipLength) {
            if (clipLength == myPlayer.audioClip.getMicrosecondPosition()) {
                assertEquals(clipLength, myPlayer.audioClip.getMicrosecondPosition());
            }
            if (counter == 2000000){
                myPlayer.stopClip();
                counter += 1;
            }
            if (counter == 3000000){
                myPlayer.playClip(false);
            }
            counter += 1;
        }
    }

    @Test
    void unusualInteraction() {
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

        long counter = 0;
        while (myPlayer.audioClip.getMicrosecondPosition()/1000 < clipLength) {
            if (clipLength == myPlayer.audioClip.getMicrosecondPosition()) {
                assertEquals(clipLength, myPlayer.audioClip.getMicrosecondPosition());
            }
            if (counter == 2000000){
                myPlayer.playClip(false);
                counter += 1;
            }
            if (counter == 5000000){
                myPlayer.playClip(false);
            }
            counter += 1;
        }
    }
}