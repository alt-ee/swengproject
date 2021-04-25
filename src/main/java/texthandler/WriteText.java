package texthandler;

import javax.swing.*;
import java.awt.*;

public class WriteText
{
    JLabel label = new JLabel();

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
            //System.out.print(" IN"); //Test
            //panel.setBackground(Color.YELLOW);                           //make JPanel Pink for identification during TESTING
            label = new JLabel();                                        //create new JLabels for each text added
            label.setBounds(XPos, YPos, fontSize * 10, fontSize);  //determines x & y bound positions for text
            label.setFont(new Font(font, Font.PLAIN, fontSize));         //determines font and font size of text
            label.setForeground(colour);                                 //changes text colour
            label.setText(text);                                         //writes text on panel
            panel.add(label);                                            //add JLabel onto JPanel
        }
        else
        {
            //System.out.print(" OUT"); //Test
            panel.setBackground(Color.PINK);
            panel.remove(label);
            //label.setVisible(false);
            //label.setForeground(Color.CYAN);
        }

        //Refresh the JPanel so changes can be seen
        panel.repaint();
        panel.revalidate();
    }
}