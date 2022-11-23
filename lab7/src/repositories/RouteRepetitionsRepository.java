package repositories;

import models.RouteRepetition;

import java.sql.*;

public class RouteRepetitionsRepository implements iRepository<RouteRepetition> {
    @Override
    public ResultSet getAll() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String query = "SELECT id, RepetitionName AS \"Название повторения\" FROM RouteRepetitions";
        return statement.executeQuery(query);
    }

    @Override
    public RouteRepetition getById(long id) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "SELECT id, RepetitionName FROM RouteRepetitions " +
                "WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, id);
        ResultSet set = statement.executeQuery();
        if (set.next()) {
            return new RouteRepetition(set.getInt(1), set.getString(2));
        }
        return null;
    }

    @Override
    public void create(RouteRepetition element) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "INSERT INTO RouteRepetitions (RepetitionName) values (?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, element.getRepetitionName());
        statement.execute();
        statement.close();
        connection.close();
    }

    @Override
    public void update(RouteRepetition element) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "UPDATE RouteRepetitions SET " +
                "RepetitionName = ? " +
                "WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, element.getRepetitionName());
        statement.setLong(2, element.getId());
        statement.execute();
        statement.close();
        connection.close();
    }

    @Override
    public void delete(long id) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "DELETE FROM RouteRepetitions WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, id);
        statement.execute();
        statement.close();
        connection.close();
    }

    public ResultSet getComboboxContents() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String query = "SELECT id, RepetitionName FROM RouteRepetitions";
        return statement.executeQuery(query);
    }
}
