package edu.rit.swen262.food;


import java.util.List;

public class Meal implements Food{
    private MealType mealType;
    private List<Recipe> recipes;
    private String name;

    public Meal(String name, List<Recipe> recipes, MealType mealType) {
        this.name = name;
        this.recipes = recipes;
        this.mealType = mealType;
    }

    @Override public String getName() {return this.name;}

    public MealType getMealType() { return mealType; }

    public List<Recipe> getRecipes() {
        return recipes;
    }
    
    @Override
    public int getCalories() {
        int totalCalories = 0;
        for (Food recipe : recipes) { 
            totalCalories += recipe.getCalories();
        }
        return totalCalories;
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
        for (Food food: recipes) {carbs += food.getCarbs();}
        return carbs;
    }

    @Override
    public double getFiber() {
        double fiber = 0;
        for (Food food: recipes) {fiber += food.getFiber();}
        return fiber;
    }


}
