package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLConnector {
    private static Connection conn;

    private SQLConnector() {
        // Private constructor to prevent instantiation
    }

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost/hospitalreservationapp";
                String user = "root";
                String pass = "";
                conn = DriverManager.getConnection(url, user, pass);
                System.out.println("Database connection established.");
            } catch (ClassNotFoundException | SQLException e) {
                handleException("Error establishing database connection", e);
            }
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                handleException("Error closing database connection", e);
            }
        }
    }

    private static void handleException(String message, Exception e) {
        System.err.println(message);
        e.printStackTrace();
        Logger.getLogger(SQLConnector.class.getName()).log(Level.SEVERE, null, e);
    }
}
