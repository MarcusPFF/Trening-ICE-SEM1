package JFrame;

import app.Account;
import app.WorkoutPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;

public class JFrameGUI extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private Account acc;
    private ArrayList<String> loadEarlierWorkouts = new ArrayList<>();
    private DefaultListModel<String> listModel = new DefaultListModel<>();

    // Fields for Account settings (height, weight)
    private JTextField heightField;
    private JTextField weightField;

    // Reference to homepage label to display user's email
    private JLabel homePage;

    public void launchGUI() {
        displayLoginGUI();
    }

    String url = "jdbc:sqlite:src/data/Database.db";

    public void displayMenuGUI() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel menuPanel = new JPanel(new BorderLayout());
        menuPanel.setBackground(new Color(88, 88, 88));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(88, 88, 88));

        /* Side-menu buttons */
        JButton button1 = createModernButton("Home");
        JButton button2 = createModernButton("Workout");
        JButton button3 = createModernButton("Shop");
        JButton button4 = createModernButton("Account");

        /* Set button size */
        button1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        button2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        button3.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        button4.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));

        /* Add buttons to the panel */
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        CardLayout layout = new CardLayout();
        JPanel sidePanel = new JPanel(layout);

        /* HomePage menu (MENU 1) */
        JPanel menu1 = new JPanel();
        menu1.setLayout(new BoxLayout(menu1, BoxLayout.Y_AXIS));
        menu1.setBackground(new Color(29, 49, 53));
        menu1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        homePage = new JLabel("Welcome back: ", JLabel.CENTER);
        homePage.setFont(new Font("Roboto", Font.BOLD, 24));
        homePage.setForeground(new Color(255, 255, 255));
        homePage.setAlignmentX(Component.CENTER_ALIGNMENT);
        menu1.add(homePage);

        JLabel workoutHistory = new JLabel("Workout history:", JLabel.CENTER);
        workoutHistory.setFont(new Font("Roboto", Font.PLAIN, 18));
        workoutHistory.setForeground(new Color(255, 255, 255));
        workoutHistory.setAlignmentX(Component.CENTER_ALIGNMENT);
        menu1.add(workoutHistory);

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

        JButton pushButton = createModernButton("Push");
        JButton pullButton = createModernButton("Pull");
        JButton legsButton = createModernButton("Legs");

        buttonToPanel.add(pushButton);
        buttonToPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonToPanel.add(pullButton);
        buttonToPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonToPanel.add(legsButton);

        buttonToPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        menu2.add(buttonToPanel);

        /* Button functionality for workouts */
        pushButton.addActionListener(e -> displayWorkoutForm(sidePanel, layout, "Push Workout", 1));
        pullButton.addActionListener(e -> displayWorkoutForm(sidePanel, layout, "Pull Workout", 2));
        legsButton.addActionListener(e -> displayWorkoutForm(sidePanel, layout, "Legs Workout", 3));

        /* Order your Tren now (MENU 3) */
        JPanel menu3 = createModernPanel("Order your Tren now!");
        JButton buyTrenButton = createModernButton("Shop now!");
        menu3.add(buyTrenButton, BorderLayout.SOUTH);
        menu3.setBackground(new Color(29, 49, 53));

        buyTrenButton.addActionListener(e -> {
            // In a real scenario, call OrderTrenPage.orderTren() before or after.
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
        heightField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = row++;
        formPanel.add(heightField, gbc);

        JLabel weightLabel = new JLabel("Weight in KG:");
        gbc.gridx = 0;
        gbc.gridy = row++;
        formPanel.add(weightLabel, gbc);
        weightField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = row++;
        formPanel.add(weightField, gbc);

        JLabel saveLabel = new JLabel("Click Save to save your data:");
        gbc.gridx = 0;
        gbc.gridy = row++;
        formPanel.add(saveLabel, gbc);

        JButton saveKnap = createModernButton("Save");
        gbc.gridx = 0;
        gbc.gridy = row++;
        formPanel.add(saveKnap, gbc);
        menu4.add(formPanel, BorderLayout.CENTER);

        // Save account changes
        saveKnap.addActionListener(e -> {
            if (acc != null) {
                try {
                    float h = Float.parseFloat(heightField.getText());
                    float w = Float.parseFloat(weightField.getText());
                    acc.setHeight(h);
                    acc.setWeight(w);
                    JOptionPane.showMessageDialog(this, "Account data saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input for height or weight.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No account logged in.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton logoutButton = createModernButton("Logout");
        menu4.add(logoutButton, BorderLayout.SOUTH);
        logoutButton.addActionListener(e -> {
            acc = null;
            cardLayout.show(mainPanel, "Login");
        });

        /* Add panels to sidePanel */
        sidePanel.add(menu1, "Menu");
        sidePanel.add(menu2, "Workout");
        sidePanel.add(menu3, "Shop");
        sidePanel.add(menu4, "Account");

        /* Button actions to switch panels */
        button1.addActionListener(e -> {
            // Update welcome text if acc is not null
            if (acc != null) {
                homePage.setText("Welcome back: " + acc.getEmail());
            } else {
                homePage.setText("Welcome back:");
            }
            layout.show(sidePanel, "Menu");
        });
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

        /* Setup window */
        setTitle("Trening - App for Muscle Growth");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setResizable(false);

        /* Logo */
        ImageIcon image = new ImageIcon("src/doc/docs/treninglogo.png");
        setIconImage(image.getImage());

        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(new Color(4, 45, 50));

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

        /* Email field */
        gbc.gridy = 1;
        JTextField emailField = new JTextField(20);
        emailField.setText("Email");
        loginPanel.add(emailField, gbc);

        /* Password field */
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
            // Simulate login
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            // For demo: create a dummy account. In a real scenario, validate from DB.
            acc = new Account(1, email, password, 70.0f, 175.0f);
            displayMenuGUI();
            homePage.setText("Welcome back: " + acc.getEmail());
            cardLayout.show(mainPanel, "Menu");
        });

        createAccountButton.addActionListener(e -> {
            // Simulate account creation and then login
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            // For demo: create a new account
            acc = new Account(2, email, password, 65.0f, 170.0f);
            displayMenuGUI();
            homePage.setText("Welcome back: " + acc.getEmail());
            cardLayout.show(mainPanel, "Menu");
        });

        setVisible(true);
    }

    public void addWorkoutToHistory(String workout) {
        loadEarlierWorkouts.add(workout);
        listModel.addElement(workout);
    }

    private void displayWorkoutForm(JPanel rightPanel, CardLayout layout, String workoutType, int programID) {
        // Check if user is logged in
        if (acc == null) {
            JOptionPane.showMessageDialog(this, "Please log in first!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create a panel to hold the workout fields
        JPanel workoutFormPanel = new JPanel();
        workoutFormPanel.setLayout(new BoxLayout(workoutFormPanel, BoxLayout.Y_AXIS));
        workoutFormPanel.setBackground(new Color(255, 255, 255));
        workoutFormPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Single list to hold all the JTextField objects (for reps, weight, note)
        ArrayList<JTextField> fields = new ArrayList<>();

        // Dynamically create fields for each set and exercise
        for (int exercise = 1; exercise <= 9; exercise++) {
            JLabel exerciseLabel = new JLabel("Exercise " + exercise + ":");
            exerciseLabel.setFont(new Font("Roboto", Font.BOLD, 16));
            workoutFormPanel.add(exerciseLabel);

            for (int set = 1; set <= 3; set++) {
                JPanel setPanel = new JPanel();
                setPanel.setLayout(new BoxLayout(setPanel, BoxLayout.X_AXIS));
                setPanel.setBackground(new Color(255, 255, 255));

                JLabel setLabel = new JLabel("Set " + set + ": ");
                setLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
                setPanel.add(setLabel);

                // Reps field
                JTextField repsField = new JTextField(5);
                repsField.setMaximumSize(new Dimension(60, 25));
                fields.add(repsField);
                setPanel.add(repsField);

                JLabel weightLabel = new JLabel("  Weight: ");
                weightLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
                setPanel.add(weightLabel);

                // Weight field
                JTextField weightField = new JTextField(5);
                weightField.setMaximumSize(new Dimension(60, 25));
                fields.add(weightField);
                setPanel.add(weightField);

                JLabel noteLabel = new JLabel("  Note: ");
                noteLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
                setPanel.add(noteLabel);

                // Note field
                JTextField noteField = new JTextField(10);
                noteField.setMaximumSize(new Dimension(120, 25));
                fields.add(noteField);
                setPanel.add(noteField);

                workoutFormPanel.add(setPanel);
            }

            // Add spacing between exercises
            workoutFormPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        }

        // Add the save button in the bottom right
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(255, 255, 255));
        JButton saveButton = createModernButton("Save");
        buttonPanel.add(saveButton);

        // ActionListener to save the workout data
        saveButton.addActionListener(e -> {
            saveWorkoutDataToDatabase(fields, programID);
            addWorkoutToHistory(workoutType + " logged on " + LocalDate.now());
        });

        workoutFormPanel.add(buttonPanel);

        // Scroll pane to handle large content
        JScrollPane scrollPane = new JScrollPane(workoutFormPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Add workout form to the right panel and show it
        rightPanel.add(scrollPane, workoutType);
        layout.show(rightPanel, workoutType);
    }

    private void saveWorkoutDataToDatabase(ArrayList<JTextField> fields, int programID) {
        if (acc == null) {
            JOptionPane.showMessageDialog(this, "No user logged in!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int accountID = acc.getAccountID();
        String setDate = LocalDate.now().toString(); // Current date as string
        int workoutID = 1; // A simple static ID, ideally generated dynamically

        WorkoutPage workoutPage = new WorkoutPage(accountID);
        ArrayList<WorkoutPage.UserInput> userInputs = new ArrayList<>();

        // Process the fields for workout data in groups of three: (reps, weight, note)
        for (int i = 0; i < fields.size(); i += 3) {
            int exerciseID = (i / 3) + 1; // Calculate exercise ID based on index
            try {
                int reps = Integer.parseInt(fields.get(i).getText());
                float weight = Float.parseFloat(fields.get(i + 1).getText());
                String note = fields.get(i + 2).getText();

                userInputs.add(new WorkoutPage.UserInput(exerciseID, reps, weight, note));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: Please ensure all fields are filled correctly.");
                JOptionPane.showMessageDialog(this, "Invalid input in the workout form.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        workoutPage.logWorkoutForProgram(programID, userInputs, workoutID, setDate);
        JOptionPane.showMessageDialog(null, "Workout saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
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
}
