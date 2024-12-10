package app;

import util.ExercisesMapper;

import java.util.List;

public class Exercises {
    protected ExercisesMapper em;
    public String exerciseName;
    protected String note;
    public List<Sets> exercise;
    public List<Exercises> TrainingProgram;


    public Exercises(String exerciseName, List<Sets> exercise, String note) {
        this.exerciseName = exerciseName;
        this.exercise = exercise;
        this.note = note;

    }
}


