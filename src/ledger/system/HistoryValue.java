/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ledger.system;
import static database.DatabaseConnector.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import ledger.system.MyFrame;
/**
 *
 * @author Liyik
 */
public class HistoryValue {
    public static Connection connection = getConnection();
    public static void getHistory(int userId, Object[][] history){
        
        String sql = "SELECT * FROM transactions WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1,userId);
            
            ResultSet rs = stmt.executeQuery();
            int i=0;
            while (rs.next()){
                history[i][0]=rs.getObject("date");
                history[i][1]=rs.getObject("description");
                String type = rs.getString("transaction_type");
                if(type.equals("Debit")){
                    history[i][2]=rs.getObject("amount");
                    history[i][3]="";
                }else{
                    history[i][2]="";
                    history[i][3]=rs.getObject("amount");
                }
                history[i][4]=rs.getObject("balance");
                i++;
            } 
        }catch (SQLException e) {
            System.out.println("Error fetching balance:"+e.getMessage());

        }
    }
    public static int getRowCount(int userId, int a){
        String sql = "SELECT COUNT(*) AS row_count FROM transactions WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1,userId);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("row_count");
                
            }
        }catch (SQLException e) {
            System.out.println("Error fetching balance:"+e.getMessage());
            
        }
        return 0;
    }
}
    

