package classes;
import java.awt.*;

public class Label {
    int x, y;
    String text;
    Color color;
    Font font;

    public Label(int x, int y, String text, Color color, Font font) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.color = color;
        this.font = font;
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.setFont(font);
        g.drawString(text, x, y);
    }
}
