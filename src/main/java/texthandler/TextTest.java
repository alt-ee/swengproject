package texthandler;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class TextTest
{
    WriteText write = new WriteText();
    JFrame frame = new JFrame();

    // Make 4 different JPanels, to test 4 different JLabel on
    JPanel panel = new JPanel();         //Test 1st JPanel
    JPanel panel2 = new JPanel();        //Test 2nd JPanel
    JPanel panel3 = new JPanel();        //Test 3rd JPanel
    JPanel panelDuration = new JPanel(); //Test 4th Duration JPanel

    public TextTest()
    {
        //example JFrame to test
        //Frame onto which the JPanel will be shown
        frame.setVisible(true);
        frame.setSize(1000, 700);
        frame.setTitle("Text Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); //null for setBounds to work

        /***********************************************************************/
        //making 1st panel to TEST
        panel.setSize(210, 190);
        panel.setLocation(240, 250);
        frame.add(panel);       //add JPanel onto JFrame
        panel.setLayout(null);  //null for JLabel to be at specific position on JPanel

        //Adding text onto JPanel
        //PLAIN TEXT
        write.addText(panel, 30, 100, "PlainText", "Serif", 40, Color.RED, 0);
        write.addLabel(panel);  //add JLabel onto JPanel

        write.addText(panel, 30, 25, "Hello", "Serif", 20, Color.RED, 0);
        write.addLabel(panel);

        write.addText(panel, 45, 65, "plain text again", "Serif", 20, Color.RED, 0);
        write.addLabel(panel); //3 JLabels of similar type (addText)

        /***********************************************************************/
        //making 2nd panel to TEST
        panel2.setSize(215, 115);
        panel2.setLocation(200, 100);
        frame.add(panel2);
        panel2.setLayout(null);

        //BOLD TEXT
        write.addBoldText(panel2, 20, 60, "Bold Text", "Calibri", 30, Color.BLACK, 0);
        write.addBoldLabel(panel2);

        write.addBoldText(panel2, 10, 25, "bold test", "Arial", 20, Color.BLACK, 0);
        write.addBoldLabel(panel2); //2 JLabels of similar type (addBoldText)

        /***********************************************************************/
        //making 3rd panel to TEST
        panel3.setSize(240, 115);
        panel3.setLocation(600, 300);
        frame.add(panel3);
        panel3.setLayout(null);

        //ITALIC TEXT
        write.addItalicText(panel3, 10, 75, "Italic Text", "Arial", 30, Color.BLUE, 0);
        write.addItalicLabel(panel3);

        write.addItalicText(panel3, 50, 15, "test italic", "Calibri", 40, Color.BLUE, 0);
        write.addItalicLabel(panel3); //2 JLabels of similar type (addItalicText)

        /***********************************************************************/
        //making 4th duration panel to TEST
        panelDuration.setSize(950, 50);
        panelDuration.setLocation(10, 10);
        frame.add(panelDuration);
        panelDuration.setLayout(null);

        for (int j = 50; j < 950; j += 50)      //Duration Test, text:"test" should disappear one-by-one
        {
            write.addItalicText(panelDuration, j, 20, "test", "Serif", 15, Color.BLACK, j*10);
            write.addItalicLabel(panelDuration);
        }

        //Example of using an external timer so that text will be displayed only for their specified duration
        int period = 500;    //How often the timer task will be called in milliseconds

        //Instantiate Timer and TimerTask objects from Java.util
        Timer timer = new Timer();
        TimerTask task = new TimerTask()
        {
            //Specify the behaviour of the timer task utilised by the timer
            @Override
            public void run()
            {
                //Increment the WriteText's stored time value by the period of the timer
                write.incrementCurrentTime(period);
                System.out.println("Current panel time: " + write.getCurrentTime() + " milliseconds");
                //Refresh the test JPanel so changes can be seen
                panelDuration.repaint();
                panelDuration.revalidate();
            }
        };

        //Schedule timer to run the timer task with no initial delay every 500 milliseconds (half a second)
        timer.scheduleAtFixedRate(task, 0, period);

        //panel.revalidate();
    }

    public static void main(String[] args)
    {
        new TextTest(); //Entry point
    }
}
