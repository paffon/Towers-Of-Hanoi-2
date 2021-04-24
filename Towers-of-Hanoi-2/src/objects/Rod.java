package objects;

import display.ObjectType;

import java.util.Stack;

public class Rod extends OnScreenObject{
    public Stack<Disc> discs;
    public String name;

    public Rod(String name, ObjectType type) {
        super(type);
        this.name = name;
        this.discs = new Stack<>();
    }

    public void push(Disc disc) {
        discs.push(disc);
    }

    public Disc pop() {
        return discs.pop();
    }

    @Override
    public String toString() {
        String string = name + ": [";
        for(Disc disc : discs) {
            string += disc.order + ",";
        }

        string += "]";

        return string;
    }
}
