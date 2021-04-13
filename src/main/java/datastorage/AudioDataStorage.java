package datastorage;

import java.net.URL;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AudioDataStorage that = (AudioDataStorage) o;
        return startTime == that.startTime &&
                loop == that.loop &&
                fileLocation.equals(that.fileLocation) &&
                id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileLocation, startTime, id, loop);
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
