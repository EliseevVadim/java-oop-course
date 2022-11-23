package repositories;

import models.TravelCompany;

import java.sql.*;

public class TravelCompaniesRepository implements iRepository<TravelCompany>{
    @Override
    public ResultSet getAll() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String query = "SELECT id, CompanyName AS \"Название компании-перевозчика\", CompanyINN AS " +
                "\"ИНН компании-перевозчика\" FROM TravelCompanies";
        return statement.executeQuery(query);
    }

    @Override
    public TravelCompany getById(long id) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "SELECT id, CompanyName, CompanyINN FROM TravelCompanies " +
                "WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, id);
        ResultSet set = statement.executeQuery();
        if (set.next()) {
            return new TravelCompany(set.getInt(1), set.getString(2), set.getString(3));
        }
        return null;
    }

    @Override
    public void create(TravelCompany element) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "INSERT INTO TravelCompanies (CompanyName, CompanyINN) values (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, element.getCompanyName());
        statement.setString(2, element.getCompanyINN());
        statement.execute();
        statement.close();
        connection.close();
    }

    @Override
    public void update(TravelCompany element) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "UPDATE TravelCompanies SET " +
                "CompanyName = ?, " +
                "CompanyINN = ? " +
                "WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, element.getCompanyName());
        statement.setString(2, element.getCompanyINN());
        statement.setLong(3, element.getId());
        statement.execute();
        statement.close();
        connection.close();
    }

    @Override
    public void delete(long id) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "DELETE FROM TravelCompanies WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, id);
        statement.execute();
        statement.close();
        connection.close();
    }

    public ResultSet getComboboxContents() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String query = "SELECT id, CompanyName FROM TravelCompanies";
        return statement.executeQuery(query);
    }
}
