package util;

import app.Exercises;
import app.Sets;
import app.Workout;

import java.util.List;

public class ExercisesMapper {
    Exercises ex;
    Workout wo;


    public ExercisesMapper() {
    }

    public List<Exercises> loadExercisesData() {
        return null;
    }

    public List<Exercises> saveExercisesData() {
        return null;
    }
// alle metoder der starter med "em." skal laves, og "em." skal slettes
    public List<Workout> loadEarlierWorkouts(){
        int SetCounterForEarlierWorkout = 1;

        while (true) {
            if (acc.getEmail() == em.getEmail()) {
                if (SetCounterForEarlierWorkout == 1) {
                    Workout test = new Workout(em.getProgram_Name(), em.getSet_Date(), em.getPorgram_ID())
                    wo.EarlierWorkouts.add(test);
                }
                SetCounterForEarlierWorkout++;
                if (SetCounterForEarlierWorkout == 28) {
                    SetCounterForEarlierWorkout = 1;
                }

            }
            nextLine();
            if (!NextLine()){
                break;
            }
        }
    }

    public List<Exercises> loadSpecificExerciseData() {
        //Split skal sættes til at være lig med det som programid er i det workout objekt man vælger
        while (true) {
            if (em.getProgram_ID == split && acc.getEmail() == em.getEmail() && Date = em.getSet_Date()) {
                int setNumber = 0;
                for (int counter = 1; counter < 28; counter++) {
                    if (setNumber < 3) {
                        setNumber = setNumber + 1;
                    } else if (setNumber == 3) {
                        setNumber = 1;
                    }
                    Sets sets = new Sets(em.getSet_Reps(), em.loadExerciseKgData(), setNumber);
                    ex.exercise.add(sets);

                    if (setNumber == 3) {
                        Exercises Exercises1 = new Exercises(em.getExercise_Name(), ex.exercise, em.getSet_Note());
                        ex.TrainingProgram.add(Exercises1);
                        ex.exercise.clear();
                    }
                    nextLine();
                }
                break;
            }

        }
    }
    public List<Exercises> loadLastExerciseData() {

        while (true){
            if (em.getProgram_ID == split && acc.getEmail() == em.getEmail()){
                int setNumber = 0;
                for (int counter = 1; counter < 28; counter++){
                    if (setNumber < 3){
                        setNumber = setNumber + 1;
                    } else if (setNumber == 3){
                        setNumber = 1;
                    }

                    Sets sets = new Sets(em.getSet_Reps(), em.loadExerciseKgData(), setNumber);
                    ex.exercise.add(sets);

                    if (setNumber == 3){
                        Exercises exercise1 = new Exercises(em.getExercise_Name(), ex.exercise, em.getSet_Note());
                        ex.TrainingProgram.add(exercise1);
                        ex.exercise.clear();
                    }
                    nextLine();
                }
                break;
            }
        }
    }


    /*public int loadExerciseRepsData(int setNr) {
        int reps = rs.getInt("Set" + setNr);
        return reps;
    }

    public float loadExerciseKgData(int setNr) {
        int Kg = rs.getInt("Set" + setNr);
        return Kg;
    }*/

}