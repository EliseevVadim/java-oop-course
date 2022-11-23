package views.modals;

import controllers.BussesController;
import models.Bus;
import utils.ComboBoxFriendlyPair;
import utils.ComboboxItemSelector;
import views.MainFrame;

import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class ProcessBusWindow extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel titleLabel;
    private JTextField carNumberField;
    private JTextField sitPlacesField;
    private JTextField generalCapacityField;
    private JComboBox travelCompanyBox;

    private final MainFrame parent;
    private final BussesController controller;
    private Bus updatingBus;

    public ProcessBusWindow(MainFrame parent, BussesController controller, ResultSet travelCompanies) throws SQLException {
        this.parent = parent;
        this.controller = controller;
        pack();
        setLocationRelativeTo(parent);
        setTitle("Добавить автобус");
        setSize(416, 300);
        setResizable(false);
        setContentPane(contentPane);
        while (travelCompanies.next()) {
            travelCompanyBox.addItem(new ComboBoxFriendlyPair(travelCompanies.getLong(1), travelCompanies.getString(2)));
        }
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

    public ProcessBusWindow(MainFrame parent, BussesController controller, ResultSet travelCompanies, long busId) throws SQLException {
        this(parent, controller, travelCompanies);
        updatingBus = controller.getById(busId);
        ComboboxItemSelector.selectComboboxItemForSpecificId(travelCompanyBox, updatingBus.getTravelCompanyId());
        setTitle("Обновить информацию об автобусе");
        titleLabel.setText("Обновить информацию об автобусе");
        carNumberField.setText(updatingBus.getCarNumber());
        sitPlacesField.setText(Byte.toString(updatingBus.getSitPlaces()));
        generalCapacityField.setText(Byte.toString(updatingBus.getGeneralCapacity()));
    }

    private void onOK() throws SQLException {
        long companyId = Long.parseLong(Objects.requireNonNull(travelCompanyBox.getSelectedItem()).toString().split(" - ")[0]);
        if (updatingBus == null) {
            controller.createBus(carNumberField.getText(), Byte.parseByte(sitPlacesField.getText()),
                    Byte.parseByte(generalCapacityField.getText()), companyId);
            finishProcessing();
            return;
        }
        updatingBus.setCarNumber(carNumberField.getText());
        updatingBus.setSitPlaces(Byte.parseByte(sitPlacesField.getText()));
        updatingBus.setGeneralCapacity(Byte.parseByte(generalCapacityField.getText()));
        updatingBus.setTravelCompanyId(companyId);
        controller.updateBus(updatingBus.getId(), updatingBus.getCarNumber(), updatingBus.getSitPlaces(),
                updatingBus.getGeneralCapacity(), updatingBus.getTravelCompanyId());
        parent.loadOrRefreshBusses();
        dispose();
    }

    private void finishProcessing() throws SQLException {
        dispose();
        parent.loadOrRefreshBusses();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
