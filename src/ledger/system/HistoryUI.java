/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package ledger.system;

import database.DatabaseConnector;
import database.HistoryTable;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumn;

/**
 *
 * @author Liyik
 */
public class HistoryUI {
static JFrame frame;
static JTable table;
static JDateChooser date;
static JDateChooser before;
static JDateChooser between1;
static JDateChooser between2;
static JDateChooser after;

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
        title.setBounds(30,50,600,50);
        title.setForeground(Color.white);
        int rowcount=0;
        Object[][] data=new Object[HistoryTable.getRowCount(MyFrame.userId, rowcount)][5];
        HistoryTable.getHistory(MyFrame.userId,data);
        
        
        JButton export=new JButton("Export");
        export.setBounds(600,5,100,30);
        export.setFocusable(false);
        export.setBackground(new Color(247,239,218));
        export.addActionListener((ActionEvent e)->{
           if (e.getSource()==export){
               ExportToCSV.csv(data);
           } 
        });
        
        JButton filter=new JButton("Filter");
        filter.setBounds(350,60,100,30);
        filter.setFocusable(false);
        
        String []columnName={"Date","Description","Debit","Credit","Balance"};
        String[]range={"Date","Before","Between","After"};
        JComboBox comboBox=new JComboBox(range);
        comboBox.setBounds(350,30,100,25);
        
        date=new JDateChooser();
        date.setBounds(470,30,100,30);
        date.setBackground(new Color(0,237,237));
        
        before=new JDateChooser();
        before.setBounds(470,30,100,30);
        before.setBackground(new Color(0,237,237));
        before.setVisible(false);
        
        
        between1=new JDateChooser();
        between1.setBounds(470,5,100,30);
        between1.setBackground(new Color(237,0,237));
        between1.setVisible(false);
        
        between2=new JDateChooser();
        between2.setBounds(470,45,100,30);
        between2.setBackground(new Color(237,0,237));
        between2.setVisible(false);
        
        after=new JDateChooser();
        after.setBounds(470,30,100,30);
        after.setForeground(Color.red);
        after.setVisible(false);
        
        
        

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
        scp.setBounds(0,100,705,370);
        
        JLayeredPane layer=new JLayeredPane();
        layer.setLayout(null);
        layer.setBounds(0,0,720,480);
        layer.add(Header, Integer.valueOf(0));
        layer.add(body, Integer.valueOf(0));
        layer.add(scp, Integer.valueOf(1));
        layer.add(title, Integer.valueOf(1));
        layer.add(export, Integer.valueOf(1));
        layer.add(comboBox, Integer.valueOf(1));
        layer.add(filter, Integer.valueOf(1));
        layer.add(date, Integer.valueOf(1));
        layer.add(before, Integer.valueOf(1));
        layer.add(between1, Integer.valueOf(1));
        layer.add(between2, Integer.valueOf(1));
        layer.add(after, Integer.valueOf(1));
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
        comboBox.addItemListener((ItemEvent e)->{
          if (e.getStateChange()==ItemEvent.SELECTED){
              resetDate();
              switch (comboBox.getSelectedIndex()){
                  case 0:
                    date.setVisible(true);
                    break;
                  case 1:
                    before.setVisible(true);
                    break;
                  case 2:
                    between1.setVisible(true);
                    between2.setVisible(true);
                    break;
                  case 3:
                    after.setVisible(true);
                    break;
              }
          }
        });
        filter.addActionListener((ActionEvent e)->{
            if (e.getSource()==filter){
                frame.dispose();
                new HistoryUI();
            }
        });

    }public static void main(String[]args){
         SwingUtilities.invokeLater(HistoryUI::new);
    }
    public void resetDate(){
        date.setDate(null);
        before.setDate(null);
        between1.setDate(null);
        between2.setDate(null);
        after.setDate(null);
        date.setVisible(false);
        before.setVisible(false);
        between1.setVisible(false);
        between2.setVisible(false);
        after.setVisible(false);
    }
}
