## Github
* Regler for projekt
  * Ny branch for hver ændring
    * Pull Request branch og få den godkendt af et team-medlem. (Auto-merger den selv efter det)
  * Selvom vi har .gitignore, så for en sikkerheds skyld så kun push relevante filer (Så bliver projektet ikke fyldt med junk, hvis nu .gitignore skulle fejle)
    * .gitignore er en fil inde på repo som bruges til at afvise junk/personalised filer.


## Metode forklaring

**Main**
- Starter programmet. 

**Jframe**
- bibliotek for JFrame. 

**JFrameGUI** - 
Hele frontend delen med visuel brug af Javax.swing. 
- launchGUI() 
  - Launcher vores visuelle del af programmet og kører createGUI(). 
- createModernButton(String) 
  - Bruger JButton til at lave en pænere knap. 
- createModernPanel
  - Bruger JPanel til at lave et pænere panel. 

**(UTIL) ExerciseMapper** - Tilføjer og hiver data fra databasen "data.db"
- exerciseMapper()
  -  Loader database af øvelser ind i et arraylist og gemmer data for træningshistorik. 

**(UTIL) AccountMapper** - Tilføjer og ændrer brugeroplysninger. 
- accountMapper
  - Tilføjer brugeroplysninger emails og passwords (strings) til arraylists og mapper dem i databasen "account"

**LoginPage** - Loginfunktion med en login knap og en registrer knap, hvis login ikke findes kan bruger oprette en ny med den indtastede data. 

- void displayLoginPage()
  - Viser loginsiden, der tager mail og password
- void createAccount
  - Hvis account ikke er lavet med mail, kan en ny laves med password og ny mail
- void login()
  - Logger brugeren ind og sender brugeren videre til menu-page.

**Menu** - Vises altid i venstre side af programmet og har 4 knapper.
- runAlways 
  - Sørger for at Menu altid vises og opdaterer løbende indtastet data.


**Account**
- account(String email, String password, float inputWeight, float inputHeight)
  - Konstruktør med brugers profildata. 
- getters and setters
  - Til fremkaldelse og ændring af profildata. 

**HomePage**
- editTraning
  - Ændrer og slet tidligere træninger. 
- displayTraningHistory
  - Viser tidligere træninger i rækkefølge efter dato. (Viser dato og træningsprogram)

**WorkoutPage**
- workoutPage
  - vælg i mellem 4 forskellige træninger (Push, pull, legs og full body) og starter currentTraining siden. 

**OrderTren**
- orderTren
  - Linker til en hjemmeside hvor man kan bestille trentrombolone. 


**AccountPage**
- displayAccountinfo
  - Viser email i et JField 
- changePassword 
  - Knap der lader dig ændre dit password. Kaldes på i loginPage hvor det kræver at man kan huske sit gamle password for at ændre det. 
- setMeasurements 
  - 2 fields der kan ændres med brugers højde og vægt.
- logOut
  - Knap til at logge ud og komme tilbage til loginPage. 
- validateEmail
  - metode der benyttes i loginPage der validerer at en email indeholder et punktum og et "@" tegn.
- validatePassword
  - Metode der benyttes i loginPage der validerer at et password indeholder 8 tegn, et tal og et stort bogstav.  

**CurrentTraining**
- startTraining
  - køres efter man har valgt et af de 4 træningsprogrammer og åbner siden hvor man kan indtaste reps for sine sets. 
- void endTraining()
  - stopper træningen igen, efter startTraining har startet et program. 
- int edit reps()
  - metode, definerer antal reps under exercises.
- float editKg()
  - metode,der definerer vægten under exercises.
- String editnote()
  - metode, der definerer noten under exercises.

**Exercises** - Klassen hvor alle vores "exercises" (Træningsøvelser) loades fra databaser, så de kan tages frem andre steder i koden.
- void addExerciseToProgram 
  - Tager en exercise (Træningsøvelse) fra databasen, og tilføjer denne ud fra ID-nummer til et program (List)
- exercises(String exerciseName, list<sets> exercise, String note)
  - Dette er konstruktøren til fremvisning af hvilke øvelser og hvor mange sets brugeren er gennemgået. Hertil skal der kunne skrives en note (String note")
- void addSetsToExercise 
  - Tilføjer hvor mange sets der er gennemgået på en given exercise (Træningsøvelse).

**Sets** - Definere hvad sets er 
- sets(int reps, float kg)
  - konstruktør til at definere sets


