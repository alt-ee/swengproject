package graphicshandler;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class GraphicsPanel extends JPanel {
    ArrayList<LineGraphic> lines = new ArrayList<LineGraphic>();
    ArrayList<ShapeGraphic> shapes = new ArrayList<ShapeGraphic>();

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (LineGraphic line : lines) {
            g2d.setColor(line.getLineColor());
            g2d.draw(line.getLine());
        }

        for (ShapeGraphic shape : shapes) {
            g2d.setColor(shape.getShapeColor());

            switch (shape.getShapeType()) {
                case Rectangle:
                    if (shape.isFilled())
                        g2d.fillRect(shape.getShapeXPos(), shape.getShapeYPos(), shape.getShapeHeight(), shape.getShapeWidth());
                    else
                        g2d.drawRect(shape.getShapeXPos(), shape.getShapeYPos(), shape.getShapeHeight(), shape.getShapeWidth());
                    break;
                case Oval:
                    if (shape.isFilled())
                        g2d.fillOval(shape.getShapeXPos(), shape.getShapeYPos(), shape.getShapeHeight(), shape.getShapeWidth());
                    else
                        g2d.drawOval(shape.getShapeXPos(), shape.getShapeYPos(), shape.getShapeHeight(), shape.getShapeWidth());
                    break;
            }
        }
    }

    public void addLine(int x1, int y1, int x2, int y2, Color color, int duration) {
        lines.add(new LineGraphic(x1, y1, x2, y2, color, duration));
    }

    public void addRect(int height, int width, int xPos, int yPos, Color color, Boolean filled, int duration) {
        shapes.add(new ShapeGraphic(ShapeGraphic.ShapeType.Rectangle, height, width, xPos, yPos, color, filled, duration));
    }

    public void addOval(int height, int width, int xPos, int yPos, Color color, Boolean filled, int duration) {
        shapes.add(new ShapeGraphic(ShapeGraphic.ShapeType.Oval, height, width, xPos, yPos, color, filled, duration));
    }

    public void clearAll() {
        lines.clear();
        shapes.clear();
    }
}
