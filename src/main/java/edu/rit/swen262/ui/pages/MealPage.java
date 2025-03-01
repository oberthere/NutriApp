package edu.rit.swen262.ui.pages;

import java.util.List;

import edu.rit.swen262.food.Meal;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.commands.UserCommand;

public class MealPage extends Page {
    private PageData pageData;

    public MealPage(PageData pageData, List<UserCommand> commands) {
        super.pageName = "Meal";
        this.pageData = pageData;
        super.userCommands = commands;
    }

    @Override
    public void printContent() {
        super.printContent();
        System.out.println("Meals Consumed:");
        for (Meal meal : pageData.getCurrentUser().getDailyHistoryService().getMeals()) {
            System.out.println("\t- " + meal.getName());
        }
    }
}
