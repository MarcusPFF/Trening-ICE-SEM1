package JFrame;

import app.Account;
import app.Exercises;
import app.Sets;
import util.FileIO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JFrameGUI extends JFrame {
    protected String currentWeightString;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private Account acc;
    private FileIO io;
    public List<String> loadEarlierWorkouts;
    public DefaultListModel<String> listModel;
    private JLabel messageLabel;
    private List<Account> accounts;
    private Account currentAccount;
    private String workoutType;
    private String email;
    private String password;
    private List<Exercises> earlierWorkouts;
    private ArrayList<Sets> ghostTextSets;
    private String exerciseName;

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

        button1.addActionListener(e -> {
            earlierWorkouts =io.loadEarlierWorkouts("src/data/accountsData/" + email);
        });
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
        earlierWorkouts = io.loadEarlierWorkouts("src/data/accountsData/" + email);
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
       //JList formatedEarlieworkoutlist = JList;
        /* JList for workout historik */
        DefaultListModel<String> workoutListModel = new DefaultListModel<>();

        if (earlierWorkouts != null) {
            earlierWorkouts = io.loadEarlierWorkouts("src/data/accountsData/" + email);
            for (Exercises exercise : earlierWorkouts) {
                workoutListModel.addElement(exercise.getExerciseName() + " - " + exercise.getDate());
            }
        }
        // Tegner workoutlisten
        JList<String> workoutList = new JList<>(workoutListModel);
        workoutList.setFont(new Font("Roboto", Font.PLAIN, 14));
        workoutList.setForeground(new Color(60, 60, 60));
        workoutList.setBackground(new Color(240, 240, 240));

        JScrollPane scrollPane = new JScrollPane(workoutList);
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
        pushButton.addActionListener(e -> {
            displayWorkoutForm(sidePanel, layout, "Push Workout", 1);
            workoutType = pushButton.getText();
        });


        pullButton.addActionListener(e -> {
            displayWorkoutForm(sidePanel, layout, "Pull Workout", 2);
            workoutType = pullButton.getText();
        });

        legsButton.addActionListener(e -> {
            displayWorkoutForm(sidePanel, layout, "Legs Workout", 3);
            workoutType = legsButton.getText();
        });

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

        // Label til højde på bruger
        JLabel heightLabel = new JLabel("Height:");
        gbc.gridx = 0;
        gbc.gridy = row++;
        formPanel.add(heightLabel, gbc);
        JTextField heightField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = row++;
        formPanel.add(heightField, gbc);

        // Label til vægt på bruger
        JLabel weightLabel = new JLabel("Weight in KG:");
        gbc.gridx = 0;
        gbc.gridy = row++;
        formPanel.add(weightLabel, gbc);
        JTextField weightField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = row++;
        formPanel.add(weightField, gbc);

        // Gennemgår listen af accounts for at finde den aktuelle bruger.
        // Når der findes et match, sættes værdierne for højde og vægt i tekstfelterne (heightField og weightField).
        // Dette sikrer, at den nuværende brugers data vises korrekt i GUI.
        for (Account acc : accounts) {
            if (acc.getEmail().equals(currentAccount.getCurrentEmail())) {
                heightField.setText(String.valueOf((currentAccount.getCurrentHeight())));
                weightField.setText(String.valueOf((currentAccount.getCurrentWeight())));
            }
        }

        // Titel til knap der gemmer data
        JLabel saveLabel = new JLabel("Click Save to save your data:");
        gbc.gridx = 0;
        gbc.gridy = row++;
        formPanel.add(saveLabel, gbc);

        // Selve save-knappen
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
            accounts = io.loadAccountData("src/data/accountsData/Accounts.csv");

            // Ifstatements der sørger for at data giver mening
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

                io.saveAccountData("src/data/accountsData/Accounts.csv", accounts, email, currentWeightString, currentHeightString);
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
        loginPanel.setBackground(new Color(41, 52, 56));

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

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginPanel.add(messageLabel, gbc);

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
            email = emailField.getText().trim();
            password = String.valueOf(passwordField.getPassword());
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

        // knap til at lave account
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (io == null) {
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("System error: FileIO not initialized.");
                    return;
                }

                // Loader account-data
                io.loadAccountData("src/data/accountsData/Accounts.csv");


                email = emailField.getText().trim();
                password = String.valueOf(passwordField.getPassword());

                // Validerer email og password
                if (!email.isEmpty() && !password.isEmpty() && email.contains("@") && email.contains(".") && password.length() >= 8) {

                    // Checker om en account med denne email already eksisterer
                    int accountWithThisEmail = 0;
                    for (Account acc : accounts) {
                        if (email.equals(acc.getEmail())) {
                            accountWithThisEmail = 1;
                        }
                    }
                    if (accountWithThisEmail == 0) {
                        // Laver en ny account
                        accounts.add(new Account(email, password, 0, 0));
                        acc.setEmail(email);
                        acc.setWeight(0);
                        acc.setHeight(0);
                        currentAccount.setCurrentEmail(email);
                        currentAccount.setCurrentWeight(0);
                        currentAccount.setCurrentHeight(0);
                        io.saveAccountData("src/data/accountsData/Accounts.csv", accounts, acc.getEmail(), acc.getWeight(), acc.getHeight());
                        messageLabel.setForeground(Color.BLACK);
                        messageLabel.setText("Account created successfully!");

                        // Laver et nyt directory tilaccounten
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

    private void displayWorkoutForm(JPanel rightPanel, CardLayout layout, String workoutType, int programID) {

        // Laver et panel til at holde workout-felter
        JPanel workoutFormPanel = new JPanel();
        workoutFormPanel.setLayout(new BoxLayout(workoutFormPanel, BoxLayout.Y_AXIS));
        workoutFormPanel.setBackground(new Color(255, 255, 255));
        workoutFormPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Liste til at holde alle JTextField objekter (for reps, weight, note)
        ArrayList<JTextField> fields = new ArrayList<>();
        ghostTextSets = io.loadLastExercisesData( "src/data/accountsData/" + email + "/" + workoutType, workoutType);
        System.out.println(ghostTextSets.size());
        int x = 3;
        for (Sets set : ghostTextSets) {
            exerciseName = set.getExerciseName();
            if (x == 3) {
                exerciseName = getExerciseName(exerciseName);


                JLabel exerciseLabel = new JLabel( exerciseName + ":");
                exerciseLabel.setFont(new Font("Roboto", Font.BOLD, 16));
                workoutFormPanel.add(Box.createRigidArea(new Dimension(0, 20)));
                workoutFormPanel.add(exerciseLabel);
                x = 0;
            }
            x++;

            JPanel setPanel = new JPanel();
            setPanel.setLayout(new BoxLayout(setPanel, BoxLayout.X_AXIS));
            setPanel.setBackground(new Color(255, 255, 255));

            //String setNumber = String.valueOf(set.getSetNumber());
            //System.out.println(setNumber);
            JLabel setLabel = new JLabel(  "Set " + x + ")   Rep:");
            setLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
            setPanel.add(setLabel);

            JTextField repsField = new JTextField(5);

            repsField.setMaximumSize(new Dimension(60, 25));
            fields.add(repsField);
            setPanel.add(repsField);
            repsField.setText(String.valueOf(set.getReps()));

            JLabel weightLabel = new JLabel("  Weight: ");
            weightLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
            setPanel.add(weightLabel);

            JTextField weightField = new JTextField(5);
            weightField.setMaximumSize(new Dimension(60, 25));
            fields.add(weightField);
            setPanel.add(weightField);
            weightField.setText(String.valueOf(set.getWeight()));

            JLabel noteLabel = new JLabel("  Note: ");
            noteLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
            setPanel.add(noteLabel);

            // Note felt
            JTextField noteField = new JTextField(10);
            noteField.setMaximumSize(new Dimension(120, 25));
            fields.add(noteField);
            setPanel.add(noteField);
            noteField.setText(set.getNote());



            workoutFormPanel.add(setPanel);
        }

        // Add the save button in the bottom right
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(255, 255, 255));
        JButton saveWorkoutButton = createModernButton("Save");
        buttonPanel.add(saveWorkoutButton);

        saveWorkoutButton.addActionListener(e -> {
            // Liste til at holde sets
            List<Sets> exercisesData = new ArrayList<>();

            int fieldIndex = 0;

            // Brug eksisterende ghostTextSets til at læse felterne
            for (Sets set : ghostTextSets) {
                String exerciseName = set.getExerciseName(); // Få øvelsesnavnet direkte fra objektet
                int setNumber = set.getSetNumber();

                // Læs tekstfelter fra den gemte rækkefølge i fields-listen
                String repsStr = fields.get(fieldIndex++).getText().trim();
                String weightStr = fields.get(fieldIndex++).getText().trim();
                String note = fields.get(fieldIndex++).getText().trim();

                int reps = 0;
                float weight = 0.0f;

                try {
                    reps = Integer.parseInt(repsStr);
                } catch (NumberFormatException exInt) {
                    System.out.println("Fejl ved parsing af reps: " + repsStr);
                }

                try {
                    weight = Float.parseFloat(weightStr);
                } catch (NumberFormatException exFloat) {
                    System.out.println("Fejl ved parsing af vægt: " + weightStr);
                }

                // Opret nyt Sets-objekt med data
                Sets updatedSet = new Sets(exerciseName, setNumber, reps, weight, note);
                exercisesData.add(updatedSet);
                messageLabel.setText("Workout saved!");
                displayMenuGUI();
                cardLayout.show(mainPanel, "Menu");
                cardLayout.show(mainPanel, "menu");
            }

            String date = String.valueOf(LocalDate.now());
            earlierWorkouts.add(new Exercises(workoutType, date));
            io.saveExerciseData("src/data/accountsData/" + email + "/", exercisesData, workoutType);
            io.saveEarlierWorkouts( "src/data/accountsData/" + email, earlierWorkouts);


        });

        workoutFormPanel.add(buttonPanel);

        JScrollPane scrollPane = new JScrollPane(workoutFormPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        rightPanel.add(scrollPane, workoutType);
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

    public Account getCurrentAccount() {
        return currentAccount;
    }

    @Override
    public String toString() {
        return "JFrameGUI{" + "acc=" + acc + '}';
    }

    public String getExerciseName(String exerciseName){
        switch (exerciseName) {
            case "Exercise 1":
                exerciseName = "Bench Press";
                break;
            case "Exercise 2":
                exerciseName = "Overhead Press";
                break;
            case "Exercise 3":
                exerciseName = "Incline Dumbbell Press";
                break;
            case "Exercise 4":
                exerciseName = "Push-Ups";
                break;
            case "Exercise 5":
                exerciseName = "Dips";
                break;
            case "Exercise 6":
                exerciseName = "Dumbbell Lateral Raise";
                break;
            case "Exercise 7":
                exerciseName = "Cable Chest Fly";
                break;
            case "Exercise 8":
                exerciseName = "Tricep Pushdown";
                break;
            case "Exercise 9":
                exerciseName = "Dumbbell Front Raise";
                break;
            case "Exercise 10":
                exerciseName = "Pull-Ups";
                break;
            case "Exercise 11":
                exerciseName = "Barbell Row";
                break;
            case "Exercise 12":
                exerciseName = "Deadlift";
                break;
            case "Exercise 13":
                exerciseName = "Lat Pulldown";
                break;
            case "Exercise 14":
                exerciseName = "Face Pulls";
                break;
            case "Exercise 15":
                exerciseName = "Bicep Curl";
                break;
            case "Exercise 16":
                exerciseName = "Dumbbell Rear Delt Fly";
                break;
            case "Exercise 17":
                exerciseName = "T-Bar Row";
                break;
            case "Exercise 18":
                exerciseName = "Cable Row";
                break;
            case "Exercise 19":
                exerciseName = "Squats";
                break;
            case "Exercise 20":
                exerciseName = "Leg Press";
                break;
            case "Exercise 21":
                exerciseName = "Lunges";
                break;
            case "Exercise 22":
                exerciseName = "Romanian Deadlift";
                break;
            case "Exercise 23":
                exerciseName = "Leg Curls";
                break;
            case "Exercise 24":
                exerciseName = "Leg Extensions";
                break;
            case "Exercise 25":
                exerciseName = "Calf Raises";
                break;
            case "Exercise 26":
                exerciseName = "Step-Ups";
                break;
            case "Exercise 27":
                exerciseName = "Bulgarian Split Squats";
                break;
            default:
                exerciseName = "Unknown Exercise";
                break;
        }
        return exerciseName;
    }

}
