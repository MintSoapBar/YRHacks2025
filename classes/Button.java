package classes;
import java.awt.*;

public class Button {
    int x, y, width, height;
    Color backgroundColor = new Color(255, 255, 255);
    Color borderColor = new Color(0, 0, 0);
    String text;

    Frame parent;

    public boolean isPosInBounds(int posX, int posY, int ox, int oy) {
        return posX >= x + ox && posX <= x + ox + width && posY >= y + oy && posY <= y + oy + height;
    }

    public void render(Graphics g, int ox, int oy) {
        if (backgroundColor != null) {
            g.setColor(backgroundColor);
            g.fillRect(x + ox, y + oy, width, height);
        }

        if (borderColor != null) {
            g.setColor(borderColor);
            g.drawRect(x + ox, y + oy, width, height);
        }

        g.drawString(
            text, 
            x + ox + (width - g.getFontMetrics().stringWidth(text)) / 2, 
            y + oy + (int) (height/2 + g.getFontMetrics().getHeight() * 0.4)
            );
    }

    public void render(Graphics g) {
        render(g, 0, 0);
    }

    public Button(int x, int y, int width, int height, Color backgroundColor, Color borderColor, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
    }

    public Button(int x, int y, int width, int height, Color backgroundColor, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.backgroundColor = backgroundColor;
    }

    public Button(int x, int y, int width, int height, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
    }
}
