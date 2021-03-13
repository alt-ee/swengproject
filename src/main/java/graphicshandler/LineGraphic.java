package graphicshandler;

import java.awt.*;
import java.awt.geom.Line2D;

public class LineGraphic {
    private Line2D lineShape;
    private Color lineColor;
    private int lineDuration;

    public LineGraphic(int x1, int y1, int x2, int y2, String hexColor, int duration) {
        lineShape = new Line2D.Double(x1, y1, x2, y2);
        lineColor = Color.decode(hexColor);
        lineDuration = duration;
    }

    public Line2D getLine() {
        return lineShape;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public int getLineDuration() {
        return lineDuration;
    }
}
