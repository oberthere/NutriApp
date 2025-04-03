package edu.rit.swen262.csv;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opencsv.CSVReader;

import edu.rit.swen262.food.Ingredient;
import edu.rit.swen262.food.PantryStock;

public class csvReader {
    public void ingredientReader() {
        try {
            Integer id = 0;
            Map<Ingredient, Integer> pantryStock = new HashMap<>();
            List<Ingredient> ingredients = new ArrayList<Ingredient>();
            CSVReader reader = new CSVReader(new FileReader("src/main/resources/data/ingredients.csv"));
            Set<String> allIngredientName = new HashSet<>();
            List<String[]> allLines = reader.readAll();
            
            allLines.remove(0);
            for (String[] line : allLines) {
                //lineData[0] is id, lineData[1] is name, lineData[3] is cals, lineData[5] is fat, lineData[4] is protein, lineData[8] is fiber, lineData[7] is carbs, 
                // System.out.println(": " + line[1] + line[3] + line[5] + line[4] + line[8] + line[7]);
                
                for (int i = 0; i < line.length; i++) {
                    if (line[i].length() == 0 || line[i] == null) {line[i] = "0";}
                }

                String rawName = line[1];
                String cutName = rawName.split(",")[0].split(" ")[0].toLowerCase();

                if (allIngredientName.contains(cutName) == false)
                {
                    ingredients.add(new Ingredient(
                                    id++,
                                    cutName, 
                                    Integer.parseInt(line[3]), 
                                    Double.parseDouble(line[5]), 
                                    Double.parseDouble(line[4]), 
                                    Double.parseDouble(line[8]), 
                                    Double.parseDouble(line[7])));
                    
                    allIngredientName.add(cutName);
                }
                
            }

            System.out.println("A Total of " + ingredients.size() + " Ingredients Has Been Found");
            
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
}