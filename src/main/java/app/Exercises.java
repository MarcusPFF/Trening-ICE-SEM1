package app;


import java.util.List;

public class Exercises {
    protected String exerciseName;
    protected String note;
    protected String date;
    protected List<Sets> exercise;

    //Konstruktør nr. 1
    public Exercises(String exerciseName, List<Sets> exercise, String note) {
        this.exerciseName = exerciseName;
        this.exercise = exercise;
        this.note = note;
    }

    //Konstruktør nr. 2
    public Exercises(String exerciseName, String date) {
        this.exerciseName = exerciseName;
        this.date = date;
    }

    public Exercises() {
    }

    //Getters og setters
    public String getExerciseName() {
        return exerciseName;
    }

    public String getDate() {
        return date;
    }
}


