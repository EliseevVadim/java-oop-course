package tasks.second;

import core.ColorableRect;

import java.awt.*;
import javax.swing.*;

public class BouncingRectSimple extends JPanel {
    private static final int BOX_WIDTH = 1000;
    private static final int BOX_HEIGHT = 500;
    private final ColorableRect target;
    private static final int speedX = 3;
    private static final int speedY = 2;
    private static final int UPDATE_RATE = 30;

    /** Constructor to create the UI components and init game objects. */
    public BouncingRectSimple(ColorableRect targetRect) {
        this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
        target = targetRect;
        // Start the ball bouncing (in its own thread)
        Thread gameThread = new Thread(() -> {
            while (true) {
                // Calculate the ball's new position
                target.moveWithSpecifiedSpeed();
                // Check if the ball moves over the bounds
                // If so, adjust the position and speed.
                if (target.getX1() < 0) {
                    target.setSpeedX(-target.getSpeedX());
                    target.setX1(0);
                }
                else if (target.getX2() > BOX_WIDTH) {
                    target.setSpeedX(-target.getSpeedX());
                    target.setX1(BOX_WIDTH - target.getWidth());
                }
                // May cross both x and y bounds
                if (target.getY1() < 0) {
                    target.setSpeedY(-target.getSpeedY());
                    target.setY1(0);
                }
                else if (target.getY1() + target.getHeight() > BOX_HEIGHT) {
                    target.setSpeedY(-target.getSpeedY());
                    target.setY1(BOX_HEIGHT - target.getHeight());
                }
                repaint();
                try {
                    Thread.sleep(1000 / UPDATE_RATE);  // milliseconds
                }
                catch (InterruptedException ignored) { }
            }
        });
        gameThread.start();
    }

    /** Custom rendering codes for drawing the JPanel */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);
        target.draw(g);
    }

    public static void main(String[] args) {
        ColorableRect rect = new ColorableRect(10, 10, 150, 170);
        rect.setSpeed(speedX, speedY);
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("A Bouncing Rect");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new BouncingRectSimple(rect));
            frame.pack();
            frame.setVisible(true);
        });
    }
}