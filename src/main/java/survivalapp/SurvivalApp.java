package survivalapp;

import java.net.MalformedURLException;
import java.net.URL;

public class SurvivalApp {

    public static void main(String[] args) throws MalformedURLException
    {

        URL testURL = new URL ("https://wikipedia.org");

        /*
        AudioDataStorage audio = new AudioDataStorage(testURL, 0, false);
        System.out.println(audio.getAudioLocation() + "\n" + audio.getStartTime() + "\n" + audio.getLoop());
        */

        System.out.print("Hello World from eclipse");

    }
}
