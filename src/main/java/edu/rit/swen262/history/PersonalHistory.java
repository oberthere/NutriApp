package edu.rit.swen262.history;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import edu.rit.swen262.user.service.DailyHistoryService;
import edu.rit.swen262.food.Meal;
import edu.rit.swen262.food.PantryStock;
import edu.rit.swen262.workout.IntensityStrategy;
import edu.rit.swen262.workout.Workout;

public final class PersonalHistory {
    private static Map<String, List<DailyHistoryService>> history = new HashMap<>();
    public static final String savedPersonalHistoryFileName = "PersonalHistorySave";


    public static Map<String, List<DailyHistoryService>> getHistory() { return Collections.unmodifiableMap(PersonalHistory.history);}
    
    public static void setHistory(Map<String, List<DailyHistoryService>> historyMap) {PersonalHistory.history = historyMap;}

    /*
     * The DailyHistory added to the history 
     * and stored only at the end of the day.
     */
    public void addDailyHistory(DailyHistoryService dh) {
        String userName = dh.getUserID();
        List<DailyHistoryService> userHistoryRecord = PersonalHistory.history.get(userName);
        userHistoryRecord.add(dh);
        PersonalHistory.history.put(userName, userHistoryRecord);
    }

    public List<DailyHistoryService> getUserHistory(String userID) {
        return PersonalHistory.history.get(userID);
        
    }

    public IntensityStrategy getWorkoutIntensityTrend(String userID) {
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

    public void serializeHistoryToSave() {
        FileOutputStream file;
        try {
            file = new FileOutputStream("./resources/" + savedPersonalHistoryFileName);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(history);
            out.close();
            file.close();
            System.out.println("Personal History has been successfully saved to file");
        } catch (IOException e) {
            System.out.println("Unsuccessful attempt in saving Personal History");
            e.printStackTrace();
        }
    }

    public void deserializeAndLoadSavedHistory() {
        FileInputStream file;
        try {
            file = new FileInputStream("./resources/" + savedPersonalHistoryFileName);
            ObjectInputStream in = new ObjectInputStream(file);
            Map<String, List<DailyHistoryService>> tempHistory = (Map<String, List<DailyHistoryService>>) in.readObject();
            in.close();
            file.close();
            setHistory(tempHistory);
            System.out.println("Personal History has been successfully loaded from file");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Unsuccessful attemp in loading Personal History");
            e.printStackTrace();
        }
    }
}
