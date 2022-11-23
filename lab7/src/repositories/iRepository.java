package repositories;

import utils.ConfigurationService;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface iRepository<T> {
    String url = ConfigurationService.getConnectionUrl();
    String user = ConfigurationService.getUser();
    String password = ConfigurationService.getPassword();

    ResultSet getAll() throws SQLException;
    T getById(long id) throws SQLException;
    void create(T element) throws SQLException;
    void update(T element) throws SQLException;
    void delete(long id) throws SQLException;
}
