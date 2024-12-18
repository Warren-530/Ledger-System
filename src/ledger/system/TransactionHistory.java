/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ledger.system;

/**
 *
 * @author Liyik
 */
import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import static database.DatabaseConnector.getConnection;

public class TransactionHistory {
    
    public static void getFilteredAndSortedHistory(Object[][] history, int filterIndex, int sortIndex, Date startDate, Date endDate, double minAmount, double maxAmount, String sortOrder, String transactionType, int userId) {
        public static Connection connection = getConnection();
        StringBuilder sql = new StringBuilder("SELECT * FROM transactions WHERE user_id = ?");
        public static Connection connection = getConnection();
        ArrayList<Object> parameters = new ArrayList<>();

        parameters.add(userId); // First parameter is always user_id

        // Add Filtering Conditions Based on filterIndex
        switch (filterIndex) {
            case 0: // Filter by Date
                sql.append(" AND date BETWEEN ? AND ?");
                parameters.add(new java.sql.Date(startDate.getTime()));
                parameters.add(new java.sql.Date(endDate.getTime()));
                break;
            case 1: // Filter by Transaction Type
                sql.append(" AND transaction_type = ?");
                parameters.add(transactionType); // "Debit" or "Credit"
                break;
            case 2: // Filter by Amount Range
                sql.append(" AND amount BETWEEN ? AND ?");
                parameters.add(minAmount);
                parameters.add(maxAmount);
                break;
        }

        // Add Sorting Condition Based on sortIndex
        if (sortIndex == 0) { // Sort by Date
            sql.append(" ORDER BY date ");
            sql.append(sortOrder.equalsIgnoreCase("asc") ? "ASC" : "DESC");
        } else if (sortIndex == 1) { // Sort by Amount
            sql.append(" ORDER BY amount ");
            sql.append(sortOrder.equalsIgnoreCase("asc") ? "ASC" : "DESC");
        }

        try (PreparedStatement stmt = connection.prepareStatement(sql.toString())) {
            // Set all parameters dynamically
            for (int i = 0; i < parameters.size(); i++) {
                stmt.setObject(i + 1, parameters.get(i));
            }

            // Execute the query
            ResultSet rs = stmt.executeQuery();
            int i = 0;

            // Populate the history array
            while (rs.next()) {
                history[i][0] = rs.getObject("date");
                history[i][1] = rs.getObject("description");

                String type = rs.getString("transaction_type");
                if (type.equals("Debit")) {
                    history[i][2] = rs.getObject("amount");
                    history[i][3] = "";
                } else {
                    history[i][2] = "";
                    history[i][3] = rs.getObject("amount");
                }

                history[i][4] = rs.getObject("balance");
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Error fetching filtered/sorted history: " + e.getMessage());
        }
    }
}
