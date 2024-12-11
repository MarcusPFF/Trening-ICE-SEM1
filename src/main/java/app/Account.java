package app;

public class Account {
    private String email;
    private String password;
    private float inputWeight;
    private float inputHeight;

    public Account(String email, String password, float inputWeight, float inputHeight) {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getInputWeight() {
        return inputWeight;
    }

    public void setInputWeight(float inputWeight) {
        this.inputWeight = inputWeight;
    }

    public float getInputHeight() {
        return inputHeight;
    }

    public void setInputHeight(float inputHeight) {
        this.inputHeight = inputHeight;
    }
}