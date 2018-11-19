
import java.util.ArrayList;
import java.util.List;


public class TransactionsHistory 
{
    private List<Transaction> transactions = new ArrayList<>();
    private List<Double> balances = new ArrayList<>();
    private int numTransact = 0;
    private int month = 1;
    private int year = 2019;
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
    
    private void addBalance(double amount) {
        balances.add(amount);
    }
    
    public double getAmount(int i) {
        return transactions.get(i).transaction;
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
}
