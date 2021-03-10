package mediahandlers;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class TestGUI extends JPanel {
    private GraphicsHandler gh = new GraphicsHandler();

    public TestGUI() {
        JFrame f = new JFrame();

        f.setTitle("Test GUI");
        f.setSize(800, 600);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setBackground(Color.gray);
        f.add(this);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i = 0; i < gh.getLines().size(); i++) {
            g2d.setColor(gh.getLineColor(i));
            g2d.draw(gh.getLine(i));
        }
    }


    public static void main(String[] args) {
        TestGUI myGUI = new TestGUI();
        myGUI.gh.addLine(0, 0, 100, 100, Color.red);
        myGUI.gh.addLine(300, 300, 500, 300, Color.green);
        myGUI.gh.clearLines();


    }
}
