package edu.rit.swen262.goal;

import edu.rit.swen262.user.User;

public class MaintainWeight implements Goal{
    private static final long serialVersionUID = 1L;

    @Override
    public int calculateTargetCalories(User user) {
        int calories = 0;
        calories = (int) user.getGoalService().getTargetWeight() * 15;
        if (user.getGoalService().isPhysicalFitness()) {
            calories += 400;
        }
        return calories;
    }

    @Override
    public void changeGoal(User user) {
        if ((user.getWeight() - user.getGoalService().getTargetWeight()) >= 5) {
            user.getGoalService().setGoal(new LoseWeight());
        }
        else if ((user.getGoalService().getTargetWeight() - user.getWeight()) >= 5) {
            user.getGoalService().setGoal(new GainWeight());
        }
    }
}
