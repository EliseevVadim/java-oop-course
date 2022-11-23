package repositories;

import models.City;

import java.sql.*;

public class CitiesRepository implements iRepository<City> {
    @Override
    public ResultSet getAll() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String query = "SELECT id, CityName AS \"Название города\" FROM cities";
        return statement.executeQuery(query);
    }

    @Override
    public City getById(long id) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "SELECT id, CityName FROM cities " +
                "WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, id);
        ResultSet set = statement.executeQuery();
        if (set.next()) {
            return new City(set.getInt(1), set.getString(2));
        }
        return null;
    }

    @Override
    public void create(City element) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "INSERT INTO cities (CityName) values (?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, element.getCityName());
        statement.execute();
        statement.close();
        connection.close();
    }

    @Override
    public void update(City element) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "UPDATE cities SET " +
                "CityName = ? " +
                "WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, element.getCityName());
        statement.setLong(2, element.getId());
        statement.execute();
        statement.close();
        connection.close();
    }

    @Override
    public void delete(long id) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "DELETE FROM cities WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, id);
        statement.execute();
        statement.close();
        connection.close();
    }

    public ResultSet getComboboxContents() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String query = "SELECT id, CityName FROM Cities";
        return statement.executeQuery(query);
    }
}
