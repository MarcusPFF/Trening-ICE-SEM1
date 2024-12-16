package app;

import JFrame.JFrameGUI;

import javax.swing.*;

public class Account {
    private String email;
    private String password;
    private float weight;
    private float height;
    private JTextField heightField;
    private JTextField weightField;

    public Account(String email, String password, float Weight, float Height) {
    }


    public void setEmail(String email) {

    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }


    public float getWeight() {
        return weight;
    }


    public float getHeight() {
        return height;
    }

    public void setCurrentWeight() {
        try {
            String weightString = weightField.getText();
            float weight = Float.parseFloat(weightString);

            if (weight >= 40.0 && weight <= 300.0) {
                this.weight = weight;
                System.out.println("Vægt opdateret til: " + weight);
            } else {
                JFrameGUI.messageLabel.setText("Indtast venligst en vægt mellem 40-300kg");
            }
        } catch (NumberFormatException e) {
            // Håndter fejl, hvis input ikke kan konverteres til et tal
            JFrameGUI.messageLabel.setText("Der skete en uventet fejl - prøv igen senere.");
        }
    }

    public void setCurrentHeight() {
        try {
            String heightString = heightField.getText();
            float height = Float.parseFloat(heightString);

            if (height >= 100.0 && height <= 220.0) {
                this.height = height;
                System.out.println("Højde opdateret til: " + height);
            } else {
                JFrameGUI.messageLabel.setText("Indtast venligst en højde mellem 100-220cm.");
            }
        } catch (NumberFormatException e) {
            // Håndter fejl, hvis input ikke kan konverteres til et tal
            JFrameGUI.messageLabel.setText("Der skete en uventet fejl - prøv igen senere.");
        }
    }
}
