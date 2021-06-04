package datastorage;

import java.awt.*;

public class TextButton extends ButtonDataStorage
{
    private final String text;
    private final String font;
    private final int fontsize;
    private final Color fontColour;

    public TextButton(int XPos, int YPos, int width, int height, String id, String text, String font, int fontsize, Color fontColour, Target target){
        super(XPos, YPos, width, height, id, target);
        this.text = text;
        this.font = font;
        this.fontsize = fontsize;
        this.fontColour = fontColour;
    }

    public String getText() {
        return text;
    }

    public String getFont() {
        return font;
    }

    public int getFontsize() {
        return fontsize;
    }

    public Color getFontColour() {
        return fontColour;
    }
}
