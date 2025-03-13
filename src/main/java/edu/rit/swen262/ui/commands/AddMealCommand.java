package edu.rit.swen262.ui.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import edu.rit.swen262.food.Meal;
import edu.rit.swen262.food.MealType;
import edu.rit.swen262.food.PantryStock;
import edu.rit.swen262.food.Recipe;
import edu.rit.swen262.other.exception.InvalidMealCreation;

public class AddMealCommand extends UserCommand {
    public AddMealCommand() {
        super.nameString = "AddMeal";
        super.helpString = "AddMeal [name] [recipeName] [recipeName] ... [breakfast|lunch|dinner]";
    }

    private List<Recipe> getRecipeFromInput(String[] args) throws InvalidMealCreation {
        Map<String, Recipe> recipeRecord = PantryStock.getRecipeRecord();
        List<Recipe> ls = new ArrayList<>();
        boolean raise = false;

        for (int i = 2; i < args.length - 1; i++) {
            String recipeName = args[i];
            if (recipeRecord.containsKey(recipeName) == false) {
                System.out.println("Recipe " + recipeName + " Not Found");
                raise = true;
            }
            ls.add(recipeRecord.get(recipeName));
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
            MealType mealType = MealType.valueOf(commandArgs[commandArgs.length].toUpperCase(Locale.ENGLISH));
            Meal meal = new Meal(name, recipes, mealType);
            System.out.println(mealType);
        } catch (InvalidMealCreation e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
