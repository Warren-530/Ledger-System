package database;

import static database.DatabaseConnector.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountBalance {

    public static Connection connection = getConnection();

    //method to get the lastest user balance
    public static double getBalance(int userId) {
        String sql = "SELECT balance FROM accountbalance WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();

            // Check if a row is returned
            if (rs.next()) {
                // Return the balance
                return rs.getDouble("balance");
            } else {
                return 0.00;
            }
        } catch (SQLException e) {
            System.out.println("Error fetching balance: " + e.getMessage());
            return 0.00;
        }
    }

    //method to get the lastest user balance
    public static double getSavings(int userId) {
        String sql = "SELECT savings FROM accountbalance WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();

            // Check if a row is returned
            if (rs.next()) {
                // Return the savings
                return rs.getDouble("savings");
            } else {
                return 0.00;
            }
        } catch (SQLException e) {
            System.out.println("Error fetching savings: " + e.getMessage());
            return 0.00;
        }
    }

    //method to get the lastest user balance
    public static double getLoan(int userId) {
        String sql = "SELECT loan FROM accountbalance WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();

            // Check if a row is returned
            if (rs.next()) {
                // Return the loan
                return rs.getDouble("loan");
            } else {
                return 0.00;
            }
        } catch (SQLException e) {
            System.out.println("Error fetching loan: " + e.getMessage());
            return 0.00;
        }
    }

    //Update the balance for debit operation
    public static void debitBalance(int userId, double amountToAdd) {
        String selectSql = "SELECT balance FROM accountbalance WHERE user_id = ?";
        String updateSql = "UPDATE accountbalance SET balance = ? WHERE user_id = ?";

        try {
            // Fetch the current balance
            double currentBalance = 0.0;
            try (PreparedStatement selectStmt = connection.prepareStatement(selectSql)) {
                selectStmt.setInt(1, userId);
                ResultSet rs = selectStmt.executeQuery();

                if (rs.next()) {
                    currentBalance = rs.getDouble("balance");
                } else {
                    System.out.println("No account found for user_id: " + userId);
                }
            }

            // Calculate the new balance
            double newBalance = currentBalance + amountToAdd;

            // Update the balance
            try (PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
                updateStmt.setDouble(1, newBalance);
                updateStmt.setInt(2, userId);
                updateStmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error updating balance: " + e.getMessage());
        }
    }

    //Update the balance for credit operation
    public static void creditBalance(int userId, double amountToMinus) {
        String selectSql = "SELECT balance FROM accountbalance WHERE user_id = ?";
        String updateSql = "UPDATE accountbalance SET balance = ? WHERE user_id = ?";

        try {
            // Fetch the current balance
            double currentBalance = 0.0;
            try (PreparedStatement selectStmt = connection.prepareStatement(selectSql)) {
                selectStmt.setInt(1, userId);
                ResultSet rs = selectStmt.executeQuery();

                if (rs.next()) {
                    currentBalance = rs.getDouble("balance");
                } else {
                    System.out.println("No account found for user_id: " + userId);
                }
            }

            // Calculate the new balance
            double newBalance = currentBalance - amountToMinus;

            // Update the balance
            try (PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
                updateStmt.setDouble(1, newBalance);
                updateStmt.setInt(2, userId);
                updateStmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error updating balance: " + e.getMessage());
        }
    }

}
