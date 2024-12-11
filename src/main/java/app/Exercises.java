package app;

import util.ExercisesMapper;

import java.util.List;

public class Exercises {
    public String exerciseName;
    public List<Sets> exercise;
    public List<Exercises> TrainingProgram;
    protected ExercisesMapper em;
    protected String note;


    public Exercises(String exerciseName, List<Sets> exercise, String note) {
        this.exerciseName = exerciseName;
        this.exercise = exercise;
        this.note = note;

    }
}


