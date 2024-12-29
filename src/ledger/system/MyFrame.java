/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ledger.system;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
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
import database.DatabaseConnector;
import database.UserTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
/**
 *
 * @author Liyik
 */
public class MyFrame extends JFrame{
    static JPanel panelleft;
    static JPanel panelLogin;
    static JPanel panelRegister;
    static JLayeredPane layer;
    static JLabel welcome;
    static JLabel welcome2;
    static JButton Login;
    static JButton Register;
    static JLabel emailLogin;
    static JLabel passwordLogin;
    static JTextField emailTextL;
    static JPasswordField passTextL;
    static JTabbedPane tabbed;
    static JCheckBox passVisible;
    static ImageIcon show;
    static ImageIcon hide;
    static String email;
    static String password;
    static String passwordConfirm;
    static String name;
    static int userId;
        public MyFrame(){
            //Method calling to bulid connection
            DatabaseConnector dbcon = new DatabaseConnector();
            JFrame frame=new JFrame();
            userId=0;
            panelleft=new JPanel();
            panelLogin=new JPanel();
            panelRegister=new JPanel();
            welcome=new JLabel();
            welcome2=new JLabel();
            Login=new JButton("Login");
            Register=new JButton("Register");
            emailLogin=new JLabel("E-mail:");
            passwordLogin=new JLabel("Password");
            emailTextL=new JTextField();
            passTextL=new JPasswordField();
            tabbed=new JTabbedPane();
            passVisible=new JCheckBox();
            show=new ImageIcon("show-password.png");
            hide=new ImageIcon("hide-password.png");
            
            tabbed.setBounds(549,-30,731,750);
            tabbed.add(panelLogin);
            tabbed.add(panelRegister);
            
            emailLogin.setBounds(100,200,200,50);
            emailLogin.setFont(new Font("Consolas",Font.BOLD,18));
            
            emailTextL.setBounds(100,235,400,50);
            emailTextL.setFont(new Font("Consolas",Font.BOLD,18));
            
            passwordLogin.setBounds(100,350,200,50);
            passwordLogin.setFont(new Font("Consolas",Font.BOLD,18));
            
            passTextL.setBounds(100,385,400,50);
            passTextL.setFont(new Font("Consolas",Font.BOLD,18));
            
            passVisible.setBounds(500,385,55,50);
            passVisible.setBackground(new Color(204,204,204));
            passVisible.setIcon(show);
            passVisible.setSelectedIcon(hide);
            passVisible.addItemListener((ItemEvent e) ->{
                if (e.getStateChange()==ItemEvent.SELECTED){
                    passTextL.setEchoChar((char)0);
                }else{
                    passTextL.setEchoChar('*');
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
            
            JButton submit=new JButton("LOG IN");
            submit.setBounds(200,500,200,50);
            submit.setFocusable(false);
            submit.addActionListener((ActionEvent e)->{
                if (e.getSource()==submit){
                    email=emailTextL.getText();
                    password=passTextL.getText();
                    while (true){
                    if (!RegistrationAndLogin.isEmailValid(email)){
                        JOptionPane.showMessageDialog(null,"The email is not valid","Invalid email",JOptionPane.ERROR_MESSAGE);
                        emailTextL.setText("");
                        passTextL.setText("");
                        break;
                    }if (!UserTable.isRegisteredAccount(email)){
                        JOptionPane.showMessageDialog(null,"The email has not been registered","Invalid email",JOptionPane.ERROR_MESSAGE);
                    }
                    try {
                        if (!UserTable.checkPassword(email, password)) {
                            JOptionPane.showMessageDialog(null,"Invalid email or password","Login Unsuccessful",JOptionPane.ERROR_MESSAGE);
                            break;
                        }else{
                            userId = UserTable.getUserId(email);
                            JOptionPane.showMessageDialog(null,"You have login successfully!Welcome to Ledger System!","Login Success",JOptionPane.INFORMATION_MESSAGE);
                            frame.dispose();
                            new TransactionUI();
                            break;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
                        break;
                        }
                    }
                }
        });
            
            
            panelLogin.setBounds(549,0,731,720);
            panelLogin.setBackground(new Color(204,204,204));
            panelLogin.setBorder(BorderFactory.createEtchedBorder());
            panelLogin.setLayout(null);
            panelLogin.add(emailLogin);
            panelLogin.add(emailTextL);
            panelLogin.add(passwordLogin);
            panelLogin.add(passTextL);
            panelLogin.add(passVisible);
            panelLogin.add(submit);
            
            JLabel username1=new JLabel("Enter your username:");
            username1.setBounds(100,150,200,50);
            username1.setFont(new Font("Consolas",Font.BOLD,18));
            JTextField usernameField=new JTextField();
            usernameField.setBounds(100,190,400,50);
            usernameField.setFont(new Font("Consolas",Font.BOLD,18));
            
            JLabel emailReg=new JLabel("Enter your email:");
            emailReg.setBounds(100,240,400,50);
            emailReg.setFont(new Font("Consolas",Font.BOLD,18));
            
            JTextField emailTextR=new JTextField();
            emailTextR.setBounds(100,280,400,50);
            emailTextR.setFont(new Font("Consolas",Font.BOLD,18));
            

            JLabel passwordReg=new JLabel("Enter a password");
            passwordReg.setBounds(100,330,200,50);
            passwordReg.setFont(new Font("Consolas",Font.BOLD,18));
            
            JPasswordField passTextR=new JPasswordField();
            passTextR.setBounds(100,370,400,50);
            passTextR.setFont(new Font("Consolas",Font.BOLD,18));
            
            JLabel passwordCon=new JLabel("Confirm password");
            passwordCon.setBounds(100,420,200,50);
            passwordCon.setFont(new Font("Consolas",Font.BOLD,18));
            
            JPasswordField passText2R=new JPasswordField();
            passText2R.setBounds(100,460,400,50);
            passText2R.setFont(new Font("Consolas",Font.BOLD,18));
            
            JCheckBox passVisibleR=new JCheckBox();
            passVisibleR.setBounds(500,370,55,50);
            passVisibleR.setBackground(new Color(204,204,204));
            passVisibleR.setIcon(show);
            passVisibleR.setSelectedIcon(hide);
            passVisibleR.addItemListener((ItemEvent e) ->{
                if (e.getStateChange()==ItemEvent.SELECTED){
                    passTextR.setEchoChar((char)0);
                }else{
                    passTextR.setEchoChar('*');
                }
            });
            
            JCheckBox passConVisibleR=new JCheckBox();
            passConVisibleR.setBounds(500,460,55,50);
            passConVisibleR.setBackground(new Color(204,204,204));
            passConVisibleR.setIcon(show);
            passConVisibleR.setSelectedIcon(hide);
            passConVisibleR.addItemListener((ItemEvent e) ->{
                if (e.getStateChange()==ItemEvent.SELECTED){
                    passText2R.setEchoChar((char)0);
                }else{
                    passText2R.setEchoChar('*');
                }
            });
            
            
            JButton submitR=new JButton("REGISTER");
            submitR.setBounds(200,550,200,50);
            submitR.setFocusable(false);
            submitR.addActionListener((ActionEvent e)->{
                if (e.getSource()==submitR){
                    email=emailTextR.getText();
                    password=passTextR.getText();
                    passwordConfirm=passText2R.getText();
                    name=usernameField.getText();
                    if (!RegistrationAndLogin.isEmailValid(email)){
                        JOptionPane.showMessageDialog(null,"The email is not valid","Invalid email",JOptionPane.ERROR_MESSAGE);
                       
                    }else if (UserTable.isRegisteredAccount(email)){
                        JOptionPane.showMessageDialog(null,"The email has been registered","Invalid email",JOptionPane.ERROR_MESSAGE);
                        
                    }else if (!RegistrationAndLogin.isNameValid(name)){
                        JOptionPane.showMessageDialog(null,"The name cannot contain special letters or be left blank.","Invalid name",JOptionPane.ERROR_MESSAGE);
                       
                    }else if (!RegistrationAndLogin.isPasswordValid(password)){
                        JOptionPane.showMessageDialog(null,"The password must be longer than 5 characters with letters and digits","Invalid password",JOptionPane.ERROR_MESSAGE);
                      
                    }else if (!password.equals(passwordConfirm)){
                        JOptionPane.showMessageDialog(null,"The please enter the same password in Confirm password.","Invalid password",JOptionPane.ERROR_MESSAGE);
                    }else{
                        String hashedPassword=RegistrationAndLogin.hashPassword(password);
                        UserTable.insertUser(name, email, hashedPassword);
                        JOptionPane.showMessageDialog(null,"You have successfully created an account! Login Now!","Registration success",JOptionPane.INFORMATION_MESSAGE);
                   
                    }
                    }
                
        });
            
                        
            Login.setBounds(190,400,150,50);
            Login.setBackground(Color.lightGray);
            Login.setBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.darkGray));
            Login.setFocusable(false);
            Login.addActionListener((ActionEvent e) -> {
                if (e.getSource()==Login){    
                    Register.setBorder(BorderFactory.createBevelBorder(0, Color.lightGray, Color.darkGray));
                    Login.setBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.darkGray));
                    tabbed.setSelectedIndex(0);
                    usernameField.setText("");
                    passTextR.setText("");
                    emailTextR.setText("");
                }
            });
            
            Register.setBounds(190,500,150,50);
            Register.setBackground(Color.lightGray);
            Register.setBorder(BorderFactory.createBevelBorder(0, Color.lightGray, Color.darkGray));
            Register.setFocusable(false);
            Register.addActionListener((ActionEvent e) -> {
                if (e.getSource()==Register){  
                    Register.setBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.darkGray));
                    Login.setBorder(BorderFactory.createBevelBorder(0, Color.lightGray, Color.darkGray));
                    tabbed.setSelectedIndex(1);
                    emailTextL.setText("");
                    passTextL.setText("");
                    
                }
            });
            
            panelRegister.setBounds(549,0,731,720);
            panelRegister.setBackground(new Color(204,204,204));
            panelRegister.setBorder(BorderFactory.createEtchedBorder());
            panelRegister.setLayout(null);

            panelRegister.add(username1);
            panelRegister.add(usernameField);
            panelRegister.add(passwordReg);
            panelRegister.add(passTextR);
            panelRegister.add(passVisibleR);
            panelRegister.add(emailReg);
            panelRegister.add(emailTextR);
            panelRegister.add(submitR);
            panelRegister.add(passText2R);
            panelRegister.add(passwordCon);
            panelRegister.add(passConVisibleR);
            
            layer=new JLayeredPane();
            layer.setBounds(0,0,1280,720);
            layer.add(panelleft,Integer.valueOf(0));
            layer.add(tabbed,Integer.valueOf(0));
            layer.add(welcome,Integer.valueOf(1));
            layer.add(welcome2,Integer.valueOf(1));
            layer.add(Login,Integer.valueOf(1));
            layer.add(Register,Integer.valueOf(1));
            
            frame.setTitle("Ledger Syste,");//sets title of frame
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//exit application
            frame.setLayout(null);
            frame.setResizable(false);//prevent resizing
            frame.setSize(1280,720);//set size of frame^
            frame.add(layer);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);//make frame visible
            
            frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we){
                int close=JOptionPane.showConfirmDialog(null,"Do you want to Exit??","Exit",JOptionPane.YES_NO_OPTION);
                if (close==0){
                    JOptionPane.showMessageDialog(null,"Thank you for using Ledger System. See you next time!","Exit",JOptionPane.PLAIN_MESSAGE);
                    frame.dispose();
                }
            }
        
        });

        }
        public static void main(String[]args){
         SwingUtilities.invokeLater(MyFrame::new);
    }

}