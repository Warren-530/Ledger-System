/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ledger.system;

import database.DatabaseConnector;
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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Liyik
 */
public class LoanApply {
    public LoanApply(double principal,double interest, int month){
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
            
            
            JLabel totalLoan=new JLabel();
            totalLoan.setText("Total loan: RM"+principal);
            totalLoan.setFont(new Font("Monospaced",Font.BOLD,20));
            totalLoan.setBounds(100,120,300,50);
            

            JLabel monthly=new JLabel();
            monthly.setText("Monthly installment: RM "+interest);
            monthly.setFont(new Font("Monospaced",Font.BOLD,20));
            monthly.setBounds(100,180,300,50);
            
            JLabel time=new JLabel();
            time.setText("Due before: "+month);
            time.setFont(new Font("Monospaced",Font.BOLD,20));
            time.setBounds(100,250,300,50);
            

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
            layer.add(totalLoan,Integer.valueOf(1));
            layer.add(monthly, Integer.valueOf(1));
            layer.add(time, Integer.valueOf(1));
            layer.add(next, Integer.valueOf(1));

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
       }
    
    
    public static void main(String[]args){
         SwingUtilities.invokeLater(() -> new LoanApply(0,0,0));
    }
}

