package tasks.third;

import core.ColorableRect;
import core.DrawableRect;
import core.Rectangle;
import jdk.jshell.spi.ExecutionControl;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * One ball bouncing inside a rectangular box.
 * All codes in one file. Poor design!
 */
public class BouncingRectHard extends JPanel {
    private static final int BOX_WIDTH = 1000;
    private static final int BOX_HEIGHT = 500;
    private static final int MIN_SPEED = 1;
    private static final int MAX_SPEED = 7;
    private static final int MIN_SIZE = 20;
    private static final int MAX_SIZE = 110;
    private final Rectangle[] rectangles;
    private static final int UPDATE_RATE = 30;

    /** Constructor to create the UI components and init game objects. */
    public BouncingRectHard(Rectangle[] targetRectangles) {
        this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
        rectangles = targetRectangles;
        // Start the ball bouncing (in its own thread)
        for (Rectangle rectangle : rectangles) {
            Thread gameThread = new Thread(() -> {
                while (true) {
                    // Calculate the ball's new position
                    rectangle.moveWithSpecifiedSpeed();
                    // Check if the ball moves over the bounds
                    // If so, adjust the position and speed.
                    if (rectangle.getX1() < 0) {
                        rectangle.setSpeedX(-rectangle.getSpeedX());
                        rectangle.setX1(0);
                    }
                    else if (rectangle.getX2() > BOX_WIDTH) {
                        rectangle.setSpeedX(-rectangle.getSpeedX());
                        rectangle.setX1(BOX_WIDTH - rectangle.getWidth());
                    }
                    // May cross both x and y bounds
                    if (rectangle.getY1() < 0) {
                        rectangle.setSpeedY(-rectangle.getSpeedY());
                        rectangle.setY1(0);
                    }
                    else if (rectangle.getY1() + rectangle.getHeight() > BOX_HEIGHT) {
                        rectangle.setSpeedY(-rectangle.getSpeedY());
                        rectangle.setY1(BOX_HEIGHT - rectangle.getHeight());
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

    }

    /** Custom rendering codes for drawing the JPanel */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);
        for (Rectangle rectangle : rectangles) {
            try {
                rectangle.draw(g);
            }
            catch (ExecutionControl.NotImplementedException e) {
                System.out.println("passed");
            }
        }
    }

    public static void main(String[] args) {
        Rectangle[] rectangles = new Rectangle[30];
        for (int i = 0; i < rectangles.length; i++) {
            if (i < 10)
                rectangles[i] = new Rectangle(ThreadLocalRandom.current().nextInt(MIN_SIZE, MAX_SIZE), ThreadLocalRandom.current().nextInt(MIN_SIZE, MAX_SIZE));
            else if (i < 20)
                rectangles[i] = new DrawableRect(ThreadLocalRandom.current().nextInt(MIN_SIZE, MAX_SIZE), ThreadLocalRandom.current().nextInt(MIN_SIZE, MAX_SIZE));
            else
                rectangles[i] = new ColorableRect(ThreadLocalRandom.current().nextInt(MIN_SIZE, MAX_SIZE), ThreadLocalRandom.current().nextInt(MIN_SIZE, MAX_SIZE));
            int speedX = ThreadLocalRandom.current().nextInt(MIN_SPEED, MAX_SPEED);
            int speedY = ThreadLocalRandom.current().nextInt(MIN_SPEED, MAX_SPEED);
            rectangles[i].setSpeed(speedX, speedY);
        }
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("A Bouncing Bunch of rects");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new BouncingRectHard(rectangles));
            frame.pack();
            frame.setVisible(true);
        });
    }
}
