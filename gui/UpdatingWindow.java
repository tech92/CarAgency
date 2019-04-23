package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.util.Random;

class UpdatingWindow extends JFrame {
    static final Object lock = new Object();

    UpdatingWindow() {
        setAlwaysOnTop(true);
        setBounds(580, 420, 450, 150);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setTitle("Please wait...");
        setLayout(new FlowLayout());
        JLabel icon = new JLabel();
        icon.setIcon(new ImageIcon(getClass().getResource("pics/updating.png")));
        add(new JLabel("Updating database… Please wait"));
        add(new JLabel("* Updating the database during other operations is temporary not available."));
        add(icon);
        setVisible(true);
        try {
            Thread.sleep((new Random().nextInt(6) + 3) * 1000); // 3 to 8 seconds
            dispose();
        } catch (InterruptedException e) {
            dispose();
        }

    }
}
