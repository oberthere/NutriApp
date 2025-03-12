package edu.rit.swen262.ui.pages;

import java.util.List;
import edu.rit.swen262.food.Meal;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.commands.GetIngredientsCommand;
import edu.rit.swen262.user.service.DailyHistoryService;

public class MealPage extends Page {
    public MealPage(PageData pageData) {
        super(pageData);
        this.pageName = "Meal";
        super.userCommands.add(new GetIngredientsCommand());
    }

    @Override
    public void printContent() {
        DailyHistoryService dh = pageData.getCurrentUser().getDailyHistoryService();
        super.printContent();
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
