/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ledger.system;

import javax.swing.JFrame;

/**
 *
 * @author Liyik
 */
public class LoanUI {
    public LoanUI(){
        //LocalDate expired=getperiod and created from sql and calculate expiration date
        //compare local date now and expired and give message how many day left
        // do the same at myframe to see whether should denied access for credit debit or not
        
        JFrame frame=new JFrame();
        frame.setLayout(null);
        frame.setBounds(0,0,720,480);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(layer);
        frame.setVisible(true);
    }
}
