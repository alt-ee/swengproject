package mediahandlers;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.List;

public class GraphicsHandler {
    //List of Line2D objects to store all lines to be drawn
    private List<Line2D> lines = new ArrayList<Line2D>();

    //List of Color objects to store the colors for each line
    private List<Color> lineColors = new ArrayList<Color>();

    //List of RectangularShape objects (can be Rectangle2D or Ellipse 2D to store all shapes to be drawn
    private List<RectangularShape> shapes = new ArrayList<RectangularShape>();

    //List of Color objects to store the colors for each shape
    private List<Color> shapeColors = new  ArrayList<Color>();

    //Adds a new line with specified parameters to the line list
    public void addLine(int x1, int y1, int x2, int y2, Color color) {
        lines.add(new Line2D.Double(x1, y1, x2, y2));
        lineColors.add(color);
    }

    public void clearLines() {
        for (int i = 0; i < lines.size(); i++) {
            lines.clear();
            lineColors.clear();
        }
    }

    public List<Line2D> getLines() {
        return lines;
    }

    public Line2D getLine(int index) {
        return lines.get(index);
    }

    public List<Color> getLineColors() {
        return lineColors;
    }

    public Color getLineColor(int index) {
        return lineColors.get(index);
    }
}
