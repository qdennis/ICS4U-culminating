public class CheckingAccount extends Account {
    public CheckingAccount(String accountId,double initialBalance) {
        super(accountId, initialBalance);
    }

    @Override
    public String getAccountType() {
        return "Checking";
    }
}