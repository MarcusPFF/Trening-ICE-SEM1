package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WorkoutPage {
    private Connection connect;
    private int Account_ID;

    public WorkoutPage(int Account_ID) {
        connectToDatabase();
        this.Account_ID = Account_ID;
    }

    private void connectToDatabase() {
        String url = "jdbc:sqlite:workout_app.db";

        try {
            connect = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }

    public ArrayList<String> fetchExercisesForProgram(int programID) {
        ArrayList<String> exercises = new ArrayList<>();
        String sql = "SELECT Exercise_Name FROM Exercises WHERE Program_ID = ?";

        try (PreparedStatement stmt = connect.prepareStatement(sql)) {
            stmt.setInt(1, programID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                exercises.add(rs.getString("Exercise_Name"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching exercises: " + e.getMessage());
        }
        return exercises;
    }

    public void logWorkoutForProgram(int programID, ArrayList<UserInput> userInputs, int workoutID, String setDate) {
        try {
            connect.setAutoCommit(false);

            for (UserInput input : userInputs) {
                for (int setNumber = 1; setNumber <= 3; setNumber++) {
                    saveWorkoutData(input.exerciseID, setNumber, input.reps, input.weight, input.note, workoutID, setDate);
                }
            }

            connect.commit();
            System.out.println("Workout logged successfully for Program ID: " + programID);

        } catch (SQLException e) {
            System.out.println("Error logging workout: " + e.getMessage());

            try {
                connect.rollback();
                System.out.println("Transaction rolled back due to error.");
            } catch (SQLException rollbackEx) {
                System.out.println("Error during rollback: " + rollbackEx.getMessage());
            }
        } finally {
            try {
                connect.setAutoCommit(true); //Sørger for at alle ændringer der laves, bliver permanente
            } catch (SQLException e) {
                System.out.println("Error restoring auto-commit: " + e.getMessage());
            }
        }
    }

    private void saveWorkoutData(int exerciseID, int setNumber, int reps, float weight, String note, int workoutID, String setDate) {
        String sql = "INSERT INTO Set_history (Account_ID, Exercise_ID, Set_Number, Set_Reps, Set_Weight, Set_Note, Workout_ID, Set_Date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connect.prepareStatement(sql)) {
            stmt.setInt(1, Account_ID);
            stmt.setInt(2, exerciseID);
            stmt.setInt(3, setNumber);
            stmt.setInt(4, reps);
            stmt.setFloat(5, weight);
            stmt.setString(6, note);
            stmt.setInt(7, workoutID);
            stmt.setString(8, setDate);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saving workout data: " + e.getMessage());
        }
    }

    public static class UserInput {
        int exerciseID;
        int reps;
        float weight;
        String note;

        public UserInput(int exerciseID, int reps, float weight, String note) {
            this.exerciseID = exerciseID;
            this.reps = reps;
            this.weight = weight;
            this.note = note;
        }
    }
}
