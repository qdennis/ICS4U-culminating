import java.util.ArrayList;
public class user{ 
        private ArrayList<Account> accounts;
        private String cardNumber;
        private String accountType;
        private String name;
        private String password;
        private ArrayList<user> userList;
        public user (String cardNumberInput, String name, String password){
            accounts = new ArrayList<>();
            this.name = name;
            this. password = password;
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
    public void updateType(String type){
        accountType = type;
    }
    public String getName(){
        return name;
    }
    public String getPassword(){
        return password;
    }
    public String getType(){
        return accountType;
    }
    }