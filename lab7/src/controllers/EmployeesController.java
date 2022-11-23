package controllers;

import models.Employee;
import repositories.EmployeesRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeesController {
    private final EmployeesRepository repository;

    public EmployeesController(EmployeesRepository repository) {
        this.repository = repository;
    }

    public ResultSet getAllEmployees() throws SQLException {
        return repository.getAll();
    }

    public Employee getById(long id) throws SQLException {
        return repository.getById(id);
    }

    public void createEmployee(String employeeFIO, String employeeINN) throws SQLException {
        repository.create(new Employee(employeeFIO, employeeINN));
    }

    public void updateEmployee(long id, String employeeFIO, String employeeINN) throws SQLException {
        repository.update(new Employee(id, employeeFIO, employeeINN));
    }
}
