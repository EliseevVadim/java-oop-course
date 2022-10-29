public class Rectangle {
    protected int x1;
    protected int y1;
    protected int x2;
    protected int y2;

    public Rectangle(int x1, int y1, int x2, int y2) {
        if (x1 > x2 || y1 > y2)
            throw new IllegalArgumentException();
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public Rectangle(int width, int height) {
        x1 = 0;
        y1 = 0;
        x2 = width;
        y2 = height;
    }

    public Rectangle() {
        x1 = 0;
        y1 = 0;
        x2 = 0;
        y2 = 0;
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

    public Rectangle Union(Rectangle other) {
        return new Rectangle(Math.min(x1, other.x1), Math.min(y1, other.y1), Math.max(x2, other.x2), Math.max(y2, other.y2));
    }
}
