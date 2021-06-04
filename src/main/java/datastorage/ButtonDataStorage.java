package datastorage;

abstract class ButtonDataStorage
{
    protected int XPos;
    protected int YPos;
    protected int width;
    protected int height;
    protected String id;

    public ButtonDataStorage(int XPos, int YPos,
                                 int width, int height, String id)
    {
        this.XPos = XPos;
        this.YPos = YPos;
        this.width = width;
        this.height = height;
        this.id = id;
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
}
