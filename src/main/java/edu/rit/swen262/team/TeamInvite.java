package edu.rit.swen262.team;

import java.io.Serializable;

public class TeamInvite implements Serializable {
    private static final long serialVersionUID = 3L;  
    private String teamName;

    public TeamInvite(Team team) {
        this.teamName = team.getTeamName();
    }

    public String getTeamName() {return this.teamName;}

    @Override
    public int hashCode() {
        return teamName.hashCode();
    }
}
