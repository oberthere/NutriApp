package edu.rit.swen262.user;

import java.util.Date;

import edu.rit.swen262.history.SaveData;
import edu.rit.swen262.user.service.UserDataService;
import edu.rit.swen262.user.service.DailyHistoryService;
import edu.rit.swen262.user.service.GoalService;
import edu.rit.swen262.user.service.ShoppingListService;

public class User {
    private String name;
    private double weight;
    private GoalService goalService;
    private ShoppingListService shoppingListService;
    private DailyHistoryService dailyHistoryService;
    private UserDataService userDataService;

    /**
     * Constructor for when there are no previous record of the user
     * @param name
     * @param height
     * @param weight
     * @param birthdate
     */
    public User(String name, double height, double weight, Date birthdate) {
        this.name = name;
        this.weight = weight;
        this.userDataService = new UserDataService(name, birthdate, height);
    }

    public User(String name, double height, double weight, Date birthdate, DailyHistoryService dailyHistoryService)
        {
            this.name = name;
            this.weight = weight;
            this.dailyHistoryService = dailyHistoryService;
            this.userDataService = new UserDataService(name, birthdate, height);
        }

    public String getName() {return this.name;}
    public double getHeight() {return this.userDataService.getHeight();}
    public double getWeight() {return this.weight;}

    public void setWeight(double weight) {this.weight = weight;}
    public Date getBirthdate() {return this.userDataService.getBirthdate();}
    public GoalService getGoalService() {return this.goalService;}
    public void setGoalService(GoalService goalService) {this.goalService = goalService; }
    public ShoppingListService getShoppingListService() {return this.shoppingListService;}
    public void setShoppingListService(ShoppingListService shoppingListService) {this.shoppingListService = shoppingListService;}
    public DailyHistoryService getDailyHistoryService() {return this.dailyHistoryService;}
    public void setDailyHistoryService(DailyHistoryService dh) {this.dailyHistoryService = dh;}
    public UserDataService getUserDataService() {return this.userDataService;}
    public void setUserDataService(UserDataService userDataService) {this.userDataService = userDataService;}
    public Date getLastUpdated() {return this.dailyHistoryService.getDate();}

    public void startNewDay(double weight, double targetWeight, int targetCalories, boolean isPhysicalFitnessGoal) {
    
        // Save previous day's data before resetting
        if (this.dailyHistoryService != null) {
            SaveData.addDailyHistory(dailyHistoryService);
            SaveData.serializeHistoryToSave();
        }
    
        // Update goal service
        this.goalService = new GoalService(isPhysicalFitnessGoal, targetWeight, weight);
    
        this.dailyHistoryService = new DailyHistoryService(
            name,              // userID
            new Date(),        // current date   
            weight,          
            targetCalories     
        );
    }
    

    public void continueDay() {

    }


}
