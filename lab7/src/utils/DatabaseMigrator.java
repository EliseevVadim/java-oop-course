package utils;

import com.ibatis.common.jdbc.ScriptRunner;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseMigrator {
    public void createTablesIfNotExists() throws SQLException, IOException {
        String url = ConfigurationService.getConnectionUrl();
        String user = ConfigurationService.getUser();
        String password = ConfigurationService.getPassword();
        Connection connection = DriverManager.getConnection(url, user, password);
        ScriptRunner runner = new ScriptRunner(connection, false, false);
        InputStreamReader scriptReader = new InputStreamReader(new FileInputStream("./config/migration.sql"));
        runner.runScript(scriptReader);
        scriptReader.close();
        connection.close();
        System.out.println("Tables was created");
    }
}
