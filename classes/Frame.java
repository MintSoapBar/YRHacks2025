package classes;

import java.util.*;
import java.awt.*;
import java.awt.image.*;;

public class Frame {
    final static double SCROLL_ALPHA = 0.3;

    int x, y, width, height;

    Color backgroundColor;
    Color borderColor;

    String text;
    Font textFont;
    Color textColor;

    BufferedImage image;

    Frame parent;

    HashSet<Frame> children = new HashSet<>();

    boolean isScrollingFrame = false;
    Point targetScrollOffset = new Point();
    Point scrollOffset = new Point();

    public void addChild(Frame f) {
        children.add(f);
        f.parent = this;
    }

    public void removeChild(Frame f) {
        children.remove(f);
        f.parent = null;
    }

    public void render(Graphics g, int ox, int oy) {
        if (isScrollingFrame) {
            scrollOffset.x = Math2.lerp(scrollOffset.x, targetScrollOffset.x, SCROLL_ALPHA);
            scrollOffset.y = Math2.lerp(scrollOffset.y, targetScrollOffset.y, SCROLL_ALPHA);
        }

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

        if (text != null) {
            if (textColor != null) {
                g.setColor(borderColor);
            } else {
                g.setColor(Color.black);
            }
            if (textFont != null) {
                g.setFont(textFont);
            } else {
                g.setFont(new Font("Times New Roman", Font.PLAIN, 15));
            }
            g.drawString(
                    text,
                    x + ox + (width - g.getFontMetrics().stringWidth(text)) / 2,
                    y + oy + (int) (height / 2 + g.getFontMetrics().getHeight() * 0.4));
        }

        for (Frame c: children) {
            c.render(g, x + ox + scrollOffset.x, y + oy + scrollOffset.y);
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

    public Frame(int x, int y, int width, int height, Color backgroundColor) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
    }

    public Frame(int x, int y, int width, int height, Color backgroundColor, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
        this.text = text;
    }

    public Frame(int x, int y, int width, int height, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
    }

    public Frame(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}