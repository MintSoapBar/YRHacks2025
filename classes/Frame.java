package classes;

import java.util.*;
import java.awt.*;

public class Frame {
    int x, y, width, height;
    Color color;

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
        if (color != null) {
            g.setColor(color);
            g.fillRect(x + ox, y + oy, width, height);
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

    public Frame(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public Frame(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}