import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to handle the List objects, One to record the transactions made, and another to record the balances as they change.
 *
 * @author Declan O'Neill
 */
public class TransactionsHistory 
{
    private final List<Transaction> transactions = new ArrayList<>();
    private final List<Double> balances = new ArrayList<>();
    private int numTransact = 0;
    private int month = 1;
    private int year = 2019;
    
    /**
     * Adds a transaction to the list transactions, increments the month by 1 per transaction (up to 12) and year every 12 transactions added.
     * Adds new balance to the balances list.
     * 
     * @param balance Current balance in active account.
     * @param amount The amount of the transaction
     */
    public void addTransaction(double balance, double amount) {
        Transaction t1 = new Transaction(amount, month, year);
        transactions.add(t1);
        if (month<12) {
            month++;
        } else {
            month = 1;
            year++;
        }
        addBalance(balance);
        numTransact++;
    }
    
    
    /**
     * Adds the balance to the balance list.
     * 
     * @param amount The current balance,
     */
    private void addBalance(double amount) {
        balances.add(amount);
    }
    
    
 //GETTERS
    public double getAmount(int i) {
        return transactions.get(i).getTransactionAmount();
    }
    
    public double getBalanceAmount(int i) {
        return balances.get(i);
    }

    private String getMonth(int i) {
        String monthText;
        switch(i) {
            case 1: monthText = "Jan";
            break;
            case 2: monthText = "Feb";
            break;
            case 3: monthText = "Mar";
            break;
            case 4: monthText = "Apr";
            break;
            case 5: monthText = "May";
            break;
            case 6: monthText = "June";
            break;
            case 7: monthText = "July";
            break;
            case 8: monthText = "Aug";
            break;
            case 9: monthText = "Sept";
            break;
            case 10: monthText = "Oct";
            break;
            case 11: monthText = "Nov";
            break;
            case 12: monthText = "Dec";
            break;
            default: monthText = "Err";
            break;
        }
        return monthText;
    }
    
    public String getDate(int i) {
        String dateText = getMonth(transactions.get(i).getMonth()) + "/" + transactions.get(i).getYear();
        return dateText;
    }
    
    public int getNumberOfTransactions() {
        return numTransact;
    }

    private String getYear() {
        return String.valueOf(year);        
    }
//End GETTERS
}
