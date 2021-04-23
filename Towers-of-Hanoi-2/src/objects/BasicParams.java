package objects;

import display.ObjectType;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BasicParams {
    Map<ObjectType, Color> colors;

    public BasicParams() {
        colorsSetup();
    }

    private void colorsSetup() {
        colors = new HashMap<>();

        colors.put(ObjectType.BASE, Color.GRAY);
        colors.put(ObjectType.ROD_FROM, Color.GRAY);
        colors.put(ObjectType.ROD_HELPER, Color.GRAY);
        colors.put(ObjectType.ROD_TO, Color.GRAY);
        colors.put(ObjectType.PROGRESS_BAR, Color.BLUE);
        colors.put(ObjectType.DISC, Color.WHITE);
        colors.put(ObjectType.DEFAULT, Color.YELLOW);
    }

    public Color getColor(ObjectType onScreenObject) {
        return colors.get(onScreenObject);
    }


}
