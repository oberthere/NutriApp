package edu.rit.swen262.history;

import edu.rit.swen262.team.Team;
import edu.rit.swen262.team.challenge.*;
import edu.rit.swen262.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import static org.junit.jupiter.api.Assertions.*;

public class TeamDataTest {

    Team team;
    User mockUser;

    @BeforeEach
    void setUp() {
        mockUser = new User("TestUser", "Pass", 70, 200, new Date());

        // team and add user
        team = new Team("Testing Team");
        team.sendInvite("TestUser");
        team.acceptMember(mockUser);

        // notification log
        team.addToNotificationLogs(mockUser, "Welcome to Testing Team!");

        // challenge
        team.setChallengeCreator(new PushUpChallengeCreator());
        team.makeChallenge(new Date());
    }

    @Test
    void testTeamDataCapturesTeamState() {
        // team data snapshot
        TeamData data = new TeamData(team);

        // check team name
        assertEquals("Testing Team", data.getTeamName());

        // check members
        List<String> members = data.getMembers();
        assertEquals(1, members.size());
        assertEquals("TestUser", members.get(0));

        // check challenge
        Challenge challenge = data.getChallenge();
        assertNotNull(challenge);
        assertEquals("Pushups", challenge.getName());
        assertEquals("Do 100 Pushups", challenge.getInstructions());

        // check notification log
        List<Entry<String, String>> logs = data.getNotificationLogs();
        assertEquals(1, logs.size());
        assertEquals("TestUser", logs.get(0).getKey());
        assertEquals("Welcome to Testing Team!", logs.get(0).getValue());
    }
}
