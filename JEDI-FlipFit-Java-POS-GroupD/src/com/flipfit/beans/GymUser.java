package com.flipfit.beans;

import java.util.HashSet;
import java.util.Set;

public class GymUser {
  private long userId;
  private String name;
  private String email;
  private String password;
  private Set<Role> roles = new HashSet<Role>();

  public void addRole(Role role){
    roles.add(role);
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRole() {
    return roles;
  }

  public void setRole(Set<Role> role) {
    this.roles = role;
  }

  public GymUser(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public GymUser() {}
}
