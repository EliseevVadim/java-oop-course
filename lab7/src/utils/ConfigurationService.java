package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationService {
    private static final String connectionUrl;
    private static final String user;
    private static final String password;

    static  {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("./config/db_configurations.ini"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        connectionUrl = properties.getProperty("CONNECTION_URL");
        user = properties.getProperty("USER");
        password = properties.getProperty("PASSWORD");
    }

    public static String getConnectionUrl() {
        return connectionUrl;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }
}
