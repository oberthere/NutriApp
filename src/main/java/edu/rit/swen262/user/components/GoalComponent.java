package edu.rit.swen262.user.components;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import edu.rit.swen262.goal.*;

public class GoalComponent implements Serializable { 
    private static final long serialVersionUID = 1L;  

    private transient Goal currentGoal;  
    private boolean physicalFitness;
    private int targetCalories;
    private double targetWeight;

    public GoalComponent(boolean physicalFitness, double targetWeight, double currentWeight) {
        this.physicalFitness = physicalFitness;
        this.targetWeight = targetWeight;
        calculateGoal(targetWeight, currentWeight);
        setDefaultCalories();
    }

    public Goal getCurrentGoal() {return this.currentGoal;}
    public boolean isPhysicalFitness() {return this.physicalFitness;}
    public int getTargetCalories() {return this.targetCalories;}
    public double getTargetWeight() {return this.targetWeight;}

    public void setGoal(Goal newGoal) {this.currentGoal = newGoal;}
    public void setTargetCalories(int targetCalories) {this.targetCalories = targetCalories;}
    public void setTargetWeight(double targetWeight, double currentWeight) {
        this.targetWeight = targetWeight;
        calculateGoal(targetWeight, currentWeight);
        setDefaultCalories();
    }

    private void calculateGoal(double targetWeight, double currentWeight) {
        if (targetWeight == currentWeight) {
            currentGoal = new MaintainWeight();
        } else if (targetWeight < currentWeight) {
            currentGoal = new LoseWeight();
        } else {
            currentGoal = new GainWeight();
        }
    }

    private void setDefaultCalories() {
        if (currentGoal instanceof MaintainWeight) {
            this.targetCalories = 2000;
        } else if (currentGoal instanceof LoseWeight) {
            this.targetCalories = 1800;
        } else if (currentGoal instanceof GainWeight) {
            this.targetCalories = 2500;
        }
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(currentGoal.getClass().getSimpleName());  // Save goal as String
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        String goalType = (String) ois.readObject();
        
        switch (goalType) {
            case "GainWeight":
                this.currentGoal = new GainWeight();
                break;
            case "LoseWeight":
                this.currentGoal = new LoseWeight();
                break;
            case "MaintainWeight":
                this.currentGoal = new MaintainWeight();
                break;
            default:
                this.currentGoal = new MaintainWeight();  // Default fallback
        }
    }
}