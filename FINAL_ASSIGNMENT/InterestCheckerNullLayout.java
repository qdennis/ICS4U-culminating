import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InterestCheckerNullLayout extends JFrame {
    private static user user;
    private static ArrayList<user> allUsers;
    private JTextField principalField, rateField, yearsField, resultField;

    public InterestCheckerNullLayout(user user, ArrayList<user> allUsers) {
        this.user = user;
        this.allUsers = allUsers;
        setTitle("Recursive Interest Calculator (Null Layout)");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel principalLabel = new JLabel("Principal Amount:");
        principalLabel.setBounds(30, 20, 120, 25);
        add(principalLabel);

        principalField = new JTextField();
        principalField.setBounds(160, 20, 180, 25);
        add(principalField);

        JLabel rateLabel = new JLabel("Interest Rate (%):");
        rateLabel.setBounds(30, 60, 120, 25);
        add(rateLabel);

        rateField = new JTextField();
        rateField.setBounds(160, 60, 180, 25);
        add(rateField);

        JLabel yearsLabel = new JLabel("Number of Years:");
        yearsLabel.setBounds(30, 100, 120, 25);
        add(yearsLabel);

        yearsField = new JTextField();
        yearsField.setBounds(160, 100, 180, 25);
        add(yearsField);

        JLabel resultLabel = new JLabel("Final Balance:");
        resultLabel.setBounds(30, 140, 120, 25);
        add(resultLabel);

        resultField = new JTextField();
        resultField.setBounds(160, 140, 180, 25);
        resultField.setEditable(false);
        add(resultField);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBounds(80, 190, 100, 30);
        add(calculateButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(200, 190, 100, 30);
        add(exitButton);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double principal = Double.parseDouble(principalField.getText());
                    double rate = Double.parseDouble(rateField.getText()) / 100;
                    int years = Integer.parseInt(yearsField.getText());

                    double result = calculateRecursiveInterest(principal, rate, years);
                    resultField.setText(String.format("%.2f", result));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Please enter valid numbers.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Closes the window
                HomePage HomePage = new HomePage(user,allUsers);
                HomePage.setVisible(true);
            }
        });

        setVisible(true);
    }

    private double calculateRecursiveInterest(double principal, double rate, int years) {
        if (years == 0) {
            return principal;
        } else {
            return calculateRecursiveInterest(principal * (1 + rate), rate, years - 1);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterestCheckerNullLayout(user, allUsers));
    }
}
