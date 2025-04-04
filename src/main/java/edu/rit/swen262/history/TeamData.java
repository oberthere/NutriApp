package edu.rit.swen262.history;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import edu.rit.swen262.team.Team;
import edu.rit.swen262.team.challenge.Challenge;
import edu.rit.swen262.user.User;

public class TeamData implements Serializable {
    private static int currentId = 0;

    private int id;
    private List<String> members;
    private List<Entry<User, String>> notificationLogs;
    private Challenge challenge;
    
    public TeamData(Team team) {
        TeamData.currentId++;
        this.id = TeamData.currentId;
        this.challenge = team.getChallenge();
        this.notificationLogs = team.getNotificationLogs();

        List<String> memberNames = new ArrayList<>();
        for (User user : team.getMembers()) {
            memberNames.add(user.getName());
        }
        this.members = memberNames;
    }

}
