package edu.rit.swen262.food;

import java.util.List;

public class Recipe implements Food {
    private String name;
    private String instructions;
    private List<Ingredient> ingredients;

    public Recipe(String name, List<Ingredient> ingredients, String instructions) {
        this.name = name;
        this.instructions = instructions;
        this.ingredients = ingredients;
    }

    public String getInstructions() {return this.instructions;}
    public List<Ingredient> getIngredients() {return ingredients;}
    @Override public String getName() {return this.name;}

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
