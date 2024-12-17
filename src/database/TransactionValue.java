/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import static database.DatabaseConnector.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
/**
 *
 * @author Liyik
 */
public class TransactionValue {
    
    public static Connection connection = getConnection();

    //method to get the lastest user balance
    
    public static String getDate(int userId) {
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
}
