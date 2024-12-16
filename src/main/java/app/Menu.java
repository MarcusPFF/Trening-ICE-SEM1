package app;
import util.AccountMapper;
import util.ExercisesMapper;

public class Menu {
    public WorkoutPage wp;
    public OrderTrenPage ot;
    public AccountPage ap;
    public AccountMapper am;
    public ExercisesMapper em;

    public Menu() {
        wp = new WorkoutPage();
        ot = new OrderTrenPage();
        ap = new AccountPage();
        am = new AccountMapper();
        em = new ExercisesMapper();
    }

    public void runAlways() {
    }
}