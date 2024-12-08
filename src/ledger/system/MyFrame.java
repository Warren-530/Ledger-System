/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ledger.system;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
/**
 *
 * @author Liyik
 */
public class MyFrame extends JFrame{
    JPanel panelleft;
    JPanel panelLogin;
    JPanel panelRegister;
    JLayeredPane layer;
    JLabel welcome;
    JLabel welcome2;
    JButton Login;
    JButton Register;
    JLabel username;
    JLabel password;
    JTextField user;
    JPasswordField pass;
    JTabbedPane tabbed;
    JCheckBox passVisible;
    ImageIcon show;
    ImageIcon hide;
        MyFrame(){
            panelleft=new JPanel();
            panelLogin=new JPanel();
            panelRegister=new JPanel();
            welcome=new JLabel();
            welcome2=new JLabel();
            Login=new JButton("Login");
            Register=new JButton("Register");
            username=new JLabel("E-mail:");
            password=new JLabel("Password");
            user=new JTextField();
            pass=new JPasswordField();
            tabbed=new JTabbedPane();
            passVisible=new JCheckBox();
            show=new ImageIcon("show-password.png");
            hide=new ImageIcon("hide-password.png");
            
            tabbed.setBounds(549,-30,731,750);
            tabbed.add(panelLogin);
            tabbed.add(panelRegister);
            
            username.setBounds(100,200,200,50);
            username.setFont(new Font("Consolas",Font.BOLD,18));
            
            user.setBounds(100,235,400,50);
            
            password.setBounds(100,350,200,50);
            password.setFont(new Font("Consolas",Font.BOLD,18));
            
            pass.setBounds(100,385,400,50);
            passVisible.setBounds(500,385,55,50);
            passVisible.setBackground(new Color(204,204,204));
            passVisible.setIcon(show);
            passVisible.setSelectedIcon(hide);
            passVisible.addItemListener((ItemEvent e) ->{
                if (e.getStateChange()==ItemEvent.SELECTED){
                    pass.setEchoChar((char)0);
                }else{
                    pass.setEchoChar('*');
                }
            });
            
            
            Login.setBounds(190,400,150,50);
            Login.setBackground(Color.lightGray);
            Login.setBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.yellow));
            Login.setFocusable(false);
            Login.addActionListener((ActionEvent e) -> {
                if (e.getSource()==Login){    
                    tabbed.setSelectedIndex(0);
                }
            });
            
            Register.setBounds(190,500,150,50);
            Register.setBackground(Color.lightGray);
            Register.setBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.yellow));
            Register.setFocusable(false);
            Register.addActionListener((ActionEvent e) -> {
                if (e.getSource()==Register){  
                    tabbed.setSelectedIndex(1);
                }
            });
            
            welcome.setText("Welcome to");
            welcome.setBounds(152,200,250,50);
            welcome.setFont(new Font("Consolas",Font.ITALIC,45));
            welcome.setForeground(Color.white);
            
            welcome2.setText("Ledger System");
            welcome2.setBounds(100,250,500,50);
            welcome2.setFont(new Font("Consolas",Font.ITALIC,50));
            welcome2.setForeground(Color.white);
            
            panelleft.setBounds(0,0,549,720);
            panelleft.setBackground(new Color(51,51,153));
            
            panelLogin.setBounds(549,0,731,720);
            panelLogin.setBackground(new Color(204,204,204));
            panelLogin.setBorder(BorderFactory.createEtchedBorder());
            panelLogin.setLayout(null);
            panelLogin.add(username);
            panelLogin.add(user);
            panelLogin.add(password);
            panelLogin.add(pass);
            panelLogin.add(passVisible);
            
            panelRegister.setBounds(549,0,731,720);
            panelRegister.setBackground(new Color(204,204,204));
            panelRegister.setBorder(BorderFactory.createEtchedBorder());
            panelRegister.setLayout(null);
            
            layer=new JLayeredPane();
            layer.setBounds(0,0,1280,720);
            layer.add(panelleft,Integer.valueOf(0));
            layer.add(tabbed,Integer.valueOf(0));
            layer.add(welcome,Integer.valueOf(1));
            layer.add(welcome2,Integer.valueOf(1));
            layer.add(Login,Integer.valueOf(1));
            layer.add(Register,Integer.valueOf(1));
            
            this.setTitle("Ledger Syste,");//sets title of frame
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit application
            this.setLayout(null);
            this.setResizable(false);//prevent resizing
            this.setSize(1280,720);//set size of frame^
            this.add(layer);
            this.setVisible(true);//make frame visible

        }

}


