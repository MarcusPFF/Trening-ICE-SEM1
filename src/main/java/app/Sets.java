package app;

public class Sets {
    private String exerciseName;
    private int setNumber;
    private int reps;
    private float weight;
    private String note;
    private int sets;

    public Sets(String exerciseName, int setNumber, int reps, float weight, String note) {
        this.exerciseName = exerciseName;
        this.setNumber = setNumber;
        this.reps = reps;
        this.weight = weight;
        this.note = note;
    }

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
    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }
    public void setSetNumber(int setNumber) {
        this.setNumber = setNumber;
    }
    public void setReps(int reps) {
        this.reps = reps;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public int getsetNumber(){
        return setNumber;
    }
}
