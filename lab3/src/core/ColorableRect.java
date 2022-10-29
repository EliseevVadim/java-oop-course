package core;

import java.awt.*;

public class ColorableRect extends DrawableRect {
    private Color inColor;
    public ColorableRect(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
        inColor = Color.red;
    }

    public ColorableRect(int width, int height) {
        super(width, height);
        inColor = Color.red;
    }

    public ColorableRect() {
        super();
        inColor = Color.red;
    }

    public Color getInColor() {
        return inColor;
    }

    public void setInColor(Color inColor) {
        this.inColor = inColor;
    }

    public void draw(Graphics g) {
        g.setColor(outColor);
        g.drawRect(x1, y1, width, height);
        g.setColor(inColor);
        g.fillRect(x1, y1, width, height);
    }
}