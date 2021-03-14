package graphicshandler;

import javax.swing.*;
import java.awt.*;

public class GraphicsDemo {
    //Create a GraphicsPanel object where all graphics will be drawn on
    GraphicsPanel gp = new GraphicsPanel();

    public GraphicsDemo() {
        JFrame f = new JFrame();                            //Create new JFrame, acting as the main window
        f.setVisible(true);                                 //Set the frame to visible
        f.setSize(1600, 900);                   //Set size to 1600x900 pix
        f.setTitle("Graphics Demo");                        //Set frame title to "Graphics Test"
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //Will exit out when close button pressed

        f.add(gp);                                          //Add the GraphicsPanel object to the frame
    }

    public static void main(String[] args) {
        GraphicsDemo myDemo = new GraphicsDemo();

        //Demonstrating the different gradient hints when drawing a shaded rectangle
        myDemo.gp.addShadedRect(300, 300, 50, 50, "#ff0000", "#0000ff", GraphicsPanel.GradientHints.HORIZONTAL,  0);
        myDemo.gp.addShadedRect(300, 300, 375, 50, "#ff0000", "#0000ff", GraphicsPanel.GradientHints.VERTICAL,  0);
        myDemo.gp.addShadedRect(300, 300, 700, 50, "#ff0000", "#0000ff", GraphicsPanel.GradientHints.DIAGONAL_DOWN,  0);
        myDemo.gp.addShadedRect(300, 300, 1025, 50, "#ff0000", "#0000ff", GraphicsPanel.GradientHints.DIAGONAL_UP,  0);

        //Drawing a black line
        myDemo.gp.addLine(50, 375, 1325, 375, "#000000", 10);

        //Iterating and drawing several slanted black lines
        for (int i = 50; i < 1325; i += 10) {
            myDemo.gp.addLine(i, 375, i + 5, 390, "#000000", 10);
        }

        //Check for NumberFormatException in the hexadecimal colour input to ensure the string is of the correct format.
        //Here, hexShadeColor1 is "#ff000t". The t at the end means this isn't in hexadecimal format, so the NumberFormatException will be caught
        try {
            myDemo.gp.addShadedOval(100, 100, 50, 615, 50, 415, "#ff000t", 50, 515, "#0000ff", false, 0);   //Advanced shaded oval drawing (specify gradient coordinates, aligned with shape location)
        } catch (NumberFormatException e) {
            //Put your exception handling here (perhaps have a default colour to assign to the erroneous fields?)
            System.out.println("Invalid hexadecimal colour value!");
        }

        //Various ways of drawing a circle
        myDemo.gp.addShadedOval(100, 100, 50, 415, 50, 415, "#ff0000", 50, 515, "#0000ff", false, 0);   //Advanced shaded oval drawing (specify gradient coordinates, aligned with shape location)
        myDemo.gp.addShadedOval(100, 100, 200, 415, "#ffA500", "#9400d3", GraphicsPanel.GradientHints.DIAGONAL_UP, 0);                      //Easier shaded oval drawing (specify gradient direction)
        myDemo.gp.addOval(100, 100, 350, 415, "#00ffff", true, 10);     //Draw a filled circle
        myDemo.gp.addOval(100, 100, 500, 415, "#00ffff", false, 10);    //Draw an unfilled circle
        myDemo.gp.addOval(100, 100, 650, 415, "#00ffff", true, 10);     //Draw a smaller, filled white circle on top of another filled circle to look like a circle with a certain thickness
        myDemo.gp.addOval(80, 80, 660, 425, "#ffffff", true, 10);

        myDemo.gp.addShadedRect(50, 100, 50, 540, 50, 540, "#228b22", 50, 590, "#a0522d", false, 0);    //Advanced shaded rectangle drawing (specify gradient coordinates, aligned with shape location)
        myDemo.gp.addShadedRect(50, 100, 200, 540, "#228b22", "#a0522d", GraphicsPanel.GradientHints.DIAGONAL_DOWN, 0);                     //Easier shaded rectangle drawing (specify gradient direction)
        myDemo.gp.addRect(50, 100, 350, 540, "#00ff00", true, 10);      //Draw a filled rectangle
        myDemo.gp.addRect(50, 100, 500, 540, "#00ff00", false, 10);     //Draw an unfilled rectangle
        myDemo.gp.addRect(50, 100, 650, 540, "#00ff00", true, 10);      //Draw a smaller, filled white rectangle on top of another filled rectangle to look like a rectangle with a certain thickness
        myDemo.gp.addRect(30, 80, 660, 550, "#ffffff", true, 10);
    }
}
