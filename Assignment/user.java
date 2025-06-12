import java.util.ArrayList;
public class user{ 
        private static ArrayList<Account> accounts;
        private String cardNumber;
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
    public static ArrayList<Account> getAccount(){
        return accounts;
    }
    }