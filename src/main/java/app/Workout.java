package app;

import java.util.List;

public class Workout {
    protected String ProgramName;
    protected float ProgramDate;
    public int ProgramID;
    public List<Workout> EarlierWorkouts;
    public List<Workout> defaultWorkoutsProgram;


    public Workout(String ProgramName, float ProgramDate, int ProgramID){
        this.ProgramName = ProgramName;
        this.ProgramDate = ProgramDate;
        this.ProgramID = ProgramID;
    }
    public Workout(String ProgramName, int ProgramID){
        this.ProgramName = ProgramName;
        this.ProgramID = ProgramID;
    }

    public String getProgramName() {
        return ProgramName;
    }

    public float getProgramDate() {
        return ProgramDate;
    }

    public int getProgramID() {
        return ProgramID;
    }

    public List<Workout> getEarlierWorkouts() {
        return EarlierWorkouts;
    }

    public void setProgramDate(float programDate) {
        ProgramDate = programDate;
    }

    public void setProgramName(String programName) {
        ProgramName = programName;
    }

    public void setProgramID(int programID) {
        ProgramID = programID;
    }

    public void setEarlierWorkouts(List<Workout> earlierWorkouts) {
        EarlierWorkouts = earlierWorkouts;
    }
}
