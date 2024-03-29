package datastorage;

import java.awt.Color;
import java.util.Objects;

public class ShaderDataStorage {

    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;
    private final Color colour1;
    private final Color colour2;
    private final boolean cyclic;

    /**
     * Construct a shader
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param colour1
     * @param colour2
     * @param cyclic
     */
    public ShaderDataStorage(int x1, int y1, int x2, int y2, Color colour1, Color colour2, boolean cyclic) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.colour1 = colour1;
        this.colour2 = colour2;
        this.cyclic = cyclic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShaderDataStorage that = (ShaderDataStorage) o;
        return x1 == that.x1 &&
                y1 == that.y1 &&
                x2 == that.x2 &&
                y2 == that.y2 &&
                cyclic == that.cyclic &&
                colour1.equals(that.colour1) &&
                colour2.equals(that.colour2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, x2, y2, colour1, colour2, cyclic);
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public Color getColour1() {
        return colour1;
    }

    public String getColour1InHex()
    {
        int RGB = colour1.getRGB();
        String hex = String.format("#%06X", (0xffffff & RGB));
        return hex;
    }
    public Color getColour2() {
        return colour2;
    }

    public String getColour2InHex()
    {
        int RGB = colour2.getRGB();
        String hex = String.format("#%06X", (0xffffff & RGB));
        return hex;
    }
    public boolean isCyclic() {
        return cyclic;
    }
}
