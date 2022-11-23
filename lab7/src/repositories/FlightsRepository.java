package repositories;

import models.Flight;

import java.sql.*;

public class FlightsRepository implements iRepository<Flight> {
    @Override
    public ResultSet getAll() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String query = "SELECT Flights.id, DepartureTime AS \"Время отправления\", ArrivalTime AS \"Время прибытия\" , AvailableTickets AS \"Осталось билетов\", TicketPrice AS \"Цена билета\", CONCAT(\"(\", d.cityName, \" | \", a.cityName, \")\") AS \"Маршрут\", busses.CarNumber AS \"Номер автобуса\"\n" +
                "FROM flights INNER JOIN Routes ON flights.RouteId = Routes.id\n" +
                "INNER JOIN Cities AS d ON Routes.DepartureCityId = d.id\n" +
                "INNER JOIN Cities AS a ON Routes.ArrivalCityId = a.id\n" +
                "INNER JOIN busses ON Flights.BusId = Busses.id";
        return statement.executeQuery(query);
    }

    @Override
    public Flight getById(long id) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "SELECT id, DepartureTime, ArrivalTime, AvailableTickets, TicketPrice, RouteId, BusId FROM Flights " +
                "WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, id);
        ResultSet set = statement.executeQuery();
        if (set.next()) {
            return new Flight(set.getLong(1), set.getString(2), set.getString(3), set.getByte(4), set.getInt(5), set.getLong(6), set.getLong(7));
        }
        return null;
    }

    @Override
    public void create(Flight element) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "INSERT INTO Flights (DepartureTime, ArrivalTime, AvailableTickets, TicketPrice, RouteId, BusId) " +
                "values (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, element.getDepartureTime());
        statement.setString(2, element.getArrivalTime());
        statement.setByte(3, element.getAvailableTickets());
        statement.setInt(4, element.getTicketPrice());
        statement.setLong(5, element.getRouteId());
        statement.setLong(6, element.getBusId());
        statement.execute();
        statement.close();
        connection.close();
    }

    @Override
    public void update(Flight element) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "UPDATE Flights SET " +
                "DepartureTime = ?, " +
                "ArrivalTime = ?, " +
                "AvailableTickets = ?, " +
                "TicketPrice = ?, " +
                "RouteId = ?, " +
                "BusId = ? " +
                "WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, element.getDepartureTime());
        statement.setString(2, element.getArrivalTime());
        statement.setByte(3, element.getAvailableTickets());
        statement.setInt(4, element.getTicketPrice());
        statement.setLong(5, element.getRouteId());
        statement.setLong(6, element.getBusId());
        statement.setLong(7, element.getId());
        statement.execute();
        statement.close();
        connection.close();
    }

    public void turnBackTicket(long ticketId) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "UPDATE Flights SET\n" +
                "AvailableTickets = AvailableTickets + 1\n" +
                "WHERE Flights.id = (SELECT FlightId FROM soldTickets WHERE soldTickets.id = ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, ticketId);
        statement.execute();
        statement.close();
        connection.close();
    }

    public void sellTicket(long ticketId) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "UPDATE Flights SET\n" +
                "AvailableTickets = AvailableTickets - 1\n" +
                "WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, ticketId);
        statement.execute();
        statement.close();
        connection.close();
    }

    @Override
    public void delete(long id) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "DELETE FROM Flights WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, id);
        statement.execute();
        statement.close();
        connection.close();
    }
}
