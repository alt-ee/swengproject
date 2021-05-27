package datastorage;

import java.awt.Color;

public class ShapeDataStorage {
    public enum Shapes {Rectangle, Oval};


    private final int xPosition;
    private final int yPosition;
    private final int width;
    private final int height;
    private final Color colour;
    private ShaderDataStorage shader = null;
    private final int duration;
    private final Shapes shapetype;

    /**
     * Construct a Shape with a single colour
     *
     * @param xPosition X coordinate of the top left corner
     * @param yPosition Y coordinate of the top left corner
     * @param width     Width of the shape
     * @param height    Height of the shape
     * @param colour    Colour of the shape as a java.awt.Color object
     * @param duration  Duration to display the shape for in seconds. If 0 then shape will be displayed indefinitely.
     * @param shapeType Type of shape, either Rectangle or Oval as defined by enum
     */
    public ShapeDataStorage(int xPosition, int yPosition, int width, int height, Color colour, int duration, Shapes shapeType) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
        this.colour = colour;
        this.duration = duration;
        this.shapetype = shapeType;
    }

    public void setShader(ShaderDataStorage shader) {
        this.shader = shader;
    }

    /**
     * @return X coordinate of top left corner
     */
    public int getxPosition() {
        return xPosition;
    }

    /**
     * @return Y coordinate of top left corner
     */
    public int getyPosition() {
        return yPosition;
    }

    /**
     * @return width of shape
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return height of shape
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return colour of shape
     */
    public Color getColour() {
        return colour;
    }

    //return colour in form of hex string
    public String getColourInHex()
    {
        int RGB = colour.getRGB();
        String hex = String.format("#%06X", (0xffffff & RGB));
        return hex;
    }

    /**
     * @return duration to display shape for
     */
    public int getDuration() {
        return duration;
    }

    public ShaderDataStorage getShader() {
        return shader;
    }

    /**
     * @return true if shape is a rectangle
     */
    public boolean isRectangle() {
        return shapetype == Shapes.Rectangle;
    }

    /**
     * @return true if shape is an oval
     */
    public boolean isOval() {
        return shapetype == Shapes.Oval;
    }
}
