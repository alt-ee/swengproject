package graphicshandler;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class ShapeGraphic {
    enum ShapeType {
        Rectangle,
        Oval
    }

    private int shapeHeight;
    private int shapeWidth;
    private int shapeXPos;
    private int shapeYPos;
    private Color shapeColor;
    private int shapeDuration;
    private ShapeType shapeType;
    private Boolean shapeFilled;

    public ShapeGraphic(ShapeType type, int height, int width, int xPos, int yPos, Color color, Boolean filled, int duration) {
        shapeType = type;
        shapeHeight = height;
        shapeWidth = width;
        shapeXPos = xPos;
        shapeYPos = yPos;
        shapeColor = color;
        shapeFilled = filled;
        shapeDuration = duration;
    }

    public int getShapeHeight() {
        return shapeHeight;
    }

    public int getShapeWidth() {
        return shapeWidth;
    }

    public int getShapeXPos() {
        return shapeXPos;
    }

    public int getShapeYPos() {
        return shapeYPos;
    }

    public Boolean isFilled() {
        return shapeFilled;
    }

    public Color getShapeColor() {
        return shapeColor;
    }

    public int getShapeDuration() {
        return shapeDuration;
    }

    public ShapeType getShapeType() {
        return shapeType;
    }
}
//
//class RectGraphic extends ShapeGraphic {
//    public RectGraphic(int height, int width, int xPos, int yPos, Color color, Boolean filled, int duration) {
//        shape = new Rectangle2D.Double(height, width, xPos, yPos);
//        shapeColor = color;
//        shapeDuration = duration;
//        shapeType = ShapeType.Rectangle;
//        shapeFilled = filled;
//    }
//}
//
//class OvalGraphic extends ShapeGraphic {
//    public OvalGraphic(int height, int width, int xPos, int yPos, Color color, Boolean filled, int duration) {
//        shape = new Ellipse2D.Double(height, width, xPos, yPos);
//        shapeColor = color;
//        shapeDuration = duration;
//        shapeType = ShapeType.Oval;
//        shapeFilled = filled;
//    }
//}
