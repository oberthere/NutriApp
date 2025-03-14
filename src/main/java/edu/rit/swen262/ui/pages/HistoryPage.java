package edu.rit.swen262.ui.pages;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import edu.rit.swen262.food.Meal;
import edu.rit.swen262.history.PersonalHistory;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.user.User;
import edu.rit.swen262.user.service.DailyHistoryService;
import edu.rit.swen262.workout.Workout;

public class HistoryPage extends Page {
    public HistoryPage(PageData pageData) {
        super(pageData);
        this.pageName = "History";
        this.userCommands = new ArrayList<>(); // Generate commands here
    }

    @Override
    public void printContent() {
        User user = pageData.getCurrentUser();
        List<DailyHistoryService> dhs = PersonalHistory.getUserHistory(user.getName());
        List<DailyHistoryService> sorted = sortRecordByDate(dhs);

        super.printContent();

        // Print Weight History
        printWeightHistory(user);
        
        for (DailyHistoryService dailyHistoryService : sorted) {
            printDailyHistoryRecord(dailyHistoryService);
            System.out.println();
        }
    }

    private List<DailyHistoryService> sortRecordByDate(List<DailyHistoryService> dhs) {
        dhs.sort(Comparator.comparing(d -> d.getDate().getTime()));
        return dhs;
    }

    private void printDailyHistoryRecord(DailyHistoryService dh) {
        System.out.println("Activity Info For " + dh.getDate() + " :");
        System.out.println("Net Calories Consumed: " + dh.getNetCalories());
        System.out.println("Meals Prepared:");
        printMeal(dh.getPreparedMeals());
        System.out.println("Meals Consumed:");
        printMeal(dh.getEatenMeals());
        
        System.out.println("Weight: " + "\n" + dh.getWeight() + " lbs"); // show previous weight

        System.out.println("Workouts Recorded:"); // Workout Details
        for (Workout workout : dh.getWorkouts()) {
            System.out.println("\t- " + workout.getRecordedDate() + " | " + workout.getIntensity() + " | " 
                            + workout.getDurationMin() + " min (" + workout.getCaloriesBurned() + " kcal)");
        }

    }

    private void printWeightHistory(User user) {
        List<DailyHistoryService> historyRecords = PersonalHistory.getUserHistory(user.getName());

        if (historyRecords == null || historyRecords.isEmpty()) {
            System.out.println("No weight history available.");
            return;
        }

        System.out.println("Weight History: ");
        for (DailyHistoryService dh : historyRecords) {
            System.out.println("\t- " + dh.getDate() + " | Weight: " + dh.getWeight() + " lbs");
        }

    }   

    private void printMeal(List<Meal> meals) {
        for (Meal meal : meals) {
            System.out.println("\t- " + meal.getName() + " (" + meal.getCalories() + " kcal)");
        }
    }
}

