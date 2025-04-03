package edu.rit.swen262.user;

import java.util.Date;

import edu.rit.swen262.history.SaveData;
import edu.rit.swen262.user.service.UserDataComponent;
import edu.rit.swen262.user.service.DailyHistoryComponent;
import edu.rit.swen262.user.service.GoalComponent;
import edu.rit.swen262.user.service.ShoppingListComponent;

public class User {
    private String name;
    private double weight;
    private GoalComponent goalService;
    private ShoppingListComponent shoppingListService;
    private DailyHistoryComponent userHistoryService;
    private UserDataComponent userDataService;

    /**
     * Constructor for when there are no previous record of the user
     * @param name
     * @param height
     * @param weight
     * @param birthdate
     */
    public User(String name, String password, double height, double weight, Date birthdate) {
        this.name = name;
        this.weight = weight;
        this.userDataService = new UserDataComponent(name, password, birthdate, height);
    }

    public User(String name, String password, double height, double weight, Date birthdate, DailyHistoryComponent userHistoryService)
        {
            this.name = name;
            this.weight = weight;
            this.userHistoryService = userHistoryService;
            this.userDataService = new UserDataComponent(name, password, birthdate, height);
        }

    public String getName() {return this.name;}
    public double getHeight() {return this.userDataService.getHeight();}
    public double getWeight() {return this.weight;}

    public void setWeight(double weight) {this.weight = weight;}
    public Date getBirthdate() {return this.userDataService.getBirthdate();}
    public GoalComponent getGoalService() {return this.goalService;}
    public void setGoalService(GoalComponent goalService) {this.goalService = goalService; }
    public ShoppingListComponent getShoppingListService() {return this.shoppingListService;}
    public void setShoppingListService(ShoppingListComponent shoppingListService) {this.shoppingListService = shoppingListService;}
    public DailyHistoryComponent getUserHistoryService() {return this.userHistoryService;}
    public void setUserHistoryService(DailyHistoryComponent dh) {this.userHistoryService = dh;}
    public UserDataComponent getUserDataService() {return this.userDataService;}
    public void setUserDataService(UserDataComponent userDataService) {this.userDataService = userDataService;}
    public Date getLastUpdated() {return this.userHistoryService.getDate();}

    public void startNewDay(double weight, double targetWeight, int targetCalories, boolean isPhysicalFitnessGoal) {
    
        // Save previous day's data before resetting
        if (this.userHistoryService != null) {
            SaveData.addUserHistory(userHistoryService);
            SaveData.serializeHistoryToSave();
        }
    
        // Update goal service
        this.goalService = new GoalComponent(isPhysicalFitnessGoal, targetWeight, weight);
    
        this.userHistoryService = new DailyHistoryComponent(
            name,              // userID
            new Date(),        // current date   
            weight,          
            targetCalories     
        );
    }
    

    public void continueDay() {

    }


}
