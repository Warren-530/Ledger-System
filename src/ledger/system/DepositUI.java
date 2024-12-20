/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ledger.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
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
    public DepositUI(){
        
        
        JPanel Header=new JPanel();
        Header.setBounds(0,0,720,100);
        Header.setBackground(new Color(56,36,128));
        
        JPanel body=new JPanel();
        body.setBounds(0,100,720,380);
        body.setBackground(new Color(240,240,240));
        
        
        RHB=new JButton("RHB");
        RHB.setBounds(100,100,150,50);
        RHB.setFont(new Font("Consolas",Font.BOLD,18));
        RHB.setFocusable(false);
        RHB.setBorder(BorderFactory.createBevelBorder(0));
        
            
          
        
        JLabel RHBint=new JLabel("2.6");
        RHBint.setBounds(250,100,100,50);
        RHBint.setFont(new Font("Consolas",Font.BOLD,18));
        RHBint.setOpaque(true);
        RHBint.setBackground(Color.cyan);
        RHBint.setHorizontalAlignment(JLabel.CENTER);
        
        
        Maybank=new JButton("Maybank");
        Maybank.setBounds(100,150,150,50);
        Maybank.setFont(new Font("Consolas",Font.BOLD,18));
        Maybank.setFocusable(false);
        Maybank.setBorder(BorderFactory.createBevelBorder(0));
        
        
        JLabel Maybankint=new JLabel("2.6");
        Maybankint.setBounds(250,150,100,50);
        Maybankint.setFont(new Font("Consolas",Font.BOLD,18));
        Maybankint.setOpaque(true);
        Maybankint.setBackground(Color.cyan);
        Maybankint.setHorizontalAlignment(JLabel.CENTER);
        
        HL=new JButton("HL");
        HL.setBounds(100,200,150,50);
        HL.setFont(new Font("Consolas",Font.BOLD,18));
        HL.setFocusable(false);
        HL.setBorder(BorderFactory.createBevelBorder(0));
        
        
        JLabel HLint=new JLabel("2.6");
        HLint.setBounds(250,200,100,50);
        HLint.setFont(new Font("Consolas",Font.BOLD,18));
        HLint.setOpaque(true);
        HLint.setBackground(Color.cyan);
        HLint.setHorizontalAlignment(JLabel.CENTER);
        
        Alliance=new JButton("Alliance");
        Alliance.setBounds(100,250,150,50);
        Alliance.setFont(new Font("Consolas",Font.BOLD,18));
        Alliance.setFocusable(false);
        Alliance.setBorder(BorderFactory.createBevelBorder(0));
        
        
        JLabel Allianceint=new JLabel("2.6");
        Allianceint.setBounds(250,250,100,50);
        Allianceint.setFont(new Font("Consolas",Font.BOLD,18));
        Allianceint.setOpaque(true);
        Allianceint.setBackground(Color.cyan);
        Allianceint.setHorizontalAlignment(JLabel.CENTER);
        
        AmBank=new JButton("AmBank");
        AmBank.setBounds(100,300,150,50);
        AmBank.setFont(new Font("Consolas",Font.BOLD,18));
        AmBank.setFocusable(false);
        AmBank.setBorder(BorderFactory.createBevelBorder(0));
        
        
        JLabel AmBankint=new JLabel("2.6");
        AmBankint.setBounds(250,300,100,50);
        AmBankint.setFont(new Font("Consolas",Font.BOLD,18));
        AmBankint.setOpaque(true);
        AmBankint.setBackground(Color.cyan);
        AmBankint.setHorizontalAlignment(JLabel.CENTER);
        
        Standard=new JButton("Standard");
        Standard.setBounds(100,350,150,50);
        Standard.setFont(new Font("Consolas",Font.BOLD,18));
        Standard.setFocusable(false);
        Standard.setBorder(BorderFactory.createBevelBorder(0));
        
        
        JLabel Standardint=new JLabel("2.6");
        Standardint.setBounds(250,350,100,50);
        Standardint.setFont(new Font("Consolas",Font.BOLD,18));
        Standardint.setOpaque(true);
        Standardint.setBackground(Color.cyan);
        Standardint.setHorizontalAlignment(JLabel.CENTER);
        
        String []period={"Daily","Monthly","Annually"};
        JComboBox rate=new JComboBox(period);
        rate.setFont(new Font("Consolas",Font.BOLD,18));
        rate.setBounds(450,250,150,40);
        
        Calculate=new JButton("Calculate");
        Calculate.setBounds(400,350,150,50);
        Calculate.setFont(new Font("Consolas",Font.BOLD,18));
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
            
            
            
            JFrame frame=new JFrame();
            frame.setLayout(null);
            frame.setBounds(0,0,720,480);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.add(layer);
            frame.setVisible(true);

    
    }
    private double selectedInterest;
    private ActionListener button(double interest){
        return e->{
            selectedInterest=interest;
            resetBorders();
            System.out.println(interest);
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
    
    private ActionListener cal(){
        return e->{
            System.out.println(selectedInterest);
        };
    }
                
    public static void main(String[]args){
         SwingUtilities.invokeLater(DepositUI::new);
    }
}
