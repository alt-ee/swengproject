package audiohandler;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {

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

        // Get info about the dataLine to construct the clip from
        AudioFormat format = inputStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);

        if (audioClip != null) {
            if (audioClip.isOpen()) {
                audioClip.close();
            }
        }

        audioClip = (Clip) AudioSystem.getLine(info);
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

    public void togglePlayback() {
        if (audioClip != null) {
            if (!audioClip.isActive()) {
                audioClip.start();
            } else {
                audioClip.stop();
            }
        }
    }

    /**
     * Close the DataLine, unloading the clip from the player.
     */
    public void closeClip() {
        if (audioClip != null) {
            audioClip.close();
        }
    }

}
