package display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPanel extends JPanel implements ActionListener {
    int PANEL_WIDTH;
    int PANEL_HEIGHT;
    Timer timer;
    int fps = 24;

    public MyPanel() {
        windowSetup();

        timer = new Timer(1000/fps, this);
        timer.start();
    }

    private void windowSetup() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();

        this.PANEL_WIDTH = (int) (0.9 * screenWidth);
        this.PANEL_HEIGHT = (int) (0.9 * screenHeight);

        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // the actions to be performed between frames

        repaint(); // this calls paint() for us every time.
    }
}
