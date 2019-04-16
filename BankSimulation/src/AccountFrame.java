import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A JFrame subclass that provides a GUI to display account information, start and stop a simulation
 * Every 5 seconds simulates 1 month duration, at the start of each month 1 random transaction is made.
 * 
 * @author Maciej Rozmiarek
 * @author Declan O'Neill
 * @author John Murphy
 */
public class AccountFrame extends javax.swing.JFrame 
{
    boolean isSavingsAccType = false;       //Used to determine which account class is used
    public Account activeAccount;       // abstract class to be determined in constructor
    boolean isLooping = false;      // Used in both start and stop method.
    
     /**
      * Creates new frame AccountFrame
      * Constructor to create a frame that can be used to run simulation.
      * @param initDepo Initial balance amount
      * @param accType Uses a boolean value to determine which Account subclass. true for Savings Account, false for Current Account
      */
    public AccountFrame(double initDepo, boolean accType) {       
        isSavingsAccType = accType;        
        if (isSavingsAccType == true) {
            activeAccount = new SavingsAccount(initDepo);
        } else {
            activeAccount = new CurrentAccount(initDepo);
        }        
        activeAccount.balance = initDepo;

        initComponents();       //adds in components that are added through NetBeans Design
        stopBtn.setEnabled(false);      //initially disabled. Enables once start button has been pressed

        if (isSavingsAccType == true) {     //Sets frameTitle dependent on account subclass.
            setTitle("Savings Account");
        } else {
            setTitle("Current Account");
        }
        transactionsJTextArea.append((String.format("Account opened, £%,.2f added", activeAccount.balance)));       // Initial balance text added to transactions text window.
        
        //add action listeners
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //disables All GUI Buttons other than "stop" and runs the simulation.
                toggleButtons();
                runSimulation();
            }
        } );

        stopBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                //Enables all GUI buttons other than stop. stops the simulation running with dialog.
                toggleButtons();
                stopSimulation();
                JOptionPane.showMessageDialog(null, "Simulation has stopped", "Simulation", JOptionPane.PLAIN_MESSAGE);
            }
        } );
        
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                AccountFrame.this.windowClosed();
            }
        } );
        
        /**Opens new JFrame which displays graph based on the activeAccounts accHistory (transactions history).*/
        buttonGraph.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                JFrame graphFrame = new JFrame("Line Graph");
                graphFrame.setLayout(new BorderLayout());
                
                JLabel title = new JLabel("Transaction History");
                graphFrame.add(title, BorderLayout.NORTH);
                title.setHorizontalAlignment(JLabel.CENTER);
                
                JLabel leftAxis = new JLabel("Amount (£)");
                graphFrame.add(leftAxis, BorderLayout.WEST);
                leftAxis.setHorizontalAlignment(JLabel.RIGHT);
                
                JLabel xAxis = new JLabel("Month");
                graphFrame.add(xAxis, BorderLayout.SOUTH);
                xAxis.setHorizontalAlignment(JLabel.CENTER);
                
                graphFrame.getContentPane().add(new GraphPanel(activeAccount.accHistory));
                graphFrame.setSize(800, 600);
                graphFrame.setLocationRelativeTo(buttonGraph);
                Dimension minSize = new Dimension(250, 250);  //Prevents program crashing when reduced to too small a size
                graphFrame.setMinimumSize(minSize);
                
                graphFrame.setVisible(true);
                graphFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            }
        } );
        
        /**Opens start screen "UIFrame" to make new account*/
        newAccBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                UIFrame startUpFrame = new UIFrame();
                startUpFrame.setVisible(true);
                dispose();
            }
        });
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        exitBtn = new javax.swing.JButton();
        simulationLabel = new javax.swing.JLabel();
        startBtn = new javax.swing.JButton();
        stopBtn = new javax.swing.JButton();
        buttonGraph = new javax.swing.JButton();
        monthProgress = new javax.swing.JProgressBar();
        progressText = new javax.swing.JLabel();
        newAccBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        transactionsJTextArea = new javax.swing.JTextArea();
        transactionsLabel = new javax.swing.JLabel();
        balanceLabel = new javax.swing.JLabel();
        poundLabel1 = new javax.swing.JLabel();
        maxLabel = new javax.swing.JLabel();
        maxAmountLabel = new javax.swing.JLabel();
        minLabel = new javax.swing.JLabel();
        minAmountLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(java.awt.Color.lightGray);

        exitBtn.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        exitBtn.setText("Exit");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

        simulationLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        simulationLabel.setText("Simulation");

        startBtn.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        startBtn.setText("Start");
        startBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startBtnActionPerformed(evt);
            }
        });

        stopBtn.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        stopBtn.setText("Stop");
        stopBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopBtnActionPerformed(evt);
            }
        });

        buttonGraph.setText("Draw Graph");
        buttonGraph.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGraphActionPerformed(evt);
            }
        });

        progressText.setText("0 Sec");

        newAccBtn.setText("New Account");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(startBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonGraph, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                    .addComponent(stopBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(simulationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(monthProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(progressText))
                    .addComponent(newAccBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(simulationLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stopBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(monthProgress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(progressText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addComponent(buttonGraph)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newAccBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exitBtn)
                .addContainerGap())
        );

        jPanel3.setBackground(java.awt.Color.lightGray);

        transactionsJTextArea.setEditable(false);
        transactionsJTextArea.setColumns(20);
        transactionsJTextArea.setRows(5);
        jScrollPane1.setViewportView(transactionsJTextArea);

        transactionsLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        transactionsLabel.setText("Transactions: ");

        balanceLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        balanceLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        balanceLabel.setText("Balance: ");

        poundLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        poundLabel1.setText("£0.00");
        poundLabel1.setText(String.format("£%,.2f",activeAccount.balance));

        maxLabel.setText("Maximum Balance:");

        maxAmountLabel.setText("£0.00");
        maxAmountLabel.setText(String.format("£%,.2f", activeAccount.maxBalance()));

        minLabel.setText("Minimum Balance:");

        minAmountLabel.setText("£0.00");
        minAmountLabel.setText(String.format("£%,.2f", activeAccount.minBalance()));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(balanceLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(poundLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(minLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(minAmountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(maxLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(maxAmountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(219, 219, 219))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(transactionsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(balanceLabel)
                    .addComponent(poundLabel1)
                    .addComponent(maxLabel)
                    .addComponent(maxAmountLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(minLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(minAmountLabel))
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(transactionsLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(802, 397));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed

    }//GEN-LAST:event_exitBtnActionPerformed

    private void startBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startBtnActionPerformed

    }//GEN-LAST:event_startBtnActionPerformed

    private void stopBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopBtnActionPerformed

    }//GEN-LAST:event_stopBtnActionPerformed

    private void buttonGraphActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGraphActionPerformed

    }//GEN-LAST:event_buttonGraphActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel balanceLabel;
    private javax.swing.JButton buttonGraph;
    private javax.swing.JButton exitBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel maxAmountLabel;
    private javax.swing.JLabel maxLabel;
    private javax.swing.JLabel minAmountLabel;
    private javax.swing.JLabel minLabel;
    private javax.swing.JProgressBar monthProgress;
    private javax.swing.JButton newAccBtn;
    private javax.swing.JLabel poundLabel1;
    private javax.swing.JLabel progressText;
    private javax.swing.JLabel simulationLabel;
    private javax.swing.JButton startBtn;
    private javax.swing.JButton stopBtn;
    private javax.swing.JTextArea transactionsJTextArea;
    private javax.swing.JLabel transactionsLabel;
    // End of variables declaration//GEN-END:variables

    
//Methods    
    /**
     * credits the balance by the double amount provided.
     * If the Account is a SavingsAccount type it will credit the interest amount first.
     * Transaction is recorded in the Transaction text area and GUI elements are updated with new figures.
     * 
     * @param amount Amount to be added to balance.
     */
    private void creditTransaction(double amount) {
        if (activeAccount instanceof SavingsAccount) {
            activeAccount.creditInterest();
            transactionsJTextArea.append(String.format("\nInterest added, New balance: £%,.2f", activeAccount.getBalance()));
            updateTransactionsText();
        }
        transactionsJTextArea.append(activeAccount.creditTransaction(amount));
        updateTransactionsText();
    }    
    
    /**
     * debits the balance by the double amount provided.
     * If the account is a SavingsAccount type it will credit the interest amount first.
     * Transaction is recorded in the Transaction text area and GUI elements are updated with new figures.
     * 
     * @param amount Amount to be subtracted from balance.
     */
    private void debitTransaction(double amount) {
        if (activeAccount instanceof SavingsAccount) {
            activeAccount.creditInterest();
            transactionsJTextArea.append(String.format("\nInterest added, New balance: £%,.2f", activeAccount.getBalance()));
        }
        transactionsJTextArea.append(activeAccount.debitTransaction(amount));
        updateTransactionsText();
    }
    
    /**
     * Loops a runSim thread that randomly produces transactions.
     * Separate thread required in order to allow user to push buttons on the GUI. Otherwise program locks up in infinite loop.
     */
    private void runSimulation() {
        isLooping = true;

        Thread runSim;
        runSim = new Thread(() -> {
            while (isLooping == true) {
                double randTransact = Math.random();
                double randAmount = randomWithRange(100,2000);
                
                if (randTransact >= 0.50) {
                    creditTransaction(randAmount);
                } else {
                    debitTransaction(randAmount);
                }
                transactionsJTextArea.repaint();
                try {
                    for (int i =0 ; i <100 && isLooping == true ; i++) {
                        monthProgress.setValue(i);
                        progressText.setText(String.valueOf(i/20) + " Sec");
                        Thread.sleep(49);
                    }
                } catch (InterruptedException ex) {
                }
            }
        });
        runSim.start();
    }
    
    /**
     * Stops runSim looping and resets progress values.
     */
    private void stopSimulation() {
        isLooping = false;
        monthProgress.setValue(0);
        progressText.setText("0 Sec");
    }
        
    /**
     * Updates text values the TransactionsJTextArea, and Balance labels on the GUI.
     */
    public void updateTransactionsText() {
        transactionsJTextArea.append(String.format(", New balance: £%,.2f",activeAccount.getBalance()));
        poundLabel1.setText(String.format("£%,.2f", activeAccount.getBalance()));
        maxAmountLabel.setText(String.format("£%,.2f",activeAccount.maxBalance()));
        minAmountLabel.setText(String.format("£%,.2f",activeAccount.minBalance()));
    }
    
    /**
     * Determines a random Double figure between the range given
     * 
     * @param min Minimum value of range
     * @param max Maximum value of range
     * @return  Random value between ranges
     */
    private double randomWithRange(int min, int max) {
        double range = (max - min) + 1;
        range = Math.random() * range + min;
        return range;
    }
    
    /**
     * Switches the buttons on the GUI on/off depending on if it is running Simulation.
     */
    private void toggleButtons() {        
        startBtn.setEnabled(isLooping);
        stopBtn.setEnabled(!isLooping);
        exitBtn.setEnabled(isLooping);
        buttonGraph.setEnabled(isLooping);
    }
    
    /**
     * Ends the Program.
     */
    protected void windowClosed() {
        System.exit(0);
    }
    //ends Methods
}//end AccountFrame