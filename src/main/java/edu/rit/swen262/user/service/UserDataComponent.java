package edu.rit.swen262.user.service;

import java.io.Serializable;
import java.util.Date;

public class UserDataComponent implements Serializable {

  private static final long serialVersionUID = 1L;

  private String username;
  private String password;
  private Date birthdate;
  private double height;

  public UserDataComponent(String username, String password, Date birthdate, double height) {
    this.username = username;
    this.password = password;
    this.birthdate = birthdate;
    this.height = height;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public Date getBirthdate() {
    return birthdate;
  }

  public double getHeight() {
    return height;
  }
}
