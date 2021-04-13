package datastorage;

import java.awt.Color;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineDataStorage that = (LineDataStorage) o;
        return x1 == that.x1 &&
                y1 == that.y1 &&
                x2 == that.x2 &&
                y2 == that.y2 &&
                duration == that.duration &&
                colour.equals(that.colour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, x2, y2, colour, duration);
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

    //return colour in form of hex string
    public String getColourInHex()
        {
            int RGB = colour.getRGB();
            String hex = String.format("#%06X", (0xffffff & RGB));
            System.out.println(hex);
            return hex;
        }


    /**
     * @return duration to display shape for
     */
    public int getDuration() {
        return duration;
    }
}
