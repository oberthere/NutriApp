package edu.rit.swen262.history;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

// import edu.rit.swen262.history.DailyHistory;
// import edu.rit.swen262.food.Meal;

public class PersonalHistory {
    private List<DailyHistory> history;
    
    public PersonalHistory() {
        history = new ArrayList<>();
    }

    /*
     * The DailyHistory added to the history 
     * and stored only at the end of the day.
     */
    public void addDailyHistory(DailyHistory dh) {
        history.add(dh);
    }

    public Map<Date, Double> getWeightTrend() {
        Map<Date, Double> weightTrend = new HashMap<>();
        for (DailyHistory dh : history) {
            weightTrend.put(dh.getDate(), dh.getWeight());
        }
        return weightTrend;
    }

    public Map<Date, Integer> getCalorieTrend() {
        Map<Date, Integer> calorieTrend = new HashMap<>();
        for (DailyHistory dh : history) {
            calorieTrend.put(dh.getDate(), dh.calculateNetCalories());
        }
        return calorieTrend;
    }

    // public Map<Date, Integer> getWorkoutTrend() {
    //     Map<Date, Integer> workoutTrend = new HashMap<>();
    //     for (DailyHistory dh : history) {
    //         workoutTrend.put(dh.getDate(), dh.getWorkouts().size());
    //     }
    //     return workoutTrend;
    // }

    // public Map<Date, List<Meal>> getMealTrend() {
    //     Map<Date, List<Meal>> mealTrend = new HashMap<>();
    //     for (DailyHistory dh : history) {
    //         mealTrend.put(dh.getDate(), dh.getMeals());
    //     }
    //     return mealTrend;
    // }
}
