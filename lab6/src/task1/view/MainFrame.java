package task1.view;

import task1.enums.DisplayTables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainFrame extends JFrame implements ActionListener {
    private final JMenuItem openOption;
    private final JLabel outputLabel;
    private static final String ADDITION_PATH = "addition.jpg";
    private static final String MULTIPLICATION_PATH = "multiplication.jpg";

    public MainFrame() {
        outputLabel = new JLabel();
        JMenuBar menuBar = new JMenuBar();
        JMenu fileOption = new JMenu("Файл");
        openOption = new JMenuItem("Открыть");
        openOption.addActionListener(this);
        fileOption.add(openOption);
        menuBar.add(fileOption);

        outputLabel.setVisible(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Главное окно");
        setSize(1000, 600);
        setLayout(new FlowLayout());
        setJMenuBar(menuBar);
        add(outputLabel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openOption) {
            new SelectionFrame(this);
        }
    }

    public void updateContent(DisplayTables output) {
        String imageName;
        switch (output) {
            case Addition:
                imageName = ADDITION_PATH;
                outputLabel.setText("Таблица сложения:");
                break;
            case Multiplication:
                imageName = MULTIPLICATION_PATH;
                outputLabel.setText("Таблица умножения:");
                break;
            default:
                imageName = null;
        }
        File image = new File("./images/" + imageName);
        ImageIcon labelContent = new ImageIcon(image.getAbsolutePath());
        outputLabel.setIcon(labelContent);
        outputLabel.setFont(new Font("Verdana", Font.BOLD, 32));
        outputLabel.setHorizontalTextPosition(JLabel.CENTER);
        outputLabel.setVerticalTextPosition(JLabel.TOP);
        outputLabel.setVisible(true);
    }
}
