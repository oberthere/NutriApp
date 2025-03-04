package edu.rit.swen262.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.rit.swen262.history.PersonalHistory;
import edu.rit.swen262.user.User;
import edu.rit.swen262.user.service.DailyHistoryService;
import edu.rit.swen262.user.service.GoalService;

public class PageData {
    private Map<String, User> users;
    private static Date currentDate = new Date();
    private User currentUser;

    public PageData() {
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
     * Loads saved users from PersonalHistory on startup.
     */
    public void loadUsersFromHistory() {
        System.out.println("Loading saved users from history...");
        PersonalHistory.deserializeAndLoadSavedHistory();
        Map<String, List<DailyHistoryService>> history = PersonalHistory.getHistory();
    
        for (String username : history.keySet()) {
            if (!users.containsKey(username)) {
                List<DailyHistoryService> userHistory = history.get(username);
                if (!userHistory.isEmpty()) {
                    DailyHistoryService latestHistory = userHistory.get(userHistory.size() - 1);  // Most recent entry
    
                    // Create user and assign latest history
                    User user = new User(username, latestHistory.getHeight(), latestHistory.getWeight(), latestHistory.getBirthdate());
                    user.setDailyHistoryService(latestHistory);
    
                    // Ensure GoalService is initialized properly
                    if (latestHistory.getGoalService() != null) {
                        user.setGoalService(latestHistory.getGoalService());
                    } else {
                        System.out.println("Warning: GoalService is null for user " + username + ". Setting default goal.");
                        user.setGoalService(new GoalService(false, latestHistory.getWeight(), latestHistory.getWeight()));  
                    }
    
                    users.put(username, user);
                }
            }
        }
    
        System.out.println("Users loaded from history: " + users.keySet());
    }
    

    public static Date getCurrentDate() { return PageData.currentDate; }
}
