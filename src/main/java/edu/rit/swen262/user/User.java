package edu.rit.swen262.user;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import edu.rit.swen262.history.SaveData;
import edu.rit.swen262.history.UserData;
import edu.rit.swen262.team.Team;
import edu.rit.swen262.team.TeamInvite;
import edu.rit.swen262.user.components.DailyHistoryComponent;
import edu.rit.swen262.user.components.GoalComponent;
import edu.rit.swen262.user.components.ShoppingListComponent;

public class User {
    private String username;
    private double weight;
    private String password;
	private Date birthdate;
	private double height;
    private GoalComponent goalComponent;
    private ShoppingListComponent shoppingListComponent;
    private DailyHistoryComponent dailyHistoryComponent;
    private Team team;
    private Set<TeamInvite> teamInvites;
    private int teamNotificationIndex;

    /**
     * Constructor for when there are no previous record of the user
     * @param username
     * @param height
     * @param weight
     * @param birthdate
     */
    public User(String username, String password, double height, double weight, Date birthdate, Set<TeamInvite> teamInvites) {
        this.username = username;
        this.weight = weight;
        this.password = password;
        this.birthdate = birthdate;
        this.height = height;
        this.teamInvites = teamInvites;
    }

    public User(String username, String password, double height, double weight, Date birthdate) {
        this.username = username;
        this.weight = weight;
        this.password = password;
        this.birthdate = birthdate;
        this.height = height;
        this.teamInvites = new HashSet<>();
    }

    public User(String username, String password, double height, double weight, Date birthdate, DailyHistoryComponent dailyHistoryComponent) {
        this.username = username;
        this.weight = weight;
        this.dailyHistoryComponent = dailyHistoryComponent;
        this.password = password;
        this.birthdate = birthdate;
        this.height = height;
        this.teamInvites = new HashSet<>();
    }

    public String getName() {return this.username;}
    public double getHeight() {return this.height;}
    public double getWeight() {return this.weight;}
    public String getPassword() {return this.password;}
    public Date getBirthdate() {return this.birthdate;}
    public Set<TeamInvite> getTeamInvite() {return this.teamInvites;}
    public GoalComponent getGoalComponent() {return this.goalComponent;}
    public ShoppingListComponent getShoppingListComponent() {return this.shoppingListComponent;}
    public DailyHistoryComponent getDailyHistoryComponent() {return this.dailyHistoryComponent;}
    public Team getTeam() {return this.team;}
    public int getTeamNotificationIndex() {return this.teamNotificationIndex;}
    public UserData createUserData() {return new UserData(username, password, birthdate, height);}

    public void setWeight(double weight) {this.weight = weight;}
    public void setPassword(String password) {this.password = password;}
    public void setGoalComponent(GoalComponent goalComponent) {this.goalComponent = goalComponent; }
    public void setShoppingListComponent(ShoppingListComponent shoppingListComponent) {this.shoppingListComponent = shoppingListComponent;}
    public void setDailyHistoryComponent(DailyHistoryComponent dailyHistoryComponent) {this.dailyHistoryComponent = dailyHistoryComponent;}
    public void setTeam(Team team) {this.team = team;}
    public void incrementTeamNotificationIndex() {this.teamNotificationIndex++;}
    public void resetTeamNotificationIndex() {this.teamNotificationIndex = 0;}
    public Date getLastUpdated() {return this.dailyHistoryComponent.getDate();}
    public void addTeamInvite(TeamInvite invite) {this.teamInvites.add(invite);}

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
    
    public void undoStartDay() {
      this.goalComponent = null;
      this.dailyHistoryComponent = null;
    }
    
    public void continueDay() {

    }
}