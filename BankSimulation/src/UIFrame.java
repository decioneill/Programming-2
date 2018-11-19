import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//First frame that you see on screen
public class UIFrame extends JFrame {
    //constructor
    public UIFrame() {
        
        
        this.setLayout(new GridLayout(3,1,0,20));
        JButton buttonCurrentAcc = new JButton("Current Account");
        JButton buttonSavingsAcc = new JButton("Savings Account");
        JLabel createJLbl = new JLabel("Choose an Account Type");
        createJLbl.setHorizontalAlignment(JLabel.CENTER);
        add(createJLbl);
        add(buttonCurrentAcc);
        add(buttonSavingsAcc);
        
        // Current Acc Action listener
        buttonCurrentAcc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 	UIFrame.this.currentAccount();
                }
        }       
        );
        
        // Savings Acc Action listener
        buttonSavingsAcc.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
                    UIFrame.this.savingsAccount();
                }
        }
        );
        
        //frame settings
        setTitle("Create Account");
        setSize(new Dimension(450, 300));
        this.setLocationRelativeTo(null);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    protected void savingsAccount() {
        String strInitDepo = JOptionPane.showInputDialog("Please enter initial deposit to start your account.");
        double initDepo = Double.parseDouble(strInitDepo);
        boolean isSavingsAccType = true; 
        
        if(initDepo < 100) {
            JOptionPane.showMessageDialog(this, "Unfortunately we can't create the account. The initial balance must be at least £100\n\n" + "Would you like to try a different amount?" ,
        	"Message", JOptionPane.PLAIN_MESSAGE) ;
            // ask user whether he wants to try again
            String yesOrNo = JOptionPane.showInputDialog("Would you like to enter another value? (y/n)");
            if (yesOrNo.equals("y")) {
                this.savingsAccount();
            } 
        } else {
            JOptionPane.showMessageDialog(this, String.format("Hello,\nYour account balance is £%.2f",initDepo),
        	"Message", JOptionPane.PLAIN_MESSAGE) ;
            //create new savings account frame
            AccountFrame savAcc = new AccountFrame(initDepo, isSavingsAccType);
            savAcc.setVisible(true);
            // make previous frame invisible - imitate closing
            this.setVisible(false);
        }
    }
    
    protected void currentAccount() {
        String strInitDepo = JOptionPane.showInputDialog("Please enter initial deposit to start your account.");
        double initDepo = Double.parseDouble(strInitDepo);
        boolean isSavingsAccType = false; 
        //if deposit is less than £1
        if (initDepo < 1) {
            JOptionPane.showMessageDialog(this, "Unfortunately we can't create the account. The initial balance must be at least £1\n\n" + "Would you like to try a different amount?" ,
        	"Message", JOptionPane.PLAIN_MESSAGE) ;
            // ask user whether he wants to continue
            String yesOrNo = JOptionPane.showInputDialog("Would you like to enter another value? (y/n)");
            if (yesOrNo.equals("y")) {
                this.currentAccount();
            }
            
        } else {
            JOptionPane.showMessageDialog(this, String.format("Hello,\nYour account balance is £%.2f",initDepo), 
                    "Message" , JOptionPane.PLAIN_MESSAGE) ;
            //create new frame current account
            AccountFrame curAcc = new AccountFrame(initDepo, isSavingsAccType);
            curAcc.setVisible(true);
            //make initial frame invisible - to imitate closing 
            this.setVisible(false);
        }
    }
}