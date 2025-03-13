package edu.rit.swen262.csv;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.supercsv.io.CsvListReader;

import com.opencsv.CSVReader;

import edu.rit.swen262.food.Ingredient;
import edu.rit.swen262.food.PantryStock;
import jakarta.validation.constraints.NotNull;

public class csvReader {
    public void ingredientReader() {
        try {
            Map<Ingredient, Integer> pantryStock = new HashMap<>();
            List<Ingredient> ingredients = new ArrayList<Ingredient>();
            CSVReader reader = new CSVReader(new FileReader("src/main/resources/data/ingredients.csv"));

            List<String[]> allLines = reader.readAll();
            allLines.remove(0);
            for (String[] line : allLines) {
                //lineData[1] is name, lineData[3] is cals, lineData[5] is fat, lineData[4] is protein, lineData[8] is fiber, lineData[7] is carbs, 
                //System.out.println(": " + line[1] + line[3] + line[5] + line[4] + line[8] + line[7]);
                
                for (int i = 0; i < line.length; i++) {
                    if (line[i].length() == 0 || line[i] == null) {line[i] = "0";}
                }

                ingredients.add(new Ingredient(line[1], 
                                    Integer.parseInt(line[3]), 
                                    Double.parseDouble(line[5]), 
                                    Double.parseDouble(line[4]), 
                                    Double.parseDouble(line[8]), 
                                    Double.parseDouble(line[7])));
            }

            System.out.println("A Total of " + ingredients.size() + " Has Been Found");
            
            for (Ingredient ingredient : ingredients) { 
                if (pantryStock.containsKey(ingredient)) {
                    pantryStock.put(ingredient, pantryStock.get(ingredient) + 1);
                    System.out.println("Duplicate of " + ingredient.getName() + " was found");
                }
                else {pantryStock.put(ingredient, 1);}
            }

            PantryStock.updateIngredientRecord(pantryStock);
            reader.close();
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println("*Unable to load in Ingredients");
        }
    }

    public void test() {
        try {
            CSVReader reader = new CSVReader(new FileReader("src/main/resources/data/ingredients.csv"));
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // String returnString = "";
                // for (String string : nextLine) {
                //     returnString += string;
                // }
                // System.out.println(returnString);
                System.out.println(nextLine.length);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
