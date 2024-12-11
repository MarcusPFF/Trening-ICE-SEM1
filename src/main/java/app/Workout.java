package app;

import java.util.List;

public class Workout {
    public List<Workout> EarlierWorkouts;
    protected String ProgramName;
    protected float ProgramDate;
    protected int ProgramID;


    public Workout(String ProgramName, float ProgramDate, int ProgramID) {
        this.ProgramName = ProgramName;
        this.ProgramDate = ProgramDate;
        this.ProgramID = ProgramID;
    }

}
