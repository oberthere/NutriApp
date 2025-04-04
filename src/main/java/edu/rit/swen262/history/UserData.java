package edu.rit.swen262.history;

import java.io.Serializable;
import java.util.Date;

public class UserData implements Serializable {
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private Date birthdate;
	private double height;

	public UserData(String username, String password, Date birthdate, double height) {
		this.username = username;
		this.password = password;
		this.birthdate = birthdate;
		this.height = height;
	}

	public String getUsername() {return this.username;}

	public String getPassword() {return this.password;}

	public Date getBirthdate() {return this.birthdate;}

	public double getHeight() {return this.height;}
}