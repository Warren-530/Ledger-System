package database;

import static database.DatabaseConnector.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class LoansTable {

    public static Connection connection = getConnection();

    public static void insertLoan(int userId, double principal_amount, double interest, int period, double total_loan, String status) {
        String sql = "INSERT INTO loans (user_id, principal_amount, interest_rate, repayment_period, outstanding_balance, status, created_at) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {            
            // update values for placeholders
            
            stmt.setInt(1, userId);
            stmt.setDouble(2, principal_amount);
            stmt.setDouble(3, interest);
            stmt.setInt(4, period);
            stmt.setDouble(5, total_loan);
            stmt.setString(6, status);

            // Set timestamp
            Date created_at = new Date();
            Timestamp timestamp = new Timestamp(created_at.getTime());
            stmt.setTimestamp(7, timestamp);

            // Execute the insertion
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error insert loans: " + e.getMessage());
        }
    }

    // update loan must be minus from the total
    public static void updateLoan(int userId, double remaining, String status) {
        String sql = "UPDATE loans SET outstanding_balance = ?, status = ? WHERE user_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {            
            // update values for placeholders
            
            stmt.setDouble(1, remaining);
            stmt.setString(2, status);
            stmt.setInt(3, userId);

            // Execute the insertion
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error insert loans: " + e.getMessage());
        }
    }

    // method to get the lastest user principal
    public static double getPrincipal(int userId) {
        String sql = "SELECT principal_amount FROM loans WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();

            // Check if a row is returned
            if (rs.next()) {
                // Return the balance
                return rs.getDouble("principal_amount");
            } else {
                return 0.00;
            }
        } catch (SQLException e) {
            System.out.println("Error fetching principal amount: " + e.getMessage());
            return 0.00;
        }
    }

    // method to get status
    public static String getStatus(int userId) {
        String sql = "SELECT status FROM loans WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();

            // Check if a row is returned
            if (rs.next()) {
                // Return the balance
                return rs.getString("status");
            } else {
                return "false";
            }
        } catch (SQLException e) {
            System.out.println("Error fetching status: " + e.getMessage());
            return null;
        }
    }

    // method to get the lastest loan
    public static double getLoan(int userId) {
        String sql = "SELECT outstanding_balance FROM loans WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();

            // Check if a row is returned
            if (rs.next()) {
                // Return the balance
                return rs.getDouble("outstanding_balance");
            } else {
                return 0.00;
            }
        } catch (SQLException e) {
            System.out.println("Error fetching outstanding balance: " + e.getMessage());
            return 0.00;
        }
    }

    // method to get the lastest timestamp
    public static Timestamp getCreateAt(int userId) {
        String sql = "SELECT created_at FROM loans WHERE user_id = ? ORDER BY created_at DESC LIMIT 1";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();

            // Check if a row is returned
            if (rs.next()) {
                // Return the balance
                return rs.getTimestamp("created_at");
            } else {
                // testing there's a tracking history or not
                System.out.println("No loan found for the user");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error fetching created at: " + e.getMessage());
            return null;
        }
    }

    // method to get the period
    public static int getPeriod(int userId) {
        String sql = "SELECT repayment_period FROM loans WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();

            // Check if a row is returned
            if (rs.next()) {
                // Return the balance
                return rs.getInt("repayment_period");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            System.out.println("Error fetching repayment_period: " + e.getMessage());
            return 0;
        }
    }
    
}
