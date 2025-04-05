package edu.rit.swen262.ui.commands;

import edu.rit.swen262.food.PantryStock;
import edu.rit.swen262.food.Recipe;

public class GetRecipeCommand extends UserCommand {
    public GetRecipeCommand() {
        super.nameString = "ViewRecipes";
        super.helpString = "ViewRecipes";
    }

    @Override
    public void performAction(String[] commandArgs) throws Exception {
        if (commandArgs.length != 1) {
            throw new Exception("Error: Invalid number of arguments. Usage: " + getHelp());
        }
        
        System.out.println("A Total Of " + PantryStock.getRecipeIDMap().size() + " Recipes Were Found:");
        for (Recipe recipe : PantryStock.getRecipeIDMap().values()) {
            System.out.println("\t- " + recipe.getName() + " (ID: " + recipe.getID() + ")");
        }
    }
}