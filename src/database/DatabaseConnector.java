package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydb"; 
    private static final String DB_USER = "user";
    private static final String DB_PASSWORD = "userpassword1234"; 

    private static Connection connection;

    //Constructor
    public DatabaseConnector() {
        try {
            //Get connection
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
        }
    }
    
    //Method to provides connection to the other methods
    public static Connection getConnection(){
        return connection;
    }

    // Method to close the database connection
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Failed to close the connection.");
            }
        }
    }
}
