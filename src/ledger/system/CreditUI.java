/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import java.time.LocalDate;
import javax.swing.SwingUtilities;

/**
 *
 * @author Liyik
 */
public class CreditUI {

    /**
     * @param args the command line arguments
     */
    static JFrame frame;
    static JLayeredPane layer;
    static JLabel account;
    static double balance=0.00;
    static JLabel creditAmount;
    static JTextField amount;
    static JLabel reference;
    static JTextArea refText;
    
    public CreditUI(){
        DatabaseConnector dbcon = new DatabaseConnector();
        
        JPanel Header=new JPanel();
        Header.setBounds(0,0,720,100);
        Header.setBackground(new Color(56,36,128));
        
        JPanel body=new JPanel();
        body.setBounds(0,100,720,380);
        body.setBackground(new Color(240,240,240));
        
        JLabel title=new JLabel("Credit");
        title.setFont(new Font("Algerian",Font.BOLD,50));
        title.setBounds(280,25,200,50);
        title.setForeground(Color.white);
        
        balance=AccountBalance.getBalance(MyFrame.userId);
        account=new JLabel();
        account.setText("Account Balance: "+balance);
        account.setFont(new Font("Monospaced",Font.BOLD,20));
        account.setBounds(100,120,300,50);
        
        creditAmount=new JLabel();
        creditAmount.setText("Amount of credit: ");
        creditAmount.setFont(new Font("Monospaced",Font.BOLD,19));
        creditAmount.setBounds(100,180,300,50);
        
        amount=new JTextField();
        amount.setBounds(300,185,200,45);
        amount.setFont(new Font("Monospaced",Font.BOLD,20));
        amount.setBorder(BorderFactory.createBevelBorder(1,Color.lightGray,Color.darkGray));
        
        reference=new JLabel();
        reference.setText("References     : ");
        reference.setFont(new Font("Monospaced",Font.BOLD,20));
        reference.setBounds(100,250,300,50);
        
        refText=new JTextArea();
        refText.setBounds(300,260,250,90);
        refText.setFont(new Font("Monospaced",Font.BOLD,20));
        refText.setLineWrap(true);
        refText.setBorder(BorderFactory.createLineBorder(Color.black));
        refText.setBorder(BorderFactory.createBevelBorder(1,Color.lightGray,Color.darkGray));
        
        JButton confirm=new JButton("CONFIRM");
        confirm.setBounds(275,380,150,50);
        confirm.setFont(new Font("Monospaced",Font.BOLD,20));
        confirm.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        confirm.setBackground(new Color(75,75,128));
        confirm.setForeground(Color.white);
        confirm.setFocusable(false);
        confirm.addActionListener((ActionEvent e)->{
            if (e.getSource()==confirm){
                String amountCheck=amount.getText();
                String refCheck=refText.getText();
                while (true){
                    if (amountCheck.trim().equals("")){
                        JOptionPane.showMessageDialog(null,"Please enter an amount for credit.", "Transaction failed",JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    else if (ifAmountNotValid(amountCheck)||balance<Double.parseDouble(amountCheck)){
                        JOptionPane.showMessageDialog(null,"Please enter an VALID amount for credit.", "Transaction failed",JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    else if (refCheck.trim().equals("")){
                        JOptionPane.showMessageDialog(null,"Please enter a description for references", "Transaction failed",JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    else{
                        LocalDate date=LocalDate.now();
                        balance=AccountBalance.creditBalance(MyFrame.userId, Double.parseDouble(amountCheck));//update the balance of user in account_balance table
                        AccountBalance.updateBalance(MyFrame.userId,balance);
                        TransactionsTable.insertTransaction(MyFrame.userId, "Credit", Double.parseDouble(amountCheck), refCheck, date, balance);//insert a new debit record into transactions table
                        JOptionPane.showMessageDialog(null,"Credit transaction successfully recorded. Current balance is "+balance, "Transaction completed",JOptionPane.PLAIN_MESSAGE);
                        TransactionUI.balance=balance;
                        TransactionUI.WindowStatus=false;
                        TransactionUI.frame.dispose();
                        TransactionUI.main(null);
                        frame.dispose();
                        break;
                    }
                }
            }
        });
        
        layer=new JLayeredPane();
        layer.setLayout(null);
        layer.setBounds(0,0,720,480);
        layer.add(Header,Integer.valueOf(0));
        layer.add(title,Integer.valueOf(1));
        layer.add(body, Integer.valueOf(0));
        layer.add(account,Integer.valueOf(1));
        layer.add(creditAmount,Integer.valueOf(1));
        layer.add(amount, Integer.valueOf(1));
        layer.add(reference,Integer.valueOf(1));
        layer.add(refText, Integer.valueOf(1));
        layer.add(confirm, Integer.valueOf(1));
        
        frame=new JFrame();
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
                SwingUtilities.updateComponentTreeUI(TransactionUI.frame);
                frame.dispose();
            }
        }
        
    });
        
    }
    public static boolean ifAmountNotValid(String amount){
        try{
        String total=amount.trim();
        double value=Double.parseDouble(total);
        if (total.toLowerCase().contains("f")||total.toLowerCase().contains("d")||value<0){
            return true;
        }else
            return false;
        }catch (NumberFormatException e){
            return true;
        }
    }
    public static void main(String[]args){
         SwingUtilities.invokeLater(CreditUI::new);
    }
}
