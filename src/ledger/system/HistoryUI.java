/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package ledger.system;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 *
 * @author Liyik
 */
public class HistoryUI {
static JFrame frame;
static JTable table;
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        JPanel Header=new JPanel();
        Header.setBounds(0,0,720,100);
        Header.setBackground(new Color(56,36,128));
        
        JPanel body=new JPanel();
        body.setBounds(0,100,720,380);
        body.setBackground(new Color(240,240,240));
        
        JLabel title=new JLabel("Transaction History");
        title.setFont(new Font("Algerian",Font.BOLD,35));
        title.setBounds(60,40,600,50);
        title.setForeground(Color.white);
        
        String[]month={"JAN","FEB","MAC","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
        JComboBox comboBox=new JComboBox(month);
        comboBox.setBounds(550,60,100,25);
        comboBox.addActionListener((ActionEvent e)->{
          if (e.getSource()==comboBox){
              System.out.println(comboBox.getSelectedItem());
          }
        });
        
        
        Object [][]data={{"Oct 24","living in a new house but having a new problem right now",new Double(1000),new Double(500),new Double(1000)},
                        {"Oct 24","living",new Double(1000),new Double(500),new Double(1000)}};
        String []columnName={"Date","Description","Debit","Credit","Balance"};
        table=new JTable();

        table.setFont(new Font("Consolas",Font.PLAIN,15));
        table.setShowGrid(false);
        table.setModel(new MyTableModel(data,columnName));
        table.setDefaultRenderer(Object.class,new MultiLineCellRenderer());

        TableColumn column=null;
        for(int i=0;i<5;i++){
            column=table.getColumnModel().getColumn(i);
            if(i==1){
              
                column.setPreferredWidth(200);
            }else if (i==2)
                column.setPreferredWidth(100);
            else
                column.setPreferredWidth(70);
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
        layer.add(comboBox, Integer.valueOf(1));
        //table.getTableHeader().setBounds(50,50,520,50);
        //table.setBounds(50,100,520,280);
        
        frame=new JFrame();
        frame.setLayout(null);
        frame.setBounds(0,0,720,480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(layer);
        frame.setVisible(true);
        

    }
}
