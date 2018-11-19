
public abstract class Account {

        TransactionsHistory accHistory;
        double balance;
        double interest; //Interest per annum

        int lim;
        int time;


        abstract String debitTransaction(double debit);

        abstract String creditTransaction(double credit);

        abstract void addTransaction(double balance, double amount);

        abstract double minBalance();

        abstract double maxBalance();

        abstract double getBalance();

        abstract void creditInterest();


}
