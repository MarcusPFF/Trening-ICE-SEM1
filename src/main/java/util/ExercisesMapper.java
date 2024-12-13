package util;

import app.Exercises;
import app.Sets;
import app.Workout;
import app.Menu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExercisesMapper {
    private Exercises ex;
    private Workout wo;
    private Menu menu;
    private Connection connect;

    public ExercisesMapper() {
        // Default constructor
    }
    public void setConnection(Connection connect) {
        this.connect = connect;
    }

    public void exConnectToDB(String url) {
        try {
            connect = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }

    public List<Exercises> loadExercisesData() {
        List<Exercises> exercisesList = new ArrayList<>();
        String sql = "SELECT Exercise_Name, Program_ID FROM Exercises";

        try (Statement stmt = connect.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Load exercises and add to the list
                String exerciseName = rs.getString("Exercise_Name");
                Exercises exercise = new Exercises(exerciseName, new ArrayList<>(), null);
                exercisesList.add(exercise);
            }
        } catch (SQLException e) {
            System.out.println("Error loading exercises data: " + e.getMessage());
        }
        return exercisesList;
    }

    public void saveExercise(String exerciseName, int setNumber, int setReps, float setWeight, String setNote) {
        String sql = "INSERT INTO Set_history (Exercise_ID, Set_Number, Set_Reps, Set_Weight, Set_Note) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connect.prepareStatement(sql)) {
            stmt.setString(1, exerciseName);
            stmt.setInt(2, setNumber);
            stmt.setInt(3, setReps);
            stmt.setFloat(4, setWeight);
            stmt.setString(5, setNote);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saving exercise: " + e.getMessage());
        }
    }

    public void loadDefaultWorkoutPrograms() {
        String sql = "SELECT Program_ID, Program_Name FROM Programs";

        try (Statement stmt = connect.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String programName = rs.getString("Program_Name");
                int programID = rs.getInt("Program_ID");
                Workout workout = new Workout(programName, programID);
                wo.addWorkoutToDefaultWorkoutPrograms(workout);
            }
        } catch (SQLException e) {
            System.out.println("Error loading default workout programs: " + e.getMessage());
        }
    }

    public void loadEarlierWorkouts() {
        int accountId = menu.ap.acc.getAccountID();
        String sql = """
            SELECT sh.Set_Date, p.Program_ID, p.Program_Name, sh.Account_ID
            FROM Set_history sh
            JOIN Programs p ON sh.Program_ID = p.Program_ID
            WHERE sh.Account_ID = ?
            """;

        try (PreparedStatement stmt = connect.prepareStatement(sql)) {
            stmt.setInt(1, accountId);

            try (ResultSet rs = stmt.executeQuery()) {
                int setCounter = 1;

                while (rs.next()) {
                    int programId = rs.getInt("Program_ID");
                    String programName = rs.getString("Program_Name");
                    int setDate = rs.getInt("Set_Date");

                    if (setCounter == 1) {
                        Workout workout = new Workout(programName, setDate, programId);
                        wo.addWorkoutToEarlierWorkouts(workout);
                    }
                    setCounter = (setCounter == 28) ? 1 : setCounter + 1;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error loading earlier workouts: " + e.getMessage());
        }
    }

    public void loadSpecificExerciseData() {
        String sql = """
            SELECT sh.Set_Date, p.Program_ID, a.Email, sh.Set_Reps, sh.Set_Weight, e.Exercise_Name, sh.Set_Note
            FROM Set_history sh
            JOIN Programs p ON sh.Program_ID = p.Program_ID
            JOIN Accounts a ON sh.Account_ID = a.Account_ID
            JOIN Exercises e ON sh.Exercise_ID = e.Exercise_ID
            """;

        int setNumber = 0;

        try (Statement stmt = connect.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            int split = wo.getProgramID();

            while (rs.next()) {
                if (rs.getInt("Program_ID") == split &&
                        menu.ap.acc.getEmail().equals(rs.getString("Email")) &&
                        wo.getProgramDate() == rs.getInt("Set_Date")) {

                    for (int counter = 1; counter < 28; counter++) {
                        setNumber = (setNumber < 3) ? setNumber + 1 : 1;

                        Sets sets = new Sets(rs.getString("Exercise_Name"), setNumber, rs.getInt("Set_Reps"),
                                rs.getFloat("Set_Weight"), rs.getString("Set_Note"));
                        ex.addSetToExercise(sets);

                        if (setNumber == 3) {
                            Exercises exercise = new Exercises(rs.getString("Exercise_Name"), ex.getExercise(), rs.getString("Set_Note"));
                            ex.addExerciseToTrainingProgram(exercise);
                            ex.removeSetsFromExercise();
                        }
                    }
                    break;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error loading specific exercise data: " + e.getMessage());
        }
    }

    public void loadLastExerciseData() {
        String sql = """
            SELECT sh.Set_Date, p.Program_ID, a.Email, sh.Set_Reps, sh.Set_Weight, e.Exercise_Name, sh.Set_Note
            FROM Set_history sh
            JOIN Programs p ON sh.Program_ID = p.Program_ID
            JOIN Accounts a ON sh.Account_ID = a.Account_ID
            JOIN Exercises e ON sh.Exercise_ID = e.Exercise_ID
            """;

        try (Statement stmt = connect.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            int split = wo.getProgramID();
            int setNumber = 0;

            while (rs.next()) {
                if ((rs.getInt("Program_ID") == split && menu.ap.acc.getEmail().equals(rs.getString("Email"))) ||
                        (rs.getInt("Program_ID") == split && "default".equals(rs.getString("Email")))) {

                    for (int counter = 1; counter < 28; counter++) {
                        setNumber = (setNumber < 3) ? setNumber + 1 : 1;

                        Sets sets = new Sets(rs.getString("Exercise_Name"), setNumber, rs.getInt("Set_Reps"),
                                rs.getFloat("Set_Weight"), rs.getString("Set_Note"));
                        ex.addSetToExercise(sets);

                        if (setNumber == 3) {
                            Exercises exercise = new Exercises(rs.getString("Exercise_Name"), ex.getExercise(), rs.getString("Set_Note"));
                            ex.addExerciseToTrainingProgram(exercise);
                            ex.removeSetsFromExercise();
                        }
                    }
                    break;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error loading last exercise data: " + e.getMessage());
        }
    }
}
