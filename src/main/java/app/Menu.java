package app;


public class Menu {
    public WorkoutPage wp;
    public OrderTrenPage ot;
    public AccountPage ap;


    public Menu() {
        wp = new WorkoutPage();
        ot = new OrderTrenPage();
        ap = new AccountPage();

    }

    public void runAlways() {
    }
}