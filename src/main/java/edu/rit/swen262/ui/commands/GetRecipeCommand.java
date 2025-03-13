package edu.rit.swen262.ui.commands;

import java.util.Set;

import edu.rit.swen262.food.PantryStock;

public class GetRecipeCommand extends UserCommand {
    
    public GetRecipeCommand() {
        super.nameString = "ViewRecipe";
        super.helpString = "ViewRecipe";
    }

    @Override
    public void performAction(String[] commandArgs) {
        Set<String> nameSet = PantryStock.getRecipeRecord().keySet();
        System.out.println("A Total Of " + nameSet.size() + " Recipes Were Found:");
        for (String recipeName : PantryStock.getRecipeRecord().keySet()) {
            System.out.println("\t- " + recipeName);
        }
    }
}
