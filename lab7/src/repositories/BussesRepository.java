package repositories;

import models.Bus;

import java.sql.*;

public class BussesRepository implements iRepository<Bus> {

    @Override
    public ResultSet getAll() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String query = "SELECT Busses.id, CarNumber AS \"Номер машины\", SitPlaces AS \"Количество сидячих мест\", " +
                "GeneralCapacity AS \"Общая вместимость\", CompanyName AS \"Компания-владелец\" " +
                "FROM Busses INNER JOIN TravelCompanies ON " +
                "Busses.TravelCompanyId = TravelCompanies.id";
        return statement.executeQuery(query);
    }

    @Override
    public Bus getById(long id) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "SELECT id, CarNumber, SitPlaces, GeneralCapacity, TravelCompanyId FROM Busses " +
                "WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, id);
        ResultSet set = statement.executeQuery();
        if (set.next()) {
            return new Bus(set.getInt(1), set.getString(2), set.getByte(3), set.getByte(4), set.getLong(5));
        }
        return null;
    }

    @Override
    public void create(Bus element) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "INSERT INTO Busses (CarNumber, SitPlaces, GeneralCapacity, TravelCompanyId) " +
                "values (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, element.getCarNumber());
        statement.setByte(2, element.getSitPlaces());
        statement.setByte(3, element.getGeneralCapacity());
        statement.setLong(4, element.getTravelCompanyId());
        statement.execute();
        statement.close();
        connection.close();
    }

    @Override
    public void update(Bus element) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "UPDATE Busses SET " +
                "CarNumber = ?, " +
                "SitPlaces = ?, " +
                "GeneralCapacity = ?, " +
                "TravelCompanyId = ? " +
                "WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, element.getCarNumber());
        statement.setByte(2, element.getSitPlaces());
        statement.setByte(3, element.getGeneralCapacity());
        statement.setLong(4, element.getTravelCompanyId());
        statement.setLong(5, element.getId());
        statement.execute();
        statement.close();
        connection.close();
    }

    @Override
    public void delete(long id) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        String query = "DELETE FROM Busses WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, id);
        statement.execute();
        statement.close();
        connection.close();
    }

    public ResultSet getComboboxContents() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String query = "SELECT id, CarNumber FROM Busses";
        return statement.executeQuery(query);
    }
}
