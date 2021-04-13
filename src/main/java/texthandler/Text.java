package texthandler;

import java.awt.Color;

public class Text
{
    private int textXPos;
    private int textYPos;
    private String writtenText;
    private String textFont;
    private int textFontSize;
    private Color textColour;
    private int textDuration;

    //Constructor
    public Text(int XPos, int YPos, String text, String font, int fontSize, Color colour, int duration)
    {
        textXPos = XPos;
        textYPos = YPos;
        writtenText = text;
        textFont = font;
        textFontSize = fontSize;
        textColour = colour;
        textDuration = duration;
    }

    //setter
    public void setWrittenText(String text) { this.writtenText = text; }

    //append text
    public void appendText(String newWrittenText) { writtenText += newWrittenText; } //append new text to existing text
    public void appendBoldText(String newWrittenText) { writtenText += "<b>" + newWrittenText+ "</b>"; } //append new BOLD text to existing text
    public void appendItalicText(String newWrittenText) { writtenText += "<i>" + newWrittenText + "</i>"; } //append new ITALIC text to existing text


    //Getters for required parameters
    public int getTextXPos() { return textXPos; } //Returns X-position of text

    public int getTextYPos() { return textYPos; } //Returns Y-position of text

    public String getWrittenText() { return writtenText; } //Returns written text as String

    public String getTextFont() { return textFont; } //Returns the text font as String

    public int getTextFontSize() { return textFontSize; } //Returns font size of text

    public Color getTextColour() { return textColour; } //Returns colour of text

    public int getTextDuration() { return textDuration; } //Returns the duration text will be present in seconds
}
