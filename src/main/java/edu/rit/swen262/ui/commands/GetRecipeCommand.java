package edu.rit.swen262.ui.commands;

import edu.rit.swen262.food.PantryStock;
import edu.rit.swen262.food.Recipe;

public class GetRecipeCommand extends UserCommand {
    
    public GetRecipeCommand() {
        super.nameString = "ViewRecipe";
        super.helpString = "ViewRecipe";
    }

    @Override
    public void performAction(String[] commandArgs) {
        // Set<String> nameSet = PantryStock.getRecipeRecord().keySet();
        // System.out.println("A Total Of " + nameSet.size() + " Recipes Were Found:");
        // for (String recipeName : PantryStock.getRecipeRecord().keySet()) {
        //     System.out.println("\t- " + recipeName);
        // }

        System.out.println("A Total Of " + PantryStock.getRecipeIDMap().size() + " Recipes Were Found:");
        for (Recipe recipe : PantryStock.getRecipeIDMap().values()) {
            System.out.println("\t- " + recipe.getName() + " (ID: " + recipe.getID() + ")");
        }
    }
}
