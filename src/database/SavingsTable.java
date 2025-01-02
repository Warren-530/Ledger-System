package database;

import static database.DatabaseConnector.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class SavingsTable {

    public static Connection connection = getConnection();

    public static void updateSavings(int userId, String status, int percentage) {
        String sql = "UPDATE savings SET status = ?, percentage = ? WHERE user_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // update values for placeholders
            
            stmt.setString(1, "Active");
            stmt.setInt(2, percentage);
            stmt.setInt(3, userId);

            // Execute the insertion
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error update savings: " + e.getMessage());
        }
    }
}