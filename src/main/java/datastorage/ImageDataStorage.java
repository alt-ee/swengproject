package datastorage;

import java.net.URL;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageDataStorage that = (ImageDataStorage) o;
        return XPos == that.XPos &&
                YPos == that.YPos &&
                width == that.width &&
                height == that.height &&
                duration == that.duration &&
                fileLocation.equals(that.fileLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(XPos, YPos, fileLocation, width, height, duration);
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
