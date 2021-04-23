package TowersOfHanoi;

import objects.Rod;

public class RodsTriple {
    public Rod from, helper, to;

    public RodsTriple(Rod from, Rod helper, Rod to) {
        this.from = from;
        this.helper = helper;
        this.to = to;
    }

    @Override
    public String toString() {
        return "RodsTriple { " +
                "from " + from +
                ", helper " + helper +
                ", to " + to +
                " }";
    }
}
