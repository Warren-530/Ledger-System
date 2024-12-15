package database;

import static database.DatabaseConnector.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import org.mindrot.jbcrypt.BCrypt;

import com.mysql.cj.protocol.Resultset;
import java.sql.Statement;

//Class that contains all methods about SQL queries
public class UserTable {

    public static Connection connection = getConnection();

    // Method to insert a new user
    public static void insertUser(String name, String email, String hashedPassword) {
        String sql = "INSERT INTO user (name, email, password) VALUES (?, ?, ?)";
        String sql2 = "INSERT INTO accountbalance (user_id, balance, savings, loan) VALUES (?, NULL, NULL, NULL)";
        String sql3 = "INSERT INTO savings (user_id, status, percentage) VALUES (?, 'Inactive', NULL)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, hashedPassword);

            stmt.executeUpdate();

            // Insert account userId
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int userId = rs.getInt(1); // userId is auto-incremented
                
                // Insert the userId into the ACCOUNT table
                try (PreparedStatement stmt2 = connection.prepareStatement(sql2)) {
                    stmt2.setInt(1, userId);
                    stmt2.executeUpdate();
                }
                

                // Insert the userId into the SAVINGS table
                try (PreparedStatement stmt3 = connection.prepareStatement(sql3)) {
                    stmt3.setInt(1, userId);
                    stmt3.executeUpdate();
                }

            } else {
                throw new SQLException("Failed to retrieve generated userId");
            }
                
        } catch (SQLException e) {
            System.out.println("Error inserting user: " + e.getMessage());
        }
    }

    // Method to check if the account already exists in the database
    public static boolean isRegisteredAccount(String email) {
        String sql = "SELECT COUNT(*) FROM user WHERE email = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);

            // Execute the query
            ResultSet rs = stmt.executeQuery();

            // Check if the email exists in the result
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // If count > 0, email exists, otherwise it doesn't
            }
        } catch (SQLException e) {
            System.out.println("Error checking email: " + e.getMessage());
        }

        return false; // Return false if email doesn't exist
    }

    //check the password with the password of a particular email 
    public static boolean checkPassword(String email, String password) throws SQLException {
        // Select the password from email entered
        String sql = "SELECT password FROM user WHERE email = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);

        //Set ? with email
        stmt.setString(1, email);

        // Execute the query and get the result set
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            // Get the stored hashed password from the result set in column "password"
            String storedHashedPassword = rs.getString("password");

            // Compare the entered password with the stored hashed password using BCrypt
            return BCrypt.checkpw(password, storedHashedPassword);
        }
        return false;
    }

    //Obtain the user id
    public static int getUserId(String email) throws SQLException {
        // SQL query to get user_id based on email
        String sql = "SELECT user_id FROM user WHERE email = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, email);

        // Execute the query
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            // Return the user_id if email is found
            return rs.getInt("user_id");
        } else {
            // Return -1 if the email is not found
            return 0;
        }
    }

    public static String getName(int userId) throws SQLException {
        String sql = "SELECT name FROM user WHERE user_id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, userId); 

        ResultSet rs = stmt.executeQuery();

        // If a row exists, return the name
        if (rs.next()) {
            return rs.getString("name");
        } else {
            // Return null if user_id is not found
            return null;
        }

    }
}


