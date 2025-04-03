package edu.rit.swen262.goal;

import java.io.Serializable;
import edu.rit.swen262.user.User;

public interface Goal extends Serializable {
    void calculateTargetCalories(User user);
    void changeGoal(User user);
}