package util;

import app.Exercises;
import app.Menu;
import app.Workout;

import java.util.List;

public class ExercisesMapper {
    private Exercises ex;
    private Workout wo;
    private Menu menu;


    public ExercisesMapper() {

    }

    public List<Exercises> loadExercisesData() {
        //Loader liste af exercise
        return List.of();
    }

    public void loadDefaultWorkoutPrograms(String split) {
        //Load program hvis bruger ikke allerede har

    }

    //public void loadSpecificExerciseData() {


    public void loadLastExerciseData() {
        //Load de sidste workouts fra user login på homepage
    }
}
