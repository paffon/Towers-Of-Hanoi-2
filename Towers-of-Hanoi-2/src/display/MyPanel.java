package display;

import TowersOfHanoi.TowersOfHanoi;
import objects.ColorPalette;
import objects.OnScreenObject;
import objects.ProgressBar;
import objects.Rod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class MyPanel extends JPanel implements ActionListener {
    int PANEL_WIDTH;
    int PANEL_HEIGHT;
    Timer timer;
    int fps = 24;
    TowersOfHanoi toh;
    Set<OnScreenObject> onScreenObjectList;
    ProgressBar progressBar;

    public MyPanel(TowersOfHanoi toh) {
        this.toh = toh;

        panelSetup();
        onScreenObjectsSetup();

        timer = new Timer(1000/fps, this);
        timer.start();
    }

    private void onScreenObjectsSetup() {
        onScreenObjectList = new HashSet<>();
        setupProgressBar();
        setupBase();
        setupRods();
//        setupDiscs();
    }

    private void setupRods() {
        Rod rod0 = toh.rods[0];
        Rod rod1 = toh.rods[1];
        Rod rod2 = toh.rods[2];

        basicParamsSetup(rod0, 0.15, 0.3, 0.05,0.6);
        basicParamsSetup(rod1, 0.475, 0.3, 0.05,0.6);
        basicParamsSetup(rod2, 0.8, 0.3, 0.05,0.6);
    }

    private void setupBase() {
        OnScreenObject base = new OnScreenObject(ObjectType.BASE);
        basicParamsSetup(base, 0.0, 0.9, 1.0,0.1);
    }

    private void setupProgressBar() {
        progressBar = new ProgressBar(0.0, ObjectType.PROGRESS_BAR);
        basicParamsSetup(progressBar, 0.0, 0.025, 0.0,0.05);
    }

    private void basicParamsSetup(OnScreenObject object, double x, double y, double width, double height) {
        object.screenCoordinates.x = (int) (x * PANEL_WIDTH);
        object.screenCoordinates.y = (int) (y * PANEL_HEIGHT);
        object.width = (int) (width * PANEL_WIDTH);
        object.height = (int) (width * PANEL_HEIGHT);

        onScreenObjectList.add(object);
    }

    private void panelSetup() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();

        // window set to 95% of the screen's size
        this.PANEL_WIDTH = (int) (0.95 * screenWidth);
        this.PANEL_HEIGHT = (int) (0.95 * screenHeight);

        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // the actions to be performed between frames

        repaint(); // this calls paint() for us every time.
    }
}
