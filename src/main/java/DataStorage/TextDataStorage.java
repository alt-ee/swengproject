package DataStorage;

import java.awt.Color;

public class TextDataStorage
{
    private final int XPos;
    private final int YPos;
    private String text;
    private final String font;
    private final int fontSize;
    private final Color colour;


    public TextDataStorage(int XPos, int YPos, String text, String font, int fontSize, Color colour)
    {
        this.XPos = XPos;
        this.YPos = YPos;
        this.text = text;
        this.font = font;
        this.fontSize = fontSize;
        this.colour = colour;
    }

    //setter
    public void setText(String text)
    {
        this.text = text;
    }

    //append-ers
    public String appendText(String newText)
    {
        text += newText;
        return text;
    }
    public String appendBoldText(String newText)
    {
        text += "<b>" + newText + "</b>";
        return text;
    }
    public String appendItalicText(String newText)
    {
        text += "<i>" + newText + "</i>";
        return text;
    }

    //getters
    public int getXPos() {
        return XPos;
    }

    public int getYPos() {
        return YPos;
    }

    public String getText() {
        return text;
    }

    public String getFont() {
        return font;
    }

    public int getFontSize() {
        return fontSize;
    }

    public Color getColour() {
        return colour;
    }
}
