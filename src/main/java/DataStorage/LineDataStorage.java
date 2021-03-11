package DataStorage;

import java.awt.Color;

public class LineDataStorage {

    private final int X1;
    private final int Y1;
    private final int X2;
    private final int Y2;
    private final Color COLOUR;
    private final int DURATION;

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
        X1 = x1;
        Y1 = y1;
        X2 = x2;
        Y2 = y2;
        this.COLOUR = colour;
        this.DURATION = duration;
    }

    /**
     * @return X coordinate of start point
     */
    public int getX1() {
        return X1;
    }

    /**
     * @return Y coordinate of start point
     */
    public int getY1() {
        return Y1;
    }

    /**
     * @return X coordinate of end point
     */
    public int getX2() {
        return X2;
    }

    /**
     * @return Y coordinate of end point
     */
    public int getY2() {
        return Y2;
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
}
