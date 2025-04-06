package edu.rit.swen262.team.challenge;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import edu.rit.swen262.user.User;

public class Challenge implements Serializable{
    private String name;
    private Date endDate;
    private String instructions;
    private Map<User, Integer> recordedTime;

    public Challenge(String name, Date endDate, String instructions) {
        this.name = name;
        this.endDate = endDate;
        this.instructions = instructions;
        this.recordedTime = new HashMap<>();
    }

    public String getName() {return this.name;}
    public Date getEndDate() {return this.endDate;}
    public String getInstructions() {return this.instructions;}
    public Map<User, Integer> getRecord() {return this.recordedTime;}

    public void addToRecord(User user, int time) {
        for (User member : recordedTime.keySet()) {
            if (user == member) {
                recordedTime.put(member, Integer.valueOf(time + recordedTime.get(member)));
            }
        }
    }
}