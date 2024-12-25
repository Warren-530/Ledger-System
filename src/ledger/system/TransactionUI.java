/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package ledger.system;

import database.AccountBalance;
import database.DatabaseConnector;
import database.TransactionsTable;
import database.LoansTable;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Liyik
 */
public class TransactionUI {
static JLayeredPane layer;
static JLabel account;
static JButton debit;
static JButton credit;
static JButton history;
static JButton savings;
static JButton creditLoan;
static JButton deposit;
static JButton logout;
static JFrame frame;
static JButton exit;
static boolean WindowStatus=false;
static double balance;
static boolean savingStatus;
static String loanStatus;
static int activate;
static String percentageS;
static double percentage;
static String loanAmountS;
static double loanAmount;

    public static void main(String args[]) {
        DatabaseConnector dbcon = new DatabaseConnector();
        debit=new JButton("DEBIT");
        credit=new JButton("CREDIT");
        history=new JButton("HISTORY");
        savings=new JButton("SAVINGS");
        creditLoan=new JButton("CREDIT LOAN");
        deposit=new JButton("<html>DEPOSIT INTEREST<br>PREDICTOR</html>");
        logout=new JButton("LOGOUT");
        exit=new JButton("LOGOUT AND EXIT");
        LocalDate date = LocalDate.now();
        JLabel datetime=new JLabel(String.valueOf(date));
        datetime.setBounds(25,600,300,75);
        datetime.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,30));
        String name=AccountBalance.getName(MyFrame.userId);
        JLabel welcome=new JLabel("<html>Welcome, "+name+"!<br>What can we help you today?");
        welcome.setBounds(40,0,1000,150);
        welcome.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,45));
        JPanel header=new JPanel();
        header.setBounds(-5,-5,1290,155);
        header.setBackground(new Color(204, 212, 220));
        header.setBorder(BorderFactory.createLineBorder(Color.lightGray, 5));

        JPanel left=new JPanel();
        left.setBackground(new Color(188, 222, 224));
        left.setBounds(-5,145,500,680);
        left.setBorder(BorderFactory.createBevelBorder(0));
        
        balance =AccountBalance.getBalance(MyFrame.userId);
        account=new JLabel();
        account.setText("<html>Account Balance :<br>"+balance+"</html>");
        account.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,30));
        account.setBounds(50,200,300,75);
        
        double SavingBalance=AccountBalance.getSavings(MyFrame.userId);
        JLabel accSaving=new JLabel();
        accSaving.setText("<html>Savings :<br>"+SavingBalance+"</html>");
        accSaving.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,30));
        accSaving.setBounds(50,300,300,75);
        
        double LoanBalance=0;//AcountBalance.getLoan(MyFrame.userId);
        JLabel accLoan=new JLabel();
        accLoan.setText("<html>Loan :<br>"+LoanBalance+"</html>");
        accLoan.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,30));
        accLoan.setBounds(50,400,300,75);
        
        debit.setBounds(550,200,300,100);
        debit.setBackground(new Color(12,35,89));
        debit.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,30));
        debit.setForeground(Color.white);
        debit.setFocusable(false);
        debit.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        debit.addActionListener((ActionEvent e)->{
            if (e.getSource()==debit){
                if (WindowStatus){
                    JOptionPane.showMessageDialog(null,"Please complete or exit the ongoing transaction process before other transactions.","Transaction ongoing",JOptionPane.ERROR_MESSAGE);
                }else {
                    WindowStatus=true;
                    DebitUI debitUI=new DebitUI();
                }
                }
            
        });
        
        credit.setBounds(550,350,300,100);
        credit.setBackground(new Color(12,35,89));
        credit.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,30));
        credit.setForeground(Color.white);
        credit.setFocusable(false);
        credit.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        credit.addActionListener((ActionEvent e)->{
            if (e.getSource()==credit){
                if (WindowStatus){
                    JOptionPane.showMessageDialog(null,"Please complete or exit the ongoing transaction process before other transactions.","Transaction ongoing",JOptionPane.ERROR_MESSAGE);
                }else{
                    WindowStatus=true;
                    CreditUI creditUI = new CreditUI();
                }
                }
            
        });
        
        history.setBounds(550,500,300,100);
        history.setBackground(new Color(12,35,89));
        history.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,30));
        history.setForeground(Color.white);
        history.setFocusable(false);
        history.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        history.addActionListener((ActionEvent e)->{
            if (e.getSource()==history){
                if (WindowStatus){
                    JOptionPane.showMessageDialog(null,"Please complete or exit the ongoing transaction process before other transactions.","Transaction ongoing",JOptionPane.ERROR_MESSAGE);
                }else{
                    WindowStatus=true;
                    HistoryUI historyUI = new HistoryUI();
                }
                }
            
        });
        savingStatus=TransactionsTable.SavingActive(MyFrame.userId);
        percentage=TransactionsTable.getPercentage(MyFrame.userId);
        savings.setBounds(900,200,300,100);
        savings.setBackground(new Color(12,35,89));
        savings.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,30));
        savings.setForeground(Color.white);
        savings.setFocusable(false);
        savings.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        
        savings.addActionListener((ActionEvent e)->{
            if (e.getSource()==savings){
                if (savingStatus){
                    activate=JOptionPane.showConfirmDialog(null,"You have activate your saving. Do you want to stop it?","Saving Cancelation",JOptionPane.YES_NO_OPTION);
                    if (activate==0){
                        TransactionsTable.updateSaving(MyFrame.userId,false,0);
                        JOptionPane.showMessageDialog(null,"Saving cancelation success!","Saving Cancelation",JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                        frame.setVisible(true);
                        savingStatus=TransactionsTable.SavingActive(MyFrame.userId);
                    }
                }else{
                    activate=JOptionPane.showConfirmDialog(null,"Your saving is inactive. Do you want to activate it?","Saving activation",JOptionPane.YES_NO_OPTION);
                    //activate saving, enter percentage
                    while(true){
                        percentageS=JOptionPane.showInputDialog(null,"Please enter the percentage you wish to deduct from your next debit: ","Saving Percentage",JOptionPane.PLAIN_MESSAGE);
                        try{
                            if (percentageS==null)
                                break;
                            percentage=Double.parseDouble(percentageS);
                                if (percentage>100){
                                    JOptionPane.showMessageDialog(null,"Percentage entered cannot exceed 100.","Saving Activation",JOptionPane.ERROR_MESSAGE);
                                    continue;
                                }else if (percentage<=0){
                                    JOptionPane.showMessageDialog(null,"Percentage entered cannot lower than 100.","Saving Activation",JOptionPane.ERROR_MESSAGE);
                                    continue;
                                }
                            TransactionsTable.updateSaving(MyFrame.userId,true,percentage);
                            JOptionPane.showMessageDialog(null,"Saving activation success!","Saving Activation",JOptionPane.INFORMATION_MESSAGE);
                            frame.dispose();
                            frame.setVisible(true);
                            savingStatus=TransactionsTable.SavingActive(MyFrame.userId);
                            break;
                        }catch (NumberFormatException a){
                            JOptionPane.showMessageDialog(null,"Please enter the valid input for percentage.","Invalid value",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
            
        });
        loanStatus=LoansTable.getStatus(MyFrame.userId);
        if (loanStatus.equals("unpaid")){
            if (CreditLoan.isOverdue(MyFrame.userId)){
                debit.setEnabled(false);
                credit.setEnabled(false);
            }
        }

        creditLoan.setBounds(900,350,300,100);
        creditLoan.setBackground(new Color(12,35,89));
        creditLoan.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,30));
        creditLoan.setForeground(Color.white);
        creditLoan.setFocusable(false);
        creditLoan.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        creditLoan.addActionListener((ActionEvent e)->{
            if (e.getSource()==creditLoan){
                if (WindowStatus){
                    JOptionPane.showMessageDialog(null,"Please complete or exit the ongoing transaction process before other transactions.","Transaction ongoing",JOptionPane.ERROR_MESSAGE);
                }else{
                    WindowStatus=true; 
                    if (loanStatus.equals("paid")||loanStatus.equals("false")){
                        int loanActive=JOptionPane.showConfirmDialog(null,"You have not apply any credit loan. Do you want to apply one?.","Credit Loan Application",JOptionPane.YES_NO_OPTION);
                            if (loanActive==0){
                                LoanUI loanUI=new LoanUI();
                            }
                    }else if (loanStatus.equals("unpaid")){
                        LoanRepay loanRepay=new LoanRepay();
                    }
                }
                }
            
        });
        
        deposit.setBounds(900,500,300,100);
        deposit.setBackground(new Color(12,35,89));
        deposit.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,25));
        deposit.setForeground(Color.white);
        deposit.setFocusable(false);
        deposit.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        deposit.addActionListener((ActionEvent e)->{
            if (e.getSource()==deposit){
                if (WindowStatus){
                    JOptionPane.showMessageDialog(null,"Please complete or exit the ongoing transaction process before other transactions.","Transaction ongoing",JOptionPane.ERROR_MESSAGE);
                }else{
                    WindowStatus=true;
                    DepositUI depositUI = new DepositUI();
                }
                }
            
        });
        
        logout.setBounds(1000,20,200,50);
        logout.setBackground(new Color(12,35,89));
        logout.setIcon(new ImageIcon("logoutIcon.png"));
        logout.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,20));
        logout.setForeground(Color.white);

        
        
        logout.setFocusable(false);
        logout.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        logout.addActionListener((ActionEvent e)->{
            if (e.getSource()==logout){
                int value=JOptionPane.showConfirmDialog(null, "Do you want to logout?","Logout Confirmation",JOptionPane.YES_NO_OPTION);
                if (WindowStatus){
                    JOptionPane.showMessageDialog(null,"Please complete or exit the ongoing transaction process before other transactions.","Transaction ongoing",JOptionPane.ERROR_MESSAGE);
                }else{
                    if (value==0){
                        JOptionPane.showMessageDialog(null,"Thank you for using Ledger System. See you next time!","LOGOUT",JOptionPane.PLAIN_MESSAGE);
                        frame.dispose();
                        MyFrame myframe=new MyFrame();
                    }
                }
            }
        });
        
        exit.setBounds(1000,85,200,50);
        exit.setBackground(new Color(12,35,89));
        exit.setIcon(new ImageIcon("powerIcon.png"));
        exit.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,14));
        exit.setForeground(Color.white);
        exit.setFocusable(false);
        exit.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        exit.addActionListener((ActionEvent e)->{
            if (e.getSource()==exit){
                int value=JOptionPane.showConfirmDialog(null, "Do you want to exit?","Logout Confirmation",JOptionPane.YES_NO_OPTION);
                if (WindowStatus){
                    JOptionPane.showMessageDialog(null,"Please complete or exit the ongoing transaction process before other transactions.","Transaction ongoing",JOptionPane.ERROR_MESSAGE);
                }else{
                    if (value==0){
                        JOptionPane.showMessageDialog(null,"Thank you for using Ledger System. See you next time!","LOGOUT",JOptionPane.PLAIN_MESSAGE);
                        frame.dispose();
                    }
                }
            }
        });
        
        layer=new JLayeredPane();
        layer.setBounds(0,0,1280,720);
        layer.setLayout(null);
        layer.setBackground(new Color(250, 247, 240));
        layer.add(header, Integer.valueOf(0));
        layer.add(welcome, Integer.valueOf(1));
        layer.add(logout, Integer.valueOf(1));
        layer.add(debit, Integer.valueOf(1));
        layer.add(credit, Integer.valueOf(1));
        layer.add(history, Integer.valueOf(1));
        layer.add(savings, Integer.valueOf(1));
        layer.add(deposit, Integer.valueOf(1));
        layer.add(creditLoan, Integer.valueOf(1));
        layer.add(exit, Integer.valueOf(1));
        layer.add(left, Integer.valueOf(0));
        layer.add(account, Integer.valueOf(1));
        layer.add(accSaving, Integer.valueOf(1));
        layer.add(accLoan, Integer.valueOf(1));
        layer.add(datetime, Integer.valueOf(1));
        
        frame=new JFrame();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(1280,720);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.add(layer);
        
        
        frame.setLocationRelativeTo(null);
        
        frame.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent we){
            int close=JOptionPane.showConfirmDialog(null,"Do you want to Log Out and Exit??","Log Out and Exit",JOptionPane.YES_NO_OPTION);
            if (WindowStatus){
                 JOptionPane.showMessageDialog(null,"Please complete or exit the ongoing transaction process before other transactions.","Transaction ongoing",JOptionPane.ERROR_MESSAGE);
            }else
                {if (close==0){
                    JOptionPane.showMessageDialog(null,"Thank you for using Ledger System. See you next time!","Log Out and Exit",JOptionPane.PLAIN_MESSAGE);
                    frame.dispose();
                }
            }
        }
        
    });
    }
}
