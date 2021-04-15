package texthandler;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class TextTest
{
    WriteText write = new WriteText();

    public TextTest()
    {
        //example JFrame to test
        JFrame frame = new JFrame(); //Frame onto which the text will be shown
        frame.setVisible(true);
        frame.setSize(800, 500);
        frame.setTitle("Text Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); //null for setBounds to work

        /*write.addItalicText(50, 375, "test", "Serif", 15, Color.CYAN, 10);
        for (int i = 50; i < 1350; i += 50)      //Duration Test
        {
            write.addItalicText(50+i, 375, "test", "Serif", 15, Color.CYAN, 10);
            write.addLabel(frame);
            frame.revalidate();
        }*/

        //Adding text onto JFrame
        write.addText(400, 200, "Hello", "Serif", 40, Color.RED, 0, 0);
        write.addLabel(frame, 0); //adds label from WriteText Class to this JFrame

        write.addText(300, 250, "hiii", "Serif", 20, Color.ORANGE, 0, 1);
        write.addLabel(frame, 1); //2 JLabels of similar type (addText)

        write.addText(450, 350, "byeeee", "Serif", 20, Color.PINK, 0, 2);
        write.addLabel(frame, 2); //3 JLabels of similar type (addText)

        write.addBoldText(200, 100, "Goodbye", "Calibri", 30, Color.BLACK, 0);
        write.addItalicText(600, 300, "Wait", "Arial", 30, Color.BLUE, 0);
        write.addBoldLabel(frame, 0);
        //write.addItalicText(100, 25000, "nooo", "Arial", 20, Color.BLUE, 0, 1);
        write.addItalicLabel(frame, 0);

        frame.revalidate();
    }

    public static void main(String[] args)
    {
        TextTest test = new TextTest(); //Entry point

        //Example of using an external timer so that text will be displayed only for their specified duration
        int period = 1000;    //How often the timer task will be called in milliseconds

        //Instantiate Timer and TimerTask objects from Java.util
        Timer timer = new Timer();
        TimerTask task = new TimerTask()
        {
            //Specify the behaviour of the timer task utilised by the timer
            @Override
            public void run()
            {
                //Increment the WriteText's stored time value by the period of the timer
                test.write.incrementCurrentTime(period);
                System.out.println("Current time: " + test.write.getCurrentTime() + " milliseconds");
                //Refresh the test JFrame so changes can be seen
                test.write.repaint();
            }
        };

        //Schedule timer to run the timer task with no initial delay every 500 milliseconds (half a second)
        timer.scheduleAtFixedRate(task, 0, period);
    }
}
