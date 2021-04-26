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

    //draws text on JPanel
    public void addText(JPanel panel, int XPos, int YPos, String text, String font, int fontSize, Color colour, int duration)
    {
        JLabel label = new JLabel();    // new JLabel for every time a new text is drawn
        //panel.remove(label);            // removes previous label, so labels aren't stacked on top of each other

        if ((duration == 0 || duration >= currentTime)) //Draw text if duration=0 or duration set is more than the currentTime
        {
            //panel.setOpaque(false);
            label.setBounds(XPos, YPos, fontSize*10, fontSize);    //determines x & y bound positions for text
            label.setFont(new Font(font, Font.PLAIN, fontSize));         //determines font and font size of text
            label.setForeground(colour);                                 //changes text colour
            label.setText(text);                                         //writes text on panel
            panel.add(label);                                            //add JLabel onto JPanel
            System.out.println(" IN: " + label.getText()); //Test
        }
        else
        {
            System.out.println(" OUT: " + label.getText()); //Test -- label does not go through 'else' statement
            //panel.setOpaque(false);
            //label.setOpaque(false);
            //label.setText(".");
            //label.setForeground(Color.LIGHT_GRAY);
            panel.remove(label); //does not work as JLabels do not go in 'else' statement correctly, indicated by the print statements
        }

        //Refresh the JPanel so changes can be seen
        panel.repaint();
        panel.revalidate();
    }
}