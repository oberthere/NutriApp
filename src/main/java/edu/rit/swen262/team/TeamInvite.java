package edu.rit.swen262.team;

import edu.rit.swen262.user.User;

public class TeamInvite {
    private Team team;

    public TeamInvite(Team team) {
        this.team = team;
    }

    public void JoinTeam(User user) {
        team.acceptMember(user);
    }
}
