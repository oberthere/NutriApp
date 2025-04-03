package edu.rit.swen262.user;

import java.util.Date;

import edu.rit.swen262.history.SaveData;
import edu.rit.swen262.user.components.UserDataComponent;
import edu.rit.swen262.user.components.DailyHistoryComponent;
import edu.rit.swen262.user.components.GoalComponent;
import edu.rit.swen262.user.components.ShoppingListComponent;

public class User {
    private String username;
    private double weight;
    private GoalComponent goalComponent;
    private ShoppingListComponent shoppingListComponent;
    private DailyHistoryComponent dailyHistoryComponent;
    private UserDataComponent userDataComponent;

    /**
     * Constructor for when there are no previous record of the user
     * @param username
     * @param height
     * @param weight
     * @param birthdate
     */
    public User(String username, String password, double height, double weight, Date birthdate) {
        this.username = username;
        this.weight = weight;
        this.userDataComponent = new UserDataComponent(username, password, birthdate, height);
    }

    public User(String username, String password, double height, double weight, Date birthdate, DailyHistoryComponent dailyHistoryComponent) {
        this.username = username;
        this.weight = weight;
        this.dailyHistoryComponent = dailyHistoryComponent;
        this.userDataComponent = new UserDataComponent(username, password, birthdate, height);
    }

    public String getName() {return this.username;}
    public double getHeight() {return this.userDataComponent.getHeight();}
    public double getWeight() {return this.weight;}
    public Date getBirthdate() {return this.userDataComponent.getBirthdate();}
    public GoalComponent getGoalComponent() {return this.goalComponent;}
    public ShoppingListComponent getShoppingListComponent() {return this.shoppingListComponent;}
    public DailyHistoryComponent getDailyHistoryComponent() {return this.dailyHistoryComponent;}
    public UserDataComponent getUserDataComponent() {return this.userDataComponent;}

    public void setWeight(double weight) {this.weight = weight;}
    public void setGoalComponent(GoalComponent goalComponent) {this.goalComponent = goalComponent; }
    public void setShoppingListComponent(ShoppingListComponent shoppingListComponent) {this.shoppingListComponent = shoppingListComponent;}
    public void setDailyHistoryComponent(DailyHistoryComponent dailyHistoryComponent) {this.dailyHistoryComponent = dailyHistoryComponent;}
    public void setUserDataComponent(UserDataComponent userDataComponent) {this.userDataComponent = userDataComponent;}

    public Date getLastUpdated() {return this.dailyHistoryComponent.getDate();}

    public void startNewDay(double weight, double targetWeight, boolean isPhysicalFitnessGoal) {
        // Save previous day's data before resetting
        if (this.dailyHistoryComponent != null) {
            SaveData.addDailyHistory(dailyHistoryComponent);
            SaveData.serializeHistoryToSave();
        }

        // Update goalComponent
        this.goalComponent = new GoalComponent(isPhysicalFitnessGoal, targetWeight, weight);

        // Create a new dailyHistoryComponent
        this.dailyHistoryComponent = new DailyHistoryComponent(username, new Date(), weight);
    }
    
    public void continueDay() {

    }
}