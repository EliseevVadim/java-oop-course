package controllers;

import models.RouteRepetition;
import repositories.RouteRepetitionsRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteRepetitionsController {
    private final RouteRepetitionsRepository repository;

    public RouteRepetitionsController(RouteRepetitionsRepository repository) {
        this.repository = repository;
    }

    public ResultSet getAllRepetitions() throws SQLException {
        return repository.getAll();
    }

    public ResultSet getAllRepetitionsForCombobox() throws SQLException {
        return repository.getComboboxContents();
    }

    public RouteRepetition getById(long id) throws SQLException {
        return repository.getById(id);
    }

    public void createRepetition(String repetitionName) throws SQLException {
        repository.create(new RouteRepetition(repetitionName));
    }

    public void updateRepetition(long id, String repetitionName) throws SQLException {
        repository.update(new RouteRepetition(id, repetitionName));
    }

    public void deleteRepetition(long id) throws SQLException {
        repository.delete(id);
    }
}
