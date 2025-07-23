package com.flipfit.beans;

public class Role {
  private long roleId;
  private String roleName;
  private String roleDescription;

  public long getRoleId() {
    return roleId;
  }

  public void setRoleId(long roleId) {
    this.roleId = roleId;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public String getRoleDescription() {
    return roleDescription;
  }

  public void setRoleDescription(String roleDescription) {
    this.roleDescription = roleDescription;
  }

  public Role(String roleName, String roleDescription) {
    this.roleName = roleName;
    this.roleDescription = roleDescription;
  }
}
