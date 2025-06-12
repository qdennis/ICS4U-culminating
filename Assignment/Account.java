public class Account {
    private String accountId;
    
    private double balance;

    public Account(String accountId, double initialBalance) {
        this.accountId = accountId;
        this.balance = 0;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) return false;
        balance -= amount;
        return true;
    }
    public String getAccountType() {
        return "Unselected";
    }

    
    
}
public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String accountId, double initialBalance, double interestRate) {
        super(accountId, initialBalance);
        this.interestRate = interestRate;
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }

    public double getInterestRate() {
        return interestRate;
    }
}

public class CheckingAccount extends Account {
    public CheckingAccount(String accountId,double initialBalance) {
        super(accountId, initialBalance);
    }

    @Override
    public String getAccountType() {
        return "Checking";
    }
}