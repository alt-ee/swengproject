package graphicshandler;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

public class GraphicsPanel extends JPanel {
    enum GradientHints {
        VERTICAL,
        HORIZONTAL,
        DIAGONAL_DOWN,
        DIAGONAL_UP
    }

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
            if (shape.getShapeShading() == null) {
                g2d.setColor(shape.getShapeColor());
            }
            else {
                g2d.setPaint(shape.getShapeShading());
            }

            //Check if shape is rectangle or oval, then check if the shape should be filled/unfilled and draw appropriately
            switch (shape.getShapeType()) {
                case Rectangle:
                    if (shape.isFilled())
                        g2d.fillRect(shape.getShapeXPos(), shape.getShapeYPos(), shape.getShapeWidth(), shape.getShapeHeight());
                    else
                        g2d.drawRect(shape.getShapeXPos(), shape.getShapeYPos(), shape.getShapeWidth(), shape.getShapeHeight());
                    break;
                case Oval:
                    if (shape.isFilled())
                        g2d.fillOval(shape.getShapeXPos(), shape.getShapeYPos(), shape.getShapeWidth(), shape.getShapeHeight());
                    else
                        g2d.drawOval(shape.getShapeXPos(), shape.getShapeYPos(), shape.getShapeWidth(), shape.getShapeHeight());
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

    //Add a rectangle with the specified shading via a GradientPaint object
    //Top-left corner at (x1, y1) of specified hexadecimal colour, height and width
    //Parameters shadeXN and shadeYN determine the global coordinates of each hexShadeColorN
    //Align these coordinates with the shape's edges/corners to better see the gradient pattern
    public void addShadedRect(int height, int width, int xPos, int yPos, int shadeX1, int shadeY1, String hexShadeColor1, int shadeX2, int shadeY2, String hexShadeColor2, Boolean cyclic, int duration) {
        shapes.add(new ShapeGraphic(ShapeGraphic.ShapeType.Rectangle, height, width, xPos, yPos, shadeX1, shadeY1, hexShadeColor1, shadeX2, shadeY2, hexShadeColor2, cyclic, duration));
    }


    public void addShadedRect(int height, int width, int xPos, int yPos, String hexShadeColor1, String hexShadeColor2, GradientHints gradientHint, int duration) {
        int shadeX1;
        int shadeY1;
        int shadeX2;
        int shadeY2;

        switch (gradientHint) {
            case HORIZONTAL:
            default:    //By default, shade as if horizontal
                shadeX1 = xPos;
                shadeY1 = 0;
                shadeX2 = xPos + width;
                shadeY2 = 0;
                break;
            case VERTICAL:
                shadeX1 = 0;
                shadeY1 = yPos;
                shadeX2 = 0;
                shadeY2 = yPos + height;
                break;
            case DIAGONAL_DOWN:
                shadeX1 = xPos;
                shadeY1 = yPos;
                shadeX2 = xPos + width;
                shadeY2 = yPos + height;
                break;
            case DIAGONAL_UP:
                shadeX1 = xPos;
                shadeY1 = yPos + height;
                shadeX2 = xPos + width;
                shadeY2 = yPos;
                break;
        }

        shapes.add(new ShapeGraphic(ShapeGraphic.ShapeType.Rectangle, height, width, xPos, yPos, shadeX1, shadeY1, hexShadeColor1, shadeX2, shadeY2, hexShadeColor2, false, duration));
    }

    //Add an oval to the shape array
    //Top-left corner at (x1, y1) of specified hexadecimal colour, height and width
    //If filled is true, the rectangle will be drawn filled
    public void addOval(int height, int width, int xPos, int yPos, String hexColor, Boolean filled, int duration) {
        shapes.add(new ShapeGraphic(ShapeGraphic.ShapeType.Oval, height, width, xPos, yPos, hexColor, filled, duration));
    }

    //Add an oval with the specified shading via a GradientPaint object
    //Top-left corner at (x1, y1) of specified hexadecimal colour, height and width
    //Parameters shadeXN and shadeYN determine the global coordinates of each hexShadeColorN
    //Align these coordinates with the shape's edges/corners to better see the gradient pattern
    public void addShadedOval(int height, int width, int xPos, int yPos, int shadeX1, int shadeY1, String hexShadeColor1, int shadeX2, int shadeY2, String hexShadeColor2, Boolean cyclic, int duration) {
        shapes.add(new ShapeGraphic(ShapeGraphic.ShapeType.Oval, height, width, xPos, yPos, shadeX1, shadeY1, hexShadeColor1, shadeX2, shadeY2, hexShadeColor2, cyclic, duration));
    }

    public void addShadedOval(int height, int width, int xPos, int yPos, String hexShadeColor1, String hexShadeColor2, GradientHints gradientHint, int duration) {
        int shadeX1;
        int shadeY1;
        int shadeX2;
        int shadeY2;

        switch (gradientHint) {
            case HORIZONTAL:
            default:    //By default, shade as if horizontal
                shadeX1 = xPos;
                shadeY1 = 0;
                shadeX2 = xPos + width;
                shadeY2 = 0;
                break;
            case VERTICAL:
                shadeX1 = 0;
                shadeY1 = yPos;
                shadeX2 = 0;
                shadeY2 = yPos + height;
                break;
            case DIAGONAL_DOWN:
                shadeX1 = xPos;
                shadeY1 = yPos;
                shadeX2 = xPos + width;
                shadeY2 = yPos + height;
                break;
            case DIAGONAL_UP:
                shadeX1 = xPos;
                shadeY1 = yPos + height;
                shadeX2 = xPos + width;
                shadeY2 = yPos;
                break;
        }

        shapes.add(new ShapeGraphic(ShapeGraphic.ShapeType.Oval, height, width, xPos, yPos, shadeX1, shadeY1, hexShadeColor1, shadeX2, shadeY2, hexShadeColor2, false, duration));
    }

    //Clear all lines and shapes from the arrays
    public void clearAll() {
        lines.clear();
        shapes.clear();
    }
}
