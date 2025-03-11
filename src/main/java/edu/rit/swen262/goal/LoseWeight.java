package edu.rit.swen262.goal;

import edu.rit.swen262.user.User;

public class LoseWeight implements Goal {
    private static final long serialVersionUID = 1L;

    @Override
    public int calculateTargetCalories(User user) {
        int calories = 0;
        calories = (int) (user.getGoalService().getTargetWeight() * 15) - 750;
        if (user.getGoalService().isPhysicalFitness()) {
            calories += 400;
        }
        return calories;
    }

    @Override
    public void changeGoal(User user) {
        if (user.getWeight() == user.getGoalService().getTargetWeight()) {
            user.getGoalService().setGoal(new MaintainWeight());
        }
    }

    @Override public String toString() {return "Lose Weight";}
}
