package database;

import static database.DatabaseConnector.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.sql.Date;

public class TransactionsTable {

    public static Connection connection = getConnection();

    public static void insertTransaction(int userId, String transactionType, double amount, String description, LocalDate date, double balance) {
        String sql = "INSERT INTO transactions (user_id, transaction_type, amount, description, date, balance) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Set values for placeholders
            stmt.setInt(1, userId);
            stmt.setString(2, transactionType);
            stmt.setDouble(3, amount);
            stmt.setString(4, description);
            stmt.setDate(5, Date.valueOf(date));//Convert LocalDate to Date in sql
            stmt.setDouble(6, balance);

            // Execute the insertion
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting transaction: " + e.getMessage());
        }
    }
}
