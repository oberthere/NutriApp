package edu.rit.swen262.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.rit.swen262.history.SaveData;
import edu.rit.swen262.history.TeamData;
import edu.rit.swen262.history.UserData;
import edu.rit.swen262.team.Team;
import edu.rit.swen262.user.User;
import edu.rit.swen262.user.components.DailyHistoryComponent;
import edu.rit.swen262.user.components.GoalComponent;

public class PageData {
    private Map<String, User> users;
    private Map<String, Team> teams;
    private static Date currentDate = new Date();
    private User currentUser;
    private PageRunner pageRunner;

    public PageData(PageRunner pageRunner) {
        this.pageRunner = pageRunner;
        this.users = new HashMap<>();
        this.teams = new HashMap<>();
        loadUsersFromHistory(); // Load saved user data
    }
    
    public PageData(Map<String, User> users) {
        this.users = users;
    }

    public Map<String, User> getAllUsers() {return this.users;}
    public User getUser(String name) {return this.users.get(name);}
    public User getCurrentUser() {return this.currentUser;}
    public Team getTeam(String name) {return this.teams.get(name);}

    public static Date getCurrentDate() {return PageData.currentDate;}
    public static void setCurrentDate(Date date) { PageData.currentDate = date;}
    public void setCurrentUser(User user) {this.currentUser = user;}
    public PageRunner getPageRunner() {return this.pageRunner;}

    public void addUser(String name, User user) {this.users.put(name, user);}
    public void addTeam(String name, Team team) {
        this.teams.put(name, team);
        team.acceptMember(currentUser);
        currentUser.setTeam(team);
    }
    public void removeUser(String name) {this.users.remove(name);}

    /**
     * Checks if the given date is today's date.
     */
    public static boolean isTodayDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String inputDate = sdf.format(date);
        String todayDate = sdf.format(PageData.currentDate);
        
        return inputDate.equals(todayDate);
    }

    /**
     * Loads saved users from SaveData on startup.
     */
    public void loadUsersFromHistory() {
        System.out.println("Loading saved users from history...");
        SaveData.deserializeFromSave();
        Map<String, List<DailyHistoryComponent>> history = SaveData.getHistory();
        Map<String, UserData> userData = SaveData.getUserData();
    
        for (String username : history.keySet()) {
            if (!users.containsKey(username)) {
                List<DailyHistoryComponent> dailyHistory = history.get(username);
                UserData userDataComponent = userData.get(username);
                if (!dailyHistory.isEmpty()) {
                    DailyHistoryComponent latestHistory = dailyHistory.get(dailyHistory.size() - 1);  // Most recent entry
    
                    // Create user and assign latest history
                    User user = new User(username, userDataComponent.getPassword(),userDataComponent.getHeight(), latestHistory.getWeight(), userDataComponent.getBirthdate());
                    user.setDailyHistoryComponent(latestHistory);
    
                    // Ensure GoalComponent is initialized properly
                    if (latestHistory.getGoalComponent() != null) {
                        user.setGoalComponent(latestHistory.getGoalComponent());
                    } else {
                        System.out.println("Warning: GoalComponent is null for user " + username + ". Setting default goal.");
                        user.setGoalComponent(new GoalComponent(false, latestHistory.getWeight(), latestHistory.getWeight()));  
                    }
    
                    users.put(username, user);
                }
            }
        }
        System.out.println("Users loaded from history: " + users.keySet());
    }

    public void loadTeam() {
        System.out.println("Loading team...");
        Map<String, TeamData> teamDataRecord = SaveData.getTeamData();
        for (TeamData teamData : teamDataRecord.values()) {
            Team team = new Team(pageRunner.getPageData(), teamData);
            this.teams.put(team.getTeamName(), team);
        }
    }
}