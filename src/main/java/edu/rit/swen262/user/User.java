package edu.rit.swen262.user;

import java.util.Date;

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
        goalService = new GoalService(false, 0, 0);
    }

    public User(String name, double height, double weight, Date birthdate, 
        GoalService goalService, ShoppingListService shoppingListService, DailyHistoryService dailyHistoryService)
        {
            this.name = name;
            this.height = height;
            this.weight = weight;
            this.birthdate = birthdate;
            this.goalService = goalService;
            this.shoppingListService = shoppingListService;
            this.dailyHistoryService = dailyHistoryService;
        }

    public String getName() {return this.name;}
    public double getHeight() {return this.height;}
    public double getWeight() {return this.weight;}
    public Date getBirthdate() {return this.birthdate;}
    public GoalService getGoalService() {return this.goalService;}
}
