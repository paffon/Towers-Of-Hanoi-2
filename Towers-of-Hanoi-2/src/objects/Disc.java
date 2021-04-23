package objects;

import java.awt.*;

public class Disc extends OnScreenObject {
    int discOrder; // How many discs are below this discs. e.g. 7 ==> there are 6 discs below me
    Rod rod;

    public Disc(Rod rod, int discOrder) {
        super();

        this.rod = rod;
        this.discOrder = discOrder;
    }
}
