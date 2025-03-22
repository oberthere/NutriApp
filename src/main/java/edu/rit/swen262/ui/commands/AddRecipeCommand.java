package edu.rit.swen262.ui.commands;

import java.util.ArrayList;
import java.util.List;

import edu.rit.swen262.food.Ingredient;
import edu.rit.swen262.food.PantryStock;
import edu.rit.swen262.food.Recipe;

public class AddRecipeCommand extends UserCommand {
    
    public AddRecipeCommand() {
        super.nameString = "AddRecipe";
        super.helpString = "AddRecipe [Name] [Instruction] [IngredientID] [IngredientID] ...";
    }

    @Override
    public void performAction(String[] commandArgs) {
        if (commandArgs.length < 4) {
            System.out.println("Error: Invalid number of arguments. Usage: " + getHelp());
            return;
        }

        // build the arguments with spaces inside quotes
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
            System.out.println("Error: Mismatched quotes in arguments.");
            return;
        }

        // make srue there is enough args after parsing
        if (parsedArgs.size() < 4) {
            System.out.println("Error: Invalid number of arguments. Usage: " + getHelp());
            return;
        }

        // parse the ingredient IDs
        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 3; i < parsedArgs.size(); i++) {
            try {
                int id = Integer.parseInt(parsedArgs.get(i));
                Ingredient ingredient = PantryStock.getIngredientByID(id);
                ingredients.add(ingredient);
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid ingredient ID: " + parsedArgs.get(i));
                return;
            }
        }

        // add the recipe to PantryStock
        PantryStock.addRecipe(new Recipe(parsedArgs.get(1), ingredients, parsedArgs.get(2)));
        System.out.println("Successfully added Recipe \"" + parsedArgs.get(1) + "\"");
    }
}
