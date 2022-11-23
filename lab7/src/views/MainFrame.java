package views;

import controllers.*;
import repositories.*;
import views.modals.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class MainFrame extends JFrame implements WindowListener {
    private JTabbedPane contentTabs;
    private JPanel mainPanel;
    private JTable routesDataTable;
    private JButton addRouteButton;
    private JButton deleteRouteButton;
    private JButton updateRouteButton;
    private JTable flightsDataTable;
    private JButton addFlightButton;
    private JButton deleteFlightButton;
    private JButton updateFlightButton;
    private JButton sellTicketButton;
    private JTable soldTicketsDataTable;
    private JButton turnBackTicketButton;
    private JTable bussesDataTable;
    private JButton addBusButton;
    private JButton deleteBusButton;
    private JButton editBusButton;
    private JTable travelCompaniesDataTable;
    private JButton addCompanyButton;
    private JButton deleteCompanyButton;
    private JButton editCompanyButton;
    private JTable citiesDataTable;
    private JButton addCityButton;
    private JButton deleteCityButton;
    private JButton editCityButton;
    private JTable repetitionsDataTable;
    private JButton addRepetition;
    private JButton deleteRepetition;
    private JButton editRepetition;
    private JTable employeesDataTable;
    private JButton addEmployee;
    private JButton editEmployee;

    private final MainFrame instance;
    private final CitiesController citiesController;
    private final RouteRepetitionsController routeRepetitionsController;
    private final TravelCompaniesController travelCompaniesController;
    private final EmployeesController employeesController;
    private final BussesController bussesController;
    private final RoutesController routesController;
    private final FlightsController flightsController;
    private final SoldTicketsController soldTicketsController;

    public MainFrame() {
        instance = this;
        citiesController = new CitiesController(new CitiesRepository());
        routeRepetitionsController = new RouteRepetitionsController(new RouteRepetitionsRepository());
        travelCompaniesController = new TravelCompaniesController(new TravelCompaniesRepository());
        employeesController = new EmployeesController(new EmployeesRepository());
        bussesController = new BussesController(new BussesRepository());
        routesController = new RoutesController(new RoutesRepository());
        flightsController = new FlightsController(new FlightsRepository());
        soldTicketsController = new SoldTicketsController(new SoldTicketsRepository());
        setTitle("Лабораторная работа №7");
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 600);
        addWindowListener(this);
        setVisible(true);
        addCityButton.addActionListener(e -> new ProcessCityWindow(instance, citiesController));
        editCityButton.addActionListener(e -> {
            int row = citiesDataTable.getSelectedRow();
            if (row == -1)
                return;
            try {
                new ProcessCityWindow(instance, citiesController, Long.parseLong(citiesDataTable.getValueAt(row, 0).toString()));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Произошла ошибка, попробуйте еще раз.");
            }
        });
        deleteCityButton.addActionListener(e -> {
            int row = citiesDataTable.getSelectedRow();
            if (row == -1)
                return;
            long id = Long.parseLong(citiesDataTable.getValueAt(row, 0).toString());
            int answer = JOptionPane.showConfirmDialog(instance, "Вы действительно хотите удалить запись с id = " + id + " ?");
            if (answer == 0){
                try {
                    citiesController.deleteCity(id);
                    loadOrRefreshCities();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Произошла ошибка, попробуйте еще раз.");
                }
            }
        });
        addRepetition.addActionListener(e -> new ProcessRouteRepetitionWindow(instance, routeRepetitionsController));
        editRepetition.addActionListener(e -> {
            int row = repetitionsDataTable.getSelectedRow();
            if (row == -1)
                return;
            try {
                new ProcessRouteRepetitionWindow(instance, routeRepetitionsController, Long.parseLong(repetitionsDataTable.getValueAt(row, 0).toString()));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Произошла ошибка, попробуйте еще раз.");
            }
        });
        deleteRepetition.addActionListener(e -> {
            int row = repetitionsDataTable.getSelectedRow();
            if (row == -1)
                return;
            long id = Long.parseLong(repetitionsDataTable.getValueAt(row, 0).toString());
            int answer = JOptionPane.showConfirmDialog(instance, "Вы действительно хотите удалить запись с id = " + id + " ?");
            if (answer == 0){
                try {
                    routeRepetitionsController.deleteRepetition(id);
                    loadOrRefreshRepetitions();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Произошла ошибка, попробуйте еще раз.");
                }
            }
        });
        addCompanyButton.addActionListener(e -> new ProcessTravelCompanyWindow(instance, travelCompaniesController));
        editCompanyButton.addActionListener(e -> {
            int row = travelCompaniesDataTable.getSelectedRow();
            if (row == -1)
                return;
            try {
                new ProcessTravelCompanyWindow(instance, travelCompaniesController, Long.parseLong(travelCompaniesDataTable.getValueAt(row, 0).toString()));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Произошла ошибка, попробуйте еще раз.");
            }
        });
        deleteCompanyButton.addActionListener(e -> {
            int row = travelCompaniesDataTable.getSelectedRow();
            if (row == -1)
                return;
            long id = Long.parseLong(travelCompaniesDataTable.getValueAt(row, 0).toString());
            int answer = JOptionPane.showConfirmDialog(instance, "Вы действительно хотите удалить запись с id = " + id + " ?");
            if (answer == 0){
                try {
                    travelCompaniesController.deleteCompany(id);
                    loadOrRefreshTravelCompanies();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Произошла ошибка, попробуйте еще раз.");
                }
            }
        });
        addEmployee.addActionListener(e -> new ProcessEmployeeWindow(instance, employeesController));
        editEmployee.addActionListener(e -> {
            int row = employeesDataTable.getSelectedRow();
            if (row == -1)
                return;
            try {
                new ProcessEmployeeWindow(instance, employeesController, Long.parseLong(employeesDataTable.getValueAt(row, 0).toString()));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Произошла ошибка, попробуйте еще раз.");
            }
        });
        addBusButton.addActionListener(e -> {
            try {
                new ProcessBusWindow(instance, bussesController, travelCompaniesController.getAllCompaniesForCombobox());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Произошла ошибка, попробуйте еще раз.");
            }
        });
        editBusButton.addActionListener(e -> {
            int row = bussesDataTable.getSelectedRow();
            if (row == -1)
                return;
            try {
                new ProcessBusWindow(instance, bussesController, travelCompaniesController.getAllCompaniesForCombobox(), Long.parseLong(bussesDataTable.getValueAt(row, 0).toString()));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Произошла ошибка, попробуйте еще раз.");
            }
        });
        deleteBusButton.addActionListener(e -> {
            int row = bussesDataTable.getSelectedRow();
            if (row == -1)
                return;
            long id = Long.parseLong(bussesDataTable.getValueAt(row, 0).toString());
            int answer = JOptionPane.showConfirmDialog(instance, "Вы действительно хотите удалить запись с id = " + id + " ?");
            if (answer == 0){
                try {
                    bussesController.deleteBus(id);
                    loadOrRefreshBusses();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Произошла ошибка, попробуйте еще раз.");
                }
            }
        });
        addRouteButton.addActionListener(e -> {
            try {
                new ProcessRouteWindow(instance, routesController, routeRepetitionsController.getAllRepetitionsForCombobox(), citiesController.getAllCitiesForCombobox());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Произошла ошибка, попробуйте еще раз.");
            }
        });
        updateRouteButton.addActionListener(e -> {
            int row = routesDataTable.getSelectedRow();
            if (row == -1)
                return;
            try {
                new ProcessRouteWindow(instance, routesController, routeRepetitionsController.getAllRepetitionsForCombobox(), citiesController.getAllCitiesForCombobox(), Long.parseLong(routesDataTable.getValueAt(row, 0).toString()));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Произошла ошибка, попробуйте еще раз.");
            }
        });
        deleteRouteButton.addActionListener(e -> {
            int row = routesDataTable.getSelectedRow();
            if (row == -1)
                return;
            long id = Long.parseLong(routesDataTable.getValueAt(row, 0).toString());
            int answer = JOptionPane.showConfirmDialog(instance, "Вы действительно хотите удалить запись с id = " + id + " ?");
            if (answer == 0){
                try {
                    routesController.deleteRoute(id);
                    loadOrRefreshRoutes();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Произошла ошибка, попробуйте еще раз.");
                }
            }
        });
        addFlightButton.addActionListener(e -> {
            try {
                new ProcessFlightWindow(instance, flightsController, routesController.getAllRoutesForCombobox(), bussesController.getAllBussesForCombobox());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Произошла ошибка, попробуйте еще раз.");
            }
        });
        updateFlightButton.addActionListener(e -> {
            int row = flightsDataTable.getSelectedRow();
            if (row == -1)
                return;
            try {
                new ProcessFlightWindow(instance, flightsController, routesController.getAllRoutesForCombobox(), bussesController.getAllBussesForCombobox(), Long.parseLong(flightsDataTable.getValueAt(row, 0).toString()));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Произошла ошибка, попробуйте еще раз.");
            }
        });
        deleteFlightButton.addActionListener(e -> {
            int row = flightsDataTable.getSelectedRow();
            if (row == -1)
                return;
            long id = Long.parseLong(flightsDataTable.getValueAt(row, 0).toString());
            int answer = JOptionPane.showConfirmDialog(instance, "Вы действительно хотите удалить запись с id = " + id + " ?");
            if (answer == 0){
                try {
                    flightsController.deleteFlight(id);
                    loadOrRefreshFlights();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Произошла ошибка, попробуйте еще раз.");
                }
            }
        });
        sellTicketButton.addActionListener(e -> {
            int row = flightsDataTable.getSelectedRow();
            if (row == -1)
                return;
            long id = Long.parseLong(flightsDataTable.getValueAt(row, 0).toString());
            try {
                new SellTicketWindow(instance, flightsController, soldTicketsController, employeesController.getAllEmployees(), id);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Произошла ошибка, попробуйте еще раз.");
            }
        });
        turnBackTicketButton.addActionListener(e -> {
            int row = soldTicketsDataTable.getSelectedRow();
            if (row == -1)
                return;
            long id = Long.parseLong(soldTicketsDataTable.getValueAt(row, 0).toString());
            int answer = JOptionPane.showConfirmDialog(instance, "Вы действительно хотите вернуть билет с id = " + id + " ?");
            if (answer == 0) {
                try {
                    flightsController.turnBackTicket(id);
                    soldTicketsController.deleteTicket(id);
                    loadOrRefreshFlights();
                    loadOrRefreshTickets();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Произошла ошибка, попробуйте еще раз.");
                }
            }
        });
    }

    public void loadOrRefreshCities() throws SQLException {
        loadOrRefreshTable(citiesController.getAllCities(), citiesDataTable);
    }

    public void loadOrRefreshRepetitions() throws SQLException {
        loadOrRefreshTable(routeRepetitionsController.getAllRepetitions(), repetitionsDataTable);
    }

    public void loadOrRefreshTravelCompanies() throws SQLException {
        loadOrRefreshTable(travelCompaniesController.getAllCompanies(), travelCompaniesDataTable);
    }

    public void loadOrRefreshEmployees() throws SQLException {
        loadOrRefreshTable(employeesController.getAllEmployees(), employeesDataTable);
    }

    public void loadOrRefreshBusses() throws SQLException {
        loadOrRefreshTable(bussesController.getAllBusses(), bussesDataTable);
    }

    public void loadOrRefreshRoutes() throws SQLException {
        loadOrRefreshTable(routesController.getAllRoutes(), routesDataTable);
    }

    public void loadOrRefreshFlights() throws SQLException {
        loadOrRefreshTable(flightsController.getAllFlights(), flightsDataTable);
    }

    public void loadOrRefreshTickets() throws SQLException {
        loadOrRefreshTable(soldTicketsController.getAllTickets(), soldTicketsDataTable);
    }

    private void loadOrRefreshTable(ResultSet data, JTable table) throws SQLException {
        table.setModel(new DefaultTableModel());
        ResultSetMetaData meta = data.getMetaData();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int columnCount = meta.getColumnCount();
        String[] columns = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
            columns[i] = meta.getColumnLabel(i + 1);
        }
        model.setColumnIdentifiers(columns);
        while (data.next()) {
            String[] row = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                row[i] = data.getString(i + 1);
            }
            model.addRow(row);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        try {
            loadOrRefreshCities();
            loadOrRefreshRepetitions();
            loadOrRefreshTravelCompanies();
            loadOrRefreshEmployees();
            loadOrRefreshBusses();
            loadOrRefreshRoutes();
            loadOrRefreshFlights();
            loadOrRefreshTickets();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {

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
