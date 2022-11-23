package views.modals;

import controllers.RouteRepetitionsController;
import models.RouteRepetition;
import views.MainFrame;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class ProcessRouteRepetitionWindow extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField repetitionNameField;
    private JLabel titleLabel;

    private final RouteRepetitionsController controller;
    private final MainFrame parent;
    private RouteRepetition updatingRepetition;

    public ProcessRouteRepetitionWindow(MainFrame parent, RouteRepetitionsController controller) {
        this.parent = parent;
        this.controller = controller;
        pack();
        setLocationRelativeTo(parent);
        setTitle("Добавить повторяемость маршрута");
        setSize(416, 150);
        setResizable(false);
        setContentPane(contentPane);
        setVisible(true);
        setModal(false);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> {
            try {
                onOK();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Произошла ошибка, попробуйте еще раз.");
            }
        });

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public ProcessRouteRepetitionWindow(MainFrame parent, RouteRepetitionsController controller, long repetitionId) throws SQLException {
        this(parent, controller);
        updatingRepetition = controller.getById(repetitionId);
        setTitle("Обновить информацию о повторяемости маршрута");
        titleLabel.setText("Обновить информацию о повторяемости маршрута");
        repetitionNameField.setText(updatingRepetition.getRepetitionName());
    }

    private void onOK() throws SQLException {
        if (updatingRepetition == null) {
            controller.createRepetition(repetitionNameField.getText());
            finishProcessing();
            return;
        }
        updatingRepetition.setRepetitionName(repetitionNameField.getText());
        controller.updateRepetition(updatingRepetition.getId(), updatingRepetition.getRepetitionName());
        parent.loadOrRefreshRepetitions();
        dispose();
    }

    private void finishProcessing() throws SQLException {
        dispose();
        parent.loadOrRefreshRepetitions();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
