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
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
static JRadioButton byDate;
static JRadioButton byAmount;
static JComboBox type;
static JTextField minAmount;
static JTextField maxAmount;
static JLabel from;
static JLabel to;
static JCheckBox sort;
static Object[][] data;
static int filterIndex, sortingIndex;
static Date startDate,endDate,beforeDate,afterDate,onDate;
static double minAm,maxAm;
static String sortOrder,transactionType,minAms,maxAms;
static boolean isSorting=false;
    public HistoryUI(){
        ImageIcon ascending=new ImageIcon("ascending.png");
        ImageIcon descending=new ImageIcon("descending.png");
        DatabaseConnector dbcon = new DatabaseConnector();
        
        JPanel Header=new JPanel();
        Header.setBounds(0,0,720,100);
        Header.setBackground(new Color(56,36,128));
        
        JPanel body=new JPanel();
        body.setBounds(0,100,720,380);
        body.setBackground(new Color(240,240,240));
        
        JLabel title=new JLabel("<html>Transaction <br>History</html>");
        title.setFont(new Font("Algerian",Font.BOLD,22));
        title.setBounds(30,0,600,100);
        title.setForeground(Color.white);
        int rowcount=0;
        data=new Object[HistoryTable.getRowCount(MyFrame.userId, rowcount)][5];
        if (!isSorting){
            HistoryTable.getHistory(MyFrame.userId,data);
        }
         
        
        
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
        filter.setBounds(600,60,100,30);
        filter.setFocusable(false);
        
        sortingIndex=0;
        String []columnName={"Date","Description","Debit","Credit","Balance"};
        String[]range={"Date","Before","Between","After","Type","Amount"};
        JComboBox comboBox=new JComboBox(range);
        comboBox.setBounds(250,10,100,25);
        String []transType={"Credit","Debit"};
        
        from=new JLabel("From");
        from.setBounds(420,10,100,30);
        from.setForeground(Color.white);
        from.setVisible(false);
        from.setFont(new Font("Consolas",Font.BOLD,12));
        to=new JLabel("to");
        to.setBounds(420,50,100,30);
        to.setForeground(Color.white);
        to.setVisible(false);
        to.setFont(new Font("Consolas",Font.BOLD,12));
        
        
        minAmount=new JTextField();
        minAmount.setBounds(470,10,100,30);
        minAmount.setBackground(new Color(237,237,237));
        minAmount.setVisible(false);
        
        maxAmount=new JTextField();
        maxAmount.setBounds(470,50,100,30);
        maxAmount.setBackground(new Color(237,237,237));
        maxAmount.setVisible(false);
        
        type=new JComboBox(transType);
        type.setBounds(470,30,100,30);
        type.setBackground(new Color(237,237,237));
        type.setVisible(false);
        
        date=new JDateChooser();
        date.setBounds(470,30,100,30);
        date.setBackground(new Color(0,237,237));
        
        before=new JDateChooser();
        before.setBounds(470,30,100,30);
        before.setBackground(new Color(0,237,237));
        before.setVisible(false);
        
        
        between1=new JDateChooser();
        between1.setBounds(470,10,100,30);
        between1.setBackground(new Color(237,0,237));
        between1.setVisible(false);
        
        between2=new JDateChooser();
        between2.setBounds(470,50,100,30);
        between2.setBackground(new Color(237,0,237));
        between2.setVisible(false);
        
        after=new JDateChooser();
        after.setBounds(470,30,100,30);
        after.setForeground(Color.red);
        after.setVisible(false);
        
        
        
        byDate=new JRadioButton("Sort by date");
        byDate.setBounds(250,45,100,20);
        byDate.setBackground(new Color(56,36,128));
        byDate.setForeground(Color.white);
        byDate.setSelected(true);
        byAmount=new JRadioButton("Sort by amount");
        byAmount.setBounds(250,65,150,20);
        byAmount.setBackground(new Color(56,36,128));
        byAmount.setForeground(Color.white);
        byDate.addActionListener(this::sorting);
        byAmount.addActionListener(this::sorting);
        
        sort=new JCheckBox();
        sort.setBounds(370,45,25,25);
        sort.setIcon(ascending);
        sort.setSelectedIcon(descending);
        

        
        ButtonGroup button=new ButtonGroup();
        
        button.add(byDate);
        button.add(byAmount);
            
        
        
        

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
        layer.add(byDate, Integer.valueOf(1));
        layer.add(byAmount, Integer.valueOf(1));
        layer.add(type, Integer.valueOf(1));
        layer.add(minAmount, Integer.valueOf(1));
        layer.add(maxAmount, Integer.valueOf(1));
        layer.add(from, Integer.valueOf(1));
        layer.add(to, Integer.valueOf(1));
        layer.add(sort, Integer.valueOf(2));
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
                    from.setVisible(true);
                    to.setVisible(true);
                    break;
                  case 3:
                    after.setVisible(true);
                    break;
                  case 4:
                    type.setVisible(true);
                    break;
                  case 5:
                    minAmount.setVisible(true);
                    maxAmount.setVisible(true);
                    from.setVisible(true);
                    to.setVisible(true);
                    break;
              }
          }
        });
        filter.addActionListener((ActionEvent e)->{
            
            if (e.getSource()==filter){
                startDate=null;endDate=null; beforeDate=null;afterDate=null;onDate=null;
                minAm=0;maxAm=0;
                sortOrder=null;transactionType=null;
                filterIndex=comboBox.getSelectedIndex();
                switch(comboBox.getSelectedIndex()){
                    case 0:
                        try{
                        onDate=date.getDate();
                        }catch(NullPointerException ex){
                            JOptionPane.showMessageDialog(null,"Please enter date", "Not valid date",JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case 1:
                        try{
                        beforeDate=before.getDate();
                        }catch(NullPointerException ex){
                            JOptionPane.showMessageDialog(null,"Please enter date", "Not valid date",JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case 2:
                        try{
                        startDate=between1.getDate();
                        endDate=between2.getDate();
                        }catch(NullPointerException ex){
                            JOptionPane.showMessageDialog(null,"Please enter date", "Not valid date",JOptionPane.ERROR_MESSAGE);
                        }
                        
                        break;
                    case 3:
                        try{
                        afterDate=after.getDate();
                        }catch(NullPointerException ex){
                            JOptionPane.showMessageDialog(null,"Please enter date", "Not valid date",JOptionPane.ERROR_MESSAGE);
                        }
                        
                        break;
                    case 4:
                        transactionType=String.valueOf(type.getSelectedItem());
                        System.out.println(transactionType);
                        break;
                    case 5:
                        minAms=minAmount.getText();
                        maxAms=maxAmount.getText();
                        if (minAms.trim().equals("")||maxAms.trim().equals("")){
                            JOptionPane.showMessageDialog(null,"Please enter value for amounts.", "Not valid amount",JOptionPane.ERROR_MESSAGE);
                        }
                        else if (DebitUI.ifAmountNotValid(minAms)||DebitUI.ifAmountNotValid(maxAms)){
                            JOptionPane.showMessageDialog(null,"Please enter valid amounts.", "Not valid amount",JOptionPane.ERROR_MESSAGE);
                            
                        }else{
                            minAm=Double.parseDouble(minAms);
                            maxAm=Double.parseDouble(maxAms);
                        }    
                        break;
                    }

                if (sort.isSelected()){
                    sortOrder="DESC";
                }else{
                    sortOrder="ASC";
                }
                isSorting=true;
                HistoryTable.getFilteredAndSortedHistory(data, filterIndex, sortingIndex, startDate, endDate, minAm, maxAm, sortOrder, transactionType, MyFrame.userId);
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
        type.setVisible(false);
        minAmount.setVisible(false);
        maxAmount.setVisible(false);
        from.setVisible(false);
        to.setVisible(false);
        
    }
    
    private void sorting(ActionEvent e){
        if (e.getSource()==byDate){
            sortingIndex=0;
        }else if(e.getSource()==byAmount){
            sortingIndex=1;
        }
    }
}
