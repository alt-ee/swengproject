package texthandler;

import java.awt.*;
import javax.swing.*;

public class WriteText extends JPanel
{
    //JLabels as arrays, so multiple labels of the same type (e.g. Italic) are all added to screen
    JLabel[] label = new JLabel[20];
    JLabel[] labelBold = new JLabel[20];
    JLabel[] labelItalic = new JLabel[20];

    //The current time in milliseconds that an external timer has been running for
    int currentTime = 0;
    public int getCurrentTime()
    {
        return currentTime;
    }
    public void setCurrentTime(int updatedCurrentTime)
    {
        currentTime = updatedCurrentTime;
    }
    public void incrementCurrentTime(int increment)
    {
        currentTime += increment;
    }

    //draws PLAIN text on frame
    public void addText(int XPos, int YPos, String text, String font, int fontSize, Color colour, int duration)
    {
        if (duration == 0 || duration >= currentTime) //Draw text if duration=0 or duration set is more than the currentTime
        {
            int i;
            for (i =0; i < 20; i++)
            {
                label[i] = new JLabel(); //create new JLabels for each text added
                label[i].setBounds(XPos, YPos, fontSize * 10, fontSize); //determines x & y bound positions for text
                label[i].setFont(new Font(font, Font.PLAIN, fontSize)); //determines font and font size of text
                label[i].setForeground(colour); //changes text colour
                label[i].setText(text); //writes text on frame
            }
        }
    }

    //draws BOLD text on frame
    public void addBoldText(int XPos, int YPos, String text, String font, int fontSize, Color colour, int duration)
    {
        if (duration == 0 || duration >= currentTime)
        {
            int i;
            for (i=0; i < 20; i++)
            {
                labelBold[i] = new JLabel();
                labelBold[i].setBounds(XPos, YPos, fontSize * 10, fontSize);
                labelBold[i].setFont(new Font(font, Font.BOLD, fontSize));
                labelBold[i].setForeground(colour);
                labelBold[i].setText(text);
            }
        }
    }

    //draws ITALIC text on frame
    public void addItalicText(int XPos, int YPos, String text, String font, int fontSize, Color colour, int duration)
    {
        if (duration == 0 || duration >= currentTime)
        {
            int i;
            for (i=0; i < 20; i++)
            {
                labelItalic[i] = new JLabel();
                labelItalic[i].setBounds(XPos, YPos, fontSize * 10, fontSize);
                labelItalic[i].setFont(new Font(font, Font.ITALIC, fontSize));
                labelItalic[i].setForeground(colour);
                labelItalic[i].setText(text);
            }
        }
    }

    //create labels to add to main JFrame
    public void addLabel(JFrame frame)
    {
        int i = 0;
        frame.add(label[i]);
    }

    public void addBoldLabel(JFrame frame)
    {
        int i = 0;
        frame.add(labelBold[i]);
    }
    public void addItalicLabel(JFrame frame)
    {
        int i = 0;
        frame.add(labelItalic[i]);
    }

}
