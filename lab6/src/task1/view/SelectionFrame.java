package task1.view;

import task1.enums.DisplayTables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectionFrame extends JFrame implements ActionListener {
    private final JRadioButton displayMultiplicationTable;
    private final JRadioButton displayAdditionTable;
    private final JButton submitChoice;
    private DisplayTables selectedChoice;
    private final MainFrame mainFrame;

    public SelectionFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.mainFrame.setEnabled(false);
        displayMultiplicationTable = new JRadioButton("отображать таблицу умножения");
        displayAdditionTable = new JRadioButton("отображать таблицу сложения");
        submitChoice = new JButton("Подтвердить");

        displayAdditionTable.addActionListener(this);
        displayMultiplicationTable.addActionListener(this);
        submitChoice.addActionListener(this);

        updateSubmitButton();

        ButtonGroup choices = new ButtonGroup();
        choices.add(displayMultiplicationTable);
        choices.add(displayAdditionTable);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Выбор отображаемой таблицы");
        setLayout(new FlowLayout());
        setResizable(false);
        add(displayMultiplicationTable);
        add(displayAdditionTable);
        add(submitChoice);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitChoice) {
            mainFrame.setEnabled(true);
            dispose();
            mainFrame.updateContent(selectedChoice);
        }
        if (e.getSource() == displayAdditionTable) {
            selectedChoice = DisplayTables.Addition;
            updateSubmitButton();
        }
        if (e.getSource() == displayMultiplicationTable) {
            selectedChoice = DisplayTables.Multiplication;
            updateSubmitButton();
        }
    }

    private void updateSubmitButton() {
        submitChoice.setEnabled(selectedChoice != null);
    }
}
