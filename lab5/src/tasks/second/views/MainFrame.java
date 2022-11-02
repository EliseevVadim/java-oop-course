package tasks.second.views;

import tasks.second.core.ColoredRect;
import tasks.second.core.DrawableRect;
import tasks.second.core.Rectangle;
import tasks.second.presenters.RectanglesPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class MainFrame extends JFrame implements ActionListener, WindowListener {
    private final JButton createRectangleButton;
    private final JButton createDrawableRectangleButton;
    private final JButton createColoredRectangleButton;
    private final JButton loadFromFileButton;
    private final JButton saveToFileButton;
    private final RectanglesPresenter rectanglesPresenter;
    private static final int MIN_SIZE = 20;
    private static final int MAX_SIZE = 110;
    private static final int OFFSET = 30;
    private static final String RECTANGLES_FILE_NAME = new File("./files/rectangles.dat").getAbsolutePath();

    public MainFrame() {
        rectanglesPresenter = new RectanglesPresenter();
        loadFromFileButton = new JButton("LoadFromFile");
        createRectangleButton = new JButton("Создать простой прямоугольник");
        createDrawableRectangleButton = new JButton("Создать незакрашенный прямоугольник");
        createColoredRectangleButton = new JButton("Создать закрашенный прямоугольник");
        saveToFileButton = new JButton("SaveToFile");

        loadFromFileButton.addActionListener(this);
        createRectangleButton.addActionListener(this);
        createDrawableRectangleButton.addActionListener(this);
        createColoredRectangleButton.addActionListener(this);
        saveToFileButton.addActionListener(this);

        JPanel panel = new JPanel();
        panel.add(loadFromFileButton);
        panel.add(createRectangleButton);
        panel.add(createDrawableRectangleButton);
        panel.add(createColoredRectangleButton);
        panel.add(saveToFileButton);

        setTitle("Лабораторная работа №5");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 600);
        getContentPane().add(BorderLayout.SOUTH, panel);
        getContentPane().add(rectanglesPresenter);
        addWindowListener(this);
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
        if (e.getSource() == loadFromFileButton) {
            loadFromFile();
        }
        if (e.getSource() == saveToFileButton) {
            saveToFile();;
        }
    }

    private void saveToFile() {
        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(RECTANGLES_FILE_NAME));
            stream.writeObject(rectanglesPresenter.getRectangles());
            stream.close();
            System.out.println("Данные были сохранены");
        }
        catch (Exception ignored) {}
    }

    private void loadFromFile() {
        ArrayList<Rectangle> rectangles;
        try {
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream(RECTANGLES_FILE_NAME));
            rectangles = ((ArrayList<Rectangle>)stream.readObject());
            for (Rectangle rectangle : rectangles) {
                rectanglesPresenter.AddRectangle(rectangle);
            }
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        loadFromFile();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        saveToFile();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
