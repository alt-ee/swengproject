package DataStorage;

import java.net.URL;

public class ImageDataStorage
{
    private final int XPos;
    private final int YPos;
    private final URL fileLocation;
    private final int width;
    private final int height;
    private final int duration;

    public ImageDataStorage(int XPos, int YPos, URL fileLocation,
                            int width, int height, int duration)
    {
        this.XPos = XPos;
        this.YPos = YPos;
        this.fileLocation = fileLocation;
        this.width = width;
        this.height = height;
        this.duration = duration;
    }

    public int getXPos()
    {
        return XPos;
    }
    public int getYPos()
    {
        return YPos;
    }
    public URL getFileLocation()
    {
        return fileLocation;
    }
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }
    public int getDuration()
    {
        return duration;
    }
}
