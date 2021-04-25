package texthandler;

import javax.swing.*;
import java.awt.*;

public class WriteText
{
    //JLabels as array, so multiple labels of the same type are all added to JPanel
    JLabel[] label = new JLabel[20];

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

    //draws text on JPanel
    public void addText(JPanel panel, int XPos, int YPos, String text, String font, int fontSize, Color colour, int duration)
    {
        //Draw text if duration=0 or duration set is more than the currentTime
        if (duration == 0 || duration >= currentTime)
        {
            for (int i = 0; i < 20; i++)
            {
                //panel.setBackground(Color.PINK);                                //make JPanel Pink for identification during TESTING
                label[i] = new JLabel();                                        //create new JLabels for each text added
                label[i].setBounds(XPos, YPos, fontSize * 10, fontSize);  //determines x & y bound positions for text
                label[i].setFont(new Font(font, Font.PLAIN, fontSize));         //determines font and font size of text
                label[i].setForeground(colour);                                 //changes text colour
                label[i].setText(text);                                         //writes text on panel
                panel.add(label[i]);                                            //add JLabel onto JPanel
            }
        }
    }
}