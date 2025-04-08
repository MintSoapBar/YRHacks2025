package classes;

import java.util.*;
import java.awt.*;
import java.awt.image.*;;

public class Frame {
    int x, y, width, height;
    Color backgroundColor;
    Color borderColor;
    BufferedImage image;

    HashSet<Frame> frames = new HashSet<>();
    HashSet<Button> buttons = new HashSet<>();

    Frame parent;

    public void addFrame(Frame f) {
        frames.add(f);
        f.parent = this;
    }

    public void removeFrame(Frame f) {
        frames.remove(f);
        f.parent = null;
    }

    public void addButton(Button b) {
        buttons.add(b);
        b.parent = this;
    }

    public void removeButton(Button b) {
        buttons.remove(b);
        b.parent = null;
    }

    public void render(Graphics g, int ox, int oy) {
        if (backgroundColor != null) {
            g.setColor(backgroundColor);
            g.fillRect(x + ox, y + oy, width, height);
        }

        if (image != null) {
            g.drawImage(image, x + ox, y + oy, width, height, null);
        }

        if (borderColor != null) {
            g.setColor(borderColor);
            g.drawRect(x + ox, y + oy, width, height);
        }

        for (Frame f: frames) {
            f.render(g, x + ox, y + oy);
        }

        for (Button b: buttons) {
            b.render(g, x + ox, y + oy);
        }
    }

    public void render(Graphics g) {
        render(g, 0, 0);
    }

    public Frame(int x, int y, int width, int height, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    public Frame(int x, int y, int width, int height, Color backgroundColor, Color borderColor) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
    }

    public Frame(int x, int y, int width, int height, Color backgroundColor) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
    }

    public Frame(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}