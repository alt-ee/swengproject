package survivalapp;

import javax.swing.*;

public class View {

    private JFrame window;

    public void newWindow(int width, int height) {
        window = new JFrame();

        window.setSize(width, height);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
