package objects;

import display.ObjectType;

import java.awt.*;

public class Disc extends OnScreenObject {
    public int discOrder; // How many discs are below this discs. e.g. 7 ==> there are 6 discs below me
    public Rod rod;
    public int speedX;
    public int speedY;

    public Disc(Rod rod, int discOrder, ObjectType type) {
        super(type);

        this.discOrder = discOrder;
        this.rod = rod;

        this.speedX = 0;
        this.speedY = 0;
    }
}
