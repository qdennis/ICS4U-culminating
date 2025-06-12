public class s extends Account {
    private double interestRate;

    public s(String accountId, double initialBalance, double interestRate) {
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