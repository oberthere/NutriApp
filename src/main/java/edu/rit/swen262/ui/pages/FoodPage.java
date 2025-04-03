package edu.rit.swen262.ui.pages;

import java.util.List;
import edu.rit.swen262.food.Meal;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.commands.AddMealCommand;
import edu.rit.swen262.ui.commands.AddRecipeCommand;
import edu.rit.swen262.ui.commands.EatMealCommand;
import edu.rit.swen262.ui.commands.GetIngredientsCommand;
import edu.rit.swen262.ui.commands.GetRecipeCommand;
import edu.rit.swen262.user.service.DailyHistoryComponent;

public class FoodPage extends Page {
    public FoodPage(PageData pageData) {
        super(pageData);
        this.pageName = "Food Page";
        super.userCommands.add(new GetIngredientsCommand());
        super.userCommands.add(new AddRecipeCommand());
        super.userCommands.add(new GetRecipeCommand());
        super.userCommands.add(new AddMealCommand(pageData));
        super.userCommands.add(new EatMealCommand(pageData));
    }

    @Override
    public void printContent() {
        DailyHistoryComponent dh = pageData.getCurrentUser().getUserHistoryService();
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
