package edu.rit.swen262.goal;

import edu.rit.swen262.user.User;

public interface Goal {
    public int calculateTargetCalories(User user);
    public void changeGoal(User user);
}
