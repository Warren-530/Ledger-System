/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ledger.system;

import database.DatabaseConnector;
import database.LoansTable;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Liyik
 */
public class LoanApply {
    public LoanApply(double principal, double interest ,int period, LocalDateTime date, double loan, double installment){
    DatabaseConnector dbcon = new DatabaseConnector();

            JPanel Header=new JPanel();
            Header.setBounds(0,0,720,100);
            Header.setBackground(new Color(56,36,128));

            JPanel body=new JPanel();
            body.setBounds(0,100,720,380);
            body.setBackground(new Color(240,240,240));

            JLabel title=new JLabel("Loan Application");
            title.setFont(new Font("Algerian",Font.BOLD,50));
            title.setBounds(100,25,600,50);
            title.setForeground(Color.white);
            
            
            JLabel totalLoan=new JLabel();
            totalLoan.setText("Total loan         : RM "+loan);
            totalLoan.setFont(new Font("Monospaced",Font.BOLD,24));
            totalLoan.setBounds(50,120,500,50);
            

            JLabel monthly=new JLabel();
            monthly.setText("Monthly installment: RM "+installment);
            monthly.setFont(new Font("Monospaced",Font.BOLD,24));
            monthly.setBounds(50,170,500,50);
            
            LocalDate newdate=date.toLocalDate();
            JLabel time=new JLabel();
            time.setText("Due before         : "+newdate);
            time.setFont(new Font("Monospaced",Font.BOLD,24));
            time.setBounds(50,220,600,50);
            
            JLabel warning=new JLabel();
            warning.setText("<html>**Warning**: Unable to pay the loan by due date <br>will restrict user access to debit and credit</html>");
            warning.setFont(new Font("Monospaced",Font.BOLD,20));
            warning.setBounds(50,270,600,80);
            warning.setForeground(Color.red);
            

            JButton apply=new JButton("APPLY");
            apply.setBounds(275,380,150,50);
            apply.setFont(new Font("Monospaced",Font.BOLD,20));
            apply.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            apply.setBackground(new Color(75,75,128));
            apply.setForeground(Color.white);
            apply.setFocusable(false);
            

            JLayeredPane layer=new JLayeredPane();
            layer.setLayout(null);
            layer.setBounds(0,0,720,480);
            layer.add(Header,Integer.valueOf(0));
            layer.add(title,Integer.valueOf(1));
            layer.add(body, Integer.valueOf(0));
            layer.add(totalLoan,Integer.valueOf(1));
            layer.add(monthly, Integer.valueOf(1));
            layer.add(time, Integer.valueOf(1));
            layer.add(apply, Integer.valueOf(1));
            layer.add(warning, Integer.valueOf(1));

            JFrame frame=new JFrame();
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.setSize(720,480);
            frame.setVisible(true);
            frame.setResizable(false);
            frame.setLayout(null);
            frame.add(layer);
            frame.setLocationRelativeTo(null);

            frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we){
                int close=JOptionPane.showConfirmDialog(null,"Do you want to cancel this transaction?","Transaction cancellation",JOptionPane.YES_NO_OPTION);
                if (close==0){
                    TransactionUI.WindowStatus=false;
                    frame.dispose();
                }
            }

        });
            apply.addActionListener((ActionEvent e)->{
               if(e.getSource()==apply){
                    
                    LoansTable.insertLoan(Login.userId, principal , interest, period, loan, "Unpaid");
                    JOptionPane.showMessageDialog(null,"Credit loan application success!","Loan application completed",JOptionPane.PLAIN_MESSAGE);
                    TransactionUI.WindowStatus=false;
                    TransactionUI.loanAlert=true;
                    TransactionUI.frame.dispose();
                    TransactionUI transactionUI = new TransactionUI();
                    frame.dispose();
               } 
            });
       }
    
    
    public static void main(String[]args){
         SwingUtilities.invokeLater(() -> new LoanApply(0,0,0,LocalDateTime.now(),0,0));
    }
}

