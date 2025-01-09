package ledger.system;


import database.DatabaseConnector;
import java.sql.SQLException;


public class LedgerSystem {

    public static void main(String[] args) throws SQLException {

        //Method calling to bulid connection
        DatabaseConnector dbcon = new DatabaseConnector();
        System.out.println("== Ledger System ==");
        Login myframe=new Login();
       
 

    }

}
