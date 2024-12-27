/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ledger.system;

import database.DatabaseConnector;
import database.AccountBalance;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Liyik
 */
public class DepositUI {
    
    JButton RHB;
    JButton Maybank;
    JButton HL;
    JButton Alliance;
    JButton AmBank;
    JButton Standard;
    JButton Calculate;
    private int Period=365;
    public DepositUI(){
        DatabaseConnector dbcon = new DatabaseConnector();
        
        
        JPanel Header=new JPanel();
        Header.setBounds(0,0,720,100);
        Header.setBackground(new Color(56,36,128));
        
        JPanel body=new JPanel();
        body.setBounds(0,100,400,380);
        body.setBackground(new Color(240,240,240));
        
        JPanel right=new JPanel();
        right.setBounds(400,100,320,380);
        right.setBackground(new Color(229, 221, 240));
        
        JLabel depo=new JLabel("Deposit Interest Predictor");
        depo.setBounds(30,5,500,50);
        depo.setFont(new Font("Consolas",Font.BOLD,30));
        depo.setForeground(Color.white);
        
        JLabel select=new JLabel("Select your bank:");
        select.setBounds(30,50,200,50);
        select.setFont(new Font("Consolas",Font.BOLD,20));
        select.setForeground(Color.white);
        
        JLabel bank=new JLabel("Bank            Interest rate(%)");
        bank.setBounds(50,103,500,40);
        bank.setFont(new Font("Consolas",Font.BOLD,20));
       
        
        RHB=new JButton("RHB");
        RHB.setBounds(0,140,200,50);
        RHB.setFont(new Font("Consolas",Font.BOLD,20));
        RHB.setFocusable(false);
        RHB.setBorder(BorderFactory.createBevelBorder(0));
        

        JLabel RHBint=new JLabel("2.6");
        RHBint.setBounds(200,140,200,50);
        RHBint.setFont(new Font("Consolas",Font.BOLD,20));
        RHBint.setOpaque(true);
        RHBint.setBackground(new Color(237,237,237));
        RHBint.setHorizontalAlignment(JLabel.CENTER);
        
        
        Maybank=new JButton("Maybank");
        Maybank.setBounds(0,190,200,50);
        Maybank.setFont(new Font("Consolas",Font.BOLD,20));
        Maybank.setFocusable(false);
        Maybank.setBorder(BorderFactory.createBevelBorder(0));
        
        
        JLabel Maybankint=new JLabel("2.5");
        Maybankint.setBounds(200,190,200,50);
        Maybankint.setFont(new Font("Consolas",Font.BOLD,20));
        Maybankint.setOpaque(true);
        Maybankint.setBackground(new Color(237,234,228));  
        Maybankint.setHorizontalAlignment(JLabel.CENTER);
        
        HL=new JButton("HL");
        HL.setBounds(0,240,200,50);
        HL.setFont(new Font("Consolas",Font.BOLD,20));
        HL.setFocusable(false);
        HL.setBorder(BorderFactory.createBevelBorder(0));
        
        
        JLabel HLint=new JLabel("2.3");
        HLint.setBounds(200,240,200,50);
        HLint.setFont(new Font("Consolas",Font.BOLD,20));
        HLint.setOpaque(true);
        HLint.setBackground(new Color(237,237,237));
        HLint.setHorizontalAlignment(JLabel.CENTER);
        
        Alliance=new JButton("Alliance");
        Alliance.setBounds(0,290,200,50);
        Alliance.setFont(new Font("Consolas",Font.BOLD,20));
        Alliance.setFocusable(false);
        Alliance.setBorder(BorderFactory.createBevelBorder(0));
        
        
        JLabel Allianceint=new JLabel("2.85");
        Allianceint.setBounds(200,290,200,50);
        Allianceint.setFont(new Font("Consolas",Font.BOLD,20));
        Allianceint.setOpaque(true);
        Allianceint.setBackground(new Color(237,234,228));  
        Allianceint.setHorizontalAlignment(JLabel.CENTER);
        
        AmBank=new JButton("AmBank");
        AmBank.setBounds(0,340,200,50);
        AmBank.setFont(new Font("Consolas",Font.BOLD,20));
        AmBank.setFocusable(false);
        AmBank.setBorder(BorderFactory.createBevelBorder(0));
        
        
        JLabel AmBankint=new JLabel("2.55");
        AmBankint.setBounds(200,340,200,50);
        AmBankint.setFont(new Font("Consolas",Font.BOLD,20));
        AmBankint.setOpaque(true);
        AmBankint.setBackground(new Color(237,237,237));
        AmBankint.setHorizontalAlignment(JLabel.CENTER);
        
        Standard=new JButton("Standard");
        Standard.setBounds(0,390,200,50);
        Standard.setFont(new Font("Consolas",Font.BOLD,20));
        Standard.setFocusable(false);
        Standard.setBorder(BorderFactory.createBevelBorder(0));
        
        
        JLabel Standardint=new JLabel("2.65");
        Standardint.setBounds(200,390,200,50);
        Standardint.setFont(new Font("Consolas",Font.BOLD,20));
        Standardint.setOpaque(true);
        Standardint.setBackground(new Color(237,234,228));  
        Standardint.setHorizontalAlignment(JLabel.CENTER);
        
        JLabel selectPeriod=new JLabel("Period:");
        selectPeriod.setBounds(450,205,150,50);
        selectPeriod.setFont(new Font("Consolas",Font.BOLD,20));
        
        String []period={"Daily","Monthly","Annually"};
        JComboBox rate=new JComboBox(period);
        rate.setFont(new Font("Consolas",Font.BOLD,20));
        rate.setBounds(450,250,150,40);
        rate.addActionListener((ActionEvent e)->{
            int day=rate.getSelectedIndex();
            switch(day){
                case 0 -> Period=365;
                case 1 -> Period=12;
                case 2 -> Period=1;
            }
        });
        
        
        Calculate=new JButton("Calculate");
        Calculate.setBounds(450,350,150,50);
        Calculate.setFont(new Font("Consolas",Font.BOLD,20));
        Calculate.setBackground(new Color(56,36,128));
        Calculate.setForeground(Color.white);
        Calculate.setFocusable(false);
        Calculate.setBorder(BorderFactory.createBevelBorder(0));
       
        RHB.addActionListener(button(2.6));
        Maybank.addActionListener(button(2.5));
        HL.addActionListener(button(2.3));
        Alliance.addActionListener(button(2.85));
        AmBank.addActionListener(button(2.55));
        Standard.addActionListener(button(2.65));
        Calculate.addActionListener(cal());
        
            JLayeredPane layer=new JLayeredPane();
            layer=new JLayeredPane();
            layer.setLayout(null);
            layer.setBounds(0,0,720,480);
            layer.add(Header, Integer.valueOf(0));
            layer.add(body, Integer.valueOf(0));
            layer.add(right, Integer.valueOf(0));
            layer.add(RHB, Integer.valueOf(1));
            layer.add(RHBint, Integer.valueOf(1));
            layer.add(Maybank, Integer.valueOf(1));
            layer.add(Maybankint, Integer.valueOf(1));
            layer.add(HL, Integer.valueOf(1));
            layer.add(HLint, Integer.valueOf(1));
            layer.add(Alliance, Integer.valueOf(1));
            layer.add(Allianceint, Integer.valueOf(1));
            layer.add(AmBank, Integer.valueOf(1));
            layer.add(AmBankint, Integer.valueOf(1));
            layer.add(Standard, Integer.valueOf(1));
            layer.add(Standardint, Integer.valueOf(1));
            layer.add(Calculate, Integer.valueOf(1));
            layer.add(rate, Integer.valueOf(1));
            layer.add(select, Integer.valueOf(1));
            layer.add(bank, Integer.valueOf(1));
            layer.add(selectPeriod, Integer.valueOf(1));
            layer.add(depo, Integer.valueOf(1));
            
            
            JFrame frame=new JFrame();
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
        }
    
                    
    private double selectedInterest;
    private ActionListener button(double interest){
        return e->{
            selectedInterest=interest;
            resetBorders();
            JButton clicked=(JButton)e.getSource();
            clicked.setBorder(BorderFactory.createBevelBorder(1));
        };
    }
    
