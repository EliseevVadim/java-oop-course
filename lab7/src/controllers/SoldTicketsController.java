package controllers;

import models.SoldTicket;
import repositories.SoldTicketsRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SoldTicketsController {
    private final SoldTicketsRepository repository;

    public SoldTicketsController(SoldTicketsRepository repository) {
        this.repository = repository;
    }

    public ResultSet getAllTickets() throws SQLException {
        return repository.getAll();
    }

    public void sellTicket(int ticketPrice, byte sitNumber, long flightId, long sellerId) throws SQLException {
        repository.create(new SoldTicket(ticketPrice, sitNumber, flightId, sellerId));
    }

    public void deleteTicket(long id) throws SQLException {
        repository.delete(id);
    }
}
