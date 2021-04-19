package texthandler;

import java.awt.*;
import javax.swing.*;

public class WriteText
{
    //JLabels as arrays, so multiple labels of the same type (e.g. Italic) are all added to JPanel
    JLabel[] label = new JLabel[20];
    JLabel[] labelBold = new JLabel[20];
    JLabel[] labelItalic = new JLabel[20];

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

    //draws PLAIN text on frame
    public void addText(JPanel panel, int XPos, int YPos, String text, String font, int fontSize, Color colour, int duration)
    {
        //Draw text if duration=0 or duration set is more than the currentTime
        if (duration == 0 || duration >= currentTime)
        {
            for (int i = 0; i < 20; i++)
            {
                panel.setBackground(Color.PINK);                                //make JPanel Yellow for identification during TESTING
                label[i] = new JLabel();                                        //create new JLabels for each text added
                label[i].setBounds(XPos, YPos, fontSize * 10, fontSize);  //determines x & y bound positions for text
                label[i].setFont(new Font(font, Font.PLAIN, fontSize));         //determines font and font size of text
                label[i].setForeground(colour);                                 //changes text colour
                label[i].setText(text);                                         //writes text on panel
                panel.add(label[i]);                                            //add JLabel onto JPanel
            }
        }
    }

    //draws BOLD text on frame
    public void addBoldText(JPanel panel, int XPos, int YPos, String text, String font, int fontSize, Color colour, int duration)
    {
        if (duration == 0 || duration >= currentTime)
        {
            for (int i = 0; i < 20; i++)
            {
                panel.setBackground(Color.PINK);
                labelBold[i] = new JLabel();
                labelBold[i].setBounds(XPos, YPos, fontSize * 10, fontSize);
                labelBold[i].setFont(new Font(font, Font.BOLD, fontSize));
                labelBold[i].setForeground(colour);
                labelBold[i].setText(text);
                panel.add(labelBold[i]);
            }
        }
    }

    //draws ITALIC text on frame
    public void addItalicText(JPanel panel, int XPos, int YPos, String text, String font, int fontSize, Color colour, int duration)
    {
        if (duration == 0 || duration >= currentTime)
        {
            for (int i = 0; i < 20; i++)
            {
                panel.setBackground(Color.PINK);
                labelItalic[i] = new JLabel();
                labelItalic[i].setBounds(XPos, YPos, fontSize * 10, fontSize);
                labelItalic[i].setFont(new Font(font, Font.ITALIC, fontSize));
                labelItalic[i].setForeground(colour);
                labelItalic[i].setText(text);
                panel.add(labelItalic[i]);
            }
        }
    }

    //create labels to add to JPanels
    //public void addLabel(JPanel panel)
    {
        //panel.add(label[i]);
    }

    //public void addBoldLabel(JPanel panel)
    {
        //panel.add(labelBold[i]);
    }
    //public void addItalicLabel(JPanel panel)
    {
        //panel.add(labelItalic[i]);
    }
}