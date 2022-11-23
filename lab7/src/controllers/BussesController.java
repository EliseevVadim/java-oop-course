package controllers;

import models.Bus;
import repositories.BussesRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BussesController {
    private final BussesRepository repository;

    public BussesController(BussesRepository repository) {
        this.repository = repository;
    }

    public ResultSet getAllBusses() throws SQLException {
        return repository.getAll();
    }

    public ResultSet getAllBussesForCombobox() throws SQLException {
        return repository.getComboboxContents();
    }

    public Bus getById(long id) throws SQLException {
        return repository.getById(id);
    }

    public void createBus(String carNumber, byte sitPlaces, byte generalCapacity, long travelCompanyId) throws SQLException {
        repository.create(new Bus(carNumber, sitPlaces, generalCapacity, travelCompanyId));
    }

    public void updateBus(long id, String carNumber, byte sitPlaces, byte generalCapacity, long travelCompanyId) throws SQLException {
        repository.update(new Bus(id, carNumber, sitPlaces, generalCapacity, travelCompanyId));
    }

    public void deleteBus(long id) throws SQLException {
        repository.delete(id);
    }
}
