import java.awt.event.*;
import javax.swing.*;

public class AccountSelectorFrame extends JFrame implements ActionListener {
    private JComboBox<String> accountComboBox;
    private JButton selectButton;
    private JTextArea accountDetailsArea;
    //private User user;  // The user whose accounts we are selecting from

    public AccountSelectorFrame(user user) {
        //this.user = user;

        setTitle("Select Account");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel label = new JLabel("Select an account:");
        label.setBounds(30, 30, 150, 25);
        add(label);

        accountComboBox = new JComboBox<>();
        accountComboBox.setBounds(150, 30, 200, 25);
        add(accountComboBox);

        selectButton = new JButton("Select");
        selectButton.setBounds(150, 70, 100, 25);
        selectButton.addActionListener(this);
        add(selectButton);

        accountDetailsArea = new JTextArea();
        accountDetailsArea.setBounds(30, 110, 320, 120);
        accountDetailsArea.setEditable(false);
        add(accountDetailsArea);

        //populateAccounts();

        setVisible(true);
    }

    private void populateAccounts() {
        // Clear combo box first
        accountComboBox.removeAllItems();

        // Add all account IDs from the user's accounts
        for (String accountId : user.getAllAccounts().keySet()) {
            accountComboBox.addItem(accountId);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    
        if (e.getSource() == selectButton) {
            String selectedAccountId = (String) accountComboBox.getSelectedItem();
            if (selectedAccountId != null) {
                Account selectedAccount = user.getAccountById(selectedAccountId);
                if (selectedAccount != null) {
                    String details = "Account ID: " + selectedAccount.getAccountId() + "\n" +
                                     "Type: " + selectedAccount.getAccountType() + "\n" +
                                     "Balance: $" + selectedAccount.getBalance();
                    accountDetailsArea.setText(details);
                } else {
                    accountDetailsArea.setText("Account not found.");
                }
            }
        }
    }

    // Example main to test the frame
    public static void main(String[] args) {
        // Setup a user with some accounts
        //User user = new User("U001", "Alice");
        //user.addAccount(new SavingsAccount("S100", 0.02));
        //user.addAccount(new CheckingAccount("C200"));
        // Show the GUI
}
}