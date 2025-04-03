package edu.rit.swen262.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.rit.swen262.history.SaveData;
import edu.rit.swen262.user.User;
import edu.rit.swen262.user.service.UserDataComponent;
import edu.rit.swen262.user.service.DailyHistoryComponent;
import edu.rit.swen262.user.service.GoalComponent;

public class PageData {
    private Map<String, User> users;
    private static Date currentDate = new Date();
    private User currentUser;
    private PageRunner pageRunner;

    public PageData(PageRunner pageRunner) {
        this.pageRunner = pageRunner;
        this.users = new HashMap<>();
        loadUsersFromHistory(); // Load saved user data
    }
    
    public PageData(Map<String, User> users) {
        this.users = users;
    }

    public User getUser(String name) { return this.users.get(name); }
    public void addUser(String name, User user) { this.users.put(name, user); }
    public void removeUser(String name) { this.users.remove(name); }
    public void setCurrentUser(User user) { this.currentUser = user; }
    public User getCurrentUser() { return this.currentUser; }
    public Map<String, User> getAllUsers() { return this.users; }
    public PageRunner getPageRunner() {return this.pageRunner;}

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
        SaveData.deserializeAndLoadSavedHistory();
        Map<String, List<DailyHistoryComponent>> history = SaveData.getHistory();
        Map<String, UserDataComponent> userData = SaveData.getUserData();
    
        for (String username : history.keySet()) {
            if (!users.containsKey(username)) {
                List<DailyHistoryComponent> userHistory = history.get(username);
                UserDataComponent userDataService = userData.get(username);
                if (!userHistory.isEmpty()) {
                    DailyHistoryComponent latestHistory = userHistory.get(userHistory.size() - 1);  // Most recent entry
    
                    // Create user and assign latest history
                    User user = new User(username, userDataService.getPassword(),userDataService.getHeight(), latestHistory.getWeight(), userDataService.getBirthdate());
                    user.setUserHistoryService(latestHistory);
    
                    // Ensure GoalService is initialized properly
                    if (latestHistory.getGoalService() != null) {
                        user.setGoalService(latestHistory.getGoalService());
                    } else {
                        System.out.println("Warning: GoalService is null for user " + username + ". Setting default goal.");
                        user.setGoalService(new GoalComponent(false, latestHistory.getWeight(), latestHistory.getWeight()));  
                    }
    
                    users.put(username, user);
                }
            }
        }
    
        System.out.println("Users loaded from history: " + users.keySet());
    }
    

    public static Date getCurrentDate() { return PageData.currentDate; }
}
