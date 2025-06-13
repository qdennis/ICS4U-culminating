import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
public class moveMoneyFrame extends JFrame implements ActionListener {

    private JTextField amountField;
    private JLabel resultLabel;
    private user user;
    private ArrayList<user> allUsers;
    private JButton backButton;  // added

    public moveMoneyFrame(user user, ArrayList<user> allUsers) {
        this.user = user;
        this.allUsers = allUsers;
        this.setTitle("Move Money");
        setSize(300, 200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel promptLabel = new JLabel("Enter amount to add:");
        promptLabel.setBounds(30, 30, 200, 25);
        add(promptLabel);

        amountField = new JTextField();
        amountField.setBounds(30, 60, 100, 25);
        add(amountField);

        JButton addButton = new JButton("Add Money");
        addButton.setBounds(150, 60, 100, 25);
        addButton.addActionListener(this);
        add(addButton);

        resultLabel = new JLabel("");
        resultLabel.setBounds(30, 100, 250, 25);
        add(resultLabel);

        // Back button added here
        backButton = new JButton("Back");
        backButton.setBounds(30, 130, 100, 25);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close this frame (you can customize to open previous frame)
                dispose();
                new HomePage(user, allUsers);
            }
        });
        add(backButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = amountField.getText().trim();
        String selectedAccount = user.getType();
        c checkingAccount = null;
        s savingsAccount = null;
        try {
            double amount = Double.parseDouble(input);
            if (amount <= 0) {
                resultLabel.setText("Amount must be positive.");
            } else {
                resultLabel.setText("Added $" + amount + " successfully.");
                // Here you'd call something like: account.deposit(amount);
            }
            String targetAccountId = "";
                List<Account> accounts = user.getAccount();

                if (selectedAccount.equals("Checking")) {
                    targetAccountId = "Checking";
                    for (Account acc : accounts) {
                        if (acc.getAccountType().equals(targetAccountId)) {
                            checkingAccount = (c) acc;
                            checkingAccount.addBalance(amount);
                            FileHandler.saveAllUsers("AccountInfo.txt", allUsers);
                        }
                    }
                } else if (selectedAccount.equals("Savings")) {
                    targetAccountId = "Savings";
                    for (Account acc : accounts) {
                        if (acc.getAccountType().equals(targetAccountId)) {
                            savingsAccount = (s) acc;
                            savingsAccount.addBalance(amount);
                            FileHandler.saveAllUsers("AccountInfo.txt", allUsers);
                        }
                    }
                }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid number entered.");
        }
    }
}
