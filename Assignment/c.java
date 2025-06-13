
public class c extends Account {
    public c(String accountId,double initialBalance) {
        super(accountId, initialBalance);
    }

    @Override
    public String getAccountType() {
        return "Checking";
    }
    public void addBalance(double balanceEntry){
        deposit(balanceEntry);
    }

}
