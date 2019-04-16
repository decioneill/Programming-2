/**
 * Simple class to store month, year and amount as a transaction.
 * 
 * @author Harry McGoldrick
 * @author Declan O'Neill
 */
public class Transaction {

   private final int month; //range from 1-12
   private final int year;
   private final double transaction;

   /**
    * Constructor that sets the Amount, month and year
    * 
    * @param transactionAmount double amount of transaction
    * @param month Stored as an integer between 1 and 12
    * @param year Stored as integer, YYYY
    */
    public Transaction(double transactionAmount, int month, int year) {
        this.month = month;
        this.year = year;
        this.transaction = transactionAmount;
    }

    /**Method returns transaction amount
     * 
     * @return (double) Amount
     */
    public double getTransactionAmount() {
        return transaction;
    }

    /**
     * Method returns month integer
     * 
     * @return (Integer) month value
     */
    public int getMonth() {        
        return month;
    }
    
    /**
     * Method returns year integer
     * 
     * @return (Integer) year value
     */
    public int getYear() {        
        return year;
    }
}
