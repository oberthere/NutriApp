package edu.rit.swen262.user.service;

import java.io.Serializable;
import java.util.Date;

public class UserDataService implements Serializable {

  private static final long serialVersionUID = 1L;

  private String username;
  private Date birthdate;
  private double height;

  public UserDataService(String username, Date birthdate, double height) {
    this.username = username;
    this.birthdate = birthdate;
    this.height = height;
  }

  public String getUsername() {
    return username;
  }

  public Date getBirthdate() {
    return birthdate;
  }

  public double getHeight() {
    return height;
  }
}
