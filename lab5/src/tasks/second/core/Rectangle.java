package tasks.second.core;

import jdk.jshell.spi.ExecutionControl;

import java.awt.*;
import java.io.Serializable;

public class Rectangle implements Serializable {
    protected int x1;
    protected int y1;
    protected int x2;
    protected int y2;
    protected int width;
    protected int height;

    public Rectangle(int x1, int y1, int x2, int y2) {
        super();
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        width = x2 - x1;
        height = y2 - y1;
    }

    public Rectangle(int width, int height) {
        x1 = 0;
        y1 = 0;
        x2 = width;
        y2 = height;
        this.width = width;
        this.height = height;
    }

    public Rectangle() {
        x1 = 0;
        y1 = 0;
        x2 = 0;
        y2 = 0;
        width = 0;
        height = 0;
    }

    public void setX1(int x1) {
        this.x1 = x1;
        x2 = x1 + width;
    }

    public int getX1() {
        return x1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
        x1 = x2 - width;
    }

    public int getX2() {
        return x2;
    }

    public void setY1(int y1) {
        this.y1 = y1;
        y2 = y1 + height;
    }

    public int getY1() {
        return y1;
    }

    public void setY2(int y2) {
        this.y2 = y2;
        y1 = y2 - height;
    }

    public int getY2() {
        return y2;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean pointWithinTheRectangle(int x, int y) {
        return (x >= getX1() && x <= getX2()) && (y >= getY1() && y <= getY2());
    }

    public void rect_print() {
        System.out.println("Printing rectangle state:");
        System.out.println("X1 = " + x1);
        System.out.println("Y1 = " + y1);
        System.out.println("X2 = " + x2);
        System.out.println("Y2 = " + y2);
    }

    public void move(int dx, int dy) {
        x1 += dx;
        y1 += dy;
        x2 += dx;
        y2 += dy;
    }

    public void moveAt(int x, int y) {
        x1 = x;
        y1 = y;
        x2 = x1 + width;
        y2 = y1 + height;
    }

    public void draw(Graphics g) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("need to override");
    }

    public Rectangle Union(Rectangle other) {
        return new Rectangle(Math.min(x1, other.x1), Math.min(y1, other.y1), Math.max(x2, other.x2), Math.max(y2, other.y2));
    }
}