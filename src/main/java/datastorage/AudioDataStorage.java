package datastorage;

import java.net.URL;

public class AudioDataStorage
{
    private final URL fileLocation;
    private final int startTime;
    private final String id;
    private final boolean loop;

    public AudioDataStorage(URL fileLocation, int startTime, boolean loop)
    {
        this.fileLocation = fileLocation;
        this.startTime = startTime;
        this.loop = loop;
        this.id = null;
    }

    public AudioDataStorage(URL fileLocation, String id, boolean loop) {

        this.fileLocation = fileLocation;
        this.startTime = -1; // Indicates startTime should not be used
        this.loop = loop;
        this.id = id;
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

    public boolean isLoop()
    {
        return loop;
    }

    public boolean hasStartTime() {
        return startTime != -1;
    }

}
