package edu.rit.swen262.ui.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import edu.rit.swen262.food.MealType;
import edu.rit.swen262.food.PantryStock;
import edu.rit.swen262.food.Recipe;
import edu.rit.swen262.other.exception.InvalidMealCreation;
import edu.rit.swen262.other.exception.LowStockException;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.user.service.UserHistoryService;

public class AddMealCommand extends UserCommand {
    private PageData pageData;

    public AddMealCommand(PageData pageData) {
        super.nameString = "AddMeal";
        super.helpString = "AddMeal [name] [recipeID] [recipeID] ... [breakfast|lunch|dinner]";
        this.pageData = pageData;
    }

    private List<Recipe> getRecipeFromInput(String[] args) throws InvalidMealCreation {
        Map<Integer, Recipe> recipeIDMap = PantryStock.getRecipeIDMap();
        List<Recipe> ls = new ArrayList<>();
        boolean raise = false;

        for (int i = 2; i < args.length - 1; i++) {
            Integer recipeID = Integer.valueOf(args[i]);

            if (recipeIDMap.containsKey(recipeID) == false) {
                System.out.println("Recipe ID " + recipeID + " Not Found");
                raise = true;
            }
            ls.add(recipeIDMap.get(recipeID));
        }

        if (raise) {throw new InvalidMealCreation("Unable to Create Meal");}
        return ls;
    }

    @Override
    public void performAction(String[] commandArgs) {
        if (commandArgs.length < 4) {
            System.out.println("Error: Invalid number of arguments. Usage: " + getHelp());
            return;
        }
        
        try {
            List<Recipe> recipes = getRecipeFromInput(commandArgs);
            String name = commandArgs[1];
            MealType mealType = MealType.valueOf(commandArgs[commandArgs.length - 1].toUpperCase(Locale.ENGLISH));
            
            try {
                UserHistoryService userHistory = pageData.getCurrentUser().getUserHistoryService();
                userHistory.prepareMeal(name, recipes, mealType);
                System.out.println("Successfully added Meal " + commandArgs[1]);
            } catch (LowStockException e) {
                System.out.println("Low Stock Exception. Go to ShoppingList to see the low ingredients");
            }
        } catch (InvalidMealCreation e) {
            System.out.println("Invalid Meal Creation: Check your arguments and try again. " + getHelp());
        }
    }
}
