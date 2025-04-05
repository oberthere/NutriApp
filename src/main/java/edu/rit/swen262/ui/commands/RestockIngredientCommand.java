package edu.rit.swen262.ui.commands;

import edu.rit.swen262.food.Ingredient;
import edu.rit.swen262.food.PantryStock;

public class RestockIngredientCommand extends UndoableCommand {
    public RestockIngredientCommand() {
        super.nameString = "Restock";
        super.helpString = "Restock [IngredientID] [Amount to add]";
    }

    /**
     * Method to select and goes to an user page
     * @param commandArgs Restock [IngredientID] [Amount to add]
     */
    @Override
    public void performAction(String[] commandArgs) throws Exception {
        // Ensure proper length
        if (commandArgs.length != 3) {
            throw new Exception("Error: Invalid number of arguments. Usage: " + getHelp());
        }

        // check that ingredient ID is a number and a valid ID 
        Ingredient ingredient;
        try {
            ingredient = PantryStock.getIngredientByID(Integer.parseInt(commandArgs[1]));
            if (ingredient == null) {
                throw new Exception("Error: Invalid ingredient ID. Usage: " + getHelp());
            }
        } catch (NumberFormatException e) {
            throw new Exception("Error: Ingredient ID must be an number. Usage: " + getHelp());
        }

        // check that the amount is a positive number 
        int amount;
        try {
            amount = Integer.parseInt(commandArgs[2]);
            if (amount <= 0) {
                throw new Exception("Error: Amount must be a positive number. Usage: " + getHelp());
            }
        } catch (NumberFormatException e) {
            throw new Exception("Error: Amount must be a number. Usage: " + getHelp());
        }

        // update the ingredient stock
        PantryStock.updateIngredients(ingredient, amount);
        System.out.println("Sucessfully added " + amount + " stock to " + ingredient.getName());
    }
}