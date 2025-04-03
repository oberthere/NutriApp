package edu.rit.swen262.team;

import java.util.Map.Entry;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.rit.swen262.team.challenge.*;
import edu.rit.swen262.user.User;

public class Team implements ChallengeCreator {
    private List<User> members;
    private List<Entry<User, String>> logs;
    private Challenge challenge;

    public Team() {
        logs = new ArrayList<>();
    }

    public List<User> getMembers() {return this.members;}
    public List<Entry<User, String>> getLogs() {return this.logs;}
    public Challenge getChallenge() {return this.challenge;}
    
    public List<Entry<User, String>> getLogs(int index) {
        if (index >= 0 && index < logs.size()) {
            return List.of(logs.get(index));
        }
        else {
            return List.of();
        }
    }

    public void addToLogs(User user, String log) {
        logs.add(new AbstractMap.SimpleEntry<>(user, log));
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