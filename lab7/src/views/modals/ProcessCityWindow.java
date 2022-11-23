package views.modals;

import controllers.CitiesController;
import models.City;
import views.MainFrame;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class ProcessCityWindow extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel titleLabel;
    private JTextField cityNameField;

    private final CitiesController controller;
    private final MainFrame parent;
    private City updatingCity;

    public ProcessCityWindow(MainFrame parent, CitiesController controller) {
        this.parent = parent;
        this.controller = controller;
        pack();
        setLocationRelativeTo(parent);
        setTitle("Добавить город");
        setSize(316, 150);
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

    public ProcessCityWindow(MainFrame parent, CitiesController controller, long cityId) throws SQLException {
        this(parent, controller);
        updatingCity = controller.getById(cityId);
        setTitle("Обновить информацию о городе");
        titleLabel.setText("Обновить информацию о городе");
        cityNameField.setText(updatingCity.getCityName());
    }

    private void onOK() throws SQLException {
        if (updatingCity == null) {
            controller.createCity(cityNameField.getText());
            finishProcessing();
            return;
        }
        updatingCity.setCityName(cityNameField.getText());
        controller.updateCity(updatingCity.getId(), updatingCity.getCityName());
        parent.loadOrRefreshCities();
        dispose();
    }

    private void finishProcessing() throws SQLException {
        dispose();
        parent.loadOrRefreshCities();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
