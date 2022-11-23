package repositories;

import models.SoldTicket;

import java.sql.*;

public class SoldTicketsRepository implements iRepository<SoldTicket> {

    @Override
    public ResultSet getAll() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String query = "SELECT soldTickets.id, soldTickets.TicketPrice AS \"Цена билета\", SitNumber AS \"Место\" , CONCAT(\"(\", d.cityName, \" | \", a.cityName, \") : \", DepartureTime, \" - \", ArrivalTime) AS \"Рейс\", EmployeeFIO AS \"Продавец\"\n" +
                "FROM SoldTickets INNER JOIN flights ON SoldTickets.FlightId = flights.id\n" +
                "INNER JOIN employees ON SoldTickets.SellerId = employees.id\n" +
                "INNER JOIN routes ON flights.RouteId = routes.id\n" +
                "INNER JOIN cities AS d ON routes.DepartureCityId = d.id\n" +
                "INNER JOIN cities AS a ON routes.ArrivalCityId = a.id\n" +
                "INNER JOIN busses ON flights.BusId = busses.id";
        return statement.executeQuery(query);
    }

    @Override
    public SoldTicket getById(long id) throws SQLException {
        return null;
    }

    @Override
    public void create(SoldTicket element) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "INSERT INTO SoldTickets (TicketPrice, SitNumber, FlightId, SellerId) " +
                "values (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, element.getTicketPrice());
        statement.setByte(2, element.getSitNumber());
        statement.setLong(3, element.getFlightId());
        statement.setLong(4, element.getSellerId());
        statement.execute();
        statement.close();
        connection.close();
    }

    @Override
    public void update(SoldTicket element) throws SQLException {

    }

    @Override
    public void delete(long id) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "DELETE FROM SoldTickets WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, id);
        statement.execute();
        statement.close();
        connection.close();
    }
}
