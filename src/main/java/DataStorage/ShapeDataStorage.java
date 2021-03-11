package DataStorage;

import java.awt.Color;

public class ShapeDataStorage {
    public enum Shapes {Rectangle, Oval};

    private final int XPOSITION;
    private final int YPOSITION;
    private final int WIDTH;
    private final int HEIGHT;
    private final Color COLOUR;
    private final ShaderDataStorage SHADER;
    private final int DURATION;
    private final Shapes SHAPETYPE;

    /**
     * Construct a Shape with a single colour
     *
     * @param xPosition X coordinate of the top left corner
     * @param yPosition Y coordinate of the top left corner
     * @param width Width of the shape
     * @param height Height of the shape
     * @param colour Colour of the shape as a java.awt.Color object
     * @param duration Duration to display the shape for in seconds. If 0 then shape will be displayed indefinitely.
     * @param shapeType Type of shape, either Rectangle or Oval as defined by enum
     */
    public ShapeDataStorage(int xPosition, int yPosition, int width, int height, Color colour, int duration, Shapes shapeType) {
        this.XPOSITION = xPosition;
        this.YPOSITION = yPosition;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.COLOUR = colour;
        this.SHADER = null;
        this.DURATION = duration;
        this.SHAPETYPE = shapeType;
    }

    /**
     * Construct a Shape with shading
     *
     * @param xPosition X coordinate of the top left corner
     * @param yPosition Y coordinate of the top left corner
     * @param width Width of the shape
     * @param height Height of the shape
     * @param shader ShaderDataStorage object defining the desired shading
     * @param duration Duration to display the shape for in seconds. If 0 then shape will be displayed indefinitely.
     * @param shapeType Type of shape, either Rectangle or Oval as defined by enum
     */
    public ShapeDataStorage(int xPosition, int yPosition, int width, int height, ShaderDataStorage shader, int duration, Shapes shapeType) {
        this.XPOSITION = xPosition;
        this.YPOSITION = yPosition;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.COLOUR = null;
        this.SHADER = shader;
        this.DURATION = duration;
        this.SHAPETYPE = shapeType;
    }

    /**
     * @return X coordinate of top left corner
     */
    public int getXPOSITION() {
        return XPOSITION;
    }

    /**
     * @return Y coordinate of top left corner
     */
    public int getYPOSITION() {
        return YPOSITION;
    }

    /**
     * @return width of shape
     */
    public int getWIDTH() {
        return WIDTH;
    }

    /**
     * @return height of shape
     */
    public int getHEIGHT() {
        return HEIGHT;
    }

    /**
     * @return colour of shape
     */
    public Color getCOLOUR() {
        return COLOUR;
    }

    /**
     * @return duration to display shape for
     */
    public int getDURATION() {
        return DURATION;
    }

    /**
     * @return true if shape is a rectangle
     */
    public boolean isRectangle() {
        return SHAPETYPE == Shapes.Rectangle;
    }

    /**
     * @return true if shape is an oval
     */
    public boolean isOval() {
        return SHAPETYPE == Shapes.Oval;
    }
}
