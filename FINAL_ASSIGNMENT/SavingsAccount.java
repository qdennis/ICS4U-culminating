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