package controllers;

import models.Flight;
import repositories.FlightsRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightsController {
    private final FlightsRepository repository;

    public FlightsController(FlightsRepository repository) {
        this.repository = repository;
    }

    public ResultSet getAllFlights() throws SQLException {
        return repository.getAll();
    }

    public Flight getById(long id) throws SQLException {
        return repository.getById(id);
    }

    public void createFlight(String departureTime, String arrivalTime, byte availableTickets, int ticketPrice, long routeId, long busId) throws SQLException {
        repository.create(new Flight(departureTime, arrivalTime, availableTickets, ticketPrice, routeId, busId));
    }

    public void updateFlight(long id, String departureTime, String arrivalTime, byte availableTickets, int ticketPrice, long routeId, long busId) throws SQLException {
        repository.update(new Flight(id, departureTime, arrivalTime, availableTickets, ticketPrice, routeId, busId));
    }

    public void turnBackTicket(long id) throws SQLException {
        repository.turnBackTicket(id);
    }

    public void sellTicket(long id) throws SQLException {
        repository.sellTicket(id);
    }

    public void deleteFlight(long id) throws SQLException {
        repository.delete(id);
    }
}
