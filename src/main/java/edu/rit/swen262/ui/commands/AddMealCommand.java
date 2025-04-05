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
import edu.rit.swen262.other.exception.LowStockException;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.user.components.DailyHistoryComponent;

public class AddMealCommand extends UndoableCommand<Meal> {
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
    public void performAction(String[] commandArgs) throws Exception {
        if (commandArgs.length < 4) {
            throw new Exception("Error: Invalid number of arguments. Usage: " + getHelp());
        }
        
        try {
            List<Recipe> recipes = getRecipeFromInput(commandArgs);
            String name = commandArgs[1];
            MealType mealType = MealType.valueOf(commandArgs[commandArgs.length - 1].toUpperCase(Locale.ENGLISH));
            
            try {
                DailyHistoryComponent dailyHistory = pageData.getCurrentUser().getDailyHistoryComponent();
                Meal newMeal = dailyHistory.prepareMeal(name, recipes, mealType);
                super.addNextCommandDataToStack(newMeal);
                System.out.println("Successfully added Meal " + newMeal.getName());
            } catch (LowStockException e) {
                throw new Exception("Low Stock Exception. Go to ShoppingList to see the low ingredients");
            }
        } catch (InvalidMealCreation e) {
            throw new Exception("Invalid Meal Creation: Check your arguments and try again. " + getHelp());
        }
    }
    
  @Override
  public void undo() throws Exception {
    DailyHistoryComponent dailyHistory = pageData.getCurrentUser().getDailyHistoryComponent();
    
    if (super.isCommandDataEmpty()) {
      throw new Exception("No created meal to undo");
    }
    Meal meal = super.popLastCommandData();
    dailyHistory.removeMeal(meal);
    System.out.println("Successfully undone creation of Meal " + meal.getName());
  }
}