package buttonhandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class ButtonHandler {

    private JPanel panel;
    private ActionEvent buttonEvent;

    public ButtonHandler(JPanel panel) {
        this.panel = panel;
    }

    public void addTextButton(int xPos, int yPos, int width, int height, String id, String text, String font, int fontsize, Color fontColour, ActionListener listener) {
        JButton button = new JButton(text);
        button.setFont(new Font(font, Font.PLAIN, fontsize));
        button.setForeground(fontColour);
        button.setBounds(xPos, yPos, width, height);
        button.putClientProperty("targetid", id);
        button.addActionListener(listener);

        panel.add(button);
    }

}
