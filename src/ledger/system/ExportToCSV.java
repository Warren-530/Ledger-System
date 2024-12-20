package ledger.system;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import javax.swing.JFileChooser;

public class ExportToCSV {
    public static Path getOutputPath(String s){
        JFileChooser location=s ==null?new JFileChooser():new JFileChooser(s);
        location.setDialogTitle("Choose export location.");
        location.setSize(720,480);
        
        int returnVal= location.showSaveDialog(null);
        
        if (returnVal !=JFileChooser.APPROVE_OPTION) 
            return null;
        return location.getSelectedFile().toPath();
    }
    public static void csv(Object[][] history) {
        Path path=getOutputPath(null);
        if (path!=null){
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

}