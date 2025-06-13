import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import org.json.*;
import java.util.ArrayList;

public class StockPortfolioTracker extends JFrame {
    private JTextField txtSymbol, txtShares, txtPurchasePrice;
    private JLabel lblProfit;
    private static user user;
    private static ArrayList<user> allUsers;

    public StockPortfolioTracker(user user, ArrayList<user> allUsers) {
        this.user = user;
        this.allUsers = allUsers;
        setTitle("Stock Portfolio Tracker");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        setLayout(null);

        JLabel lblSymbol = new JLabel("Stock Symbol:");
        lblSymbol.setBounds(30, 30, 100, 25);
        add(lblSymbol);

        txtSymbol = new JTextField();
        txtSymbol.setBounds(150, 30, 150, 25);
        add(txtSymbol);

        JLabel lblShares = new JLabel("Shares Purchased:");
        lblShares.setBounds(30, 70, 120, 25);
        add(lblShares);

        txtShares = new JTextField();
        txtShares.setBounds(150, 70, 150, 25);
        add(txtShares);

        JLabel lblPurchasePrice = new JLabel("Purchase Price:");
        lblPurchasePrice.setBounds(30, 110, 120, 25);
        add(lblPurchasePrice);

        txtPurchasePrice = new JTextField();
        txtPurchasePrice.setBounds(150, 110, 150, 25);
        add(txtPurchasePrice);

        lblProfit = new JLabel("Profit: $0.00");
        lblProfit.setBounds(30, 150, 300, 25);
        add(lblProfit);

        JButton btnCalculate = new JButton("Calculate Profit");
        btnCalculate.setBounds(150, 190, 150, 30);
        add(btnCalculate);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(30, 230, 80, 30);
        add(btnBack);

        btnCalculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String symbol = txtSymbol.getText().toUpperCase();
                    int shares = Integer.parseInt(txtShares.getText());
                    double purchasePrice = Double.parseDouble(txtPurchasePrice.getText());

                    double currentPrice = fetchCurrentPrice(symbol);
                    if (currentPrice != -1) {
                        double totalPurchase = purchasePrice * shares;
                        double totalCurrentValue = currentPrice * shares;
                        double profit = totalCurrentValue - totalPurchase;
                        lblProfit.setText(String.format("Profit: $%.2f", profit));
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to fetch current price.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new HomePage(user, allUsers);
            }
        });
    }

    private double fetchCurrentPrice(String symbol) {
        String apiKey = "YOUR_API_KEY"; 
        try {
            String urlString = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + symbol + "&apikey=" + apiKey;
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject json = new JSONObject(response.toString());
            JSONObject timeSeries = json.getJSONObject("Time Series (Daily)");
            String latestDate = timeSeries.keys().next();
            JSONObject latestData = timeSeries.getJSONObject(latestDate);
            return latestData.getDouble("4. close");
        } catch (Exception e) {
            return -1;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StockPortfolioTracker(user, allUsers));
    }
}
