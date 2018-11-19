public class Transaction {

   int month; //range from 1-12
   int year;
   double transaction;

    public Transaction(double transactionAmount, int month, int year){
        this.month = month;
        this.year = year;
        this.transaction = transactionAmount;
    }

    public double getTransactionAmount() {
        return transaction;
    }

    public int getMonth() {        
        return month;
    }
    
    public int getYear() {        
        return year;
    }
}
