/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ledger.system;

import database.AccountBalance;
import database.DatabaseConnector;
import database.TransactionsTable;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Liyik
 */
public class LoanUI {
    public LoanUI(){
        DatabaseConnector dbcon = new DatabaseConnector();

            JPanel Header=new JPanel();
            Header.setBounds(0,0,720,100);
            Header.setBackground(new Color(56,36,128));

            JPanel body=new JPanel();
            body.setBounds(0,100,720,380);
            body.setBackground(new Color(240,240,240));

            JLabel title=new JLabel("Credit Loan");
            title.setFont(new Font("Algerian",Font.BOLD,50));
            title.setBounds(200,25,400,50);
            title.setForeground(Color.white);
            
            JLabel principal=new JLabel();
            principal.setText("Pricipal Amount: ");
            principal.setFont(new Font("Monospaced",Font.BOLD,20));
            principal.setBounds(100,120,300,50);
            
            JTextField amount=new JTextField();
            amount.setBounds(300,120,200,45);
            amount.setFont(new Font("Monospaced",Font.BOLD,20));
            amount.setBorder(BorderFactory.createBevelBorder(1,Color.lightGray,Color.darkGray));

            JLabel interest=new JLabel();
            interest.setText("Interest rate: ");
            interest.setFont(new Font("Monospaced",Font.BOLD,20));
            interest.setBounds(120,180,300,50);

            JTextField interestValue=new JTextField();
            interestValue.setBounds(300,185,200,45);
            interestValue.setFont(new Font("Monospaced",Font.BOLD,20));
            interestValue.setBorder(BorderFactory.createBevelBorder(1,Color.lightGray,Color.darkGray));
            
            JLabel time=new JLabel();
            time.setText("Repayment period(month):");
            time.setFont(new Font("Monospaced",Font.BOLD,18));
            time.setBounds(35,250,300,50);
            
            JTextField period=new JTextField();
            period.setBounds(300,260,250,45);
            period.setFont(new Font("Monospaced",Font.BOLD,20));
            period.setBorder(BorderFactory.createLineBorder(Color.black));
            period.setBorder(BorderFactory.createBevelBorder(1,Color.lightGray,Color.darkGray));

            JButton next=new JButton("NEXT");
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
            layer.add(amount, Integer.valueOf(1));
            layer.add(principal,Integer.valueOf(1));
            layer.add(interest, Integer.valueOf(1));
            layer.add(interestValue, Integer.valueOf(1));
            layer.add(time, Integer.valueOf(1));
            layer.add(period, Integer.valueOf(1));
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
                    String amountCheck=amount.getText();
                    String interestCheck=interestValue.getText();
                    String timeCheck=period.getText();
                    int date=1;
                    
                        if (amountCheck.trim().equals("")){
                            JOptionPane.showMessageDialog(null,"Please enter an amount for loan principal.", "Error",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if (DebitUI.ifAmountNotValid(amountCheck)){
                            JOptionPane.showMessageDialog(null,"Please enter an VALID amount for loan principal.", "Error",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if (interestCheck.trim().equals("")){
                            JOptionPane.showMessageDialog(null,"Please enter an interest rate", "Error",JOptionPane.INFORMATION_MESSAGE);
                        }else if (DebitUI.ifAmountNotValid(interestCheck)||Double.parseDouble(interestCheck)>100){
                            JOptionPane.showMessageDialog(null,"Please enter a VALID interest rate", "Error",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if(timeCheck.trim().equals("")){
                            JOptionPane.showMessageDialog(null,"Please enter for period.", "Error",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            try{
                                date=Integer.parseInt(timeCheck);
                                if (date<=0){
                                    JOptionPane.showMessageDialog(null,"Please enter a VALID month", "Error",JOptionPane.INFORMATION_MESSAGE);
                                    
                                }
                            }catch (NumberFormatException a){
                                JOptionPane.showMessageDialog(null,"Please enter a VALID month", "Error",JOptionPane.ERROR_MESSAGE);
                                
                            }
                            LoanApply loanApply = new LoanApply(Double.parseDouble(amountCheck),Double.parseDouble(interestCheck),date);
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
         SwingUtilities.invokeLater(LoanUI::new);
    }
}

