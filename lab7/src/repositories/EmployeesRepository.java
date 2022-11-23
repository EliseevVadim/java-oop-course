package repositories;

import models.Employee;

import java.sql.*;

public class EmployeesRepository implements iRepository<Employee> {
    @Override
    public ResultSet getAll() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String query = "SELECT id, EmployeeFIO AS \"ФИО сотрудника\", EmployeeINN AS " +
                "\"ИНН сотрудника\" FROM Employees";
        return statement.executeQuery(query);
    }

    @Override
    public Employee getById(long id) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "SELECT id, EmployeeFIO, EmployeeINN FROM Employees " +
                "WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, id);
        ResultSet set = statement.executeQuery();
        if (set.next()) {
            return new Employee(set.getInt(1), set.getString(2), set.getString(3));
        }
        return null;
    }

    @Override
    public void create(Employee element) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "INSERT INTO Employees (EmployeeFIO, EmployeeINN) values (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, element.getEmployeeFIO());
        statement.setString(2, element.getEmployeeINN());
        statement.execute();
        statement.close();
        connection.close();
    }

    @Override
    public void update(Employee element) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "UPDATE Employees SET " +
                "EmployeeFIO = ?, " +
                "EmployeeINN = ? " +
                "WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, element.getEmployeeFIO());
        statement.setString(2, element.getEmployeeINN());
        statement.setLong(3, element.getId());
        statement.execute();
        statement.close();
        connection.close();
    }

    @Override
    public void delete(long id) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "DELETE FROM Employees WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, id);
        statement.execute();
        statement.close();
        connection.close();
    }
}
