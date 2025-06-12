import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class moveMoneyFrame extends JFrame implements ActionListener {

    private JTextField amountField;
    private JLabel resultLabel;
    
    public moveMoneyFrame() {
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

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = amountField.getText().trim();
        try {
            double amount = Double.parseDouble(input);
            if (amount <= 0) {
                resultLabel.setText("Amount must be positive.");
            } else {
                resultLabel.setText("Added $" + amount + " successfully.");
                // Here you'd call something like: account.deposit(amount);
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid number entered.");
        }
    }
}