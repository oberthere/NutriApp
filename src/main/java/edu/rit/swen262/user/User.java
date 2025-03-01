package edu.rit.swen262.user;

import java.util.Date;

import edu.rit.swen262.history.PersonalHistory;
import edu.rit.swen262.user.service.DailyHistoryService;
import edu.rit.swen262.user.service.GoalService;
import edu.rit.swen262.user.service.ShoppingListService;

public class User {
    private String name;
    private double height;
    private double weight;
    private Date birthdate;
    private GoalService goalService;
    private ShoppingListService shoppingListService;
    private DailyHistoryService dailyHistoryService;

    /**
     * Constructor for when there are no previous record of the user
     * @param name
     * @param height
     * @param weight
     * @param birthdate
     */
    public User(String name, double height, double weight, Date birthdate) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.birthdate = birthdate;
    }

    public User(String name, double height, double weight, Date birthdate, DailyHistoryService dailyHistoryService)
        {
            this.name = name;
            this.height = height;
            this.weight = weight;
            this.birthdate = birthdate;
            this.dailyHistoryService = dailyHistoryService;
        }

    public String getName() {return this.name;}
    public double getHeight() {return this.height;}
    public double getWeight() {return this.weight;}

    public void setWeight(double weight) {this.weight = weight;}
    public Date getBirthdate() {return this.birthdate;}
    public GoalService getGoalService() {return this.goalService;}
    public void setGoalService(GoalService goalService) {this.goalService = goalService; }
    public ShoppingListService getShoppingListService() {return this.shoppingListService;}
    public DailyHistoryService getDailyHistoryService() {return this.dailyHistoryService;}
    public Date getLastUpdated() {return this.dailyHistoryService.getDate();}

    private void saveData() {
        //TODO: Save the user data into personal history
    }

    public void startNewDay(double height, double weight) {
        //Calls on saveData and Saves the user data into personal history

        //Create a new personal history and set that as the user's current dailyhistoryservice
    }

    public void continueDay() {

    }


}
