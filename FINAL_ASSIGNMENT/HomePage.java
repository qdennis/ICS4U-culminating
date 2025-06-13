// ... (your imports remain unchanged)
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePage extends javax.swing.JFrame {

    private static user user;
    private static ArrayList<user> allUsers;
    private String selected;

    public HomePage(user user, ArrayList<user> allUsers) {
        this.user = user;
        this.allUsers = allUsers;

        this.setVisible(true);
        initComponents();
    }

    public HomePage(ArrayList<String[]> accountInfo, int index){
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        lblTBS = new javax.swing.JLabel();
        lblWelcome = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        lblbalnc = new javax.swing.JLabel();
        lblMoney = new javax.swing.JLabel();
        btnsendmoney = new javax.swing.JButton();
        btnlivestock = new javax.swing.JButton();
        btnhelp = new javax.swing.JButton();
        btnsignout = new javax.swing.JButton();
        btnaccount = new javax.swing.JButton();
        btnmovemoney = new javax.swing.JButton();
        btninterest = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTBS.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); 
        lblTBS.setText("TBS");

        lblWelcome.setText("Welcome," + user.getName());

        lblID.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); 
        lblID.setText("Account #ID: " + user.getCardNumber());

        lblbalnc.setText("Balance:");
        String selectedAccount = user.getType();
        String targetAccountId = "";
        c checkingAccount = null;
        s savingsAccount = null;
        List<Account> accounts = user.getAccount();

        if (selectedAccount.equals("Checking")) {
            targetAccountId = "Checking";
            for (Account acc : accounts) {
                if (acc.getAccountType().equals(targetAccountId)) {
                    checkingAccount = (c) acc;
                    lblMoney.setText(String.valueOf("$" + checkingAccount.getBalance() + "0"));
                }
            }
        } else if (selectedAccount.equals("Savings")) {
            targetAccountId = "Savings";
            for (Account acc : accounts) {
                if (acc.getAccountType().equals(targetAccountId)) {
                    savingsAccount = (s) acc;
                    lblMoney.setText(String.valueOf("$" + savingsAccount.getBalance() + "0"));
                }
            }
        }

        btnsendmoney.setText("Send Money");
        btnsendmoney.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsendmoneyActionPerformed(evt);
                String selectedAccount = "Checking";
                new sendMoneyFrame(user, selectedAccount, allUsers);
            }
        });

        btnlivestock.setText("Live Stocks");
        btnlivestock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlivestockActionPerformed(evt);
                new StockPortfolioTracker(user, allUsers);
                dispose();
            }
        });

        btnhelp.setText("Help");
        btnhelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhelpActionPerformed(evt);
                new FAQ(user, allUsers);
                dispose();
            }
        });

        btnsignout.setText("Sign Out");
        btnsignout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsignoutActionPerformed(evt);
                dispose();
                new SignIn().setVisible(true);
            }
        });

        btnaccount.setText("Accounts");
        btnaccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaccountActionPerformed(evt);
                dispose();
                new AccountSelectorFrame(user, allUsers);
            }
        });

        btnmovemoney.setText("Deposit");
        btnmovemoney.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmovemoneyActionPerformed(evt);
                dispose();
                new moveMoneyFrame(user, allUsers);
            }
        });

        btninterest.setText("Interest");
        btninterest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btninterestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblbalnc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMoney)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnmovemoney))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTBS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblWelcome))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnlivestock)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnaccount)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btninterest)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnhelp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnsignout))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnsendmoney)))
                .addContainerGap())
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTBS)
                    .addComponent(lblWelcome))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblID)
                    .addComponent(btnsendmoney))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblbalnc)
                    .addComponent(lblMoney)
                    .addComponent(btnmovemoney))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnlivestock)
                    .addComponent(btnsignout)
                    .addComponent(btnhelp)
                    .addComponent(btnaccount)
                    .addComponent(btninterest))
                .addContainerGap())
        );

        pack();
    }

    private void btnsendmoneyActionPerformed(java.awt.event.ActionEvent evt) { }
    private void btnmovemoneyActionPerformed(java.awt.event.ActionEvent evt) { }
    private void btnlivestockActionPerformed(java.awt.event.ActionEvent evt) { }
    private void btnaccountActionPerformed(java.awt.event.ActionEvent evt) { }
    private void btnhelpActionPerformed(java.awt.event.ActionEvent evt) { }
    private void btnsignoutActionPerformed(java.awt.event.ActionEvent evt) { }

    private void btninterestActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        new InterestCheckerNullLayout(user, allUsers).setVisible(true);
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePage(user, allUsers).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnaccount;
    private javax.swing.JButton btnhelp;
    private javax.swing.JButton btninterest;
    private javax.swing.JButton btnlivestock;
    private javax.swing.JButton btnmovemoney;
    private javax.swing.JButton btnsendmoney;
    private javax.swing.JButton btnsignout;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JLabel lblMoney;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblTBS;
    private javax.swing.JLabel lblbalnc;
    // End of variables declaration                   
}
