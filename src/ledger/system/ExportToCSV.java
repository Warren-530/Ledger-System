package ledger.system;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ExportToCSV {
    private static String filename;
    public static Path getOutputPath(String s){
        JFileChooser location=s ==null?new JFileChooser():new JFileChooser(s);
        
        FileNameExtensionFilter restrict=new FileNameExtensionFilter("Only .csv files","csv");
        location.addChoosableFileFilter(restrict);
        location.setAcceptAllFileFilterUsed(false);
        location.setSelectedFile(new File("TransactionHistory"));
        location.setDialogTitle("Choose export location.");
        location.setSize(720,480);
        int returnVal= location.showSaveDialog(null);
        
        if (returnVal !=JFileChooser.APPROVE_OPTION) 
            return null;

        filename=location.getSelectedFile().getName();

        System.out.println(location.getSelectedFile().toPath());
        return location.getSelectedFile().toPath();
    }
    public static void csv(Object[][] history) {
        Path path=getOutputPath(null);
        if (path!=null){
            try {
                PrintWriter pw = new PrintWriter(new FileOutputStream(path+".csv"));
  
                pw.println("Date,Description,Debit,Credit,Balance");
                for (Object[] row : history) {
                    for (Object col : row) {
                        pw.print(col);
                        pw.print(",");

                    }
                    pw.println();
                }
                pw.close();
                JOptionPane.showMessageDialog(null,"File Exported!","File export", JOptionPane.PLAIN_MESSAGE);
                System.out.println("File Exported!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,"Problem with file output.","File export", JOptionPane.ERROR_MESSAGE);
                System.out.println("Problem with file output");
            }
        }

    }

}