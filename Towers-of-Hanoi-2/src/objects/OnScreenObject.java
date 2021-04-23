package objects;

import java.awt.*;

public class OnScreenObject {
    public int width;
    public int height;
    public Color color;
    public int speedX;
    public int speedY;
    public Point screenCoordinates;

    public OnScreenObject() {
        // default parameters:
        width = 0;
        height = 0;
        color = Color.WHITE;
        this.speedX = 0;
        this.speedY = 0;
        screenCoordinates = new Point(0, 0);
    }
}
