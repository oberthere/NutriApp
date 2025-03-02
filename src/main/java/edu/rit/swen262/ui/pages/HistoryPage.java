package edu.rit.swen262.ui.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import edu.rit.swen262.history.PersonalHistory;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.commands.UserCommand;
import edu.rit.swen262.user.User;
import edu.rit.swen262.user.service.DailyHistoryService;

public class HistoryPage extends Page {
    public HistoryPage(PageData pageData) {
        this.pageData = pageData;
        this.pageName = "History";
        this.userCommands = new ArrayList<>(); // Generate commands here
    }

    @Override
    public void printContent() {
        User user = pageData.getCurrentUser();
        List<DailyHistoryService> dhs = PersonalHistory.getUserHistory(user.getName());
        List<DailyHistoryService> sorted = sortRecordByDate(dhs);

        super.printContent();
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
    }

    private void printMeal(List<Meal> meals) {
        for (Meal meal : meals) {
            System.out.println("\t- " + meal.getName());
        }
    }
}

