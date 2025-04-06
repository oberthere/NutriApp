package edu.rit.swen262.history;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import edu.rit.swen262.team.TeamInvite;

public class UserData implements Serializable {
	private static final long serialVersionUID = 2L;

	private String username;
	private String password;
	private Set<TeamInvite> teamInvites;
	private int teamNotificationIndex;
	private Date birthdate;
	private double height;

	public UserData(String username, String password, Date birthdate, double height) {
		this.username = username;
		this.password = password;
		this.birthdate = birthdate;
		this.height = height;
		this.teamInvites = new HashSet<>();
	}

	public UserData(String username, String password, Date birthdate, double height, Set<TeamInvite> teamInvites) {
		this.username = username;
		this.password = password;
		this.birthdate = birthdate;
		this.height = height;
		this.teamInvites = teamInvites;
	}

	public void addTeamInvite(TeamInvite invite) {this.teamInvites.add(invite);}
	public String getUsername() {return this.username;}
	public String getPassword() {return this.password;}
	public Date getBirthdate() {return this.birthdate;}
	public double getHeight() {return this.height;}
	public Set<TeamInvite> getTeamInvites() {return this.teamInvites;}
	public int getTeamNotificationIndex() {return this.teamNotificationIndex;}

	public void updateUserData(UserData userData) {
		if (this.username.equals(userData.getUsername())) {
			if (!this.password.equals(userData.getPassword())) {
				this.password = userData.getPassword();
			}
			if (this.height != userData.getHeight()) {
				this.height = userData.getHeight();
			}
		}
	}
}