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
    
    public static boolean SavingActive(int userId){
        String sql = "SELECT status FROM savings WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    return rs.getBoolean("status");
                } else {
                    return false;
                }
        } catch (SQLException e) {
            System.out.println("Error inserting transaction: " + e.getMessage());
        }
        return false;
    
    }
    public static boolean updateSaving(int userId,boolean status,double percentage){
        String updateSql = "UPDATE savings SET status = ?, percentage = ? WHERE user_id = ?";

            try (PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
                updateStmt.setBoolean(1, status);
                updateStmt.setDouble(2, percentage);
                updateStmt.setInt(3, userId);

                updateStmt.executeUpdate();
                
        } catch (SQLException e) {
            System.out.println("Error updating status: " + e.getMessage());
        }
        return false;
    
    }
        public static double getPercentage(int userId) {
        String sql = "SELECT percentage FROM savings WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1,userId);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()){
                return rs.getDouble("percentage");
            } else {
                return 0;
            }
        }catch (SQLException e) {
            System.out.println("Error fetching balance:"+e.getMessage());
            return 0;
        }
    }
        public static boolean EndOfMonthCheck(){
            LocalDate today=LocalDate.now();
            LocalDate lastDay=today.withDayOfMonth(today.lengthOfMonth());
            return today.equals(lastDay);
        }
        
}
