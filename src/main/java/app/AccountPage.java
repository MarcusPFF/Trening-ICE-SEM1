package app;

public class AccountPage {
    private Account acc;

    public AccountPage() {}

    public void displayAccountInfo() {
    }

    public void setMeasurements(Account acc) {
        this.acc = acc;
        acc.getInputHeight();
        acc.getInputWeight();
    }
    public void logOut() {}
}