package datastorage;

import java.awt.Color;
import java.util.Objects;

public class TextDataStorage
{
    private final int XPos;
    private final int YPos;
    private String text;
    private final String font;
    private final int fontSize;
    private final Color colour;
    private final int duration;


    public TextDataStorage(int XPos, int YPos, String text, String font, int fontSize, Color colour, int duration)
    {
        this.XPos = XPos;
        this.YPos = YPos;
        this.text = text;
        this.font = font;
        this.fontSize = fontSize;
        this.colour = colour;
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextDataStorage that = (TextDataStorage) o;
        return XPos == that.XPos &&
                YPos == that.YPos &&
                fontSize == that.fontSize &&
                duration == that.duration &&
                text.equals(that.text) &&
                font.equals(that.font) &&
                colour.equals(that.colour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(XPos, YPos, text, font, fontSize, colour, duration);
    }

    //setter
    public void setText(String text) { this.text = text; }

    //append-ers
    public void appendText(String newText)
    {
        text += newText;
    }
    public void appendBoldText(String newText)
    {
        text += "<b>" + newText + "</b>";
    }
    public void appendItalicText(String newText)
    {
        text += "<i>" + newText + "</i>";
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
