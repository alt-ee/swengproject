package DataStorage;

import java.awt.Color;

public class ShaderDataStorage {

    private final int X1;
    private final int Y1;
    private final int X2;
    private final int Y2;
    private final Color COLOUR1;
    private final Color COLOUR2;
    private final boolean CYCLIC;

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
        X1 = x1;
        Y1 = y1;
        X2 = x2;
        Y2 = y2;
        this.COLOUR1 = colour1;
        this.COLOUR2 = colour2;
        this.CYCLIC = cyclic;
    }

    public int getX1() {
        return X1;
    }

    public int getY1() {
        return Y1;
    }

    public int getX2() {
        return X2;
    }

    public int getY2() {
        return Y2;
    }

    public Color getCOLOUR1() {
        return COLOUR1;
    }

    public Color getCOLOUR2() {
        return COLOUR2;
    }

    public boolean isCYCLIC() {
        return CYCLIC;
    }
}
