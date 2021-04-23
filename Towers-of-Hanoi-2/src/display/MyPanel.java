package display;

import TowersOfHanoi.TowersOfHanoi;
import objects.*;
import objects.Point;

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
    boolean finished = false;

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
        setupDiscs();
    }

    private void setupDiscs() {
        Disc[] discs = toh.discs;
        Rod rod = toh.rods[0];
        int amountOfDiscs = toh.amountOfDiscs;

        double rodMargin = 0.05;
        double totalDiscsHeight = rod.height - rodMargin;
        double height = totalDiscsHeight / amountOfDiscs;
        Point rodMidTopPoint = rod.getPointOnObject(0.5, 0);
        double minDiscWidth = 0.1;
        double maxDiscWidth = 0.25;

        for(int i = 0; i < discs.length; i++) {
            Disc disc = discs[i];

            // smallest disc width = min, biggest disc width = max
            double width = minDiscWidth + i * (maxDiscWidth - minDiscWidth) / (amountOfDiscs - 1);

            double x = rodMidTopPoint.x - (width / 2);
            double y = rodMidTopPoint.y + rodMargin + i * height;

            basicParamsSetup(disc, x, y, width, height);
        }
    }

    private void setupRods() {
        Rod rod0 = toh.rods[0];
        Rod rod1 = toh.rods[1];
        Rod rod2 = toh.rods[2];

        basicParamsSetup(rod0, 0.15 , 0.3, 0.05,0.6);
        basicParamsSetup(rod1, 0.475, 0.3, 0.05,0.6);
        basicParamsSetup(rod2, 0.8  , 0.3, 0.05,0.6);
    }

    private void setupBase() {
        OnScreenObject base = new OnScreenObject(ObjectType.BASE);
        basicParamsSetup(base, 0.0, 0.9, 1.0,0.1);
    }

    private void setupProgressBar() {
        progressBar = new ProgressBar(0.0, ObjectType.PROGRESS_BAR);
        basicParamsSetup(progressBar, 0.0, 0.025, 0.0,0.05);
    }

    // x, y, width & height are given as percentage of windows's width / height
    private void basicParamsSetup(OnScreenObject object, double x, double y, double width, double height) {
        object.screenCoordinates.x = x * PANEL_WIDTH;
        object.screenCoordinates.y = y * PANEL_HEIGHT;
        object.width = width * PANEL_WIDTH;
        object.height = height * PANEL_HEIGHT;
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

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2D = (Graphics2D) g;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // the actions to be performed between frames

        repaint(); // this calls paint() for us every time.
    }
}
