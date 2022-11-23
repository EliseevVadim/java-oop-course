package controllers;

import models.City;
import repositories.CitiesRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CitiesController {
    private final CitiesRepository repository;

    public CitiesController(CitiesRepository repository) {
        this.repository = repository;
    }

    public ResultSet getAllCities() throws SQLException {
        return repository.getAll();
    }

    public ResultSet getAllCitiesForCombobox() throws SQLException {
        return repository.getComboboxContents();
    }

    public City getById(long id) throws SQLException {
        return repository.getById(id);
    }

    public void createCity(String cityName) throws SQLException {
        repository.create(new City(cityName));
    }

    public void updateCity(long id, String cityName) throws SQLException {
        repository.update(new City(id, cityName));
    }

    public void deleteCity(long id) throws SQLException {
        repository.delete(id);
    }
}
