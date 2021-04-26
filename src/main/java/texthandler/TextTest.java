package texthandler;

import javax.swing.*;
import java.awt.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextTest
{
    WriteText write = new WriteText();
    JFrame frame = new JFrame();         //example JFrame to test
    JPanel panel = new JPanel();         //Test JPanel

    public TextTest()
    {
        //Frame onto which the JPanel will be shown
        frame.setVisible(true);
        frame.setSize(1000, 600);
        frame.setTitle("Text Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); //null for setBounds to work

        //panel to TEST
        panel.setSize(900, 500);    //set the height and width of JPanel
        panel.setLocation(50, 50);        //set the X-position and Y-position of the JPanel
        frame.add(panel);                       //add JPanel onto JFrame
        panel.setLayout(null);                  //set layout at null to manually input where text is drawn in panel
        panel.setBackground(Color.PINK);        //Test background colour
        //panel.setOpaque(false);
    }

    public void demoTest()
    {
        //Adding TEXT onto JPanel
        write.addText(panel, 300, 100, "Text 1", "Serif", 40, Color.RED, 4500);
        write.addText(panel, 300, 200, "Text 2", "Serif", 20, Color.RED, 6500);
        write.addText(panel, 150, 300, "Text 3", "Serif", 20, Color.RED, 8500);
        write.addText(panel, 100, 170, "Text 4", "Arial", 30, Color.BLUE, 10500);

        write.addText(panel, 200, 150, "Text 5", "Calibri", 30, Color.BLACK, 0);
        write.addText(panel, 100, 350, "Text 6", "Arial", 20, Color.BLACK, 0);
        write.addText(panel, 500, 150, "Text 7", "Calibri", 40, Color.BLUE, 0);

        //Duration Test, text:"test" should disappear one-by-one
        for (int i = 50; i < 950; i += 50)
        {
            write.addText(panel, i, 30, "test", "Serif", 15, Color.BLACK, i * 10);
            //duration: 500, 1000, 1500, 2000, 2500.....
        }
    }

    public static void main(String[] args)
    {
        TextTest test = new TextTest(); //Entry point

        //Example of using an external timer so that text will be displayed only for its specified duration
        int period = 500;    //How often the timer task will be called in milliseconds

        ActionListener al = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                test.write.incrementCurrentTime(period);
                //Increment the WriteText's stored time value by the period of the timer
                System.out.println("\nCurrent panel time: " + test.write.getCurrentTime() + " milliseconds");
                test.demoTest();
            }
        };

        //Timer to run the action listener every 500 milliseconds (half a second)
        Timer timer = new Timer(period, al);
        timer.start();
    }
}
