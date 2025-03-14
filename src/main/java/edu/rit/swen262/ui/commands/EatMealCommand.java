package edu.rit.swen262.ui.commands;

import edu.rit.swen262.food.Meal;
import edu.rit.swen262.other.exception.NetCaloriesOverflowException;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.user.service.DailyHistoryService;

public class EatMealCommand extends UserCommand {
    private PageData pageData;

    public EatMealCommand(PageData pageData) {
        super.nameString = "EatMeal";
        super.helpString = "EatMeal [mealName]";
        this.pageData = pageData;
    }

    @Override
    public void performAction(String[] commandArgs) {
        if (commandArgs.length != 2) {
            System.out.println("Error: Invalid number of arguments. Usage: " + getHelp());
            return;
        }
        try {
            DailyHistoryService dailyHistory = pageData.getCurrentUser().getDailyHistoryService();
            for (Meal meal : dailyHistory.getPreparedMeals()) {
                if (meal.getName().equals(commandArgs[1])) {
                    dailyHistory.eatMeal(meal);
                    System.out.println("Successfully ate Meal " + commandArgs[1]);
                    return;
                }
            }
            System.out.println("No Meal of that name. Try again with: " + getHelp());
        } catch (NetCaloriesOverflowException e) {
            e.printStackTrace();
        }
    }
}
