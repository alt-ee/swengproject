package graphicshandler;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

public class GraphicsPanel extends JPanel {
    //Create array lists to store line and shape data
    ArrayList<LineGraphic> lines = new ArrayList<>();
    ArrayList<ShapeGraphic> shapes = new ArrayList<>();

    //Override JPanel's paintComponent method
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);   //Draw smoother lines

        //Iterate through and draw every line in line array
        for (LineGraphic line : lines) {
            g2d.setColor(line.getLineColor());
            g2d.draw(line.getLine());
        }

        //Iterate through and draw every shape in shape array
        for (ShapeGraphic shape : shapes) {
            g2d.setColor(shape.getShapeColor());

            //Check if shape is rectangle or oval, then check if the shape should be filled/unfilled and draw appropriately
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

    //Add a line to the line array
    //Starts at (x1, y1), ends at (x2, y2) of the specified hexadecimal colour
    public void addLine(int x1, int y1, int x2, int y2, String hexColor, int duration) {
        lines.add(new LineGraphic(x1, y1, x2, y2, hexColor, duration));
    }

    //Add a rectangle to the shape array
    //Top-left corner at (x1, y1) of specified hexadecimal colour, height and width
    //If filled is true, the rectangle will be drawn filled
    public void addRect(int height, int width, int xPos, int yPos, String hexColor, Boolean filled, int duration) {
        shapes.add(new ShapeGraphic(ShapeGraphic.ShapeType.Rectangle, height, width, xPos, yPos, hexColor, filled, duration));
    }

    //Add an oval to the shape array
    //Top-left corner at (x1, y1) of specified hexadecimal colour, height and width
    //If filled is true, the rectangle will be drawn filled
    public void addOval(int height, int width, int xPos, int yPos, String hexColor, Boolean filled, int duration) {
        shapes.add(new ShapeGraphic(ShapeGraphic.ShapeType.Oval, height, width, xPos, yPos, hexColor, filled, duration));
    }

    //Clear all lines and shapes from the arrays
    public void clearAll() {
        lines.clear();
        shapes.clear();
    }
}
