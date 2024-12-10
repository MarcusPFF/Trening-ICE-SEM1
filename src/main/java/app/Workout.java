package app;

import java.util.List;

public class Workout {
    protected String ProgramName;
    protected float ProgramDate;
    protected int ProgramID;
    public List<Workout> EarlierWorkouts;


    public Workout(String ProgramName, float ProgramDate, int ProgramID){
        this.ProgramName = ProgramName;
        this.ProgramDate = ProgramDate;
        this.ProgramID = ProgramID;
    }

}
