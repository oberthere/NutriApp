package edu.rit.swen262.service;

import edu.rit.swen262.goal.GainWeight;
import edu.rit.swen262.goal.Goal;
import edu.rit.swen262.goal.LoseWeight;
import edu.rit.swen262.goal.MaintainWeight;

public class GoalService {
    private Goal currentGoal;
    private boolean physicalFitness;
    private int targetCalories;
    private int targetWeight;

    public GoalService(boolean physicalFitness, int targetWeight, int currentWeight) {
        this.physicalFitness = physicalFitness;
        this.targetWeight = targetWeight;
        calculateGoal(targetWeight, currentWeight);
    }

    private void calculateGoal(int targetWeight, int currentWeight) {
        if (targetWeight == currentWeight) {
            currentGoal = new MaintainWeight();
        }
        else if (targetWeight > currentWeight) {
            currentGoal = new LoseWeight();
        }
        else {
            currentGoal = new GainWeight();
        }
    }

    public void setGoal(Goal newGoal) {
        currentGoal = newGoal;
    }

    public Goal getCurrentGoal() {
        return this.currentGoal;
    }

    public boolean isPhysicalFitness() {
        return this.physicalFitness;
    }

    public int getTargetCalories() {
        return this.targetCalories;
    }

    public int getTargetWeight() {
        return this.targetWeight;
    }
}
