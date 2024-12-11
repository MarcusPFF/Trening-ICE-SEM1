package JFrame;

import javax.swing.*;
import java.awt.*;
import java.net.URI;

public class JFrameGUI extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public void launchGUI() {
        createLoginGUI();
    }

    public void createMenuGUI() {
        JPanel menuPanel = new JPanel(new BorderLayout());
        menuPanel.setBackground(new Color(204, 204, 238));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(34, 45, 50));

        JButton button1 = createModernButton("Home");
        JButton button2 = createModernButton("Workout");
        JButton button3 = createModernButton("Shop");
        JButton button4 = createModernButton("Account");

        button1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        button2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        button3.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        button4.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);

        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        CardLayout layout = new CardLayout();
        JPanel rightPanel = new JPanel(layout);

        JPanel menu1 = createModernPanel("Welcome to home page!");
        JPanel menu2 = createModernPanel("Start a workout");
        JPanel menu3 = createModernPanel("Order your Tren now!");
        JPanel menu4 = createModernPanel("Manage your account.");

        JButton exampleButton = createModernButton("Example Button");
        menu2.add(exampleButton, BorderLayout.SOUTH);

        JButton buyTrenButton = createModernButton("Shop now!");
        menu3.add(buyTrenButton, BorderLayout.SOUTH);

        JButton logout = createModernButton("Logout");
        menu4.add(logout, BorderLayout.SOUTH);

        rightPanel.add(menu1, "Menu");
        rightPanel.add(menu2, "Workout");
        rightPanel.add(menu3, "Shop");
        rightPanel.add(menu4, "Account");

        button1.addActionListener(e -> layout.show(rightPanel, "Menu"));
        button2.addActionListener(e -> layout.show(rightPanel, "Workout"));
        button3.addActionListener(e -> layout.show(rightPanel, "Shop"));
        button4.addActionListener(e -> layout.show(rightPanel, "Account"));

        buyTrenButton.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://politi.dk/"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        logout.addActionListener(e -> {
            try {
                logout();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        menuPanel.add(panel, BorderLayout.WEST);
        menuPanel.add(rightPanel, BorderLayout.CENTER);

        mainPanel.add(menuPanel, "Menu");
    }

    public void createLoginGUI() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        setTitle("Trening - App for Muscle Growth");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setResizable(false);

        ImageIcon image = new ImageIcon("path");
        setIconImage(image.getImage());

        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(new Color(34, 45, 50));

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

        gbc.gridy = 1;
        JTextField emailField = new JTextField(20);
        emailField.setText("Email");
        loginPanel.add(emailField, gbc);

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
            createMenuGUI();
            cardLayout.show(mainPanel, "Menu");
        });

        createAccountButton.addActionListener(e -> {
            createMenuGUI();
            cardLayout.show(mainPanel, "Menu");
        });

        setVisible(true);
    }

    private void logout() {
        cardLayout.show(mainPanel, "Login");
    }

    public void createWorkoutGUI() {
    }

    public void currentTrainingGUI() {
    }

    public void createOrderTrenGUI() {
    }

    public void createHomeGUI() {
    }

    public void createAccountGUI() {
    }

    private JButton createModernButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Roboto", Font.PLAIN, 16));
        button.setBackground(new Color(0, 123, 255));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2, true));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setBorder(BorderFactory.createCompoundBorder(button.getBorder(), BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 102, 204));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 123, 255));
            }
        });

        return button;
    }

    private JPanel createModernPanel(String labelText) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(255, 255, 255));

        JLabel label = new JLabel(labelText, JLabel.CENTER);
        label.setFont(new Font("Roboto", Font.BOLD, 24));
        label.setForeground(new Color(60, 60, 60));
        panel.add(label, BorderLayout.NORTH);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        return panel;
    }
}
