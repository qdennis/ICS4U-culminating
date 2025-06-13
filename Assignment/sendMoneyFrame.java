import java.awt.event.ActionEvent; //import all neccesary imports
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class sendMoneyFrame extends JFrame implements ActionListener {
    Account targetAccount = null;
    c checkingAccount = null;
    s savingsAccount = null;
    JTextField txfRecipient = new JTextField();
    JButton btnSend = new JButton("Send");
    JButton btnBack = new JButton("Back"); // ← New back button
    JTextField txfAmount = new JTextField();
    JLabel lblAmount = new JLabel("Amount:");
    JLabel lblTo = new JLabel("Recipient Username:");
    JLabel lblStatus = new JLabel("");
    private String selectedAccount;
    private static ArrayList<user> allUsers;
    private user user;

    sendMoneyFrame(user user, String accountSelected, ArrayList<user> allUsers) {
        this.user = user;
        this.allUsers = allUsers;
        selectedAccount = user.getType();
        this.setTitle("Send Money");
        setSize(400, 250);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblTo.setBounds(30, 30, 150, 25);
        add(lblTo);

        txfRecipient.setBounds(180, 30, 150, 25);
        add(txfRecipient);

        lblAmount.setBounds(30, 70, 150, 25);
        add(lblAmount);

        txfAmount.setBounds(180, 70, 150, 25);
        add(txfAmount);

        btnSend.setBounds(140, 120, 100, 30);
        btnSend.addActionListener(this);
        add(btnSend);

        // Back button setup
        btnBack.setBounds(10, 10, 80, 25);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close this frame
                new HomePage(user, allUsers); // Return to HomePage
            }
        });
        add(btnBack); // Add to frame

        lblStatus.setBounds(30, 170, 300, 25);
        add(lblStatus);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String recipient = txfRecipient.getText().trim();
        String amountText = txfAmount.getText().trim();

        if (recipient.isEmpty() || amountText.isEmpty()) {
            lblStatus.setText("Please fill in all fields.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);
            if (amount <= 0) {
                lblStatus.setText("Enter a positive amount.");
                return;
            } else {
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
                        System.out.println(acc.getAccountType());
                        if (acc.getAccountType().equals(targetAccountId)) {
                            savingsAccount = (s) acc;
                            savingsAccount.addBalance(amount);
                            FileHandler.saveAllUsers("AccountInfo.txt", allUsers);
                        }
                    }
                }
            }

            lblStatus.setText("Money sent to " + recipient + ": $" + amount);
            List<Account> accounts = user.getAccount();
            for (Account acc : accounts) {
                System.out.println(acc.getAccountType());
                System.out.println(acc.getBalance());
            }

        } catch (NumberFormatException ex) {
            lblStatus.setText("Invalid amount.");
        }
    }
}
