package JFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URI;

public class jFrameGUI extends JFrame {

    public void launchGUI() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               createGUI();
            }
        });
    }

    public void createGUI() {
        // Setup til vores Jframe
        setTitle("Trening - App for Muscle Growth");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setResizable(true);
        setVisible(true);

        //Logo til app
        ImageIcon image = new ImageIcon("path");
        setIconImage(image.getImage());

        //  Baggrunds farve
        getContentPane().setBackground(new Color(204, 204, 238));

        //Side menu objekt hvor vi sætter værdier
        JPanel sideMenu = new JPanel();
        sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));
        sideMenu.setBackground(new Color(34, 45, 50));

        //Kalder metode "createModernButton" som er lavet til at lave styling på knapper. Vi laver hermed 4 knapper
        JButton button1 = createModernButton("Home");
        JButton button2 = createModernButton("Workout");
        JButton button3 = createModernButton("Shop");
        JButton button4 = createModernButton("Account");


        // Hvor store knapperne er i side menuen
        button1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        button2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        button3.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        button4.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));

        // Vi tilføjer knapperne til sideMenu objektet
        sideMenu.add(button1);
        sideMenu.add(button2);
        sideMenu.add(button3);
        sideMenu.add(button4);

        // Dette går så sideMenuen har en dynamisk resize evne
        sideMenu.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Layout for højre rightpanel
        CardLayout Layout = new CardLayout();
        JPanel rightPanel = new JPanel(Layout);

        // Ligesom før så er den her metode "createModernPanel" lavet til at man kan style en side pæn.
        JPanel menu1 = createModernPanel("Welcome to home page!");
        JPanel menu2 = createModernPanel("Start a workout");
        JPanel menu3 = createModernPanel("Order your Tren now!");
        JPanel menu4 = createModernPanel("Manage your account.");

        //Dette er sådan du laver nye knapper
        JButton eksempelPåKnap = createModernButton("Eksempel på knap");
        //Du kan bruge NORTH, SOUTH, EAST, WEST til at definere hvor knappen skal være.
        menu2.add(eksempelPåKnap, BorderLayout.SOUTH);

        JButton buytren = createModernButton("Shop now!");
        menu3.add(buytren, BorderLayout.SOUTH);

        // Vi tilføjer menu1,2,3,4 til rightpanel
        rightPanel.add(menu1, "Menu");
        rightPanel.add(menu2, "Workout");
        rightPanel.add(menu3, "Shop");
        rightPanel.add(menu4, "Account");

        // Her er hvor vi definere hvad der skal ske når man trykker på knapperne. "ActionListener"
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Layout.show(rightPanel, "Menu");
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Layout.show(rightPanel, "Workout");
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Layout.show(rightPanel, "Shop");
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Layout.show(rightPanel, "Account");
            }
        });

        buytren.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Åbner webbrowseren med den angivne URL
                    Desktop.getDesktop().browse(new URI("https://politi.dk/"));
                } catch (Exception ex) {
                    ex.printStackTrace();  // Håndterer undtagelser, hvis noget går galt
                }
            }
        });

        //Eksempel
        /*eksempelPåKnap.addActionListener(new ActionListener() {
           StartTraining()
        });

         */

        //Tilføjer sidemenu og rightpanel til borderLayout.
        add(sideMenu, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }

    //Metode til at lave en flot knap
    private JButton createModernButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Roboto", Font.PLAIN, 16));
        button.setBackground(new Color(0, 123, 255));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2, true));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setBorder(BorderFactory.createCompoundBorder(
                button.getBorder(),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        // Knap hover effekt
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

    //Metode til at lave et flot panel
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