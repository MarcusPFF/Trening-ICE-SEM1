package app;

import JFrame.JFrameGUI;

import javax.swing.*;

public class Account {
    private String email;
    private String password;
    private float Weight;
    private float Height;
    private String currentEmail;
    private float currentWeight;
    private float currentHeight;
    protected String heightText;
    protected String weightText;

    private float weight;
    private float height;
    private JTextField heightField;
    private JTextField weightField;

    //Konstruktør til account
    public Account(String email, String password, float Weight, float Height) {
        this.email = email;
        this.password = password;
        this.Weight = Weight;
        this.Height = Height;
    }
    //Konstruktør til account
    public Account(String currentEmail, float CurrentWeight, float CurrentHeight) {
        this.currentEmail = currentEmail;
        this.currentWeight = CurrentWeight;
        this.currentHeight = CurrentHeight;
    }

    public Account(){

    }

    //Getters og setters
    public void setEmail(String email) {this.email = email; }

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

    public String getCurrentEmail() {
        return currentEmail;
    }

    public void setCurrentEmail(String currentEmail) {
        this.currentEmail = currentEmail;
    }

    public float getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(float currentWeight) {
        this.currentWeight = currentWeight;
    }

    public float getCurrentHeight() {
        return currentHeight;
    }

    public void setCurrentHeight(float currentHeight) {
        this.currentHeight = currentHeight;
    }

    public void setWeight(float weight) {
        Weight = weight;
    }

    public void setHeight(float height) {
        Height = height;
    }

    // Efter vægt er skrevet ind kigger denne metode igennem om svaret er indefor normalvægt og om det er et tal.
    public String validateSetCurrentWeight(float currentWeightString) {
        try {
            //currentWeightString = weightField.getText();
            float currentWeight = currentWeightString;

            if (currentWeight >= 40.0 && currentWeight <= 300.0) {
                setCurrentWeight(currentWeight);
                weightText = "Vægt opdateret til: " + currentWeight;
                return weightText;
            }
            return null;
        } catch (NumberFormatException e) {
            // Håndter fejl, hvis input ikke kan konverteres til et tal
            weightText = "Der skete en uventet fejl - prøv igen senere.";
            return weightText;
        }
    }

    // Efter højde er skrevet ind kigger denne metode igennem om svaret er indefor normalhøjde og om det er et tal.
    public String validateSetCurrentHeight(float currentHeightString) {
        try {
            //currentHeightString = heightField.getText();
            float currentHeight= currentHeightString;
            if (currentHeight >= 100.0 && currentHeight <= 220.0) {
                setCurrentHeight(currentHeight);
                heightText = "Højde opdateret til: " + currentHeight;
                return heightText;
            }
            return null;
        } catch (NumberFormatException e) {
            // Håndter fejl, hvis input ikke kan konverteres til et tal
            heightText = "Der skete en uventet fejl - prøv igen senere.";
            return heightText;
        }
    }

    public String getHeightText() {
        return heightText;
    }

    public void setHeightText(String heightText) {
        this.heightText = heightText;
    }

    public String getWeightText() {
        return weightText;
    }

    public void setWeightText(String weightText) {
        this.weightText = weightText;
    }
}
