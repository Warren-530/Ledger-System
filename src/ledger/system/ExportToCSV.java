package ledger.system;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class ExportToCSV {
    public static void csv(Object[][] history) {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream("TransactionHistory.csv"));
            pw.println("Date,Description,Debit,Credit,Balance");
            for (Object[] row : history) {
                for (Object col : row) {
                    pw.print(col);
                    pw.print(",");

                }
                pw.println();
            }
            pw.close();
            System.out.println("File Exported!");
        } catch (IOException e) {
            System.out.println("Problem with file output");
        }

    }

}