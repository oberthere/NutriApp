package edu.rit.swen262.goal;

import edu.rit.swen262.user.User;

public class MaintainWeight implements Goal{
    private static final long serialVersionUID = 1L;

    @Override
    public void calculateTargetCalories(User user) {
        int calories = 0;
        calories = (int) user.getGoalComponent().getTargetWeight() * 15;
        if (user.getGoalComponent().isPhysicalFitness()) {
            calories += 400;
        }
        user.getGoalComponent().setTargetCalories(calories);
    }

    @Override
    public void changeGoal(User user) {
        if ((user.getWeight() - user.getGoalComponent().getTargetWeight()) >= 5) {
            user.getGoalComponent().setGoal(new LoseWeight());
        }
        else if ((user.getGoalComponent().getTargetWeight() - user.getWeight()) >= 5) {
            user.getGoalComponent().setGoal(new GainWeight());
        }
    }

    @Override 
    public String toString() {return "Maintain Weight";}
}