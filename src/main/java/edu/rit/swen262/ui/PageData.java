package edu.rit.swen262.ui;

import java.util.HashMap;
import java.util.Map;

import edu.rit.swen262.user.User;

public class PageData {
    private Map<String, User> users;

    public PageData() {
        this.users = new HashMap<String, User>();
    }
    
    public PageData(Map<String, User> users) {
        this.users = users;
    }

    public User getUser(String name) {return this.users.get(name);}
    public void addUser(String name, User user) {this.users.put(name, user);}
    public void removeUser(String name) {this.users.remove(name);}
}
