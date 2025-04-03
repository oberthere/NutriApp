package edu.rit.swen262.food;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Food, Serializable {
    private static int serial = 0;
    private final int id;
    private String name;
    private List<Ingredient> ingredients;
    private String instructions;

    public Recipe(String name, List<Ingredient> ingredients, String instructions) {
        this.id = serial++;
        this.name = name;
        this.instructions = instructions;
        this.ingredients = ingredients;
    }

    public int getID() {return this.id;}

    @Override 
    public String getName() {return this.name;}

    public List<Ingredient> getIngredients() {return ingredients;}

    public String getInstructions() {return this.instructions;}

    @Override
    public int getCalories() {
        int calories = 0;
        for (Food food : ingredients) {calories += food.getCalories();}
        return calories;
    }

    @Override
    public double getFat() {
        double fat = 0;
        for (Food food : ingredients) {fat += food.getFat();}
        return fat;
    }

    @Override
    public double getProtein() {
        double protein = 0;
        for (Food food : ingredients) {protein += food.getProtein();}
        return protein;
    }

    @Override
    public double getCarbs() {
        double carbs = 0;
        for (Food food : ingredients) {carbs += food.getCarbs();}
        return carbs;
    }

    @Override
    public double getFiber() {
        double fiber = 0;
        for (Food food : ingredients) {fiber += food.getFiber();}
        return fiber;
    }
}