import java.util.ArrayList;
import java.util.Collections;

public class CurrentAccount extends Account {
    private double balance = 0;
    
    //A current account can be opened with a minimum of £100
    public CurrentAccount(double bal) {
        accHistory = new TransactionsHistory();
        if (bal < 1) {
            throw new IllegalArgumentException("Error a Current account must have at least £1");
        }
        balance = bal;
        accHistory.addTransaction(bal, bal);
    }

    @Override
    String debitTransaction(double debit) {
        if ((balance - debit) < -1000) {
            return String.format("\n£%.2f debit larger than Overdrawn limit  of -£1000, transaction aborted.", debit);
        }

        balance -= debit;
        accHistory.addTransaction(balance, -debit);
        return String.format("\nAmount £%.2f deducted", debit);
    }

    @Override
    String creditTransaction(double credit) {
       if (credit > 500) {
           balance += credit + 10;
           accHistory.addTransaction(balance, credit);  
           return String.format("\nAmount £%.2f credited. £10 rewarded", credit);
       } else {
           balance += credit;
           accHistory.addTransaction(balance, credit);  
           return String.format("\nAmount £%.2f credited.", credit);
       }
    }

    @Override
    double minBalance() {
        
        ArrayList<Double> tempbalances = new ArrayList<>();
        
        for (int i =0 ; i<accHistory.getNumberOfTransactions() ; i++) {
            tempbalances.add(accHistory.getBalanceAmount(i));
        }
        
        Collections.sort(tempbalances);
        return tempbalances.get(0);  
    }

    @Override
    double maxBalance() {
    	ArrayList<Double> tempbalances = new ArrayList<>();
    	
    	for (int i =0 ; i<accHistory.getNumberOfTransactions() ; i++) {
    	    tempbalances.add(accHistory.getBalanceAmount(i));
    	}
    	
    	Collections.sort(tempbalances);
    	Collections.reverse(tempbalances);
    	return tempbalances.get(0);
    }
    
    @Override
    double getBalance() {
        return balance;
    }

    @Override
    void creditInterest() {
    }
    
    @Override
     void addTransaction(double balance, double amount) {
        if (amount >= 0) {
            accHistory.addTransaction(balance, amount);
        } else {
            accHistory.addTransaction(balance, -amount);
        }
     }
}
