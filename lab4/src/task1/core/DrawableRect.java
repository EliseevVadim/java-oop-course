package task1.core;

import java.awt.*;

public class DrawableRect extends Rectangle {
    protected Color outColor;

    public DrawableRect(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
        outColor = Color.black;
    }

    public DrawableRect(int width, int height) {
        super(width, height);
        outColor = Color.black;
    }

    public DrawableRect() {
        super();
        outColor = Color.black;
    }

    public Color getOutColor() {
        return outColor;
    }

    public void setOutColor(Color outColor) {
        this.outColor = outColor;
    }

    public void draw(Graphics g) {
        int width = x2 - x1;
        int height = y2 - y1;
        g.setColor(outColor);
        g.drawRect(x1, y1, width, height);
    }
}