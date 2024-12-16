package app;

public class Account {
    private String email;
    private String password;
    private float Weight;
    private float Height;
    private String currentEmail;
    private float currentWeight;
    private float currentHeight;

    public Account(String email, String password, float Weight, float Height) {
        this.email = email;
        this.password = password;
        this.Weight = Weight;
        this.Height = Height;
    }

    public Account(String currentEmail, float CurrentWeight, float CurrentHeight) {
          this.currentEmail = currentEmail;
          this.currentWeight = CurrentWeight;
          this.currentHeight = CurrentHeight;
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

    public void setWeight(float Weight) {
        this.Weight = Weight;
    }

    public float getWeight() {
        return Weight;
    }

    public void setHeight(float Height) {
        this.Height = Height;
    }

    public float getHeight() {
        return Height;
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
}