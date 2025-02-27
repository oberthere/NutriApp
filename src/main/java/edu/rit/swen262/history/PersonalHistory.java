package edu.rit.swen262.history;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Map;

import edu.rit.swen262.user.service.DailyHistoryService;

import java.util.HashMap;

import edu.rit.swen262.food.Meal;

public class PersonalHistory {
    private List<DailyHistoryService> history;
    
    public PersonalHistory() {
        history = new ArrayList<>();
    }

    public List<DailyHistoryService> getHistory() { return history; }
    /*
     * The DailyHistory added to the history 
     * and stored only at the end of the day.
     */
    public void addDailyHistory(DailyHistoryService dh) {
        history.add(dh);
    }

    public Map<Date, Double> getWeightTrend() {
        Map<Date, Double> weightTrend = new HashMap<>();
        for (DailyHistoryService dh : history) {
            weightTrend.put(dh.getDate(), dh.getWeight());
        }
        return weightTrend;
    }

    public Map<Date, Integer> getCalorieTrend() {
        Map<Date, Integer> calorieTrend = new HashMap<>();
        for (DailyHistoryService dh : history) {
            calorieTrend.put(dh.getDate(), dh.calculateNetCalories());
        }
        return calorieTrend;
    }

    // uncomment when Workout is implemented itself and in DailyHistoryService
    // public Map<Date, Integer> getWorkoutTrend() {
    //     Map<Date, Integer> workoutTrend = new HashMap<>();
    //     for (DailyHistoryService dh : history) {
    //         workoutTrend.put(dh.getDate(), dh.getWorkouts().size());
    //     }
    //     return workoutTrend;
    // }

    public Map<Date, List<Meal>> getMealTrend() {
        Map<Date, List<Meal>> mealTrend = new HashMap<>();
        for (DailyHistoryService dh : history) {
            mealTrend.put(dh.getDate(), dh.getMeals());
        }
        return mealTrend;
    }
}
