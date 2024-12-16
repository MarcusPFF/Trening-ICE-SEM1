package app;

public class Menu {
    public WorkoutPage wp; // Reference til WorkoutPage
    public OrderTrenPage ot; // Reference til OrderTrenPage, som repræsenterer en side for bestilling af TREN
    public AccountPage ap; // Reference til AccountPage


    public Menu() {
        this.wp = new WorkoutPage(); // Initialisering af WorkoutPage.
        this.ot = new OrderTrenPage(); // Initialisering af OrderTrenPage.
        this.ap = new AccountPage(); // Initialisering af AccountPage.
    }


    public void runAlways() {
    }
}
