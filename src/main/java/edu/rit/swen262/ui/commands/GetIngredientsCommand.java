package edu.rit.swen262.ui.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.supercsv.io.CsvListReader;

import edu.rit.swen262.food.Ingredient;
import edu.rit.swen262.food.PantryStock;

public class GetIngredientsCommand extends UserCommand {
    private List<Ingredient[]> ingredientList; 
    
    public GetIngredientsCommand() {
        super.nameString = "Ingredient";
        super.helpString = "Ingredient [Not implemented]";

    }

    private List<Ingredient[]> generateIngredientList(int size) {
        List<Ingredient[]> returnls = new ArrayList<>();

        Set<Ingredient> allIngredients = PantryStock.getAllIngredients().keySet();
        System.out.println(allIngredients.size());
        for (Ingredient ingredient : allIngredients) {
            System.out.println(ingredient.getName());
        }

        return returnls;
    }

    @Override
    public void performAction(String[] commandArgs) {
        generateIngredientList(0);
    }
}
