package edu.rit.swen262.ui;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.rit.swen262.history.PersonalHistory;
import edu.rit.swen262.user.User;
import edu.rit.swen262.user.service.DailyHistoryService;

public class PageData {
    private Map<String, User> users;
    private static Date currentDate;
    private User currentUser;

    public PageData() {
        this.users = new HashMap<String, User>();
    }
    
    public PageData(Map<String, User> users) {
        this.users = users;
    }

    public User getUser(String name) {return this.users.get(name);}
    public void addUser(String name, User user) {this.users.put(name, user);}
    public void removeUser(String name) {this.users.remove(name);}
    public void setCurrentUser(User user) {this.currentUser = user;}
    public User getCurrentUser() {return this.currentUser;}
    public Map<String, User> getAllUsers() {
        return this.users;  // Directly return the map instead of converting to an array
    }
        

    private void loadUsersFromHistory() {
        Map<String, List<DailyHistoryService>> history = PersonalHistory.getHistory();
        for (String username : history.keySet()) {
            if (!users.containsKey(username)) {
                // Create a new user object from history (assumes history contains at least one entry per user)
                List<DailyHistoryService> userHistory = history.get(username);
                if (!userHistory.isEmpty()) {
                    DailyHistoryService dh = userHistory.get(0); // Take first entry for user data
                    User user = new User(username, dh.getHeight(), dh.getWeight(), dh.getBirthdate());
                    users.put(username, user);
                }
            }
        }
    }

    public static Date getCurrentDate() {return PageData.currentDate;}
}
