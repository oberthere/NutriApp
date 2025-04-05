package edu.rit.swen262.ui.commands;

import edu.rit.swen262.food.Meal;
import edu.rit.swen262.other.exception.NetCaloriesOverflowException;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.user.components.DailyHistoryComponent;

public class EatMealCommand extends UndoableCommand {
    private PageData pageData;

    public EatMealCommand(PageData pageData) {
        super.nameString = "EatMeal";
        super.helpString = "EatMeal [mealName]";
        this.pageData = pageData;
    }

    @Override
    public void performAction(String[] commandArgs) throws Exception {
        if (commandArgs.length != 2) {
            throw new Exception("Error: Invalid number of arguments. Usage: " + getHelp());
        }
        try {
            DailyHistoryComponent dailyHistory = pageData.getCurrentUser().getDailyHistoryComponent();
            for (Meal meal : dailyHistory.getPreparedMeals()) {
                if (meal.getName().equals(commandArgs[1])) {
                    dailyHistory.eatMeal(meal);
                    System.out.println("Successfully ate Meal " + commandArgs[1]);
                    return;
                }
            }
            throw new Exception("No Meal of that name. Try again with: " + getHelp());
        } catch (NetCaloriesOverflowException e) {
            e.printStackTrace();
        }
    }
}