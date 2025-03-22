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

import edu.rit.swen262.food.PantryRecord;
import edu.rit.swen262.user.service.DailyHistoryService;
import edu.rit.swen262.user.service.UserDataService;
import edu.rit.swen262.workout.IntensityStrategy;
import edu.rit.swen262.workout.Workout;

public final class SaveData {
    private static Map<String, List<DailyHistoryService>> history = new HashMap<>();
    private static Map<String, UserDataService> userData = new HashMap<>();
    private static PantryRecord pantryRecord = new PantryRecord();

    public static final String saveDataFileName = "SaveData";

    public static Map<String, UserDataService> getUserData() { return Collections.unmodifiableMap(SaveData.userData);}
    public static Map<String, List<DailyHistoryService>> getHistory() { return Collections.unmodifiableMap(SaveData.history);}

    public static void setHistory(Map<String, List<DailyHistoryService>> historyMap) {SaveData.history = historyMap;}

    public static void addUserData(UserDataService userDataService) {
        String username = userDataService.getUsername();
        SaveData.userData.put(username, userDataService);
        serializeHistoryToSave();
    }

    /*
     * The DailyHistory added to the history
     * and stored only at the end of the day.
     */
    public static void addDailyHistory(DailyHistoryService dh) {
        String userName = dh.getUserID();

        // Ensure the user history list exists
        List<DailyHistoryService> userHistoryRecord = SaveData.history.get(userName);
        if (userHistoryRecord == null) {
            userHistoryRecord = new ArrayList<>();
            SaveData.history.put(userName, userHistoryRecord);
        }

        userHistoryRecord.add(dh);

        // Save data immediately after adding a new history entry
        serializeHistoryToSave();
    }

    public static List<DailyHistoryService> getUserHistory(String userID) {
        return SaveData.history.get(userID);
    }

    public static IntensityStrategy getWorkoutIntensityTrend(String userID) {
        Map<IntensityStrategy, Integer> workoutIntensityCollections = new HashMap<>();
        for (DailyHistoryService dh: history.get(userID)) {
            for (Workout workout : dh.getWorkouts()) {
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
            out.writeObject(userData);
            out.writeObject(pantryRecord);
            out.close();
            file.close();

            System.out.println("Personal History has been successfully saved to file.");
        } catch (IOException e) {
            System.out.println("Unsuccessful attempt in saving Personal History.");
            e.printStackTrace();
        }
    }


    @SuppressWarnings("unchecked")
    public static void deserializeAndLoadSavedHistory() {
        try {
            File file = new File("src/main/resources/data/" + saveDataFileName);

            // Check if the file exists before trying to read it
            if (!file.exists()) {
                System.out.println("No saved history file found in resources/data. Starting fresh.");
                return;
            }

            FileInputStream fileInput = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileInput);
            Map<String, List<DailyHistoryService>> tempHistory = (Map<String, List<DailyHistoryService>>) in.readObject();
            Map<String, UserDataService> tempUserData = (Map<String, UserDataService>) in.readObject();
            PantryRecord tempPantryRecord = (PantryRecord) in.readObject();
            in.close();
            fileInput.close();

            setHistory(tempHistory);
            userData = tempUserData;
            pantryRecord = tempPantryRecord;
            // Reloads pantry data
            pantryRecord.readToPantryStock();
            
            System.out.println("Personal History successfully loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Unsuccessful attempt in loading Personal History.");
            e.printStackTrace();
        }
    }



}
