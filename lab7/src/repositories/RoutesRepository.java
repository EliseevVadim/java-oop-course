package repositories;

import models.Route;

import java.sql.*;

public class RoutesRepository implements iRepository<Route> {

    @Override
    public ResultSet getAll() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String query = "SELECT Routes.id, d.cityName AS \"Город отправления\", \n" +
                "a.cityName AS \"Город прибытия\", RouteRepetitions.RepetitionName AS \"Повторяемость маршрута\",\n" +
                "AverageTravelTime AS \"Среднее время следования (в мин.)\", TravelDistance AS \"Расстояние (в км)\"\n" +
                "FROM Routes INNER JOIN RouteRepetitions ON routes.RepetitionId = RouteRepetitions.id\n" +
                "INNER JOIN Cities AS d ON Routes.DepartureCityId = d.id\n" +
                "INNER JOIN Cities AS a ON Routes.ArrivalCityId = a.id";
        return statement.executeQuery(query);
    }

    @Override
    public Route getById(long id) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "SELECT id, DepartureCityId, ArrivalCityId, RepetitionId, AverageTravelTime, TravelDistance FROM Routes " +
                "WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, id);
        ResultSet set = statement.executeQuery();
        if (set.next()) {
            return new Route(set.getLong(1), set.getLong(2), set.getLong(3), set.getLong(4), set.getInt(5), set.getFloat(6));
        }
        return null;
    }

    @Override
    public void create(Route element) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "INSERT INTO Routes (DepartureCityId, ArrivalCityId, RepetitionId, AverageTravelTime, TravelDistance) " +
                "values (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, element.getDepartureCityId());
        statement.setLong(2, element.getArrivalCityId());
        statement.setLong(3, element.getRepetitionId());
        statement.setInt(4, element.getAverageTravelTime());
        statement.setFloat(5, element.getTravelDistance());
        statement.execute();
        statement.close();
        connection.close();
    }

    @Override
    public void update(Route element) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "UPDATE Routes SET " +
                "DepartureCityId = ?, " +
                "ArrivalCityId = ?, " +
                "RepetitionId = ?, " +
                "AverageTravelTime = ?, " +
                "TravelDistance = ? " +
                "WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, element.getDepartureCityId());
        statement.setLong(2, element.getArrivalCityId());
        statement.setLong(3, element.getRepetitionId());
        statement.setInt(4, element.getAverageTravelTime());
        statement.setFloat(5, element.getTravelDistance());
        statement.setLong(6, element.getId());
        statement.execute();
        statement.close();
        connection.close();
    }

    @Override
    public void delete(long id) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "DELETE FROM Routes WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, id);
        statement.execute();
        statement.close();
        connection.close();
    }

    public ResultSet getComboboxContents() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String query = "SELECT Routes.id, CONCAT(\"(\", d.cityName, \" | \", a.cityName, \")\")\n" +
                "FROM routes INNER JOIN Cities AS d ON\n" +
                "Routes.DepartureCityId = d.id\n" +
                "INNER JOIN Cities AS a ON \n" +
                "Routes.ArrivalCityId = a.id";
        return statement.executeQuery(query);
    }
}
