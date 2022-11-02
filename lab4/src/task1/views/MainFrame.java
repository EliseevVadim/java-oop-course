package task1.views;

import task1.core.ColoredRect;
import task1.core.DrawableRect;
import task1.core.Rectangle;
import task1.presenters.RectanglesPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

public class MainFrame extends JFrame implements ActionListener {
    private final JButton createRectangleButton;
    private final JButton createDrawableRectangleButton;
    private final JButton createColoredRectangleButton;
    private final RectanglesPresenter rectanglesPresenter;
    private static final int MIN_SIZE = 20;
    private static final int MAX_SIZE = 110;
    private static final int OFFSET = 30;

    public MainFrame() {
        rectanglesPresenter = new RectanglesPresenter();
        createRectangleButton = new JButton("Создать простой прямоугольник");
        createDrawableRectangleButton = new JButton("Создать незакрашенный прямоугольник");
        createColoredRectangleButton = new JButton("Создать закрашенный прямоугольник");

        createRectangleButton.addActionListener(this);
        createDrawableRectangleButton.addActionListener(this);
        createColoredRectangleButton.addActionListener(this);

        JPanel panel = new JPanel();
        panel.add(createRectangleButton);
        panel.add(createDrawableRectangleButton);
        panel.add(createColoredRectangleButton);

        setTitle("Лабораторная работа №4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 600);
        getContentPane().add(BorderLayout.SOUTH, panel);
        getContentPane().add(rectanglesPresenter);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createRectangleButton) {
            Rectangle rectangle = new Rectangle(OFFSET, OFFSET, ThreadLocalRandom.current().nextInt(MIN_SIZE, MAX_SIZE) + OFFSET, ThreadLocalRandom.current().nextInt(MIN_SIZE, MAX_SIZE) + OFFSET);
            rectanglesPresenter.AddRectangle(rectangle);
        }
        if (e.getSource() == createDrawableRectangleButton) {
            Rectangle rectangle = new DrawableRect(OFFSET, OFFSET, ThreadLocalRandom.current().nextInt(MIN_SIZE, MAX_SIZE) + OFFSET, ThreadLocalRandom.current().nextInt(MIN_SIZE, MAX_SIZE) + OFFSET);
            rectanglesPresenter.AddRectangle(rectangle);
        }
        if (e.getSource() == createColoredRectangleButton) {
            Rectangle rectangle = new ColoredRect(OFFSET, OFFSET, ThreadLocalRandom.current().nextInt(MIN_SIZE, MAX_SIZE) + OFFSET, ThreadLocalRandom.current().nextInt(MIN_SIZE, MAX_SIZE) + OFFSET);
            rectanglesPresenter.AddRectangle(rectangle);
        }
    }
}
