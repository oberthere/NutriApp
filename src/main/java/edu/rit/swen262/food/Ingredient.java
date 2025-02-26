package edu.rit.swen262.food;

public class Ingredient implements FoodInterface {

    String name;
    int calories;
    double fat;
    double protein;
    double carbs;
    double fiber;
    double stock;

    public Ingredient(
        String name, 
        double stock, 
        int calories, 
        double fat, 
        double protein, 
        double carbs, 
        double fiber) 
    {
        this.name = name;
        this.stock = stock;
        this.calories = calories;
        this.fat = fat;
        this.protein = protein;
        this.carbs = carbs;
        this.fiber = fiber;
        this.stock = stock;

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCalories() {
        return calories;
    }

    @Override
    public double getFat() {
        return fat;
    }

    @Override
    public double getProtein() {
        return protein;
    }

    @Override
    public double getCarbs() {
        return carbs;
    }

    @Override
    public double getFiber() {
        return fiber;
    }

    
}
