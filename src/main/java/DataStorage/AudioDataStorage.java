package DataStorage;

import java.net.URL;

public class AudioDataStorage
{
    private final URL fileLocation;
    private final int startTime;
    private final boolean loop;

    public AudioDataStorage(URL fileLocation, int startTime, boolean loop)
    {
        this.fileLocation = fileLocation;
        this.startTime = startTime;
        this.loop = loop;
    }


    //getters
    public URL getAudioLocation()
    {
        return fileLocation;
    }

    public int getStartTime()
    {
        return startTime;
    }

    public boolean getLoop()
    {
        return loop;
    }

}
