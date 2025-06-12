public class user{ 
        private ArrayList<Account> accounts;
        private String cardNumber;
        private Map<String, Account> accountsChecker;
        public user (String cardNumberInput){
            cardNumber = cardNumberInput;
        }   
        public int addBalance(int amountInput){
            return 1;
        }
        public String getCardNumber(){
            return cardNumber;
        }
        public void addAccount(Account acc){
        accounts.add(acc);
    }
    public ArrayList<Account> getAccount(){
        return accounts;
    }
    }