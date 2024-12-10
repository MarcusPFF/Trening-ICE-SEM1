package app;
import util.AccountMapper;
import util.ExercisesMapper;

public class Menu {
    protected WorkoutPage wp;
    protected OrderTrenPage ot;
    protected AccountPage ap;
    protected AccountMapper am;
    protected ExercisesMapper em;

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