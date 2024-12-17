package JFrame;

import app.Account;
import util.FileIO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class JFrameGUI extends JFrame {
    protected String currentWeightString;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private Account acc;
    private FileIO io;
    private List<String> loadEarlierWorkouts;
    private DefaultListModel<String> listModel;
    private JLabel messageLabel;
    private List<Account> accounts;
    private Account currentAccount;

    public JFrameGUI() {
        this.cardLayout = new CardLayout();
        this.mainPanel = new JPanel(cardLayout);
        this.loadEarlierWorkouts = new ArrayList<>();
        this.listModel = new DefaultListModel<>();
        this.messageLabel = new JLabel();
        this.acc = new Account();
        this.io = new FileIO();
        this.accounts = io.getAccounts();
        this.currentAccount = new Account(acc.getCurrentEmail(), acc.getCurrentWeight(), acc.getCurrentHeight());

    }

    public void launchGUI() {
        displayLoginGUI();
    }

    public void displayMenuGUI() {
        /* Dette bruges til at kunne vælge præcis hvor en knap skal være på GUI */
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel menuPanel = new JPanel(new BorderLayout());
        menuPanel.setBackground(new Color(88, 88, 88));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(88, 88, 88));

        /* side-menu knapperne */
        JButton button1 = createModernButton("Home");
        JButton button2 = createModernButton("Workout");
        JButton button3 = createModernButton("Shop");
        JButton button4 = createModernButton("Account");

        /* sætter størrelsen på knapperne */
        button1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        button2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        button3.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        button4.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));

        /* tilføjer knapperne til objektet "panel" */
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        CardLayout layout = new CardLayout();
        JPanel sidePanel = new JPanel(layout);

        /* Homepage menuen (MENU 1) */
        JPanel menu1 = new JPanel();
        menu1.setLayout(new BoxLayout(menu1, BoxLayout.Y_AXIS));
        menu1.setBackground(new Color(29, 49, 53));
        menu1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        /* Indsæt acc.getEmail() */
        JLabel homePage = new JLabel("Welcome back ", JLabel.CENTER);
        homePage.setFont(new Font("Roboto", Font.BOLD, 24));
        homePage.setForeground(new Color(255, 255, 255));
        homePage.setAlignmentX(Component.CENTER_ALIGNMENT);
        menu1.add(homePage);

        /* Seneste workouts */
        JLabel workoutHistory = new JLabel("Workout history:", JLabel.CENTER);
        workoutHistory.setFont(new Font("Roboto", Font.PLAIN, 18));
        workoutHistory.setForeground(new Color(255, 255, 255));
        workoutHistory.setAlignmentX(Component.CENTER_ALIGNMENT);
        menu1.add(workoutHistory);

        /* Brug af JList så man kan rulle på GUI */
        JList<String> historyList = new JList<>(listModel);
        historyList.setFont(new Font("Roboto", Font.PLAIN, 14));
        historyList.setForeground(new Color(60, 60, 60));
        historyList.setBackground(new Color(240, 240, 240));
        JScrollPane scrollPane = new JScrollPane(historyList);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setPreferredSize(new Dimension(400, 150));
        menu1.add(scrollPane);
        menu1.add(Box.createRigidArea(new Dimension(0, 20)));

        /* Start a workout (MENU 2) */
        JPanel menu2 = createModernPanel("Start a workout");
        menu2.setForeground(Color.WHITE);
        menu2.setLayout(new BoxLayout(menu2, BoxLayout.Y_AXIS));
        menu2.setBackground(new Color(29, 49, 53));
        menu2.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        menu2.add(Box.createRigidArea(new Dimension(0, 20)));
        JPanel buttonToPanel = new JPanel();
        buttonToPanel.setLayout(new BoxLayout(buttonToPanel, BoxLayout.X_AXIS));
        buttonToPanel.setBackground(new Color(29, 49, 53));
        /* JButton "Push" "Pull" "Legs" knapperne */
        JButton pushButton = createModernButton("Push");
        JButton pullButton = createModernButton("Pull");
        JButton legsButton = createModernButton("Legs");

        /* Tilføjer push,pull,legs til buttonToPanel med spacing imellem dem */
        buttonToPanel.add(pushButton);
        buttonToPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonToPanel.add(pullButton);
        buttonToPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonToPanel.add(legsButton);

        buttonToPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        menu2.add(buttonToPanel);

        /* Funktion på knapperne */
        pushButton.addActionListener(e -> displayWorkoutForm(sidePanel, layout, "Push Workout"));
        pullButton.addActionListener(e -> displayWorkoutForm(sidePanel, layout, "Pull Workout"));
        legsButton.addActionListener(e -> displayWorkoutForm(sidePanel, layout, "Legs Workout"));

        /* Order your tren now (MENU 3) */
        JPanel menu3 = createModernPanel("Order your Tren now!");
        JButton buyTrenButton = createModernButton("Shop now!");
        menu3.add(buyTrenButton, BorderLayout.SOUTH);
        menu3.setBackground(new Color(29, 49, 53));
        /* Funktion på knappen */
        buyTrenButton.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://www.mega-gear.net/en/12-tren-enant-150.html"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        /* Manage your account (MENU 4) */
        JPanel menu4 = createModernPanel("Manage your account");
        menu4.setBackground(new Color(29, 49, 53));
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        int row = 0;

        JLabel heightLabel = new JLabel("Height:");
        gbc.gridx = 0;
        gbc.gridy = row++;
        formPanel.add(heightLabel, gbc);
        JTextField heightField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = row++;
        formPanel.add(heightField, gbc);

        JLabel weightLabel = new JLabel("Weight in KG:");
        gbc.gridx = 0;
        gbc.gridy = row++;
        formPanel.add(weightLabel, gbc);
        JTextField weightField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = row++;
        formPanel.add(weightField, gbc);

        for (Account acc : accounts) {
            System.out.println(acc.getEmail());
            System.out.println(currentAccount.getCurrentEmail());
            if (acc.getEmail().equals(currentAccount.getCurrentEmail())) {
                heightField.setText(String.valueOf((acc.getHeight())));
                weightField.setText(String.valueOf((acc.getWeight())));
                System.out.println(weightField);
            }
        }

        JLabel saveLabel = new JLabel("Click Save to save your data:");
        gbc.gridx = 0;
        gbc.gridy = row++;
        formPanel.add(saveLabel, gbc);

        JButton saveButton = createModernButton("Save");
        gbc.gridx = 0;
        gbc.gridy = row++;
        formPanel.add(saveButton, gbc);
        menu4.add(formPanel, BorderLayout.CENTER);
        saveButton.addActionListener(e -> {
            float currentHeightString = 0;
            float currentWeightString = 0;
            String heightText = "Indtast venligst en højde mellem 100-220cm.";
            String weightText = "Indtast venligst en vægt mellem 40-300kg";
            io.loadAccountData("src/data/accountsData/Accounts.csv");

            if (Float.parseFloat(heightField.getText().trim()) >= 100.0 && Float.parseFloat(heightField.getText().trim()) <= 220.0) {
                currentHeightString = Float.parseFloat(heightField.getText().trim());
                heightText = acc.validateSetCurrentHeight(currentHeightString);
                currentAccount.validateSetCurrentHeight(currentHeightString);
            }
            if (Float.parseFloat(weightField.getText().trim()) >= 40 && Float.parseFloat(weightField.getText().trim()) <= 300) {
                currentWeightString = Float.parseFloat(weightField.getText().trim());
                weightText = acc.validateSetCurrentWeight(currentWeightString);
                currentAccount.validateSetCurrentWeight(currentWeightString);
            }
            if (Float.parseFloat(weightField.getText().trim()) >= 40 && Float.parseFloat(weightField.getText().trim()) <= 300 && Float.parseFloat(heightField.getText().trim()) >= 100.0 && Float.parseFloat(heightField.getText().trim()) <= 220.0) {

                io.saveAccountData("src/data/accountsData/Accounts.csv", accounts, currentAccount.getCurrentEmail(), currentWeightString, currentHeightString);
            }


            JOptionPane.showMessageDialog(formPanel, heightText + "\n" + weightText);
        });

        JButton logoutButton = createModernButton("Logout");
        menu4.add(logoutButton, BorderLayout.SOUTH);
        /* Funktion på knappen */
        logoutButton.addActionListener(e -> {
            try {
                cardLayout.show(mainPanel, "Login");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        /* Tilføjer knapperne til vores sidePanel */
        sidePanel.add(menu1, "Menu");
        sidePanel.add(menu2, "Workout");
        sidePanel.add(menu3, "Shop");
        sidePanel.add(menu4, "Account");

        /* Knapperne viser de forskellige Menuer */
        button1.addActionListener(e -> layout.show(sidePanel, "Menu"));
        button2.addActionListener(e -> layout.show(sidePanel, "Workout"));
        button3.addActionListener(e -> layout.show(sidePanel, "Shop"));
        button4.addActionListener(e -> layout.show(sidePanel, "Account"));

        menuPanel.add(panel, BorderLayout.WEST);
        menuPanel.add(sidePanel, BorderLayout.CENTER);
        mainPanel.add(menuPanel, "Menu");
    }

    public void displayLoginGUI() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);


        /* Setup på vindue */
        setTitle("Trening - App for Muscle Growth");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setResizable(false);

        /* Logo */
        ImageIcon image = new ImageIcon("src/doc/docs/treninglogo.png");
        setIconImage(image.getImage());

        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(new Color(4, 45, 50));

        /* Igen bruges GBC til placering af knapper på GUI */
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        ImageIcon logo = new ImageIcon("src/doc/docs/treninglogo.png");
        Image resizedImage = logo.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel logoPNG = new JLabel(resizedIcon);
        logoPNG.setHorizontalAlignment(SwingConstants.CENTER);
        loginPanel.add(logoPNG, gbc);

        /* Email tekst felt */
        gbc.gridy = 1;
        JTextField emailField = new JTextField(20);
        emailField.setText("Email");
        loginPanel.add(emailField, gbc);

        /* Password tekst felt */
        gbc.gridy = 2;
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setText("Password");
        loginPanel.add(passwordField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        JButton loginButton = createModernButton("Login");
        loginPanel.add(loginButton, gbc);

        gbc.gridx = 1;
        JButton createAccountButton = createModernButton("Create Account");
        loginPanel.add(createAccountButton, gbc);

        mainPanel.add(loginPanel, "Login");
        add(mainPanel);
        cardLayout.show(mainPanel, "Login");

        loginButton.addActionListener(e -> {
            if (io == null) {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("System error: FileIO not initialized.");
                return;
            }

            io.loadAccountData("src/data/accountsData/Accounts.csv");
            List<Account> accounts = io.getAccounts();
            String email = emailField.getText().trim();
            String password = String.valueOf(passwordField.getPassword());
            boolean validCredentials = false;
            for (Account acc : accounts) {
                if (email.equals(acc.getEmail()) && password.equals(acc.getPassword())) {
                    validCredentials = true;
                    currentAccount.setCurrentEmail(email);
                    break;
                }
            }
            if (validCredentials) {
                displayMenuGUI();
                cardLayout.show(mainPanel, "Menu");
            } else {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("Invalid email or password");
            }
        });


        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (io == null) {
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("System error: FileIO not initialized.");
                    return;
                }

                // Load account data (ensure that this method actually returns or sets the accounts list)
                io.loadAccountData("src/data/accountsData/Accounts.csv");
                // Retrieve the accounts from io (assuming there's a getter)
                String email = emailField.getText().trim();
                String password = String.valueOf(passwordField.getPassword());

                // Validate email and password
                if (!email.isEmpty() && !password.isEmpty()
                        && email.contains("@") && email.contains(".")
                        && password.length() >= 8) {

                    // Check if account with this email already exists
                    int accountWithThisEmail = 0;
                    for (Account acc : accounts) {
                        if (email.equals(acc.getEmail())) {
                            accountWithThisEmail = 1;
                        }
                    }
                    if (accountWithThisEmail == 0) {
                        // Create a new account and set its properties
                        accounts.add(new Account(email, password, 0, 0));
                        acc.setEmail(email);
                        acc.setWeight(0);
                        acc.setHeight(0);
                        io.saveAccountData("src/data/accountsData/Accounts.csv", accounts, acc.getEmail(), acc.getWeight(), acc.getHeight());
                        messageLabel.setForeground(Color.BLACK);
                        messageLabel.setText("Account created successfully!");

                        // Create directory if it doesn't exist
                        String folderName = email;
                        File folder = new File("src/data/accountsData/" + folderName);
                        if (!folder.exists()) {
                            if (folder.mkdir()) {
                                System.out.println("Folder created: " + folderName);
                            } else {
                                System.out.println("Could not create folder: " + folderName);
                            }
                        } else {
                            System.out.println("Folder already exists.");
                        }
                        displayMenuGUI();
                        cardLayout.show(mainPanel, "Menu");
                    } else {
                        messageLabel.setForeground(Color.RED);
                        messageLabel.setText("Email is already in use");
                    }
                } else {
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("Invalid email or password (password must be min 8 characters).");
                }
            }
        });


        setVisible(true);


    }

    public void addWorkoutToHistory(String workout) {
        loadEarlierWorkouts.add(workout);
        listModel.addElement(workout);
    }

    private void displayWorkoutForm(JPanel rightPanel, CardLayout layout, String workoutType) {
        JPanel workoutFormPanel = new JPanel();
        workoutFormPanel.setLayout(new BoxLayout(workoutFormPanel, BoxLayout.Y_AXIS));
        workoutFormPanel.setBackground(new Color(255, 255, 255));
        workoutFormPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        workoutFormPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        /* Felter man skriver sine gentagelser i. TILFØJ SET 2 og 3 */
        JLabel set1 = new JLabel("Set 1" + ":");
        set1.setFont(new Font("Roboto", Font.PLAIN, 16));
        set1.setAlignmentX(Component.CENTER_ALIGNMENT);
        workoutFormPanel.add(set1);
        JTextField textField = new JTextField(20);
        textField.setMaximumSize(new Dimension(300, 30));
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);
        workoutFormPanel.add(textField);
        workoutFormPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        /* Tilbage knap */
        JButton backButton = createModernButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> layout.show(rightPanel, "Workout"));
        workoutFormPanel.add(backButton);

        rightPanel.add(workoutFormPanel, workoutType);
        layout.show(rightPanel, workoutType);
    }

    private JButton createModernButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Roboto", Font.PLAIN, 16));
        button.setBackground(new Color(236, 91, 41));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(new Color(236, 91, 41), 2, true));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setBorder(BorderFactory.createCompoundBorder(button.getBorder(), BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(new Color(255, 100, 50));
            }

            public void mouseExited(MouseEvent evt) {
                button.setBackground(new Color(236, 91, 41));
            }
        });

        return button;
    }

    private JPanel createModernPanel(String text) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(255, 255, 255));

        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setFont(new Font("Roboto", Font.BOLD, 24));
        label.setForeground(new Color(255, 255, 255));
        panel.add(label, BorderLayout.NORTH);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        return panel;
    }

    public String getCurrentAccountWeight() {
        return currentWeightString;
    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }


    @Override
    public String toString() {
        return "JFrameGUI{" +
                "acc=" + acc +
                '}';
    }
}
