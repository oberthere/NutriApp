package edu.rit.swen262.team;

import java.util.Map.Entry;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.rit.swen262.history.SaveData;
import edu.rit.swen262.history.TeamData;
import edu.rit.swen262.team.challenge.*;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.user.User;

public class Team implements Serializable {
    private String teamName;
    private List<User> members;
    private List<Entry<User, String>> notificationLogs;
    private Challenge challenge;
    private ChallengeCreator challengeCreator;

    public Team(String teamName) {
        this.teamName = teamName;
        members = new ArrayList<User>();
        notificationLogs = new ArrayList<>();
    }

    public Team(PageData pageData, TeamData teamData) {
        this.members = new ArrayList<>();
        this.teamName = teamData.getTeamName();
        this.notificationLogs = new ArrayList<>();
        this.challenge = teamData.getChallenge();
        
        List<Entry<String, String>> tempNotiLog = teamData.getNotificationLogs();
        for (String username : teamData.getMembers()) {
            User user = pageData.getUser(username);
            System.out.println(user.getName() + " Have been found in team " + teamName);
            this.members.add(user);
        }

        for (Entry<String,String> entry : tempNotiLog) {
            String username = entry.getKey();
            User user = pageData.getUser(username);
            Entry<User, String> notifEntry = new AbstractMap.SimpleEntry<User, String>(user, entry.getValue());
            this.notificationLogs.add(notifEntry);
        }

        
        
    }

    public String getTeamName() {return this.teamName;}
    public List<User> getMembers() {return this.members;}
    public Challenge getChallenge() {return this.challenge;}
    public List<Entry<User, String>> getNotificationLogs() {return this.notificationLogs;}
    
    public List<Entry<User, String>> getNotificationLogsFromIndex(int index) {
        if (index >= 0 && index < notificationLogs.size()) {

            List<Entry<User,String>> returnls = new ArrayList<>();

            for (int i = index; i < notificationLogs.size(); i++) {
                returnls.add(notificationLogs.get(index));
            }
            return returnls;
        }
        else {return List.of();}
    }

    public void addToNotificationLogs(User user, String log) {
        notificationLogs.add(new AbstractMap.SimpleEntry<>(user, log));
    }

    public void acceptMember(User user) {
        this.members.add(user);
    }

    public void removeMember(User user) {
        this.members.remove(user);
    }

    public void sendInvite(String username) {
        TeamInvite invite = new TeamInvite(this);
        SaveData.addTeamInviteToUser(invite, username);
    }

    public void setChallengeCreator(ChallengeCreator challengeCreator) {
        this.challengeCreator = challengeCreator;
    }

    public void makeChallenge(Challenge challenge) {
        if (challengeCreator == null){
            challenge = this.challengeCreator.createChallenge(challenge.getEndDate());
        } else {
            challengeCreator.createChallenge(challenge.getEndDate());
        }
    }
}