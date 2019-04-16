/**Abstract Class. Superclass to CurrentAccount and SavingsAccount.
 * 
 * @author Harry McGoldrick
 * @author Declan O'Neill
 */
public abstract class Account {
    
    TransactionsHistory accHistory;
    protected double balance;
    protected double interest; //Interest per annum
    protected int limit;
    protected int time;
    
    abstract String debitTransaction(double debit);
    
    abstract String creditTransaction(double credit);
    
    abstract double minBalance();
    
    abstract double maxBalance();
    
    abstract double getBalance();
    
    abstract void creditInterest();
}
