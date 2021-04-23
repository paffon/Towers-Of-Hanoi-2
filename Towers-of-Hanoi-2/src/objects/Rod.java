package objects;

import display.ObjectType;

import java.awt.*;
import java.util.Stack;

public class Rod extends OnScreenObject{
    Stack<Disc> discs;
    String name;

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
        return name;
    }
}
