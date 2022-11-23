package views.modals;

import controllers.EmployeesController;
import models.Employee;
import views.MainFrame;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class ProcessEmployeeWindow extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel titleLabel;
    private JTextField employeeFIOField;
    private JTextField employeeINNField;

    private final MainFrame parent;
    private final EmployeesController controller;
    private Employee updatingEmployee;

    public ProcessEmployeeWindow(MainFrame parent, EmployeesController controller) {
        this.parent = parent;
        this.controller = controller;
        pack();
        setLocationRelativeTo(parent);
        setTitle("Добавить сотрудника");
        setSize(416, 200);
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

    public ProcessEmployeeWindow(MainFrame parent, EmployeesController controller, long employeeId) throws SQLException {
        this(parent, controller);
        updatingEmployee = controller.getById(employeeId);
        setTitle("Обновить информацию о сотруднике");
        titleLabel.setText("Обновить информацию о сотруднике");
        employeeFIOField.setText(updatingEmployee.getEmployeeFIO());
        employeeINNField.setText(updatingEmployee.getEmployeeINN());
    }

    private void onOK() throws SQLException {
        if (updatingEmployee == null) {
            controller.createEmployee(employeeFIOField.getText(), employeeINNField.getText());
            finishProcessing();
            return;
        }
        updatingEmployee.setEmployeeFIO(employeeFIOField.getText());
        updatingEmployee.setEmployeeINN(employeeINNField.getText());
        controller.updateEmployee(updatingEmployee.getId(), updatingEmployee.getEmployeeFIO(), updatingEmployee.getEmployeeINN());
        parent.loadOrRefreshEmployees();
        dispose();
    }

    private void finishProcessing() throws SQLException {
        dispose();
        parent.loadOrRefreshEmployees();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
