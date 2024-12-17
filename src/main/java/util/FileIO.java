package util;

import JFrame.JFrameGUI;
import app.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class FileIO {
    protected String split;
    private Menu menu;
    private Account acc;
    private Exercises ex;
    private Workout wo;
    private Sets sets;
    protected ArrayList<Account> accounts;
    public JFrameGUI frame;
    protected List<Exercises> accountEarlierWorkouts;
    protected ArrayList<Sets> accountLastExerciseData;

    public FileIO() {
        this.accounts = new ArrayList<>();
        this.accountLastExerciseData = new ArrayList<>();
        this.menu = new Menu();
        this.acc = new Account();
        this.ex = new Exercises();
        this.wo = new Workout();
        this.sets = new Sets();

    }

    /*
     Loader brugerdata ned fra en CSV fil ved den specificerede fil-path.
     hvis der ikke er nogen fil ud fra file-path kommer der en errormessage.
     Læser alle linjer, splitter med kommaer, og laver User-objects med dataen.
    */
    public List<Account> loadAccountData(String accountPath) {
        accounts.clear();
        File file = new File(accountPath);
        if (!file.exists()) {
            System.out.println("Brugerdatafil ikke fundet: " + accountPath);
            return accounts;
        }

        try (Scanner scanner = new Scanner(file)) {
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Skip header
            }
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                if (data.length == 4) {
                    String email = data[0].trim();
                    String password = data[1].trim();
                    float weight = Float.parseFloat(data[2].trim());
                    float height = Float.parseFloat(data[3].trim());
                    accounts.add(new Account(email, password, weight, height));
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning af brugerdata: " + e.getMessage());
        }
        return accounts;
    }
    /*
     Saves user data to a CSV file at the specified file path.
     If the parent directories of the file don't exist, they are created.
     Writes the user details (username, password, and validate (which is in this case admin status) for each user in the list to the file.
    */

    public void saveAccountData(String filePath, List<Account> accounts, String currentEmail, float weight, float height) {
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(file)) {
            writer.write("Email,Password,Weight,Height\n");
            for (Account account : accounts) {
                if (account.getEmail().equals(currentEmail)) {
                    writer.write(account.getEmail() + "," + account.getPassword() + "," + weight + "," + height + "\n");
                } else {
                    writer.write(account.getEmail() + "," + account.getPassword() + "," + account.getWeight() + "," + account.getHeight() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved skrivning af brugerdata: " + e.getMessage());
        }
    }

    //Benyttes til at displaye tidligere træninger på vores homePage.
    public List<Exercises> loadEarlierWorkouts(String folderPath) {
        List<Exercises> accountEarlierWorkouts = new ArrayList<>();
        String accountData = folderPath + "/earlierWorkouts.csv";
        File file = new File(accountData);

        if (!file.exists()) {
            System.out.println("Brugerdatafil ikke fundet: " + accountData);
            return accountEarlierWorkouts;
        }

        try (Scanner scanner = new Scanner(file)) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                if (data.length == 2) {
                    String workoutName = data[0].trim();
                    String dateTime = data[1].trim();
                    accountEarlierWorkouts.add(new Exercises(workoutName, dateTime));
                }

            }
            return accountEarlierWorkouts;
        } catch (IOException e) {
            System.out.println("Error loading exercise Data " + e.getMessage());
        }
        return accountEarlierWorkouts;
    }

    // Loader tidligere træninger så de kan fremkaldes på de forskellige Jfields når ny træning påbegyndes, for at man kan se tidligere reps, vægt og noter.
    public ArrayList<Sets> loadLastExercisesData(String lastExercisesPath, String workoutType) {
        String accountData = lastExercisesPath +  "_exerciseData.csv";
        File file = new File(accountData);
        System.out.println(file);
        if (!file.exists()) {
            System.out.println("Brugerdatafil ikke fundet: " + lastExercisesPath);;
            file.getParentFile().mkdirs();
            try (FileWriter writer = new FileWriter(accountData, true)) {

                if (file.length() == 0) {
                    writer.write("exerciseName,setNumber,reps,weight,note\n");
                    int x = 0;
                    switch (workoutType){
                        case "Push Workout":
                            x = 1;
                            break;
                        case "Pull Workout":
                            x = 10;
                            break;
                        case "Legs Workout":
                            x = 19;
                            break;
                    }
                    for (int antalGennemgange = 0; antalGennemgange < 9; antalGennemgange++) {
                        writer.write("Exercise " + x+ "," + 0 + "," + 0 + "," + 0 + "," + "Ingen Note" + "\n");
                        writer.write("Exercise " + x+ "," + 0 + "," + 0 + "," + 0 + "," + "Ingen Note" + "\n");
                        writer.write("Exercise " + x+ "," + 0 + "," + 0 + "," + 0 + "," + "Ingen Note" + "\n");
                        x++;
                    }
                }

            } catch (IOException e) {
                System.out.println("Error saving exercise data: " + e.getMessage());
            }

            loadLastExercisesData(lastExercisesPath, workoutType);
        }

        try (Scanner scanner = new Scanner(file)) {
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Skip header


                while (scanner.hasNextLine()) {
                    String[] data = scanner.nextLine().trim().split(",");
                    if (data.length == 5) {
                        String exerciseName = data[0].trim();
                        int setNumber = Integer.parseInt(data[1].trim());
                        int reps = Integer.parseInt(data[2].trim());
                        float weight = Float.parseFloat(data[3].trim());
                        String note = data[4].trim();

                        accountLastExerciseData.add(new Sets(exerciseName, setNumber, reps, weight, note));
                    } else if (data.length == 4) {
                        String exerciseName = data[0].trim();
                        int setNumber = Integer.parseInt(data[1].trim());
                        int reps = Integer.parseInt(data[2].trim());
                        float weight = Float.parseFloat(data[3].trim());

                        accountLastExerciseData.add(new Sets(exerciseName, setNumber, reps, weight, "ingen note"));
                    }


                    if (accountLastExerciseData.size() > 27) {
                        accountLastExerciseData.removeFirst();
                    }
                }
            }



        } catch (FileNotFoundException e) {
            System.out.println("Fejl ved skrivning af brugerdata: " + e.getMessage());
        }
        System.out.println(accountLastExerciseData.size());
        return accountLastExerciseData;
    }

    //Henter øvelser ned til de 3 forskellige programmer/splits som vi har lavet.
    public List<Exercises> loadExerciseData(String exercisesPath) {
        List<Exercises> exerciseData = new ArrayList<>();
        File file = new File(exercisesPath);

        if (!file.exists()) {
            System.out.println("Exercise data fil ikke fundet: " + exerciseData);
            return exerciseData;
        }

        try (Scanner scanner = new Scanner(file)) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                if (data.length == 3) {
                    String Nr = data[0].trim();
                    String ExerciseName = data[1].trim();
                    String Id = data[2].trim();
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading exercise Data " + e.getMessage());
        }
        return exerciseData;
    }

    //Gemmer træningsdata ned i en cvs fil.
    public void saveExerciseData(String folderPath, List<Sets> exercises, String workoutType) {
        String accountData = folderPath + "/" + workoutType + "_exerciseData.csv";
        File file = new File(accountData);
        file.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(accountData, true)) {

            if (file.length() == 0) {
                writer.write("exerciseName,setNumber,reps,weight,note\n");
            }

            for (Sets exercise : exercises) {
                writer.write(exercise.getExerciseName() + ","
                        + exercise.getSetNumber() + ","
                        + exercise.getReps() + ","
                        + exercise.getWeight()  + ","
                        + exercise.getNote() + "\n");
            }

        } catch (IOException e) {
            System.out.println("Error saving exercise data: " + e.getMessage());
        }


    }
    //Gemmer tidligere workouts
    public void saveEarlierWorkouts(String folderPath, List<Exercises> accountEarlierWorkouts) {
        String accountData = folderPath + "/earlierWorkouts.csv";
        File file = new File(accountData);
        file.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(accountData)) {
            if (file.length() == 0) {
                writer.write("workoutName, date\n");
            }
            for (Exercises exercise : accountEarlierWorkouts) {
                writer.write(exercise.getExerciseName() + ","
                        + exercise.getDate() + "\n");
            }

        } catch (IOException e) {
            System.out.println("Error saving exercise data: " + e.getMessage());
        }
    }



    public String getSplit() {
        return split;
    }

    public void setSplit(String split) {
        this.split = split;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }
}
