package app;

import util.ExercisesMapper;

import java.util.List;

public class Exercises {
    protected ExercisesMapper em;
    protected String exerciseName;
    protected String note;
    protected List<Sets> exercise;
    protected List<Exercises> trainingProgram;

    public Exercises(String exerciseName, List<Sets> exercise, String note) {
        this.exerciseName = exerciseName;
        this.exercise = exercise;
        this.note = note;

    }


    public void addSetsToExercise() {
        Sets set1 = new Sets(/*em.loadExerciseRepsData(1), em.loadExerciseKgData(1)*/);
        Sets set2 = new Sets(/*em.loadExerciseRepsData(2), em.loadExerciseKgData(2)*/);
        Sets sets3 = new Sets(/*em.loadExerciseRepsData(3), em.loadExerciseKgData(3)*/);
        exercise.add(set1);
        exercise.add(set2);
        exercise.add(sets3);

    }
    public void addExerciseToProgram() {
        trainingProgram.add(Exercises(/*getExerciseName, exercise, getExeciseNote*/));
    }
}