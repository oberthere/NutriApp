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

        List<Ingredient> ls = new ArrayList<>();

        for (int i = 3; i < commandArgs.length; i++) {
            int id = Integer.parseInt(commandArgs[i]);
            Ingredient ingredient = PantryStock.getIngredientByID(id);
            ls.add(ingredient);
        }

        PantryStock.addRecipe(new Recipe(commandArgs[1], ls, commandArgs[2]));
        System.out.println("Successfully added Recipe " + commandArgs[1]);
    }
}
