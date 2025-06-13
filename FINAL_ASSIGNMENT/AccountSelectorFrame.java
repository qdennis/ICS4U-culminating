import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
public class AccountSelectorFrame extends JFrame {

    private JButton btnChecking;
    private JButton btnSavings;
    private static user user; 
    private static ArrayList<user> allUsers;
    public AccountSelectorFrame(user user, ArrayList<user> allUsers) {
        this.allUsers = allUsers;
        this.user = user;
        setTitle("Select Account Type");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null); // center the window

        btnChecking = new JButton("Checking Account");
        btnSavings = new JButton("Savings Account");

        btnChecking.addActionListener(e -> openCheckingAccount());
        btnSavings.addActionListener(e -> openSavingsAccount());

        // Layout with buttons vertically stacked
        setLayout(new GridLayout(2, 1, 10, 10));
        add(btnChecking);
        add(btnSavings);
        this.setVisible(true);
    }

    private void openCheckingAccount() {
        // Example: open CheckingAccountGUI window
        JOptionPane.showMessageDialog(this, "Opening Checking Account GUI...");
        user.updateType("Checking");
        dispose();
        new HomePage(user, allUsers);
        
        // new CheckingAccountGUI(user).setVisible(true);
        // dispose(); // close this frame if desired
    }

    private void openSavingsAccount() {
        // Example: open SavingsAccountGUI window
        JOptionPane.showMessageDialog(this, "Opening Savings Account GUI...");
        user.updateType("Savings");
        dispose();
        new HomePage(user, allUsers);
        // new SavingsAccountGUI(user).setVisible(true);
        // dispose(); // close this frame if desired
    }

    
}