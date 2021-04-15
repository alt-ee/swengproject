package texthandler;

import java.awt.*;
import javax.swing.*;

public class WriteText extends JPanel
{
    JLabel[] label = new JLabel[15];
    JLabel labelBold = new JLabel();
    JLabel labelItalic = new JLabel();

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
    public void addText(int XPos, int YPos, String text, String font, int fontSize, Color colour, int duration, int i)
    {
        if (duration == 0 || duration >= currentTime) //Draw text if duration=0 or duration set is more than the currentTime
        {
            for (i=0; i < 15; i++)
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

                labelBold.setBounds(XPos, YPos, fontSize * 10, fontSize);
                labelBold.setFont(new Font(font, Font.BOLD, fontSize));
                labelBold.setForeground(colour);
                labelBold.setText(text);

        }
    }

    //draws ITALIC text on frame
    public void addItalicText(int XPos, int YPos, String text, String font, int fontSize, Color colour, int duration)
    {
        if (duration == 0 || duration >= currentTime)
        {

                labelItalic.setBounds(XPos, YPos, fontSize * 10, fontSize);
                labelItalic.setFont(new Font(font, Font.ITALIC, fontSize));
                labelItalic.setForeground(colour);
                labelItalic.setText(text);

        }
    }

    //create label to add to main JFrame
    public void addLabel(JFrame frame, int i)
    {
        frame.add(label[i]);
    }

    public void addBoldLabel(JFrame frame, int i)
    {
        frame.add(labelBold);
    }
    public void addItalicLabel(JFrame frame, int i)
    {
        frame.add(labelItalic);
    }

}
