package controllers;

import models.TravelCompany;
import repositories.TravelCompaniesRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TravelCompaniesController {
    private final TravelCompaniesRepository repository;

    public TravelCompaniesController(TravelCompaniesRepository repository) {
        this.repository = repository;
    }

    public ResultSet getAllCompanies() throws SQLException {
        return repository.getAll();
    }

    public ResultSet getAllCompaniesForCombobox() throws SQLException {
        return repository.getComboboxContents();
    }

    public TravelCompany getById(long id) throws SQLException {
        return repository.getById(id);
    }

    public void createCompany(String companyName, String companyINN) throws SQLException {
        repository.create(new TravelCompany(companyName, companyINN));
    }

    public void updateCompany(long id, String companyName, String companyINN) throws SQLException {
        repository.update(new TravelCompany(id, companyName, companyINN));
    }

    public void deleteCompany(long id) throws SQLException {
        repository.delete(id);
    }
}
