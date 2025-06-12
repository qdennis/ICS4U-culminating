import java.awt.event.ActionEvent; //import all neccesary imports
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class sendMoneyFrame extends JFrame implements ActionListener{
    JTextField txfRecipient = new JTextField();
    JButton btnSend = new JButton("Send");
    JTextField txfAmount = new JTextField();
    JLabel lblAmount = new JLabel("Amount:");
    JLabel lblTo = new JLabel("Recipient Username:");
    JLabel lblStatus = new JLabel("");
    private String selectedAccount;
    sendMoneyFrame(user user,String accountSelected){
        selectedAccount = accountSelected;
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
            }
            else{
                if (selectedAccount.equals("Checking")){
                    Account targetAccount = null;
                    List<Account> accounts = user.getAccount();
                    String targetAccountId = "Checking";
                    for (Account acc : accounts) {
                if (acc.getAccountId().equals(targetAccountId)) {
            targetAccount = acc;
        break;
    }
}
            
        }
        else{

        }
            }

            // TODO: Connect to actual transaction logic
            lblStatus.setText("Money sent to " + recipient + ": $" + amount);
        } catch (NumberFormatException ex) {
            lblStatus.setText("Invalid amount.");
        }
    }
    }