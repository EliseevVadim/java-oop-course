package views.modals;

import controllers.TravelCompaniesController;
import models.TravelCompany;
import views.MainFrame;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class ProcessTravelCompanyWindow extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel titleLabel;
    private JTextField companyNameField;
    private JTextField innCompanyField;

    private final MainFrame parent;
    private final TravelCompaniesController controller;
    private TravelCompany updatingCompany;

    public ProcessTravelCompanyWindow(MainFrame parent, TravelCompaniesController controller) {
        this.parent = parent;
        this.controller = controller;
        pack();
        setLocationRelativeTo(parent);
        setTitle("Добавить компанию-перевозчик");
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

    public ProcessTravelCompanyWindow(MainFrame parent, TravelCompaniesController controller, long companyId) throws SQLException {
        this(parent, controller);
        updatingCompany = controller.getById(companyId);
        setTitle("Обновить информацию о компании-перевозчике");
        titleLabel.setText("Обновить информацию о компании-перевозчике");
        companyNameField.setText(updatingCompany.getCompanyName());
        innCompanyField.setText(updatingCompany.getCompanyINN());
    }

    private void onOK() throws SQLException {
        if (updatingCompany == null) {
            controller.createCompany(companyNameField.getText(), innCompanyField.getText());
            finishProcessing();
            return;
        }
        updatingCompany.setCompanyName(companyNameField.getText());
        updatingCompany.setCompanyINN(innCompanyField.getText());
        controller.updateCompany(updatingCompany.getId(), updatingCompany.getCompanyName(), updatingCompany.getCompanyINN());
        parent.loadOrRefreshTravelCompanies();
        dispose();
    }

    private void finishProcessing() throws SQLException {
        dispose();
        parent.loadOrRefreshTravelCompanies();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
