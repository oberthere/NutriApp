package edu.rit.swen262.history;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.rit.swen262.team.Team;
import edu.rit.swen262.team.TeamInvite;
import edu.rit.swen262.user.components.DailyHistoryComponent;
import edu.rit.swen262.workout.IntensityStrategy;
import edu.rit.swen262.workout.Workout;

public final class SaveData {
    // public static final String saveDataFileName = "SaveData";
    public static final String saveDataFileName = System.getProperty("nutriapp.savefile", "SaveData");
    private static Map<String, List<DailyHistoryComponent>> history = new HashMap<>();
    private static Map<String, UserData> userDataRecord = new HashMap<>();
    private static PantryRecord pantryRecord = new PantryRecord();
    private static Map<String, TeamData> teamDataRecord = new HashMap<>();

    
    //#region DailyHistory
    public static Map<String, List<DailyHistoryComponent>> getHistory() { return Collections.unmodifiableMap(SaveData.history);}
    public static Map<String, UserData> getUserData() { return Collections.unmodifiableMap(SaveData.userDataRecord);}    
    public static Map<String, TeamData> getTeamData() {return Collections.unmodifiableMap(teamDataRecord);}
    //#endregion

    //#region Users
    public static void addUserData(UserData userDataService) {
        String username = userDataService.getUsername();
        SaveData.userDataRecord.put(username, userDataService);
        serializeHistoryToSave();
    }

    public static void addTeamInviteToUser(TeamInvite invite, String username) {
        UserData userData = userDataRecord.get(username);
        if (userData == null) {
            System.out.println("SaveData: Unable to find user named " + username);
            return;
        }

        userData.addTeamInvite(invite);
        serializeHistoryToSave();
    }
    //#endregion

    //#region Team
    public static void addTeam(Team team) {
        // SaveData.teamDataRecord.put(teamData.getTeamName(), teamData)
    }
    //#endregion
    /*
     * The DailyHistory added to the history
     */
    public static void addDailyHistory(DailyHistoryComponent dailyHistory) {
        String userName = dailyHistory.getUserID();

        // Ensure the user history list exists
        List<DailyHistoryComponent> dailyHistoryRecord = SaveData.history.get(userName);
        if (dailyHistoryRecord == null) {
            dailyHistoryRecord = new ArrayList<>();
            SaveData.history.put(userName, dailyHistoryRecord);
        }

        dailyHistoryRecord.add(dailyHistory);

        // Save data immediately after adding a new history entry
        serializeHistoryToSave();
    }

    public static List<DailyHistoryComponent> getDailyHistory(String userID) {return SaveData.history.get(userID);}

    public static IntensityStrategy getWorkoutIntensityTrend(String userID) {
        Map<IntensityStrategy, Integer> workoutIntensityCollections = new HashMap<>();
        for (DailyHistoryComponent dailyHistory: history.get(userID)) {
            for (Workout workout : dailyHistory.getWorkouts()) {
                IntensityStrategy intensity = workout.getIntensity();
                if (workoutIntensityCollections.containsKey(intensity) == false) {
                    workoutIntensityCollections.put(intensity, 0);
                }
                int currentAmount = workoutIntensityCollections.get(intensity);
                workoutIntensityCollections.put(intensity, currentAmount+1);
            }
       }

       IntensityStrategy returnIntensity = null;
       int highestFrequency = 0;
       for (IntensityStrategy intensityStrategy : workoutIntensityCollections.keySet()) {
            if (workoutIntensityCollections.get(intensityStrategy) > highestFrequency)
            {
                highestFrequency = workoutIntensityCollections.get(intensityStrategy);
                returnIntensity = intensityStrategy;
            }
       }
       return returnIntensity;
    }

    public static void serializeHistoryToSave() {
        try {
            // Ensure the data directory exists inside src/main/resources
            File directory = new File("src/main/resources/data");
            if (!directory.exists()) {
                directory.mkdirs();  // Create directories if they don't exist
            }
            
            // Reloads pantry data
            pantryRecord = new PantryRecord();

            // Save the history data inside the correct folder
            FileOutputStream file = new FileOutputStream("src/main/resources/data/" + saveDataFileName);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(history);
            out.writeObject(userDataRecord);
            out.writeObject(pantryRecord);
            out.writeObject(teamDataRecord);
            out.close();
            file.close();

            System.out.println("Data has been successfully saved to file.");
        } catch (IOException e) {
            System.out.println("Unsuccessful attempt in saving Data.");
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void deserializeFromSave() {
        try {
            File file = new File("src/main/resources/data/" + saveDataFileName);

            // Check if the file exists before trying to read it
            if (!file.exists()) {
                System.out.println("No saved history file found in resources/data. Starting fresh.");
                return;
            }

            FileInputStream fileInput = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileInput);
            Map<String, List<DailyHistoryComponent>> tempHistory = (Map<String, List<DailyHistoryComponent>>) in.readObject();
            Map<String, UserData> tempUserDataRecord = (Map<String, UserData>) in.readObject();
            PantryRecord tempPantryRecord = (PantryRecord) in.readObject();
            Map<String, TeamData> tempTeamDataRecord = (Map<String, TeamData>) in.readObject();
            
            in.close();
            fileInput.close();

            history = tempHistory;
            userDataRecord = tempUserDataRecord;
            pantryRecord = tempPantryRecord;
            teamDataRecord = tempTeamDataRecord;
            // Reloads pantry data
            pantryRecord.readToPantryStock();
            
            System.out.println("Personal History successfully loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Unsuccessful attempt in loading Personal History.");
            e.printStackTrace();
        }
    }
}