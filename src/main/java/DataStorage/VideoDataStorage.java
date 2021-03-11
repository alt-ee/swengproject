package DataStorage;

import java.net.URL;

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

    public boolean hasStartTime() {
        return startTime != -1;
    }
}
