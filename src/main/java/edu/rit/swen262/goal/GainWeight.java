package edu.rit.swen262.goal;

import edu.rit.swen262.user.User;

public class GainWeight implements Goal {
    public int calculateTargetCalories(User user) {
        int calories = 0;
        calories = (int) (user.getGoalService().getTargetWeight() * 15) + 750;
        if (user.getGoalService().isPhysicalFitness()) {
            calories += 400;
        }
        return calories;
    }

    public void changeGoal(User user) {
        if (user.getWeight() == user.getGoalService().getTargetWeight()) {
            user.getGoalService().setGoal(new MaintainWeight());
        }
    }
}
