package app;

import util.ExercisesMapper;

public class WorkoutPage {
    private final CurrentTrainingPage ct;
    private ExercisesMapper em;

    public WorkoutPage() {
        ct = new CurrentTrainingPage();
        ct.startTraining();
    }

}