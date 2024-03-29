package buttonhandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.EventListener;

public class ButtonHandler {

    private JPanel panel;

    public ButtonHandler(JPanel panel) {
        this.panel = panel;
    }

    public void addTextButton(int xPos, int yPos, int width, int height, String id, String text, String font, int fontsize, Color fontColour, ActionListener listener) {
        JButton button = new JButton(text);
        button.setFont(new Font(font, Font.PLAIN, fontsize));
        button.setBackground(Color.white);
        button.setForeground(fontColour);
        button.setContentAreaFilled(false);
        button.setBounds(xPos, yPos, width, height);
        button.putClientProperty("targetid", id);
        button.addActionListener(listener);

        panel.add(button);
    }

    public void addImageButton(int xPos, int yPos, int width, int height, String id, String imageLocation, ActionListener listener) {
        Image buttonIconImage = new ImageIcon(imageLocation).getImage();
        Icon buttonIconScaled = new ImageIcon(buttonIconImage.getScaledInstance(width, height, Image.SCALE_SMOOTH));

        JButton button = new JButton(buttonIconScaled);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.putClientProperty("targetid", id);
        button.addActionListener(listener);
        button.setBounds(xPos, yPos, width, height);


        panel.add(button);
    }

}
