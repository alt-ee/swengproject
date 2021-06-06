package datastorage;

public abstract class ButtonDataStorage
{
    public enum Target {slide, media};
    protected Target target;
    protected int XPos;
    protected int YPos;
    protected int width;
    protected int height;
    protected String id;

    public ButtonDataStorage(int XPos, int YPos,
                                 int width, int height, String id, Target target)
    {
        this.XPos = XPos;
        this.YPos = YPos;
        this.width = width;
        this.height = height;
        this.id = id;
        this.target = target;
    }

    public int getXPos() {
        return XPos;
    }

    public int getYPos() {
        return YPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getId() {
        return id;
    }

    public Target getTarget() {
        return target;
    }
}
