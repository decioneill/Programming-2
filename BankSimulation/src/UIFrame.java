import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * First frame that you see on screen, click a button to choose account type
 * 
 * @author Maciej Rozmiarek
 * @author Declan O'Neill
 */
public class UIFrame extends JFrame {
    //constructor
    public UIFrame() {
        
        // Add buttons,and label.
        this.setLayout(new GridLayout(3,1,0,20));
        JButton buttonCurrentAcc = new JButton("Current Account");
        buttonCurrentAcc.setFont(new Font(buttonCurrentAcc.getName(),Font.PLAIN, 20));
        JButton buttonSavingsAcc = new JButton("Savings Account");
        buttonSavingsAcc.setFont(new Font(buttonSavingsAcc.getName(),Font.PLAIN, 20));
        JLabel createJLbl = new JLabel("Choose an Account Type");
        createJLbl.setFont(new Font(createJLbl.getName(),Font.PLAIN, 30));
        createJLbl.setHorizontalAlignment(JLabel.CENTER);
        add(createJLbl);
        add(buttonCurrentAcc);
        add(buttonSavingsAcc);
        
        // Current Acc Action listener
        buttonCurrentAcc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 	UIFrame.this.createCurrentAccount();
                }
        }       
        );
        
        // Savings Acc Action listener
        buttonSavingsAcc.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
                    UIFrame.this.createSavingsAccount();
                }
        }
        );
        
        //frame settings
        setTitle("Create Account");
        setSize(new Dimension(450, 300));
        this.setLocationRelativeTo(null);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }//End Constructor
 
//Methods
    protected void createSavingsAccount() {
        boolean isSavingsAccType = true;         
        String strInitDepo;
        try {
            strInitDepo = JOptionPane.showInputDialog("Please enter initial deposit to start your account.").replace("£", "");//£ symbol ignored
        } catch (NullPointerException e) {
            return;
        }
        double initDepo;
        
        //attempt to catch unsuitable number inputs
        try {
            initDepo = Double.parseDouble(strInitDepo);
        } catch (NullPointerException e) {
            return;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Initial deposit must be a number.",
                    "Message", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        
        //Ensures that initial deposit is not less than the £100.00 minimum for savings accounts.
        if(initDepo < 100) {
            JOptionPane.showMessageDialog(this, "Unfortunately we can't create the account. The initial balance must be at least £100",
        	"Message", JOptionPane.PLAIN_MESSAGE) ;
        } else {
            JOptionPane.showMessageDialog(this, String.format("Hello,\nYour account balance is £%,.2f",initDepo),
        	"Message", JOptionPane.PLAIN_MESSAGE) ;
            //create new frame accountFrame and disposes UIFrame
            AccountFrame savAcc = new AccountFrame(initDepo, isSavingsAccType);
            savAcc.setVisible(true);
            this.dispose();
        }
    }
    
    protected void createCurrentAccount() {
        boolean isSavingsAccType = false;
        String strInitDepo;
        try {
            strInitDepo = JOptionPane.showInputDialog("Please enter initial deposit to start your account.").replace("£", "");//£ symbol ignored
        } catch (NullPointerException e) {
            return;
        }
        double initDepo;
        
        //attempt to catch unsuitable number inputs
        try {
            initDepo = Double.parseDouble(strInitDepo);
        } catch (NullPointerException e) {
            return;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Initial deposit must be a number",
                    "Message", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        
        //Ensures initial deposit is not less than £1.00 minimum
        if (initDepo < 1) {
            JOptionPane.showMessageDialog(this, "Unfortunately we can't create the account. The initial balance must be at least £1" ,
        	"Message", JOptionPane.PLAIN_MESSAGE) ;
        } else {
            JOptionPane.showMessageDialog(this, String.format("Hello,\nYour account balance is £%,.2f",initDepo), 
                    "Message" , JOptionPane.PLAIN_MESSAGE) ;
            //create new frame accountFrame and disposes UIFrame.
            AccountFrame curAcc = new AccountFrame(initDepo, isSavingsAccType);
            curAcc.setVisible(true);
            this.dispose();
        }
    }
    
}//End UIFrame