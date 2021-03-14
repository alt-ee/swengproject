package graphicshandler;

import java.awt.*;
import java.awt.geom.Line2D;

public class LineGraphic {
    private Line2D lineShape;
    private Color lineColor;
    private int lineDuration;

    //Constructor
    public LineGraphic(int x1, int y1, int x2, int y2, String hexColor, int duration) {
        lineShape = new Line2D.Double(x1, y1, x2, y2);
        lineColor = Color.decode(hexColor); //Decodes hexadecimal colour value so it can be used by the Graphics library
        lineDuration = duration;
    }

    //Getters for required parameters
    public Line2D getLine() {
        return lineShape;
    }

    public double getLineX1() { return lineShape.getX1(); }
    public double getLineY1() { return lineShape.getY1(); }
    public double getLineX2() { return lineShape.getX2(); }
    public double getLineY2() { return lineShape.getY2(); }

    public Color getLineColor() {
        return lineColor;
    }

    public int getLineDuration() {
        return lineDuration;
    }
}
