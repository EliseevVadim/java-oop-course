package views.modals;

import controllers.RoutesController;
import models.Route;
import utils.ComboBoxFriendlyPair;
import utils.ComboboxItemSelector;
import views.MainFrame;

import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class ProcessRouteWindow extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox departureCityBox;
    private JComboBox arrivalCityBox;
    private JComboBox repetitionBox;
    private JTextField averageTravelTimeField;
    private JTextField travelDistanceField;
    private JLabel titleLabel;

    private final MainFrame parent;
    private final RoutesController controller;
    private Route updatingRoute;

    public ProcessRouteWindow(MainFrame parent, RoutesController controller, ResultSet repetitions, ResultSet cities) throws SQLException {
        this.parent = parent;
        this.controller = controller;
        pack();
        setLocationRelativeTo(parent);
        setTitle("Добавить маршрут");
        setSize(416, 350);
        setResizable(false);
        setContentPane(contentPane);
        while (repetitions.next()) {
            repetitionBox.addItem(new ComboBoxFriendlyPair(repetitions.getLong(1), repetitions.getString(2)));
        }
        while (cities.next()) {
            arrivalCityBox.addItem(new ComboBoxFriendlyPair(cities.getLong(1), cities.getString(2)));
            departureCityBox.addItem(new ComboBoxFriendlyPair(cities.getLong(1), cities.getString(2)));
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

    public ProcessRouteWindow(MainFrame parent, RoutesController controller, ResultSet repetitions, ResultSet cities, long routeId) throws SQLException {
        this(parent, controller, repetitions, cities);
        updatingRoute = controller.getById(routeId);
        ComboboxItemSelector.selectComboboxItemForSpecificId(departureCityBox, updatingRoute.getDepartureCityId());
        ComboboxItemSelector.selectComboboxItemForSpecificId(arrivalCityBox, updatingRoute.getArrivalCityId());
        ComboboxItemSelector.selectComboboxItemForSpecificId(repetitionBox, updatingRoute.getRepetitionId());
        setTitle("Обновить информацию о маршруте");
        titleLabel.setText("Обновить информацию о маршруте");
        averageTravelTimeField.setText(Integer.toString(updatingRoute.getAverageTravelTime()));
        travelDistanceField.setText(Float.toString(updatingRoute.getTravelDistance()));
    }

        private void onOK() throws SQLException {
            long departureCityId = Long.parseLong(Objects.requireNonNull(departureCityBox.getSelectedItem()).toString().split(" - ")[0]);
            long arrivalCityId = Long.parseLong(Objects.requireNonNull(arrivalCityBox.getSelectedItem()).toString().split(" - ")[0]);
            long repetitionId = Long.parseLong(Objects.requireNonNull(repetitionBox.getSelectedItem()).toString().split(" - ")[0]);
            if (updatingRoute == null) {
                controller.createRoute(departureCityId, arrivalCityId, repetitionId,
                        Integer.parseInt(averageTravelTimeField.getText()), Float.parseFloat(travelDistanceField.getText()));
                finishProcessing();
                return;
            }
            controller.updateRoute(updatingRoute.getId(), departureCityId, arrivalCityId, repetitionId,
                    Integer.parseInt(averageTravelTimeField.getText()), Float.parseFloat(travelDistanceField.getText()));
            parent.loadOrRefreshRoutes();
            dispose();
    }

    private void finishProcessing() throws SQLException {
        dispose();
        parent.loadOrRefreshRoutes();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
