package datastorage;

import java.util.Objects;

public abstract class ButtonDataStorage
{
    public enum Target {slide, media};
    protected Target target;
    protected final int XPos;
    protected final int YPos;
    protected final int width;
    protected final int height;
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

    public ButtonDataStorage(int XPos, int YPos,
                             int width, int height)
    {
        this.XPos = XPos;
        this.YPos = YPos;
        this.width = width;
        this.height = height;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTarget(Target target) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ButtonDataStorage that = (ButtonDataStorage) o;
        return XPos == that.XPos &&
                YPos == that.YPos &&
                width == that.width &&
                height == that.height &&
                target == that.target &&
                id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(target, XPos, YPos, width, height, id);
    }
}
