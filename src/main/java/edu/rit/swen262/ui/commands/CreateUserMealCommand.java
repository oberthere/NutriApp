package edu.rit.swen262.ui.commands;

import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.user.User;
import edu.rit.swen262.user.service.DailyHistoryService;
import edu.rit.swen262.food.Meal;
import edu.rit.swen262.food.MealType;
import edu.rit.swen262.food.Recipe;
import edu.rit.swen262.other.exception.LowStockException;
import edu.rit.swen262.other.exception.NetCaloriesOverflowException;
import edu.rit.swen262.workout.Workout;

import java.util.ArrayList;
import java.util.List;

/**
 * Command to create a user meal as either a prepared or consumed meal.
 * Interacts with the user's DailyHistoryService to track meals.
 */
public class CreateUserMealCommand extends UserCommand {
    private PageData pageData;

    /**
     * Constructor for CreateUserMealCommand
     * @param pageData PageData object containing the current user session
     * @param pageRunner PageRunner manages page commands
     */
    public CreateUserMealCommand(PageData pageData) {
        super.nameString = "CreateUserMeal";
        super.helpString = "CreateUserMeal [mealName] [mealType] [isPrepared]";
        this.pageData = pageData;
    }

    /**
     * Executes the command to create and log user meals.
     * 
     * @param commandArgs Arguments include meal name, type, and whether meal is prepared
     */
    @Override
    public void performAction(String[] commandArgs) {
        // Validate argument count
        if (commandArgs.length < 4) {
            System.out.println("Error: Invalid number of arguments. Usage: CreateUserMeal [mealName] [mealType] [isPrepared]");
            return;
        }

        // get the current logged user
        User user = pageData.getCurrentUser();
        if (user == null) {
            System.out.println("Error: No user selected.");
            return;
        }

        try {
            // Parse meal name and type from command arguments
            String mealName = commandArgs[1];
            MealType mealType = MealType.valueOf(commandArgs[2].toUpperCase()); // Convert to enum
            boolean isPrepared = Boolean.parseBoolean(commandArgs[3]);

            List<Recipe> recipes = new ArrayList<>();
            DailyHistoryService dailyHistoryService = user.getDailyHistoryService();

            if (isPrepared) {
                // Prepares meal (checks the ingredient stock)
                try {
                    dailyHistoryService.prepareMeal(mealName, recipes, mealType);
                    System.out.println("Meal prepared: " + mealName + " for " + mealType);
                } catch (LowStockException e) {
                    System.out.println("Error: Cannot prepare meal due to insufficient ingredients: " + e.getMessage());
                }
            } else {
                // Creates the meal and attempts to consume it
                Meal newMeal = new Meal(mealName, recipes, mealType);
                try {
                    dailyHistoryService.eatMeal(newMeal);
                    System.out.println("Meal consumed: " + mealName + " for " + mealType);
                } catch (NetCaloriesOverflowException e) {
                    // If consuming the meals exceeds the daily target, suggest workout
                    System.out.println("Warning: Eating this meal exceeds your daily calorie target.");
                    Workout suggestedWorkout = dailyHistoryService.suggestWorkout();
                    if (suggestedWorkout != null) {
                        System.out.println("Suggested workout: " + suggestedWorkout.getName() + 
                                           " for " + suggestedWorkout.getDurationMin() + " minutes.");
                    } else {
                        System.out.println("No workout suggestion available.");
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid meal type. Valid options: BREAKFAST, LUNCH, DINNER, SNACK.");
        }
    }
}
