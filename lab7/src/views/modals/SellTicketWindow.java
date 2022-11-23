package views.modals;

import controllers.FlightsController;
import controllers.SoldTicketsController;
import models.Flight;
import utils.ComboBoxFriendlyPair;
import views.MainFrame;

import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class SellTicketWindow extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField ticketPriceField;
    private JTextField sitNumberField;
    private JComboBox sellerBox;

    private final MainFrame parent;
    private final FlightsController flightsController;
    private final SoldTicketsController ticketsController;
    private final Flight targetFlight;

    public SellTicketWindow(MainFrame parent, FlightsController flightsController, SoldTicketsController ticketsController, ResultSet employees, long routeId) throws SQLException {
        this.parent = parent;
        this.flightsController = flightsController;
        this.ticketsController = ticketsController;
        targetFlight = flightsController.getById(routeId);
        ticketPriceField.setText(Integer.toString(targetFlight.getTicketPrice()));
        pack();
        setLocationRelativeTo(parent);
        setTitle("Добавить маршрут");
        setSize(416, 250);
        setResizable(false);
        setContentPane(contentPane);
        while (employees.next()) {
            sellerBox.addItem(new ComboBoxFriendlyPair(employees.getLong(1), employees.getString(2)));
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

    private void onOK() throws SQLException {
        long sellerId = Long.parseLong(Objects.requireNonNull(sellerBox.getSelectedItem()).toString().split(" - ")[0]);
        ticketsController.sellTicket(Integer.parseInt(ticketPriceField.getText()), Byte.parseByte(sitNumberField.getText()),
                targetFlight.getId(), sellerId);
        flightsController.sellTicket(targetFlight.getId());
        JOptionPane.showMessageDialog(null, "Билет успешно продан.");
        parent.loadOrRefreshTickets();
        parent.loadOrRefreshFlights();
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
