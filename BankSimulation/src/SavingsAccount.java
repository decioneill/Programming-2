import java.util.ArrayList;
import java.util.Collections;

/**
 * Subclass of Account.
 * 
 * @author Harry McGoldrick
 * @author Declan O'Neill
 */
public class SavingsAccount extends Account {

    private int timeCheck;      //increments by 1 each transaction, resets at 12, 0-11 represents 1 year / 12 transactions as only 2 debits per year allowed,
   
    /**
     * A saving account can be opened with the minimum of £100
     * Sets up accHistory for storing transactions.
     * 
     * @param bal initial balance amount
     */
    public SavingsAccount(double bal) {
        accHistory = new TransactionsHistory();
        if(bal < 100){
            throw new IllegalArgumentException("\nError a savings account must have at least £100");
        }
        balance = bal;
        accHistory.addTransaction(bal, bal);
    }

    /**
     * Sorts a temporary array from the balances. 
     * Finds the highest balance and returns a double.
     * 
     * @return highest stored double value
     */    
    @Override
    double maxBalance() {    	
        ArrayList<Double> tempbalances = new ArrayList<>();
        
        for (int i = 0 ; i<accHistory.getNumberOfTransactions() ; i++) {
            tempbalances.add(accHistory.getBalanceAmount(i));
        }
        Collections.sort(tempbalances);
        Collections.reverse(tempbalances);
        return tempbalances.get(0);
    }

    /**
     * Sorts a temporary array from the balances. 
     * Finds the lowest balance and returns a double.
     * 
     * @return lowest stored double value
     */    
    @Override
    double minBalance() {
        ArrayList<Double> tempbalances = new ArrayList<>();
        
        for (int i = 0 ; i<accHistory.getNumberOfTransactions() ; i++) {
            tempbalances.add(accHistory.getBalanceAmount(i));
        }
        Collections.sort(tempbalances);
        return tempbalances.get(0);
    }

    /**
     * Debits an amount from the balance attribute and adds a transaction to the accHistory.
     * Checks whether there has been more than 2 debits in the last 12 months. prevents more than 2.
     * 
     * @param debit amount Double Value
     * @return Returns an string that informs amount deducted or if transaction was invalid.
     */
    @Override
    String debitTransaction(double debit) {
        timeCheck++;

        if ((balance - debit) < 100) {
            return String.format("\n£%,.2f debit would bring Balance below £100.00, debit failed!", debit);
        }
        if (timeCheck >= 12) {
            limit = 0;
            timeCheck = 0;
        }
        if (limit >= 2) {
            return String.format( "\nOnly two debits per year, £%.2f debit failed!", debit);
        }

        balance -= debit;
        limit++;
        accHistory.addTransaction(balance, -debit);
        return String.format("\nAmount £%,.2f deducted", debit);
    }
    
    /**
     * Credits an amount from the balance attribute and adds a transaction to the accHistory.
     * ticks up the time check (represents number of months)
     * 
     * @param credit amount Double Value
     * @return Returns an string that informs amount added
     */
    @Override
    String creditTransaction(double credit) {
        timeCheck++;
        balance += credit;
        accHistory.addTransaction(balance, credit);
        return String.format("\nAmount £%,.2f credited", credit);
    }
    
    /**
     * returns the stored balance value 
     * 
     * @return Double
     */
    @Override
    public double getBalance() {
        return balance;
    }

    /**
     * Gets the balance, calculates the interest based on interest variable. Adds calculated amount onto balance.
     */    
    @Override
    void creditInterest() {
        if (balance >0) {
        balance += (balance / 100) * (interest / 12);
        }
    }
    
}//End SavingsAccount
