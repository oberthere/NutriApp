package edu.rit.swen262.ui.pages;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import edu.rit.swen262.food.Meal;
import edu.rit.swen262.history.SaveData;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.user.User;
import edu.rit.swen262.user.components.DailyHistoryComponent;
import edu.rit.swen262.workout.Workout;

public class HistoryPage extends Page {
    public HistoryPage(PageData pageData) {
        super(pageData);
        this.pageName = "History Page";
        this.userCommands = new ArrayList<>(); // Generate commands here
    }

    @Override
    public void printContent() {
        User user = pageData.getCurrentUser();
        List<DailyHistoryComponent> dailyHistoryComponents = SaveData.getDailyHistory(user.getName());
        List<DailyHistoryComponent> sorted = sortRecordByDate(dailyHistoryComponents);

        super.printContent();

        // Print Weight History
        printWeightHistory(user);
        
        for (DailyHistoryComponent dailyHistoryComponent : sorted) {
            printDailyHistoryRecord(dailyHistoryComponent);
            System.out.println();
        }
    }

    private List<DailyHistoryComponent> sortRecordByDate(List<DailyHistoryComponent> dailyHistoryComponents) {
        dailyHistoryComponents.sort(Comparator.comparing(d -> d.getDate().getTime()));
        return dailyHistoryComponents;
    }

    private void printDailyHistoryRecord(DailyHistoryComponent dailyHistory) {
        System.out.println("Activity Info For " + dailyHistory.getDate() + " :");
        System.out.println("Net Calories Consumed: " + dailyHistory.getNetCalories());
        System.out.println("Meals Prepared:");
        printMeal(dailyHistory.getPreparedMeals());
        System.out.println("Meals Consumed:");
        printMeal(dailyHistory.getEatenMeals());
        
        System.out.println("Weight: " + "\n" + dailyHistory.getWeight() + " lbs"); // show previous weight

        System.out.println("Workouts Recorded:"); // Workout Details
        for (Workout workout : dailyHistory.getWorkouts()) {
            System.out.println("\t- " + workout.getRecordedDate() + " | " + workout.getIntensity() + " | " 
                            + workout.getDurationMin() + " min (" + workout.getCaloriesBurned() + " kcal)");
        }
    }

    private void printWeightHistory(User user) {
        List<DailyHistoryComponent> historyRecords = SaveData.getDailyHistory(user.getName());

        if (historyRecords == null || historyRecords.isEmpty()) {
            System.out.println("No weight history available.");
            return;
        }

        System.out.println("Weight History: ");
        for (DailyHistoryComponent dailyHistory : historyRecords) {
            System.out.println("\t- " + dailyHistory.getDate() + " | Weight: " + dailyHistory.getWeight() + " lbs");
        }
    }   

    private void printMeal(List<Meal> meals) {
        for (Meal meal : meals) {
            System.out.println("\t- " + meal.getName() + " (" + meal.getCalories() + " kcal)");
        }
    }
}