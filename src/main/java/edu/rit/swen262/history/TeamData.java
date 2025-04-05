package edu.rit.swen262.history;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import edu.rit.swen262.team.Team;
import edu.rit.swen262.team.challenge.Challenge;
import edu.rit.swen262.user.User;

public class TeamData implements Serializable {
    private String teamName;
    private List<String> members;
    private List<Entry<String, String>> notificationLogs;
    private Challenge challenge;
    
    public TeamData(Team team) {
        this.teamName = team.getTeamName();
        this.challenge = team.getChallenge();
        
        
        List<Entry<User, String>> tempLogs = team.getNotificationLogs();
        this.notificationLogs = new ArrayList<>();

        for (Entry<User,String> entry : tempLogs) {
            String name = entry.getKey().getName();
            Entry<String, String> newEntry =  new AbstractMap.SimpleEntry<>(name, entry.getValue());
            this.notificationLogs.add(newEntry);
        }

        List<String> memberNames = new ArrayList<>();
        for (User user : team.getMembers()) {
            memberNames.add(user.getName());
        }
        this.members = memberNames;
    }

    public String getTeamName() {
        return teamName;
    }

    public List<String> getMembers() {
        return members;
    }

    public List<Entry<String, String>> getNotificationLogs() {
        return notificationLogs;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    

}
