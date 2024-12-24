/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package ledger.system;

import database.DatabaseConnector;
import database.HistoryValue;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumn;

/**
 *
 * @author Liyik
 */
public class HistoryUI {
static JFrame frame;
static JTable table;

    public HistoryUI(){
        DatabaseConnector dbcon = new DatabaseConnector();
        
        JPanel Header=new JPanel();
        Header.setBounds(0,0,720,100);
        Header.setBackground(new Color(56,36,128));
        
        JPanel body=new JPanel();
        body.setBounds(0,100,720,380);
        body.setBackground(new Color(240,240,240));
        
        JLabel title=new JLabel("Transaction History");
        title.setFont(new Font("Algerian",Font.BOLD,25));
        title.setBounds(60,50,600,50);
        title.setForeground(Color.white);
        int rowcount=0;
        Object[][] data=new Object[HistoryValue.getRowCount(MyFrame.userId, rowcount)][5];
        HistoryValue.getHistory(MyFrame.userId,data);
        
        
        JButton export=new JButton("Export");
        export.setBounds(600,5,100,30);
        export.setFocusable(false);
        export.setBackground(new Color(247,239,218));
        export.addActionListener((ActionEvent e)->{
           if (e.getSource()==export){
               ExportToCSV.csv(data);
           } 
        });
        
        
        
        String []columnName={"Date","Description","Debit","Credit","Balance"};
        String[]month={"JAN","FEB","MAC","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
        JComboBox comboBox=new JComboBox(month);
        comboBox.setBounds(550,60,100,25);
        comboBox.addActionListener((ActionEvent e)->{
          if (e.getSource()==comboBox){
              System.out.println(comboBox.getSelectedItem());
              
          }
        });
        
        

        table=new JTable();

        table.setFont(new Font("Consolas",Font.PLAIN,15));
        table.setShowGrid(false);
        table.setModel(new MyTableModel(data,columnName));
        table.setDefaultRenderer(Object.class,new MultiLineCellRenderer());

        TableColumn column;
        for(int i=0;i<5;i++){
            column=table.getColumnModel().getColumn(i);
            switch (i) {
                case 1 -> column.setPreferredWidth(200);
                case 2 -> column.setPreferredWidth(100);
                default -> column.setPreferredWidth(70);
            }
        }
        for (int row=0;row<table.getRowCount();row++){
            int rowHeight=table.getRowHeight();
            for (int colum=0;colum<table.getColumnCount();colum++){
                Component comp=table.prepareRenderer(table.getCellRenderer(row, colum),row,colum);
                rowHeight=Math.max(rowHeight, comp.getPreferredSize().height);
            }
             table.setRowHeight(row,rowHeight);
        }
        
        JScrollPane scp=new JScrollPane(table);
        scp.setBounds(0,100,720,380);
        
        JLayeredPane layer=new JLayeredPane();
        layer.setLayout(null);
        layer.setBounds(0,0,720,480);
        layer.add(Header, Integer.valueOf(0));
        layer.add(body, Integer.valueOf(0));
        layer.add(scp, Integer.valueOf(1));
        layer.add(title, Integer.valueOf(1));
        layer.add(export, Integer.valueOf(1));
        //layer.add(comboBox, Integer.valueOf(1));
        //table.getTableHeader().setBounds(50,50,520,50);
        //table.setBounds(50,100,520,280);
        
        frame=new JFrame();
        frame.setLayout(null);
        frame.setBounds(0,0,720,480);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(layer);
        frame.setVisible(true);
        
        frame.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent we){

                TransactionUI.WindowStatus=false;
                frame.dispose();
                
            
        }
        
    });
        

    }public static void main(String[]args){
         SwingUtilities.invokeLater(HistoryUI::new);
    }
}