    public void resetBorders(){
        RHB.setBorder(BorderFactory.createBevelBorder(0));
        Maybank.setBorder(BorderFactory.createBevelBorder(0));
        HL.setBorder(BorderFactory.createBevelBorder(0));
        Alliance.setBorder(BorderFactory.createBevelBorder(0));
        AmBank.setBorder(BorderFactory.createBevelBorder(0));
        Standard.setBorder(BorderFactory.createBevelBorder(0));
    }
    private final double balance=AccountBalance.getBalance(MyFrame.userId);
    private double answer;
    private ActionListener cal(){
        DecimalFormat df = new DecimalFormat("0.00");
        return e->{
            answer=(balance*selectedInterest/100)/Period;
            String format=df.format(answer);
            if (selectedInterest==0){
                JOptionPane.showMessageDialog(null,"Please select a bank","Bank not selected",JOptionPane.ERROR_MESSAGE);
            }else{
                switch (Period){
                    case 1:
                        JOptionPane.showMessageDialog(null,"Your annually interest is RM"+format+".","Prediction result",JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 12:
                        JOptionPane.showMessageDialog(null,"Your monthly interest is RM"+format+".","Prediction result",JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 365:
                        JOptionPane.showMessageDialog(null,"Your daily interest is RM"+format+".","Prediction result",JOptionPane.INFORMATION_MESSAGE);
                        break;
                }
            }
        };
    }

                
    public static void main(String[]args){
         SwingUtilities.invokeLater(DepositUI::new);
    }
}
