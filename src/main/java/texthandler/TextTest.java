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
        frame.setSize(1000, 500);
        frame.setTitle("Text Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); //null for setBounds to work

        /*
         * Writing text to the screen:
         * write.addBoldText(XPos, YPos, text, font, fontSize, color, duration, i)
         * write.addBoldLabel(frame, i)
         *
         * 'i' must be the same for both "write.addBoldText()" and "write.addBoldLabel()"
         * as each text and label correspond to an integer
         * integer 'i' starts from '0' for plain/bold/italic text
         */

        //Adding text onto JFrame
        //PLAIN TEXT
        write.addText(400, 200, "Hello", "Serif", 40, Color.RED, 10);
        write.addLabel(frame); //adds label from WriteText Class to this JFrame

        write.addText(300, 250, "hiii", "Serif", 20, Color.RED, 0);
        write.addLabel(frame);

        write.addText(450, 350, "byeeee", "Serif", 20, Color.RED, 0);
        write.addLabel(frame); //3 JLabels of similar type (addText)

        //BOLD TEXT
        write.addBoldText(200, 100, "Goodbye", "Calibri", 30, Color.BLACK, 0);
        write.addBoldLabel(frame);

        write.addBoldText(100, 250, "nooo", "Arial", 20, Color.BLACK, 0);
        write.addBoldLabel(frame); //2 JLabels of similar type (addBoldText)

        //ITALIC TEXT
        write.addItalicText(600, 300, "Wait", "Arial", 30, Color.BLUE, 0);
        write.addItalicLabel(frame);

        write.addItalicText(100, 150, "yeesss", "Calibri", 40, Color.BLUE, 0);
        write.addItalicLabel(frame); //2 JLabels of similar type (addItalicText)


        for (int j = 50; j < 950; j += 50)      //Duration Test, text:"test" should disappear one-by-one
        {
            write.addItalicText( j, 20, "test", "Serif", 15, Color.ORANGE, j*10);
            write.addItalicLabel(frame);
        }

        frame.revalidate();
    }

    public static void main(String[] args)
    {
        TextTest test = new TextTest(); //Entry point

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
                test.write.incrementCurrentTime(period);
                System.out.println("Current time: " + test.write.getCurrentTime() + " milliseconds");
                //Refresh the test JFrame so changes can be seen
                //test.write.repaint();
                test.write.revalidate();
            }
        };

        //Schedule timer to run the timer task with no initial delay every 500 milliseconds (half a second)
        timer.scheduleAtFixedRate(task, 0, period);
    }
}
