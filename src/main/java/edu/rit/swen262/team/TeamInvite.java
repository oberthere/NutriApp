package edu.rit.swen262.team;

import java.io.Serializable;

import edu.rit.swen262.user.User;

public class TeamInvite implements Serializable {
    private static final long serialVersionUID = 3L;  
    private Team team;

    public TeamInvite(Team team) {
        this.team = team;
    }

    public void JoinTeam(User user) {
        team.acceptMember(user);
    }
}
