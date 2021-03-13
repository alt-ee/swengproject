package graphicshandler;

import javax.swing.*;
import java.awt.*;

public class GraphicsTest {
    //Create a GraphicsPanel object where all graphics will be drawn on
    GraphicsPanel gp = new GraphicsPanel();

    public GraphicsTest() {
        JFrame f = new JFrame();                            //Create new JFrame, acting as the main window
        f.setVisible(true);                                 //Set the frame to visible
        f.setSize(1600, 900);                   //Set size to 1600x900 pix
        f.setTitle("Graphics Test");                        //Set frame title to "Graphics Test"
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //Will exit out when close button pressed

        f.add(gp);                                          //Add the GraphicsPanel object to the frame
    }

    public static void main(String[] args) {
        GraphicsTest myTest = new GraphicsTest();
        myTest.gp.addLine(100, 100, 200, 200, "#FF0000", 10);                       //Inserts a red line
        myTest.gp.addRect(100, 100, 300, 300, "#00FFFF", true, 10);   //Inserts a cyan filled rectangle
        myTest.gp.addOval(100, 100, 600, 300, "#0000FF", true, 10);   //Inserts a blue unfilled oval (circle)
    }
}
