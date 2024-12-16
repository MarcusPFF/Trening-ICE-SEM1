package app;

public class Account {
    private String email;
    private String password;
    private float Weight;
    private float Height;

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

}