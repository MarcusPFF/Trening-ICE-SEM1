package app;

public class Sets {
    private String exerciseName;
    private int setNumber;
    private int reps;
    private float weight;
    private String note;

    //Konstruktør til Sets
    public Sets(String exerciseName, int setNumber, int reps, float weight, String note) {
        this.exerciseName = exerciseName;
        this.setNumber = setNumber;
        this.reps = reps;
        this.weight = weight;
        this.note = note;
    }

    public Sets() {

    }

    //Setters og getters til Sets - variable
    public String getExerciseName() {
        return exerciseName;
    }

    public int getSetNumber() {
        return setNumber;
    }

    public int getReps() {
        return reps;
    }


    public float getWeight() {
        return weight;
    }

    public String getNote() {
        return note;
    }

}

