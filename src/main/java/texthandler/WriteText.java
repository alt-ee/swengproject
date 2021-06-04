package texthandler;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

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

    // "\n" in string will add new line
    public static String convertToMultiline(String orig)
    {
        return "<html>" + orig.replaceAll("\\\\n|\n", "<br>");
    }

    private static String[] splitLines(String str) {
        String[] lines = str.split("\r\n|\r|\n");
        return lines;
    }

    //draws text on JPanel
    public void addText(JPanel panel, int XPos, int YPos, String text, String font, int fontSize, Color colour, int duration)
    {
        JLabel label = new JLabel();
        Font labelFont = new Font(font, Font.PLAIN, fontSize);
        FontMetrics labelFM = label.getFontMetrics(labelFont);

        System.out.println("Text: " +  text);

        String[] lines = splitLines(text);
        int numLines = lines.length;
        int maxLineWidth = 0;
        int currentLineWidth;

        for (int i = 0; i < numLines; i++) {
            currentLineWidth = labelFM.stringWidth(lines[i]);
            if (currentLineWidth > maxLineWidth) {
                maxLineWidth = currentLineWidth;
            }
        }

        panel.remove(label);                                           //remove previous label so labels aren't stacked

        label.setForeground(colour);                                   //changes text colour
        label.setFont(new Font(font, Font.PLAIN, fontSize));           //determines font and font size of text
        label.setBounds(XPos, YPos, maxLineWidth, labelFM.getHeight() * numLines);    //determines x & y bound positions for text
        label.setVerticalAlignment(JLabel.TOP);
        label.setText(convertToMultiline(text));                       //writes text on panel \n in string will add new line

//        System.out.println(convertToMultiline(text));

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