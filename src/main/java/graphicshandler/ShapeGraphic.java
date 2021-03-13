package graphicshandler;

import java.awt.*;

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

    //Constructor
    public ShapeGraphic(ShapeType type, int height, int width, int xPos, int yPos, String hexColor, Boolean filled, int duration) {
        shapeType = type;
        shapeHeight = height;
        shapeWidth = width;
        shapeXPos = xPos;
        shapeYPos = yPos;
        shapeColor = Color.decode(hexColor);    //Decodes hexadecimal colour value so it can be used by the Graphics library
        shapeFilled = filled;
        shapeDuration = duration;
    }

    //Getters for required parameters
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
