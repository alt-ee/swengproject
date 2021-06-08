package texthandler;

import javax.swing.*;
import java.awt.*;

public class WriteText
{
    //The current time in milliseconds that an external timer has been running for
    int currentTime = 0;

    public int getCurrentTime()
    {
        return currentTime;
    }

    public void incrementCurrentTime(int increment)
    {
        currentTime += increment;
    }

    // "\\\\n" or "\n" in string will add new line
    public static String convertToMultiline(String orig) {
        return "<html>" + orig.replaceAll("\\\\n|\n", "<br>");
    }

    private static String[] splitLines(String str) {
        return str.split("\r\n|\r|\n|\\\\n");
    }

    //draws text on JPanel
    public void addText(JPanel panel, int XPos, int YPos, String text, String font, int fontSize, Color colour, int duration)
    {
        JLabel label = new JLabel();
        Font labelFont = new Font(font, Font.PLAIN, fontSize);
        FontMetrics labelFM = label.getFontMetrics(labelFont);  //Get the font metrics for the specified font in the label graphical rendering context

        String[] lines = splitLines(text);
        int numLines = lines.length;
        int maxLineWidth = 0;
        int currentLineWidth;

        //Determine the line with the longest width based on the string, use this to set the width of label bounds
        for (String line : lines) {
            currentLineWidth = labelFM.stringWidth(line);
            if (currentLineWidth > maxLineWidth) {
                maxLineWidth = currentLineWidth;
            }
        }

        panel.remove(label);                                           //remove previous label so labels aren't stacked

        label.setForeground(colour);                                   //changes text colour
        label.setFont(new Font(font, Font.PLAIN, fontSize));           //determines font and font size of text
        label.setBounds(XPos, YPos, maxLineWidth, labelFM.getHeight() * numLines);    //determines x & y bound positions for text
//        System.out.println("Font metric height: " + labelFM.getHeight());
//        System.out.println("Lines in text: " + numLines);
//        System.out.println("Label has height: " + label.getHeight() + " and width: " + label.getWidth());
        label.setVerticalAlignment(JLabel.TOP);
        label.setText(convertToMultiline(text));                       //writes text on panel \n or \\\\n in string will add new line

        //Draw text if duration=0 or duration set is more than the currentTime
        if ((duration == 0 || duration >= currentTime))
        {
            panel.add(label);                                          //add JLabel onto JPanel
//            System.out.println(" IN: " + label.getText()); //Test
        }
        else
        {
            panel.remove(label);
//            System.out.println(" OUT: " + label.getText()); //Test
        }
    }
}