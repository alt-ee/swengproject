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

    // 4 different JPanels, to test 4 different JLabels
    JPanel panel = new JPanel();         //Test 1st JPanel
    JPanel panel2 = new JPanel();        //Test 2nd JPanel
    JPanel panel3 = new JPanel();        //Test 3rd JPanel
    JPanel panelDuration = new JPanel(); //Test 4th Duration JPanel

    public void textTest()
    {
        //Frame onto which the JPanel will be shown
        frame.setVisible(true);
        frame.setSize(1000, 600);
        frame.setTitle("Text Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); //null for setBounds to work

        /***********************************************************************/

        //1st panel to TEST
        panel.setSize(210, 190);    //set the height and width of JPanel
        panel.setLocation(240, 250);      //set the X-position and Y-position of the JPanel
        frame.add(panel);                       //add JPanel onto JFrame
        panel.setLayout(null);                  //set layout at null to manually input where text is drawn in panel

        //Adding TEXT onto JPanel
        write.addText(panel, 30, 100, "Test 1", "Serif", 40, Color.RED, 5000);
        write.addText(panel, 30, 25, "Test 2", "Serif", 20, Color.RED, 0);
        write.addText(panel, 45, 65, "Test 3", "Serif", 20, Color.RED, 6000);

        /***********************************************************************/
        //2nd panel to TEST
        panel2.setSize(215, 115);
        panel2.setLocation(200, 100);
        frame.add(panel2);
        panel2.setLayout(null);

        //Adding TEXT onto JPanel
        write.addText(panel2, 20, 60, "Test 4", "Calibri", 30, Color.BLACK, 7500);
        write.addText(panel2, 10, 25, "Test 5", "Arial", 20, Color.BLACK, 0);

        /***********************************************************************/
        //3rd panel to TEST
        panel3.setSize(240, 115);
        panel3.setLocation(600, 300);
        frame.add(panel3);
        panel3.setLayout(null);

        //Adding TEXT onto JPanel
        write.addText(panel3, 10, 75, "Test 6", "Arial", 30, Color.BLUE, 0);
        write.addText(panel3, 50, 15, "Test 7", "Calibri", 40, Color.BLUE, 5500);

        /***********************************************************************/
        //4th duration panel to TEST
        panelDuration.setSize(950, 50);
        panelDuration.setLocation(10, 10);
        frame.add(panelDuration);
        panelDuration.setLayout(null);

        //Duration Test, text:"test" should disappear one-by-one
        for (int i = 50; i < 950; i += 50)
        {
            write.addText(panelDuration, i, 20, "test", "Serif", 15, Color.BLACK, i * 10);
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
                //Increment the WriteText's stored time value by the period of the timer
                test.write.incrementCurrentTime(period);
                System.out.println("\nCurrent panel time: " + test.write.getCurrentTime() + " milliseconds");
                test.textTest();
            }
        };

        //Timer to run the action listener every 500 milliseconds (half a second)
        Timer timer = new Timer(period, al);
        timer.start();
    }
}
