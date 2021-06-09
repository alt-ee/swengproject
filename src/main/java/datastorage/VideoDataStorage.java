package datastorage;

import java.net.URL;
import java.util.Objects;

public class VideoDataStorage
{
    private final int XPos;
    private final int YPos;
    private final URL location;
    private final int startTime;
    private final boolean loop;
    private final String id;

    public VideoDataStorage(int XPos, int YPos, URL location, int startTime, boolean loop)
    {
        this.XPos = XPos;
        this.YPos = YPos;
        this.location = location;
        this.startTime = startTime;
        this.loop = loop;
        this.id = null;
    }

    public VideoDataStorage(int XPos, int YPos, URL location, String id, boolean loop)
    {
        this.XPos = XPos;
        this.YPos = YPos;
        this.location = location;
        this.startTime = -1;
        this.loop = loop;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideoDataStorage that = (VideoDataStorage) o;
        return XPos == that.XPos &&
                YPos == that.YPos &&
                startTime == that.startTime &&
                loop == that.loop &&
                location.equals(that.location) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(XPos, YPos, location, startTime, loop, id);
    }

    public int getXPos()
    {
        return XPos;
    }

    public int getYPos()
    {
        return YPos;
    }

    public URL getLocation()
    {
        return location;
    }

    public int getStartTime()
    {
        return startTime;
    }

    public boolean isLoop()
    {
        return loop;
    }

    public String getId() {
        return id;
    }

    public boolean hasStartTime() {
        return startTime != -1;
    }
}
