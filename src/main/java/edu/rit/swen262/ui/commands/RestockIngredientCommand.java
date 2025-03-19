package edu.rit.swen262.ui.commands;

import edu.rit.swen262.food.Ingredient;
import edu.rit.swen262.food.PantryStock;

public class RestockIngredientCommand extends UserCommand{

    public RestockIngredientCommand() {
        super.nameString = "Restock";
        super.helpString = "Restock [IngredientID] [Amount to add]";
    }

    /**
     * Method to select and goes to an user page
     * @param commandArgs Restock [IngredientID] [Amount to add]
     */
    @Override
    public void performAction(String[] commandArgs) {
        
        // Ensure proper length
        if (commandArgs.length < 2) {
            System.out.println("Error: Invalid number of arguments. Usage: " + getHelp());
            return;
        }

        Ingredient ingredient = PantryStock.getIngredientByID(Integer.parseInt(commandArgs[1]));
        int amount = Integer.parseInt(commandArgs[2]);
        PantryStock.updateIngredients(ingredient, amount);
        System.out.println("Sucessfully added " + amount + " stock to " + ingredient.getName());
    }
}
