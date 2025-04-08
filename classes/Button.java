package classes;
import java.awt.*;

public class Button {
    int x, y, width, height;
    Label label;
    Color color;

    public Button(int x, int y, int width, int height, Label label, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.label = label;
        this.color = color;
    }

    public boolean isInBounds(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
        g.drawString(label.text, x + (width - g.getFontMetrics().stringWidth(label.text)) / 2, y + (height + g.getFontMetrics().getHeight()) / 2 - 5);
    }
}
