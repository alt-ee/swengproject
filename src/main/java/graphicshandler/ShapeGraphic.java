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
    private GradientPaint shapeShading;

    //Constructor
    public ShapeGraphic(ShapeType type, int height, int width, int xPos, int yPos, String hexColor, Boolean filled, int duration) {
        shapeType = type;
        shapeHeight = height;
        shapeWidth = width;
        shapeXPos = xPos;
        shapeYPos = yPos;
        shapeColor = Color.decode(hexColor);    //Decodes hexadecimal colour value so it can be used by the Graphics library
        shapeFilled = filled;
        shapeShading = null;                    //By default, shape will have no shading applied. Use addShadedShape(...) function in GraphicsPanel to add shading
        shapeDuration = duration;
    }

    public ShapeGraphic(ShapeType type, int height, int width, int xPos, int yPos,  int shadeX1, int shadeY1, String hexShadeColor1, int shadeX2, int shadeY2, String hexShadeColor2, Boolean cyclic, int duration) {
        shapeType = type;
        shapeHeight = height;
        shapeWidth = width;
        shapeXPos = xPos;
        shapeYPos = yPos;
        shapeFilled = true;                    //If shaded with a gradient, filled parameter is true
        shapeShading = new GradientPaint(shadeX1, shadeY1, Color.decode(hexShadeColor1), shadeX2, shadeY2, Color.decode(hexShadeColor2), cyclic);  //Creates a new ShadingGraphic object with the defined parameters
        shapeDuration = duration;
    }

    //Getters for required parameters
    public int getShapeHeight() {
        return shapeHeight;
    }   //Returns the shape's height

    public int getShapeWidth() {
        return shapeWidth;
    }   //Returns the shape's width

    public int getShapeXPos() {
        return shapeXPos;
    }   //Returns the shape's x-position

    public int getShapeYPos() {
        return shapeYPos;
    }   //Returns the shape's y-position

    public Boolean isFilled() {
        return shapeFilled;
    }   //Returns true is shape is filled, false otherwise

    public Color getShapeColor() { return shapeColor; }     //Returns shape color as a Color object (you may need to convert this back into hex code)

    public GradientPaint getShapeShading() { return shapeShading; }    //Returns the shape's GradientPaint if one has been specified, null otherwise

    public int getShapeDuration() { return shapeDuration; }   //Retunrs the duration the shape will be present in seconds

    public ShapeType getShapeType() {
        return shapeType;
    }   //Returns whether the shape is a Rectangle or an Oval
}
