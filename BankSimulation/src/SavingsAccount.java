import java.util.ArrayList;
import java.util.Collections;

public class SavingsAccount extends Account {
    private double balance = 0;
    private final double interest = 3; //Interest per annum
    private int limit;
    private int timeCheck;
   
    //A saving account can be opened with the minimum of £100
    public SavingsAccount(double bal) {
        accHistory = new TransactionsHistory();
        if(bal < 100){
            throw new IllegalArgumentException("\nError a savings account must have at least £100");
        }
        balance = bal;
        accHistory.addTransaction(bal, bal);
    }

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

    @Override
    double minBalance() {
        ArrayList<Double> tempbalances = new ArrayList<>();
        
        for (int i = 0 ; i<accHistory.getNumberOfTransactions() ; i++) {
            tempbalances.add(accHistory.getBalanceAmount(i));
        }
    	
        Collections.sort(tempbalances);
        return tempbalances.get(0);  
        
    }

    @Override
    String debitTransaction(double debit) {
        timeCheck++;

        if ((balance - debit) < 100) {
            return String.format("\n£%.2f debit would bring Balance below £100.00, debit failed!", debit);
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
        return String.format("\nAmount £%.2f deducted", debit);
    }

    @Override
    String creditTransaction(double credit) {
        timeCheck++;
        balance += credit;
        accHistory.addTransaction(balance, credit);
        return String.format("\nAmount £%.2f credited", credit);
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    void creditInterest() {
        if (balance >0) {
        balance += (balance / 100) * (interest / 12);
        }
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
