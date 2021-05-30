package texthandler;

import javax.swing.JLabel;
import javax.swing.JPanel;
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

    // "\n" in string will add new line
    public static String convertToMultiline(String orig)
    {
        return "<html>" + orig.replaceAll("\n", "<br>");
    }

    //count lines in a string
    private static int countLines(String str)
    {
        String[] lines = str.split("\r\n|\r|\n");
        return  lines.length;
    }

    //draws text on JPanel
    public void addText(JPanel panel, int XPos, int YPos, String text, String font, int fontSize, Color colour, int duration)
    {
        JLabel label = new JLabel();

        panel.remove(label);                                           //remove previous label so labels aren't stacked
        label.setBounds(XPos, YPos, fontSize*text.length(), fontSize*countLines(text));    //determines x & y bound positions for text
        label.setFont(new Font(font, Font.PLAIN, fontSize));           //determines font and font size of text
        label.setForeground(colour);                                   //changes text colour
        label.setText(convertToMultiline(text));                       //writes text on panel \n in string will add new line

        //Draw text if duration=0 or duration set is more than the currentTime
        if ((duration == 0 || duration >= currentTime))
        {
            panel.add(label);                                          //add JLabel onto JPanel
            System.out.println(" IN: " + label.getText()); //Test
        }
        else
        {
            panel.remove(label);
            System.out.println(" OUT: " + label.getText()); //Test
        }
    }
}