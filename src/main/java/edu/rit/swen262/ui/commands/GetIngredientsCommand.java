package edu.rit.swen262.ui.commands;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.rit.swen262.food.Ingredient;
import edu.rit.swen262.food.PantryStock;

public class GetIngredientsCommand extends UserCommand {
    private List<Ingredient[]> ingredientList; 
    private Map<Ingredient, Integer> tempIngredientRecord;
    
    public GetIngredientsCommand() {
        super.nameString = "Ingredient";
        super.helpString = "Ingredient [IngredientID / Name | Page [0-83]]";
        this.tempIngredientRecord = PantryStock.getAllIngredients();

    }

    private void updateIngredientList(int size) {
        this.tempIngredientRecord = PantryStock.getAllIngredients();
        List<Ingredient[]> returnls = new ArrayList<>();
        
        Set<Integer> idSet = PantryStock.getIngredientIDMap().keySet();
        
        List<Integer> allID = new ArrayList<>();
        allID.addAll(idSet);
        allID.sort(Comparator.reverseOrder());

        List<Ingredient> allIngredients = new ArrayList<>();
        for (Integer id : allID) {
            allIngredients.add(PantryStock.getIngredientByID(id));
        }

        int totalIngredients = allIngredients.size();
        int numberOfGroups;
        if (totalIngredients % size == 0) {numberOfGroups = totalIngredients / size;}
        else {numberOfGroups = ((int) (totalIngredients / size)) + 1;}

        for (int i = 0; i < numberOfGroups; i++) {
            Ingredient[] group = new Ingredient[size];
            
            for (int j = 0; j < group.length; j++) {
                if (allIngredients.size() != 0 && allIngredients.get(allIngredients.size()-1) != null) {
                    group[j] = allIngredients.remove(allIngredients.size()-1);
                } else {break;}
            }
            returnls.add(group);
        }

        this.ingredientList = returnls;
    }

    private void printIngredientPage(int pageNum) {
        System.out.println("A Total of " + this.tempIngredientRecord.size() + " Unique Ingredients Was Recorded");
        System.out.println("Currently Viewing Page " + pageNum + "/" + (this.ingredientList.size() - 1));
        for (Ingredient ingredient : ingredientList.get(pageNum)) {
            if (ingredient != null) {
                System.out.println("\t- [#" + ingredient.getID() + "] " + ingredient.getName() + ": " + this.tempIngredientRecord.get(ingredient));
            }
        }
    }

    @Override
    public String getHelp() {
        if (ingredientList == null) {updateIngredientList(10);}
        String returnString = "Ingredient [IngredientID / Name | ";
        returnString += "Page [0-" + (this.ingredientList.size()-1) + "]]";
        
        return returnString;
    }

    @Override
    public void performAction(String[] commandArgs) {
        if (commandArgs.length < 2) {
            System.out.println("Error: Invalid number of arguments. Usage: " + getHelp());
            return;
        }

        if (commandArgs.length < 3) {
            Ingredient search;
            int stock;
            try {
                Integer.parseInt(commandArgs[1]);
                search = PantryStock.getIngredientByID(Integer.parseInt(commandArgs[1]));
                stock = PantryStock.getIngredientCountByID(Integer.parseInt(commandArgs[1]));
            }
            catch (NumberFormatException e) {
                search = PantryStock.getIngredientByName(commandArgs[1].toLowerCase());
                stock = PantryStock.getIngredientCountByName(commandArgs[1].toLowerCase());
            }
            if (search == null || stock == 0) {
                System.out.println("Error: That ingredient could not be found. Try again: " + getHelp());
            } else {System.out.println("\t- " + search + " [Stock: " + stock + "]");}
        } else {
            updateIngredientList(10);

            int pageNumber = Integer.parseInt(commandArgs[2]);
            
            if (pageNumber >= ingredientList.size() || pageNumber < 0) {
                System.out.println("Error: Invalid pageNumber. Usage: " + getHelp());
                return;
            }
            
            printIngredientPage(pageNumber);
        }
    }
}
