package app;

import util.ExercisesMapper;

import java.util.List;
import java.util.Set;

public class Exercises {
    protected ExercisesMapper em;
    protected String exerciseName;
    protected String note;
    protected List<Sets> exercise;
    protected List<Exercises> TrainingProgram;


    public Exercises(String exerciseName, List<Sets> exercise, String note) {
        this.exerciseName = exerciseName;
        this.exercise = exercise;
        this.note = note;

    }

    public void addExerciseToTrainingProgram(Exercises exercises){
        TrainingProgram.add(exercises);
    }

    public void addSetToExercise(Sets sets){
        exercise.add(sets);
    }

    public void removeSetsFromExercise(){
        exercise.clear();
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Sets> getExercise() {
        return exercise;
    }

    public List<Exercises> getTrainingProgram() {
        return TrainingProgram;
    }
}


