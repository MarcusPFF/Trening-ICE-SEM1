package app;

import java.util.List;

public class Workout {
    protected String ProgramName;
    protected float ProgramDate;
    protected int ProgramID;
    protected List<Workout> EarlierWorkouts;
    protected List<Workout> defaultWorkoutsProgram;


    public Workout(String ProgramName, float ProgramDate, int ProgramID) {
        this.ProgramName = ProgramName;
        this.ProgramDate = ProgramDate;
        this.ProgramID = ProgramID;
    }

    public Workout(String ProgramName, int ProgramID) {
        this.ProgramName = ProgramName;
        this.ProgramID = ProgramID;
    }

    public void addWorkoutToEarlierWorkouts(Workout workout) {
        EarlierWorkouts.add(workout);
    }

    public void addWorkoutToDefaultWorkoutPrograms(Workout workout) {
        defaultWorkoutsProgram.add(workout);
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
