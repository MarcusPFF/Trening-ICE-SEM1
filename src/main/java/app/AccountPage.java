package app;

public class AccountPage {
    public Account acc;

    public AccountPage() {

    }

    public void displayAccountInfo() {
    }


    public void setMeasurements(Account acc) {
        this.acc = acc;
        acc.getHeight();
        acc.getWeight();
    }
    public void logOut() {}
}