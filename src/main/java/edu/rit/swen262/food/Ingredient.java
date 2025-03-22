package edu.rit.swen262.food;

import java.io.Serializable;

public class Ingredient implements Food, Serializable {

    int id;
    String name;
    int calories;
    double fat;
    double protein;
    double carbs;
    double fiber;

    public Ingredient(
        int id,
        String name, 
        int calories, 
        double fat, 
        double protein, 
        double carbs, 
        double fiber) 
    {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.fat = fat;
        this.protein = protein;
        this.carbs = carbs;
        this.fiber = fiber;

    }

    public int getID() {return this.id;}

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

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Ingredient) {
            Ingredient other = (Ingredient) obj;
            return this.name.equals(other.getName());
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "[#" + this.getID() + "] " + this.getName() + " [Calories: " + this.getCalories() + ", Fat: " + this.getFat() + "g, Protein: " + this.getProtein() + "g, Carbs: " + this.getCarbs() + "g, Fiber: " + this.getFiber() + "g]";
    }
}
