package edu.rit.swen262.goal;

import edu.rit.swen262.user.User;

public class LoseWeight implements Goal {
    private static final long serialVersionUID = 1L;

    @Override
    public void calculateTargetCalories(User user) {
        int calories = 0;
        calories = (int) (user.getGoalComponent().getTargetWeight() * 15) - 750;
        if (user.getGoalComponent().isPhysicalFitness()) {
            calories += 400;
        }
        user.getGoalComponent().setTargetCalories(calories);
    }

    @Override
    public void changeGoal(User user) {
        if (user.getWeight() == user.getGoalComponent().getTargetWeight()) {
            user.getGoalComponent().setGoal(new MaintainWeight());
        }
    }

    @Override 
    public String toString() {return "Lose Weight";}
}