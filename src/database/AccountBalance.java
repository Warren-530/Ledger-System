package database;

import static database.DatabaseConnector.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class AccountBalance {

    public static Connection connection = getConnection();

    //method to get the lastest user balance
    
    public static String getName(int userId) {
        String sql = "SELECT name FROM user WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1,userId);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()){
                return rs.getString("name");
            } else {
                return null;
            }
        }catch (SQLException e) {
            System.out.println("Error fetching balance:"+e.getMessage());
            return null;
        }
    }
        
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
    public static double debitBalance(int userId, double amountToAdd, boolean savingStatus,double percentage) {
        String selectSql = "SELECT balance FROM accountbalance WHERE user_id = ?";
        
        double currentBalance = 0.0;
        try {
            // Fetch the current balance
            
            try (PreparedStatement selectStmt = connection.prepareStatement(selectSql)) {
                selectStmt.setInt(1, userId);
                ResultSet rs = selectStmt.executeQuery();

                if (rs.next()) {
                    currentBalance = rs.getDouble("balance");
                } else {
                    System.out.println("No account found for user_id: " + userId);
                }
                if (savingStatus){
                    savingBalance(userId,(amountToAdd*percentage/100));
                    return currentBalance + (amountToAdd*((100-percentage)/100));
                }else{
                    return currentBalance + amountToAdd;
                }
            }
        }catch (SQLException e) {
            System.out.println("Error updating balance: " + e.getMessage());
            return currentBalance;  
            }

}
    public static void updateBalance(int userId, double amount) {
            String updateSql = "UPDATE accountbalance SET balance = ? WHERE user_id = ?";
            // Calculate the new balance
            

            // Update the balance
            try (PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
                updateStmt.setDouble(1, amount);
                updateStmt.setInt(2, userId);
                updateStmt.executeUpdate();
            }
         catch (SQLException e) {
            System.out.println("Error updating balance: " + e.getMessage());
        }
    }
    

    //Update the balance for credit operation
        public static double creditBalance(int userId, double amountToAdd) {
        String selectSql = "SELECT balance FROM accountbalance WHERE user_id = ?";
        
        double currentBalance = 0.0;

            
            try (PreparedStatement selectStmt = connection.prepareStatement(selectSql)) {
                selectStmt.setInt(1, userId);
                ResultSet rs = selectStmt.executeQuery();

                if (rs.next()) {
                    currentBalance = rs.getDouble("balance");
                    return currentBalance - amountToAdd;
                } else {
                    System.out.println("No account found for user_id: " + userId);
                    return currentBalance;
                }
                
            
        }catch (SQLException e) {
            System.out.println("Error updating balance: " + e.getMessage());
            return currentBalance;  
            }
        }
        
        public static void savingBalance(int userId, double amount) {
        String selectSql = "UPDATE accountbalance SET savings =? WHERE user_id = ?";
        
        double balance=getSavings(userId);
        try {
            // Fetch the current balance
            
            try (PreparedStatement selectStmt = connection.prepareStatement(selectSql)) {
                
                selectStmt.setDouble(1, balance+amount);
                selectStmt.setInt(2, userId);
                
                selectStmt.executeUpdate();
            }
        }catch (SQLException e) {
            System.out.println("Error updating balance: " + e.getMessage());
            
            }
}

}
