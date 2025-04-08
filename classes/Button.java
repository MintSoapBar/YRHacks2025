package classes;

import java.awt.*;
import java.awt.image.*;

public class Button extends Frame {
    Frame parent;

    public boolean isPosInBounds(int posX, int posY, int ox, int oy) {
        return posX >= x + ox && posX <= x + ox + width && posY >= y + oy && posY <= y + oy + height;
    }
    
    public Button(int x, int y, int width, int height, BufferedImage image) {
        super(x, y, width, height, image);
    }

    public Button(int x, int y, int width, int height, Color backgroundColor, String text) {
        super(x, y, width, height, backgroundColor, text);
    }

    public Button(int x, int y, int width, int height, Color backgroundColor) {
        super(x, y, width, height, backgroundColor);
    }

    public Button(int x, int y, int width, int height, String text) {
        super(x, y, width, height, text);
    }

    public Button(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
}
