package edu.rit.swen262.csv;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import edu.rit.swen262.food.Ingredient;

public class csvReader {
    public void ingredientReader() {
        try {
            Map<Ingredient, Integer> pantryStock = new HashMap<>();
            List<Ingredient> ingredients = new ArrayList<Ingredient>();
            File file = new File("./resources/data/ingredients.csv"); //file path might be wrong
            Scanner s = new Scanner(file);
            s.nextLine(); //skip header line

            while (s.hasNextLine()) {
                List<String> lineData = Arrays.asList(s.nextLine().split(","));
                //lineData[1] is name, lineData[3] is cals, lineData[5] is fat, lineData[4] is protein, lineData[8] is fiber, lineData[7] is carbs, 
                ingredients.add(new Ingredient(lineData.get(1), 
                                    Integer.parseInt(lineData.get(3)), 
                                    Double.parseDouble(lineData.get(5)), 
                                    Double.parseDouble(lineData.get(4)), 
                                    Double.parseDouble(lineData.get(8)), 
                                    Double.parseDouble(lineData.get(7))));
            }

            for (Ingredient ingredient : ingredients) {
                pantryStock.put(ingredient, 0);
            }

            s.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
