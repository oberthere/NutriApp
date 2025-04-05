package edu.rit.swen262.ui.commands;

import java.util.ArrayList;
import java.util.List;

import edu.rit.swen262.food.Ingredient;
import edu.rit.swen262.food.PantryStock;
import edu.rit.swen262.food.Recipe;

public class AddRecipeCommand extends UndoableCommand<Recipe> {

    public AddRecipeCommand() {
        super.nameString = "AddRecipe";
        super.helpString = "AddRecipe [Name] [Instruction] [IngredientID] [IngredientID] ...";
    }

    @Override
    public void performAction(String[] commandArgs) throws Exception {
        if (commandArgs.length < 4) {
            throw new Exception("Error: Invalid number of arguments. Usage: " + getHelp());
        }

        // build the args with spaces inside quotes
        // only instructions args should be inside quotes with spaces
        List<String> parsedArgs = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (String arg : commandArgs) {
            if (arg.startsWith("\"") && !inQuotes) {
                inQuotes = true;
                current.append(arg.substring(1)); // removes the opening quote
            } else if (arg.endsWith("\"") && inQuotes) {
                inQuotes = false;
                current.append(" ").append(arg, 0, arg.length() - 1); // removes the closing quote
                parsedArgs.add(current.toString());
                current = new StringBuilder();
            } else if (inQuotes) {
                current.append(" ").append(arg);
            } else {
                parsedArgs.add(arg);
            }
        }

        if (inQuotes) {
            throw new Exception("Error: Mismatched quotes in arguments.");
        }

        // make sure there is enough args after parsing
        if (parsedArgs.size() < 4) {
            throw new Exception("Error: Invalid number of arguments. Usage: " + getHelp());
        }

        // parse the ingredient IDs
        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 3; i < parsedArgs.size(); i++) {
            try {
                int id = Integer.parseInt(parsedArgs.get(i));
                Ingredient ingredient = PantryStock.getIngredientByID(id);
                ingredients.add(ingredient);
            } catch (NumberFormatException e) {
                throw new Exception("Error: Invalid ingredient ID: " + parsedArgs.get(i));
            }
        }

        // add the recipe to PantryStock
        Recipe createdRecipe = new Recipe(parsedArgs.get(1), ingredients, parsedArgs.get(2));
        PantryStock.addRecipe(createdRecipe);
        super.addNextCommandDataToStack(createdRecipe);
        System.out.println("Successfully added Recipe \"" + parsedArgs.get(1) + "\"");
    }


    @Override
    public void undo() throws Exception {
      if (super.isCommandDataEmpty()) {
        throw new Exception("Error: No recipe to undo.");
      }
      Recipe lastRecipeAdded = super.popLastCommandData();
      PantryStock.removeRecipe(lastRecipeAdded);
      System.out.println("Successfully undone `Add Recipe \"" + lastRecipeAdded.getName() + "\"");
    }
}
