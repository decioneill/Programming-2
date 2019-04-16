import java.util.ArrayList;
import java.util.Collections;

/**
 * Subclass of Account.
 * 
 * @author Harry McGoldrick
 * @author Declan O'Neill
 */
public class CurrentAccount extends Account {
    
    /**A current account can be opened with a minimum of £100. 
     * sets up new accHistory to record transactions. 
     * 
     * @param bal Initial balance (double value)
     */
    public CurrentAccount(double bal) {
        accHistory = new TransactionsHistory();
        if (bal < 1) {
            throw new IllegalArgumentException("Error a Current account must have at least £1");
        }
        balance = bal;
        accHistory.addTransaction(bal, bal);
    }

    /**
     * Debits an amount from the balance attribute and adds a transaction to the accHistory.
     * 
     * @param debit amount Double Value
     * @return Returns an string that informs amount deducted or if transaction was invalid.
     */
    @Override
    public String debitTransaction(double debit) {
        if ((balance - debit) < -1000) {
            return String.format("\n£%,.2f debit larger than Overdrawn limit  of -£1000, transaction aborted.", debit);
        }
        balance -= debit;
        accHistory.addTransaction(balance, -debit);
        return String.format("\nAmount £%.2f deducted", debit);
    }

    /**
     * Credits an amount from the balance attribute and adds a transaction to the accHistory.
     * 
     * @param credit amount Double value
     * @return Returns an string that informs amount deducted or if transaction was invalid.
     */
    @Override
    public String creditTransaction(double credit) {
       if (credit > 500) {
           balance += credit + 10;
           accHistory.addTransaction(balance, credit);  
           return String.format("\nAmount £%,.2f credited. £10 rewarded", credit);
       } else {
           balance += credit;
           accHistory.addTransaction(balance, credit);  
           return String.format("\nAmount £%,.2f credited.", credit);
       }
    }

    /**
     * Sorts a temporary array from the balances. 
     * Finds the lowest balance and returns a double.
     * 
     * @return lowest stored double value
     */
    @Override
    public double minBalance() {
        
        ArrayList<Double> tempbalances = new ArrayList<>();
        
        for (int i =0 ; i<accHistory.getNumberOfTransactions() ; i++) {
            tempbalances.add(accHistory.getBalanceAmount(i));
        }
        Collections.sort(tempbalances);
        return tempbalances.get(0);  
    }

    /**
     * Sorts a temporary array from the balances. 
     * Finds the highest balance and returns a double.
     * 
     * @return highest stored double value
     */
    @Override
    public double maxBalance() {
    	ArrayList<Double> tempbalances = new ArrayList<>();
    	
    	for (int i =0 ; i<accHistory.getNumberOfTransactions() ; i++) {
    	    tempbalances.add(accHistory.getBalanceAmount(i));
    	}
    	Collections.sort(tempbalances);
    	Collections.reverse(tempbalances);
    	return tempbalances.get(0);
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

    @Override
    public void creditInterest() {
    }
   
}
