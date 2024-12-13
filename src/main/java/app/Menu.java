package app;
import util.AccountMapper;
import util.ExercisesMapper;

import java.sql.SQLException;

public class Menu {
    public WorkoutPage wp;
    public OrderTrenPage ot;
    public AccountPage ap;
    public AccountMapper am;
    public ExercisesMapper em;

    public Menu() throws SQLException {
        wp = new WorkoutPage(1);
        ot = new OrderTrenPage();
        ap = new AccountPage();
        am = new AccountMapper();
        em = new ExercisesMapper();
    }


    public void runAlways() {
    }
}