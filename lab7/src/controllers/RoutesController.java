package controllers;

import models.Route;
import repositories.RoutesRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoutesController {
    private final RoutesRepository repository;

    public RoutesController(RoutesRepository repository) {
        this.repository = repository;
    }

    public ResultSet getAllRoutes() throws SQLException {
        return repository.getAll();
    }

    public ResultSet getAllRoutesForCombobox() throws SQLException {
        return repository.getComboboxContents();
    }

    public Route getById(long id) throws SQLException {
        return repository.getById(id);
    }

    public void createRoute(long departureCityId, long arrivalCityId, long repetitionId, int averageTravelTime, float travelDistance) throws SQLException {
        repository.create(new Route(departureCityId, arrivalCityId, repetitionId, averageTravelTime, travelDistance));
    }

    public void updateRoute(long id, long departureCityId, long arrivalCityId, long repetitionId, int averageTravelTime, float travelDistance) throws SQLException {
        repository.update(new Route(id, departureCityId, arrivalCityId, repetitionId, averageTravelTime, travelDistance));
    }

    public void deleteRoute(long id) throws SQLException {
        repository.delete(id);
    }
}
