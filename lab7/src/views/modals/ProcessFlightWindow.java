package views.modals;

import controllers.FlightsController;
import models.Flight;
import utils.ComboBoxFriendlyPair;
import utils.ComboboxItemSelector;
import views.MainFrame;

import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class ProcessFlightWindow extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel titleLabel;
    private JTextField departureTimeField;
    private JTextField arrivalTimeField;
    private JTextField availableTicketsField;
    private JTextField ticketPriceField;
    private JComboBox routesBox;
    private JComboBox bussesBox;

    private final MainFrame parent;
    private final FlightsController controller;
    private Flight updatingFlight;

    public ProcessFlightWindow(MainFrame parent, FlightsController controller, ResultSet routes, ResultSet busses) throws SQLException {
        this.parent = parent;
        this.controller = controller;
        pack();
        setLocationRelativeTo(parent);
        setTitle("Добавить рейс");
        setSize(416, 400);
        setContentPane(contentPane);
        setVisible(true);
        setModal(false);
        while (routes.next()) {
            routesBox.addItem(new ComboBoxFriendlyPair(routes.getLong(1), routes.getString(2)));
        }
        while (busses.next()) {
            bussesBox.addItem(new ComboBoxFriendlyPair(busses.getLong(1), busses.getString(2)));
        }
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

    public ProcessFlightWindow(MainFrame parent, FlightsController controller, ResultSet routes, ResultSet busses, long flightId) throws SQLException {
        this(parent, controller, routes, busses);
        updatingFlight = controller.getById(flightId);
        ComboboxItemSelector.selectComboboxItemForSpecificId(routesBox, updatingFlight.getRouteId());
        ComboboxItemSelector.selectComboboxItemForSpecificId(bussesBox, updatingFlight.getBusId());
        setTitle("Обновить информацию о рейсе");
        titleLabel.setText("Обновить информацию о рейсе");
        departureTimeField.setText(updatingFlight.getDepartureTime());
        arrivalTimeField.setText(updatingFlight.getArrivalTime());
        availableTicketsField.setText(Byte.toString(updatingFlight.getAvailableTickets()));
        ticketPriceField.setText(Integer.toString(updatingFlight.getTicketPrice()));
    }

    private void onOK() throws SQLException {
        long routeId = Long.parseLong(Objects.requireNonNull(routesBox.getSelectedItem()).toString().split(" - ")[0]);
        long busId = Long.parseLong(Objects.requireNonNull(bussesBox.getSelectedItem()).toString().split(" - ")[0]);
        if (updatingFlight == null) {
            controller.createFlight(departureTimeField.getText(), arrivalTimeField.getText(), Byte.parseByte(availableTicketsField.getText()),
                    Integer.parseInt(ticketPriceField.getText()), routeId, busId);
            finishProcessing();
            return;
        }
        controller.updateFlight(updatingFlight.getId(), departureTimeField.getText(), arrivalTimeField.getText(), Byte.parseByte(availableTicketsField.getText()),
                Integer.parseInt(ticketPriceField.getText()), routeId, busId);
        parent.loadOrRefreshFlights();
        dispose();
    }

    private void finishProcessing() throws SQLException {
        dispose();
        parent.loadOrRefreshFlights();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
