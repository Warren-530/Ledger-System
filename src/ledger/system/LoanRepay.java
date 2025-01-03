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
import java.time.LocalDateTime;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Liyik
 */
public class LoanRepay {
    
    private double balance;
    private double repayment;
    private String status;
    
    public LoanRepay(){
        DatabaseConnector dbcon = new DatabaseConnector();

            JPanel Header=new JPanel();
            Header.setBounds(0,0,720,100);
            Header.setBackground(new Color(56,36,128));

            JPanel body=new JPanel();
            body.setBounds(0,100,720,380);
            body.setBackground(new Color(240,240,240));

            JLabel title=new JLabel("Loan Repayment");
            title.setFont(new Font("Algerian",Font.BOLD,50));
            title.setBounds(130,25,600,50);
            title.setForeground(Color.white);
            
            balance=LoansTable.getLoan(Login.userId);
            status=LoansTable.getStatus(Login.userId);
            JLabel outstanding=new JLabel();
            outstanding.setText("Outstanding balance: "+balance);
            outstanding.setFont(new Font("Monospaced",Font.BOLD,20));
            outstanding.setBounds(50,150,600,50);

            JLabel repay=new JLabel();
            repay.setText("Repay Amount: ");
            repay.setFont(new Font("Monospaced",Font.BOLD,20));
            repay.setBounds(135,220,300,50);

            JTextField repayAmount=new JTextField();
            repayAmount.setBounds(300,220,200,45);
            repayAmount.setFont(new Font("Monospaced",Font.BOLD,20));
            repayAmount.setBorder(BorderFactory.createBevelBorder(1,Color.lightGray,Color.darkGray));
            
            JButton next=new JButton("Repay");
            next.setBounds(275,380,150,50);
            next.setFont(new Font("Monospaced",Font.BOLD,20));
            next.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            next.setBackground(new Color(75,75,128));
            next.setForeground(Color.white);
            next.setFocusable(false);
            

            JLayeredPane layer=new JLayeredPane();
            layer.setLayout(null);
            layer.setBounds(0,0,720,480);
            layer.add(Header,Integer.valueOf(0));
            layer.add(title,Integer.valueOf(1));
            layer.add(body, Integer.valueOf(0));
            layer.add(outstanding,Integer.valueOf(1));
            layer.add(repay, Integer.valueOf(1));
            layer.add(repayAmount, Integer.valueOf(1));
            layer.add(next, Integer.valueOf(1));

            JFrame frame=new JFrame();
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.setSize(720,480);
            frame.setVisible(true);
            frame.setResizable(false);
            frame.setLayout(null);
            frame.add(layer);
            frame.setLocationRelativeTo(null);
            
            
            next.addActionListener((ActionEvent e)->{
                if (e.getSource()==next){
                    String amountCheck=repayAmount.getText();
                        if (amountCheck.trim().equals("")){
                            JOptionPane.showMessageDialog(null,"Please enter an amount for repayment.", "Error",JOptionPane.INFORMATION_MESSAGE);
                        }else if(DebitUI.ifAmountNotValid(amountCheck)||CreditLoan.decimalCheck(Double.parseDouble(amountCheck))) {
                            JOptionPane.showMessageDialog(null,"Please enter a VALID amount", "Error",JOptionPane.INFORMATION_MESSAGE);
                        }else if(Double.parseDouble(amountCheck)>balance){
                            JOptionPane.showMessageDialog(null,"Amount entered cannot exceed outstanding balance", "Error",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            balance-=Double.parseDouble(amountCheck);
                            if (balance==0){
                                JOptionPane.showMessageDialog(null,"Congratulations! You have paid your credit loan. You may apply for a new one now!","Loan Repaid",JOptionPane.PLAIN_MESSAGE);
                                status="Paid";
                            }else{
                                JOptionPane.showMessageDialog(null,"Credit loan repayment successfully completed. Current outstanding balance is "+balance, "Repayment completed",JOptionPane.PLAIN_MESSAGE);
                            }
                            LoansTable.insertLoanHistory(Login.userId,Double.parseDouble(amountCheck),balance);
                            LoansTable.updateLoan(Login.userId,balance, status);
                            TransactionUI.WindowStatus=false;
                            TransactionUI.frame.dispose();
                            new TransactionUI();
                            frame.dispose();
                        }
                        
                    
                }
            });
            
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
       }
    
    
    public static void main(String[]args){
         SwingUtilities.invokeLater(LoanRepay::new);
    }
}


