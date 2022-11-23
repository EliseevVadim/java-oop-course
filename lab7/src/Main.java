import utils.DatabaseMigrator;
import views.MainFrame;

import java.awt.*;
import java.io.IOException;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        DatabaseMigrator migrator = new DatabaseMigrator();
        migrator.createTablesIfNotExists();
        EventQueue.invokeLater(MainFrame::new);
    }
}
