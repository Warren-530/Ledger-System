package database;

import static database.DatabaseConnector.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class SavingsTable {

    public static Connection connection = getConnection();

    public static void insertSavings(int userId, String status, int percentage) {
        String sql = "INSERT INTO savings (user_id, , status, percentage) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Set values for placeholders
            stmt.setInt(1, userId);
            stmt.setString(2, status);
            stmt.setDouble(3, percentage);

            // Execute the insertion
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting savings: " + e.getMessage());
        }
    }
}
