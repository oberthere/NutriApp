package edu.rit.swen262.goal;

import edu.rit.swen262.user.User;

public class MaintainWeight implements Goal{
    public int calculateTargetCalories(User user) {
        int calories = 0;
        calories = user.getGoalService().getTargetWeight() * 15;
        if (user.getGoalService().isPhysicalFitness()) {
            calories += 400;
        }
        return calories;
    }

    public void changeGoal(User user) {
        if ((user.getWeight() - user.getGoalService().getTargetWeight()) >= 5) {
            user.getGoalService().setGoal(new LoseWeight());
        }
        else if ((user.getGoalService().getTargetWeight() - user.getWeight()) >= 5) {
            user.getGoalService().setGoal(new GainWeight());
        }
    }
}
