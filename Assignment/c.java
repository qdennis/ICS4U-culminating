
public class c extends Account {
    private double balance;
    public c(String accountId,double initialBalance) {
        super(accountId, initialBalance);
    }

    @Override
    public String getAccountType() {
        return "Checking";
    }
    public void addBalance(double balanceEntry){
        balance = balance + balanceEntry;
    }
}
