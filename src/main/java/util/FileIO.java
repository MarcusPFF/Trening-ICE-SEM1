package util;

import app.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    public FileIO() {
        this.accounts = new ArrayList<>();
        this.menu = new Menu();
        this.acc = new Account();
        this.ex = new Exercises();
        this.wo = new Workout();
        this.sets = new Sets();
        String folderPath = "src/data/accountsData/" + acc.getCurrentEmail() + "/";
        String accountPathFile = "src/data/accountsData/Accounts.csv";
        String exercisesPath = "src/data/Exercises.txt";
        String earlierExercisesPath = folderPath + "earlierExercises.txt";
        String lastExercisePath = folderPath + acc.getCurrentEmail() + "_exerciseData" + getSplit();

    }

/*
 Loads user data from a CSV file at the specified file path.
 If the file doesn't exist, it prints an error message and returns an empty list.
 Reads each line of the file, splits it by commas, and creates User objects with the data.
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

    public void saveAccountData(String filePath, List<Account> accounts) {
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(file)) {
            writer.write("Email,Password,Weight,Height\n");
            for (Account account : accounts) {
                if (account.getEmail() == acc.getCurrentEmail()) {
                    writer.write(account.getEmail() + "," + account.getPassword() + "," + acc.getCurrentWeight() + "," + acc.getCurrentHeight() + "\n");
                } else {
                    writer.write(account.getEmail() + "," + account.getPassword() + "," + account.getWeight() + "," + account.getHeight() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved skrivning af brugerdata: " + e.getMessage());
        }
    }

    public List<Exercises> loadEarlierExercises(String earlierExercisesPath) {
        List<Exercises> accountEarlierExerciseData = new ArrayList<>();
        File file = new File(earlierExercisesPath);
        if (!file.exists()) {
            System.out.println("Brugerdatafil ikke fundet: " + earlierExercisesPath);
            return accountEarlierExerciseData;
        }
        try (Scanner scanner = new Scanner(file)) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                if (data.length == 2) {
                    String splitName = data[0].trim();
                    String dateTime = data[1].trim();
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading exercise Data " + e.getMessage());
        }
        return accountEarlierExerciseData;

    }

    public List<Sets> loadLastExercisesData(String lastExercisesPath) {
        List<Sets> accountLastExercisesData = new LinkedList<>();
        File file = new File(lastExercisesPath);
        if (!file.exists()) {
            System.out.println("Brugerdatafil ikke fundet: " + lastExercisesPath);
            return accountLastExercisesData;
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                String[] data = line.split(",");
                if (data.length == 5) {
                    String exericseName = data[0].trim();
                    int setNumber = Integer.parseInt(data[1].trim());
                    int reps = Integer.parseInt(data[2].trim());
                    float weight = Float.parseFloat(data[3].trim());
                    String note = data[4].trim();

                    accountLastExercisesData.add(new Sets(exericseName, setNumber, reps, weight, note));
                }

                if (accountLastExercisesData.size() > 27) {
                    accountLastExercisesData.removeFirst();
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved skrivning af brugerdata: " + e.getMessage());
        }
        return accountLastExercisesData;
    }

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

    public void saveExerciseData(String folderPath, List<Sets> exercises) {
        String accountData = (folderPath + acc.getCurrentEmail() + "_exerciseData" + getSplit());
        File file = new File(accountData);
        file.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(accountData, true)) {
            if (file.length() == 0) {
                writer.write("exerciseName, setNumber, reps, weight, note");
            }
            for (Sets exercise : exercises) {
                writer.write(sets.getExerciseName() + ", " + sets.getSetNumber() + ", " + sets.getReps() + ", " + sets.getWeight() + ", " + sets.getNote());
            }
        } catch (IOException e) {
            System.out.println("Error saving exercise Data " + e.getMessage());
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
