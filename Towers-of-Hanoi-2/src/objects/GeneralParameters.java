package objects;

import display.ObjectType;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GeneralParameters {
    Map<ObjectType, Color> colors;

    public GeneralParameters() {
        colorsSetup();
    }

    private void colorsSetup() {
        colors = new HashMap<>();

        Color wood = new Color(99, 77, 00);
        Color coolBlue = new Color(50, 70, 250);
        Color darkBlue = new Color(20, 25, 100);

        colors.put(ObjectType.BASE,             wood);
        colors.put(ObjectType.ROD_FROM,         wood);
        colors.put(ObjectType.ROD_HELPER,       wood);
        colors.put(ObjectType.ROD_TO,           wood);
        colors.put(ObjectType.PROGRESS_BAR,     coolBlue);
        colors.put(ObjectType.PROGRESS_BAR_BG,  darkBlue);
        colors.put(ObjectType.DISC,             Color.WHITE); // default color for discs
        colors.put(ObjectType.DEFAULT,          Color.YELLOW);
    }

    public Color getColor(ObjectType onScreenObject) {
        return colors.get(onScreenObject);
    }

    public Color getRandomColor() {
        int r = new Random().nextInt(256);
        int g = new Random().nextInt(256);
        int b = new Random().nextInt(256);

        return new Color(r, g, b);
    }

    public Color getGradient(Color color, int i, int n) {
        int r = 255 - color.getRed();
        int g = 255 - color.getGreen();
        int b = 255 - color.getBlue();

        double ratio = ((double) i) / ((double) n);

        int newR = 255 - (int) ((double) (r) * ratio);
        int newG = 255 - (int) ((double) (g) * ratio);
        int newB = 255 - (int) ((double) (b) * ratio);

        return new Color(newR, newG, newB);
    }
}
