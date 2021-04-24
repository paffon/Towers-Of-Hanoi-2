package display;

import TowersOfHanoi.TowersOfHanoi;
import TowersOfHanoi.RodsTriple;
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
    int fps = 1000;

    TowersOfHanoi toh;

    int solutionStageIndex;
    RodsTriple solutionStage;
    Disc movingDisc;
    Rod from;
    Rod to;

    State state;

    double progressBarMaxWidth = 0.95;
    double discMargin = 0.025; // the space above a rod that a disc goes up to
    double movementSpeed = 0.075; // how fast discs are moving
    double discsSpacing = 0.01; // the space between discs stacked on a rod
    double discHeight; // the length between two discs tops [thickness + spacing]
    double rodMargin; // how much of the rod sticks out when all discs are on it

    Set<OnScreenObject> onScreenObjectList;
    ProgressBar progressBar;
    OnScreenObject progressBarBG;
    OnScreenObject base;
    Rod[] rods;
    Disc[] discs;

    public MyPanel(TowersOfHanoi toh) {
        this.toh = toh;

        panelSetup();
        onScreenObjectsSetup();
        setSolutionStage(0);

        timer = new Timer(1000/fps, this);
        timer.start();
    }

    private void setSolutionStage(int i) {
        this.solutionStageIndex = i;
        solutionStage = toh.solution.get(solutionStageIndex);
        solutionStage.from.type = ObjectType.ROD_FROM;
        solutionStage.helper.type = ObjectType.ROD_HELPER;
        solutionStage.to.type = ObjectType.ROD_TO;

        from = solutionStage.from;
        to = solutionStage.to;

        progressBar.width = progressBarMaxWidth * (double) (i + 1) / (double) toh.solution.size();
    }

    private void onScreenObjectsSetup() {
        onScreenObjectList = new HashSet<>();
        setupProgressBar();
        setupBase();
        setupRods();
        setupDiscs();
    }

    private void setupDiscs() {
        discs = toh.discs;
        Rod rod = toh.rods[0];
        int amountOfDiscs = toh.amountOfDiscs;

        rodMargin = 0.1 * rod.height;
        double totalDiscsHeight = rod.height - rodMargin;
        discHeight = totalDiscsHeight / amountOfDiscs;
        Point rodMidTopPoint = rod.getPointOnObject(0.5, 0);
        double minDiscWidth = 0.1;
        double maxDiscWidth = 0.25;

        GeneralParameters gp = new GeneralParameters();
        Color randomColor = new Color(180, 20 ,20);

        for(int i = 0; i < discs.length; i++) {
            Disc disc = discs[i];

            // smallest disc width = min, biggest disc width = max
            double width = minDiscWidth + i * (maxDiscWidth - minDiscWidth) / (amountOfDiscs - 1);
            double actualHeightForDiscs = discHeight - discsSpacing;

            double x = rodMidTopPoint.x - (width / 2);
            double y = rodMidTopPoint.y + rodMargin + i * discHeight;

            basicParamsSetup(disc, x, y, width, actualHeightForDiscs);

            disc.color = gp.getGradient(randomColor, i, discs.length);
        }
    }

    private void setupRods() {
        rods = toh.rods;
        Rod rod0 = rods[0];
        Rod rod1 = rods[1];
        Rod rod2 = rods[2];

        double width = 0.05;
        double height = 0.4;
        double y = base.screenCoordinates.y - height;
        basicParamsSetup(rod0, 0.15 , y, width, height);
        basicParamsSetup(rod1, 0.475, y, width, height);
        basicParamsSetup(rod2, 0.8  , y, width, height);
    }

    private void setupBase() {
        base = new OnScreenObject(ObjectType.BASE);
        double baseWidth = 0.95;
        double baseHeight = 0.3;
        basicParamsSetup(base, (1 - baseWidth) / 2, 1 - baseHeight, baseWidth, baseHeight);
    }

    private void setupProgressBar() {
        double x = (1- progressBarMaxWidth) / 2;
        double y = 0.025;
        double height = 0.05;

        progressBarBG = new OnScreenObject(ObjectType.PROGRESS_BAR_BG);
        basicParamsSetup(progressBarBG, x, y, progressBarMaxWidth, height);

        progressBar = new ProgressBar(ObjectType.PROGRESS_BAR);
        basicParamsSetup(progressBar, x, y, 0.0, height);
    }

    // x, y, width & height are given as percentage of windows's width / height
    private void basicParamsSetup(OnScreenObject object, double x, double y, double width, double height) {
        object.screenCoordinates.x = x;
        object.screenCoordinates.y = y;
        object.width = width;
        object.height = height;
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
        this.setBackground(Color.black);

        state = State.WAITING_FOR_NEXT_MOVE;
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2D = (Graphics2D) g;

        paint(base, g2D);
        for(Rod rod: rods) {
            paint(rod, g2D);
        }
        for(Disc object: discs) {
            paint(object, g2D);
        }
        paint(progressBarBG, g2D);
        paint(progressBar, g2D);
    }

    private void paint(OnScreenObject object, Graphics2D g2D) {
        g2D.setColor(object.color);
        int x = (int) (object.screenCoordinates.x * PANEL_WIDTH);
        int y = (int) (object.screenCoordinates.y * PANEL_HEIGHT);
        int width = (int) (object.width * PANEL_WIDTH);
        int height = (int) (object.height * PANEL_HEIGHT);
        g2D.fillRect(x, y, width, height);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // the actions to be performed between frames

        updateAllLocations();

        switch (state) {
            case WAITING_FOR_NEXT_MOVE:
                startDiscJourney();
                break;
            case UP_MOVE:
                checkUpMovement();
                break;
            case SIDE_MOVE:
                checkSideMovement();
                break;
            case DOWN_MOVE:
                checkDownMovement();
                break;
            case FINISHING:
                prepareToFinish();
                break;
            case FINISHED:
                finish();
                break;
            default:
                break;
        }

        repaint(); // this calls paint() for us every time.
    }

    private void prepareToFinish() {
        progressBar.width = progressBarMaxWidth;
        this.setBackground(new Color(10, 140, 50));
    }

    private void checkDownMovement() {
        double destinationY = to.screenCoordinates.y + to.height - discHeight * ((double) to.discs.size() + 1.0);

        if(movingDisc.screenCoordinates.y >= destinationY) {
            movingDisc.screenCoordinates.y = destinationY; // set accurately

            movingDisc.speedY = 0.0; // stop moving down;

            to.push(movingDisc);

            // check if there's another stage to the solution

            if(solutionStageIndex + 1 < toh.solution.size()) {
                setSolutionStage(solutionStageIndex + 1);
                state = State.WAITING_FOR_NEXT_MOVE;
            }
            else {
                state = State.FINISHING;
            }
        }
    }

    private void checkSideMovement() {
        double destinationX = to.getPointOnObject(0.5, 0).x - (movingDisc.width / 2);
        boolean reachedDestination = false;
        if(isMovingLeft() && movingDisc.screenCoordinates.x <= destinationX) {
            reachedDestination = true;
        }
        else if(!isMovingLeft() && movingDisc.screenCoordinates.x >= destinationX) {
            reachedDestination = true;
        }

        if(reachedDestination) {
            movingDisc.screenCoordinates.x = destinationX; // set coordinate accurately

            movingDisc.speedX = 0.0; // stop moving sideways
            movingDisc.speedY = movementSpeed; // start moving down;

            state = State.DOWN_MOVE;
        }
    }

    private void checkUpMovement() {
        double destinationY = from.screenCoordinates.y - discMargin - discHeight;

        // while disc is going up, check if reached that height
        if(movingDisc.screenCoordinates.y <= destinationY) {
            movingDisc.screenCoordinates.y = destinationY; // make it accurate, as the disc may have overshot

            movingDisc.speedY = 0.0; // stop moving up
            movingDisc.speedX = movementSpeed; // move right
            if(isMovingLeft()) movingDisc.speedX *= -1; // move left

            state = State.SIDE_MOVE;
        }
    }

    private void startDiscJourney() {
        movingDisc = from.pop();

        movingDisc.speedY = -movementSpeed;

        state = State.UP_MOVE;
    }

    // should the disc move left?
    private boolean isMovingLeft() {
        return from.screenCoordinates.x > to.screenCoordinates.x;
    }

    private void updateAllLocations() {
        for(OnScreenObject object: onScreenObjectList) {
            object.screenCoordinates.x += object.speedX;
            object.screenCoordinates.y += object.speedY;
        }
    }

    private void finish() {
        timer.stop();
    }
}
