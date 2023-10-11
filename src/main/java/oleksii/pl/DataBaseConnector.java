package oleksii.pl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnector {
    private static final String URL = "jdbc:postgresql://localhost:5432/PizzaHouseDB";
    private static final String USER = "postgres";
    private static final String PASSWORD = "    ";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
