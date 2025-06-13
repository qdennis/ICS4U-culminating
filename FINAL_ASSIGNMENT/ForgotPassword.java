import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class ForgotPassword extends JFrame {
    private JTextField cardNumberField;
    private JLabel resultLabel;

    public ForgotPassword() {
        setTitle("Forgot Password");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null); 

        JLabel lblTitle = new JLabel("Forgot Password");
        lblTitle.setBounds(130, 20, 150, 25);
        add(lblTitle);

        JLabel lblCard = new JLabel("Enter Card Number:");
        lblCard.setBounds(50, 60, 150, 25);
        add(lblCard);

        cardNumberField = new JTextField();
        cardNumberField.setBounds(180, 60, 150, 25);
        add(cardNumberField);

        JButton btnRecover = new JButton("Recover");
        btnRecover.setBounds(130, 100, 120, 30);
        add(btnRecover);

        resultLabel = new JLabel("");
        resultLabel.setBounds(50, 150, 300, 25);
        add(resultLabel);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(10, 180, 80, 25);
        add(btnBack);

        btnRecover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cardNumber = cardNumberField.getText().trim();

                if (cardNumber.length() != 16 || !cardNumber.matches("\\d+")) {
                    resultLabel.setText("Please enter a valid 16-digit card number.");
                    return;
                }

                String password = findPasswordByCardNumber(cardNumber);
                if (password != null) {
                    resultLabel.setText("Password: " + password);
                } else {
                    resultLabel.setText("Card number not found.");
                }
            }
        });

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SignIn().setVisible(true);
            }
        });

        setVisible(true);
    }

    private String findPasswordByCardNumber(String cardNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader("AccountInfo.txt"))) {
            String line;
            String currentName = null;
            String currentPassword = null;
            String currentCardNumber = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name:")) {
                    currentName = line.substring(5).trim();
                } else if (line.startsWith("Password:")) {
                    currentPassword = line.substring(9).trim();
                } else if (line.startsWith("Account Number:")) {
                    currentCardNumber = line.substring(15).trim();

                    if (currentCardNumber.equals(cardNumber)) {
                        return currentPassword;
                    }

                    // Reset for the next account entry
                    currentName = null;
                    currentPassword = null;
                    currentCardNumber = null;
                }
            }
        } catch (IOException e) {
            resultLabel.setText("Error reading account file.");
        }
        return null;
    }
}
