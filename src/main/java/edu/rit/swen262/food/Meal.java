package edu.rit.swen262.food;

import java.util.List;

public class Meal implements Food{
    private String mealType; //TODO Convert this into an enum
    private List<Food> recipes;
    private String name;

    public Meal(String name, List<Food> recipes) {
        this.name = name;
        this.recipes = recipes;
    }

    @Override public String getName() {return this.name;}
    
    @Override
    public int getCalories() {
        int calories = 0;
        for (Food food : recipes) {calories += food.getCalories();}
        return calories;
    }

    @Override
    public double getFat() {
        double fat = 0;
        for (Food food: recipes) {fat += food.getFat();}
        return fat;
    }

    @Override
    public double getProtein() {
        double protein = 0;
        for (Food food: recipes) {protein += food.getProtein();}
        return protein;
    }

    @Override
    public double getCarbs() {
        double carbs  = 0;
        for (Food food: recipes) {carbs += food.getProtein();}
        return carbs;
    }

    @Override
    public double getFiber() {
        double fiber = 0;
        for (Food food: recipes) {fiber += food.getProtein();}
        return fiber;
    }


}
