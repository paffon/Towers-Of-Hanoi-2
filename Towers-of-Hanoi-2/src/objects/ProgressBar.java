package objects;

import display.ObjectType;

public class ProgressBar extends OnScreenObject{
    public double progress;

    public ProgressBar(double progress, ObjectType type) {
        super(type);
        this.progress = progress;
    }
}
