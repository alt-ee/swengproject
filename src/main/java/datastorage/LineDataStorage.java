package datastorage;

import java.awt.Color;

public class LineDataStorage {

    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;
    private final Color colour;
    private final int duration;

    /**
     * Construct a line
     *
     * @param x1 X coordinate of start point
     * @param y1 Y coordinate of start point
     * @param x2 X coordinate of end point
     * @param y2 Y coordinate of end point
     * @param colour Colour of the shape as a java.awt.Color object
     * @param duration Duration to display the shape for in seconds. If 0 then shape will be displayed indefinitely.
     */
    public LineDataStorage(int x1, int y1, int x2, int y2, Color colour, int duration) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.colour = colour;
        this.duration = duration;
    }

    /**
     * @return X coordinate of start point
     */
    public int getX1() {
        return x1;
    }

    /**
     * @return Y coordinate of start point
     */
    public int getY1() {
        return y1;
    }

    /**
     * @return X coordinate of end point
     */
    public int getX2() {
        return x2;
    }

    /**
     * @return Y coordinate of end point
     */
    public int getY2() {
        return y2;
    }

    /**
     * @return colour of shape
     */
    public Color getColour() {
        return colour;
    }

    /**
     * @return duration to display shape for
     */
    public int getDuration() {
        return duration;
    }
}
