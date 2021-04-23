package objects;

import display.ObjectType;

import java.awt.*;

public class OnScreenObject {
    public int width;
    public int height;
    public Color color;
    public Point screenCoordinates;
    public ObjectType type;

    public OnScreenObject(ObjectType type) {
        // default parameters:
        width = 0;
        height = 0;
        color = Color.WHITE;
        screenCoordinates = new Point(0, 0); // default location - top left
        this.type = type;
        colorMe();
    }

    public void colorMe() {
        color = new ColorPalette().getColor(type);
    }
}
