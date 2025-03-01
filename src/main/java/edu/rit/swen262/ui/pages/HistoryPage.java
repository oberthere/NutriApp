package edu.rit.swen262.ui.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.rit.swen262.food.Meal;
import edu.rit.swen262.history.PersonalHistory;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.commands.UserCommand;
import edu.rit.swen262.user.User;
import edu.rit.swen262.user.service.DailyHistoryService;
import edu.rit.swen262.workout.Workout;

public class HistoryPage extends Page {
    private PageData pageData;

    public HistoryPage(PageData pageData, List<UserCommand> commands) {
        super.pageName = "History";
        this.pageData = pageData;
        super.userCommands = commands;
    }

    @Override
    public void printContent() {
        User user = pageData.getCurrentUser();
        List<DailyHistoryService> dhs = PersonalHistory.getUserHistory(user.getName());
        List<DailyHistoryService> sortted = sortRecordByDate(dhs);

        super.printContent();
        for (DailyHistoryService dailyHistoryService : sortted) {
            printDailyHistoryRecord(dailyHistoryService);
            System.out.println();
        }

    }

    private List<DailyHistoryService> sortRecordByDate(List<DailyHistoryService> dhs) {
        Collections.sort(dhs, new Comparator<DailyHistoryService>() {
            @Override
            public int compare(DailyHistoryService arg0, DailyHistoryService arg1) {
                if (arg0.getDate().getTime() == arg1.getDate().getTime()) {return 0;}
                else if (arg0.getDate().getTime() > arg1.getDate().getTime()){return 1;}
                else {return -1;}
            }
        });
        return dhs;
    }

    private void printDailyHistoryRecord(DailyHistoryService dh)
    {
        System.out.println("Activity Info For " + dh.getDate() + " :");
        System.out.println("Net Calories Consumed: " + dh.getNetCalories());
        System.out.println("Meals Prepared:");
        printMeal(dh.getPreparedMeals());
        System.out.println("Meals Consumed:");
        printMeal(dh.getEatenMeals());
        System.out.println("Workouts Recorded:");
        printWorkouts(dh.getWorkouts());
        System.out.println();
    }

    private void printMeal(List<Meal> meals) {
        for (Meal meal : meals) {
            System.out.println("\t- " + meal.getName());
        }
    }

    private void printWorkouts(List<Workout> workouts) {
        for (Workout workout : workouts) {
            System.out.println("\t- " + workout.getName());
        }
    }
}
