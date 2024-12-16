package app;


import java.util.List;
import java.util.Set;

public class Exercises {
    protected String exerciseName;
    protected String note;
    protected String date;
    protected List<Sets> exercise;
    protected List<Exercises> TrainingProgram;

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

    public Exercises(){
    }

    //Adder exercises til programmet
    public void addExerciseToTrainingProgram(Exercises exercises){
        TrainingProgram.add(exercises);
    }

    //Adder sets til exercises
    public void addSetToExercise(Sets sets){
        exercise.add(sets);
    }

    //Fjerner alt fra exercises
    public void removeSetsFromExercise(){
        exercise.clear();
    }


    //Getters og setters
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


