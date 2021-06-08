package media;

import javax.swing.*;

public class ToggleableVideo extends Video {
    private boolean isPlaying;

    public ToggleableVideo(JPanel panel, String urlname, int starttime, boolean loop, int xstart, int ystart, int width, int height, Boolean local, Boolean autoCalculateWidth) {
        super(panel, urlname, starttime, loop, xstart, ystart, width, height, local, autoCalculateWidth);
        isPlaying = false;
    }

    @Override
    public void startVideo() {
        isPlaying = true;
        super.startVideo();
    }

    public void togglePlayback() {
        if (!isPlaying) {
            super.startVideo();
            isPlaying = true;
        } else {
            player.stop();
            isPlaying = false;
        }
    }


}
