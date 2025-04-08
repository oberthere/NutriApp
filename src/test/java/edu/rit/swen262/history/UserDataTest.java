package edu.rit.swen262.history;

import edu.rit.swen262.team.Team;
import edu.rit.swen262.team.TeamInvite;
import edu.rit.swen262.user.User;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserDataTest {
    User user;
    UserData userData;
    Date birthdate;

    @BeforeEach
    void setUp() {
        birthdate = new Date();
        user = new User("TestUser", "Pass", 180.0, 70.0, birthdate);
        userData = user.createUserData();
    }

    @Test
    void testCreateUserDataFromUser() {
        assertEquals("TestUser", userData.getUsername());
        assertEquals("Pass", userData.getPassword());
        assertEquals(180.0, userData.getHeight());
        assertEquals(birthdate, userData.getBirthdate());
        assertNotNull(userData.getTeamInvites());
        assertTrue(userData.getTeamInvites().isEmpty());
    }

    @Test
    void testUpdateUserDataChangesPasswordAndHeight() {
        UserData updated = new UserData("TestUser", "newPass", birthdate, 175.0);
        userData.updateUserData(updated);

        assertEquals("newPass", userData.getPassword());
        assertEquals(175.0, userData.getHeight());
    }

    @Test
    void testAddTeamInviteToUserData() {
        Team team = new Team("Test Team");
        TeamInvite invite = new TeamInvite(team);

        userData.addTeamInvite(invite);
        Set<TeamInvite> invites = userData.getTeamInvites();

        assertEquals(1, invites.size());
        assertTrue(invites.contains(invite));
    }

    @AfterAll
    public static void cleanUpTestData() {
        String filename = System.getProperty("nutriapp.savefile", "SaveData");
        File file = new File("src/main/resources/data/" + filename);
        if (file.exists() && file.delete()) {
            System.out.println("Test save file '" + filename + "' deleted after test.");
        } else {
            System.out.println("Could not delete test save file '" + filename + "'");
        }
    }
}
