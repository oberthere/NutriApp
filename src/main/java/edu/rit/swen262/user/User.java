package edu.rit.swen262.user;

import java.util.Date;

// import edu.rit.swen262.service.GoalService;

public class User {
    private String name;
    private double height;
    private double weight;
    private Date birthdate;
    // private GoalService goalService;
    // private ShoppingListService shoppingListService;
    // private DailyHistory dailyHistory;

    public User(String name, double height, double weight, Date birthdate) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.birthdate = birthdate;
    }
}
