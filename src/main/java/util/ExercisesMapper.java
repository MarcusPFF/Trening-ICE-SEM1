package util;

import app.Exercises;
import app.Sets;
import app.Workout;
import app.Menu;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.List;

public class ExercisesMapper {
    Exercises ex;
    Workout wo;
    Menu menu;
    Connection connect;


    public ExercisesMapper() {

    }

    public void exConnectToDB(String url) {

        try {
            connect = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }

    public List<Exercises> loadExercisesData() {
        return null;
    }

    public List<Exercises> saveExercisesData() {
        return null;
    }

    public List<Workout> loadDefaultWorkoutPrograms() {
        String sql = "SELECT Program_ID, Program_Name FROM Programs";

        try {
            Statement stmt = connect.createStatement();
            // execute the query
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Workout test = new Workout(rs.getString("Program_Name"), rs.getInt("Program_ID"));
                wo.defaultWorkoutsProgram.add(test);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return wo.defaultWorkoutsProgram;
    }

    // alle metoder der starter med "em." skal laves, og "em." skal slettes
    public List<Workout> loadEarlierWorkouts() {
        int id = menu.ap.acc.getAccountID();
        String sql = "SELECT Set_Date, Program_ID, Program_Name, Account_ID FROM Set_history JOIN Exercises ON Set_history.Exercise_ID = Exercises.Exercise_ID WHERE Account_ID =" + id;

        int SetCounterForEarlierWorkout = 1;

        try {
            Statement stmt = connect.createStatement();
            // execute the query
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                if (menu.ap.acc.getAccountID() == rs.getInt("Account_ID")) {
                    if (SetCounterForEarlierWorkout == 1) {
                        Workout test = new Workout(rs.getString("Program_Name"), rs.getInt("Set_Date"), rs.getInt("Program_ID"));
                        wo.EarlierWorkouts.add(test);
                    }
                    SetCounterForEarlierWorkout++;
                    if (SetCounterForEarlierWorkout == 28) {
                        SetCounterForEarlierWorkout = 1;
                    }

                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return wo.EarlierWorkouts;
    }

    public List<Exercises> loadSpecificExerciseData() {
        //Split skal sættes til at være lig med det som programid er i det workout objekt man vælger
        String sql = "SELECT Set_Date, Program_ID, Email, Set_Reps, Set_Weight, Exercise_Name, Set_Note FROM Set_history";

        int setNumber = 0;
        try {
            Statement stmt = connect.createStatement();
            // execute the query
            ResultSet rs = stmt.executeQuery(sql);
            int split = wo.getProgramID();
            while (rs.next()) {
                if (rs.getInt("Program_ID") == split && menu.ap.acc.getEmail() == rs.getString("Email") && wo.getProgramDate() == rs.getInt("Set_Date")) {

                    for (int counter = 1; counter < 28; counter++) {
                        if (setNumber < 3) {
                            setNumber = setNumber + 1;
                        } else if (setNumber == 3) {
                            setNumber = 1;
                        }
                        Sets sets = new Sets(rs.getInt("Set_Reps"), rs.getFloat("Set_Weight"), setNumber);
                        ex.addSetToExercise(sets);

                        if (setNumber == 3) {
                            Exercises Exercises1 = new Exercises(rs.getString("Exercise_Name"), ex.getExercise(), rs.getString("Set_Note"));
                            ex.TrainingProgram.add(Exercises1);
                            ex.removeSetsFromExercise();
                        }

                    }
                    break;
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ex.;
    }



    public List<Exercises> loadLastExerciseData() {
        String sql = "SELECT Set_Date, Program_ID, Email, Set_Reps, Set_Weight, Exercise_Name, Set_Note FROM Set_history";
        try {
            Statement stmt = connect.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
//lav en setProgramID til i forhold til hvilken
            int split = wo.getProgramID();
            while (rs.next()) {
                if (rs.getInt("Program_ID") == split && menu.ap.acc.getEmail() == rs.getString("Email") || rs.getInt("Program_ID") == split && rs.getString("Email") == "default") {
                    int setNumber = 0;
                    for (int counter = 1; counter < 28; counter++) {
                        if (setNumber < 3) {
                            setNumber = setNumber + 1;
                        } else if (setNumber == 3) {
                            setNumber = 1;
                        }

                        Sets sets = new Sets(rs.getInt("Set_Reps"), rs.getFloat("Set_Weight"), setNumber);
                        ex.exercise.add(sets);

                        if (setNumber == 3) {
                            Exercises Exercise1 = new Exercises(rs.getString("Exercise_Name"), ex.exercise, rs.getString("Set_Note"));
                            ex.TrainingProgram.add(Exercise1);
                            ex.exercise.clear();
                        }

                    }
                    break;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ex.TrainingProgram;
    }
}

