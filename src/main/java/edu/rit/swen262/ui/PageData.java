package edu.rit.swen262.ui;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.rit.swen262.user.User;

public class PageData {
    private Map<String, User> users;
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
    public User[] getAllUsers() {
        List<User> ls = (List<User>) this.users.values();
        return (User[]) ls.toArray();
    }
}
