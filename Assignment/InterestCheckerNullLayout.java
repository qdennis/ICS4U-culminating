import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterestCheckerNullLayout extends JFrame {

    private JTextField principalField, rateField, yearsField, resultField;

    public InterestCheckerNullLayout() {
        setTitle("Recursive Interest Calculator (Null Layout)");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);  // <-- Using null layout

        // Principal
        JLabel principalLabel = new JLabel("Principal Amount:");
        principalLabel.setBounds(30, 20, 120, 25);
        add(principalLabel);

        principalField = new JTextField();
        principalField.setBounds(160, 20, 180, 25);
        add(principalField);

        // Interest Rate
        JLabel rateLabel = new JLabel("Interest Rate (%):");
        rateLabel.setBounds(30, 60, 120, 25);
        add(rateLabel);

        rateField = new JTextField();
        rateField.setBounds(160, 60, 180, 25);
        add(rateField);

        // Years
        JLabel yearsLabel = new JLabel("Number of Years:");
        yearsLabel.setBounds(30, 100, 120, 25);
        add(yearsLabel);

        yearsField = new JTextField();
        yearsField.setBounds(160, 100, 180, 25);
        add(yearsField);

        // Final Balance
        JLabel resultLabel = new JLabel("Final Balance:");
        resultLabel.setBounds(30, 140, 120, 25);
        add(resultLabel);

        resultField = new JTextField();
        resultField.setBounds(160, 140, 180, 25);
        resultField.setEditable(false);
        add(resultField);

        // Calculate Button
        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBounds(130, 190, 120, 30);
        add(calculateButton);

        // Action listener for button
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

        setVisible(true);
    }

    // Recursive method to compute compound interest
    private double calculateRecursiveInterest(double principal, double rate, int years) {
        if (years == 0) {
            return principal;
        } else {
            return calculateRecursiveInterest(principal * (1 + rate), rate, years - 1);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterestCheckerNullLayout());
    }
}
