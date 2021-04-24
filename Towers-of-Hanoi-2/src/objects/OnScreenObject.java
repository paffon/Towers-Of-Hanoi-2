package objects;

import display.ObjectType;

import java.awt.*;

public class OnScreenObject {
    public double width;
    public double height;
    public Color color;
    public Point screenCoordinates;
    public double speedX;
    public double speedY;
    public ObjectType type;

    public OnScreenObject(ObjectType type) {
        // default parameters:
        width = 0;
        height = 0;

        color = Color.WHITE;

        screenCoordinates = new Point(0, 0); // default location - top left
        speedX = 0.0;
        speedY = 0.0;

        this.type = type;
        colorMe();
    }

    public void colorMe() {
        color = new BasicParams().getColor(type);
    }

    // Returns a point with coordinates of x,y relative to the object's dimensions and location
    // getPointOnObject(0, 0) = left-top
    // getPointOnObject(0.5, 0) = mid-top
    // getPointOnObject(0.5, 1) = mid-bottom
    // etc.
    public Point getPointOnObject(double x, double y) {
        double newX = screenCoordinates.x + x * width;
        double newY = screenCoordinates.y + y * height;

        return new Point(newX, newY);
    }

    public String physicalProperties() {
        String result = "";

        result += "(" + screenCoordinates.x;
        result += ", " + screenCoordinates.y + ") ";
        result += width + "/";
        result += height;

        result += "\n";

        return result;
    }
}
