import java.awt.*;

public class ColoredRect extends DrawableRect {
    private Color inColor;

    public ColoredRect(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    public ColoredRect(int width, int height) {
        super(width, height);
    }

    public ColoredRect() {
        super();
    }

    public Color getInColor() {
        return inColor;
    }

    public void setInColor(Color inColor) {
        this.inColor = inColor;
    }

    public void draw(Graphics g) {
        int width = x2 - x1;
        int height = y2 - y1;
        g.setColor(outColor);
        g.drawRect(x1, y1, width, height);
        g.setColor(inColor);
        g.fillRect(x1, y1, width, height);
    }
}
