package edu.rit.swen262.ui;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.rit.swen262.history.PersonalHistory;
import edu.rit.swen262.user.User;
import edu.rit.swen262.user.service.DailyHistoryService;

public class PageData {
    private Map<String, User> users;
    private static Date currentDate = new Date();
    private User currentUser;

    public PageData() {
        this.users = new HashMap<String, User>();
        loadUsersFromHistory(); // load the saved data first
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

    public static boolean isTodayDate(Date date) {
   
        SimpleDateFormat sdf = new SimpleDateFormat("DD");
        String dayStr = sdf.format(date);
        int day = Integer.parseInt(dayStr);

        SimpleDateFormat monthsdf = new SimpleDateFormat("MM");
        String monthStr = monthsdf.format(date);
        int month = Integer.parseInt(monthStr);

        SimpleDateFormat yearsdf = new SimpleDateFormat("YYYY");
        String yearStr = yearsdf.format(date);
        int year = Integer.parseInt(yearStr);

        SimpleDateFormat sdfc = new SimpleDateFormat("DD");
        String dayStrc = sdfc.format(PageData.currentDate);
        int dayc = Integer.parseInt(dayStrc);

        SimpleDateFormat monthsdfc = new SimpleDateFormat("MM");
        String monthStrc = monthsdfc.format(PageData.currentDate);
        int monthc = Integer.parseInt(monthStrc);

        SimpleDateFormat yearsdfc = new SimpleDateFormat("YYYY");
        String yearStrc = yearsdfc.format(PageData.currentDate);
        int yearc = Integer.parseInt(yearStrc);

        if (day == dayc && month == monthc && year == yearc) {
            return true;
        } 

        return false;
    }
        

    /**
     * Loads saved users from PersonalHistory on startup
     */
    public void loadUsersFromHistory() {
        System.out.println("Loading saved users from history...");
        PersonalHistory.deserializeAndLoadSavedHistory(); // Load the saved history first
        Map<String, List<DailyHistoryService>> history = PersonalHistory.getHistory();

        for (String username : history.keySet()) {
            if (!users.containsKey(username)) {
                List<DailyHistoryService> userHistory = history.get(username);
                if (!userHistory.isEmpty()) {
                    DailyHistoryService dh = userHistory.get(0);  // Get first history entry

                    User user = new User(username, dh.getHeight(), dh.getWeight(), dh.getBirthdate());
                    users.put(username, user);

                    for (DailyHistoryService dhs : userHistory) {
                       
                        Date dhsDate = dhs.getDate();
    
                        if (isTodayDate(dhsDate)) {
                            user.setDailyHistoryService(dhs);
                            users.put(username, user);
                            break;
                        }
                       
                    }

                }
            }
        }

        System.out.println("Users loaded from history: " + users.keySet());
    }

    public static Date getCurrentDate() {return PageData.currentDate;}
}
