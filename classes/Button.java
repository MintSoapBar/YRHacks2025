package classes;
import java.awt.*;

public class Button {
    int x, y, width, height;
    Color color;
    String text;

    Frame parent;

    public int getAbsX() {
        int absX = x;
        for (Frame p = parent; p != null; p = p.parent) absX += p.x;
        return absX;
    }

    public int getAbsY() {
        int absY = y;
        for (Frame p = parent; p != null; p = p.parent) absY += p.y;
        return absY;
    }

    public boolean isPosInBounds(int posX, int posY) {
        int absX = getAbsX();
        int absY = getAbsY();
        return posX >= absX && posX <= absX + width && posY >= absY && posY <= absY + height;
    }

    public void render(Graphics g, int ox, int oy) {
        g.setColor(color != null? color: new Color(255, 255, 0));
        g.fillRect(x + ox, y + oy, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x + ox, y + oy, width, height);
        g.drawString(text, x + ox + (width - g.getFontMetrics().stringWidth(text)) / 2, y + oy + (height + g.getFontMetrics().getHeight()) / 2 - 5);
    }

    public void render(Graphics g) {
        render(g, 0, 0);
    }

    public Button(int x, int y, int width, int height, Color color, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.color = color;
    }

    public Button(int x, int y, int width, int height, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
    }
}
