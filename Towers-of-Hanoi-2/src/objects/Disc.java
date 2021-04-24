package objects;

import display.ObjectType;

public class Disc extends OnScreenObject {
    public int order; // How many discs are below this discs. e.g. 7 ==> there are 6 discs below me
    public Rod rod;


    public Disc(Rod rod, int discOrder, ObjectType type) {
        super(type);

        this.order = discOrder;
        this.rod = rod;
    }

    @Override
    public String toString() {
        return "D." + order;
    }
}
