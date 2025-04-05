package edu.rit.swen262.team;

import java.util.Map.Entry;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;

import edu.rit.swen262.history.SaveData;
import edu.rit.swen262.team.challenge.*;
import edu.rit.swen262.user.User;

public class Team implements ChallengeCreator {
    private String teamName;
    private List<User> members;
    private List<Entry<User, String>> notificationLogs;
    private Challenge challenge;

    public Team(String teamName) {
        this.teamName = teamName;
        notificationLogs = new Stack<>();
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

    public void sendInvite(String username) {
        TeamInvite invite = new TeamInvite(this);
        SaveData.addTeamInviteToUser(invite, username);
    }

    public void setChallengeCreator(ChallengeCreator challengeCreator) {
        // TODO
    }

    @Override
    public Challenge createChallenge(Date endDate) {
        // TODO
        return new Challenge(null, endDate, null);
    }

    public void makeChallenge() {
        // TODO
    }
}