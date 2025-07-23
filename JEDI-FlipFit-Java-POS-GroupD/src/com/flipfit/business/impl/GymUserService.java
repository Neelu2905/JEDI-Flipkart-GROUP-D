package com.flipfit.business.impl;

import com.flipfit.beans.GymUser;
import com.flipfit.beans.Role;
import com.flipfit.business.GymUserServiceInterface;
import com.flipfit.dao.GymUserDAO;
import com.flipfit.dao.GymUserDAOImpl;
import com.flipfit.exceptions.AuthenticationException;
import com.flipfit.exceptions.RegistrationException;
import com.flipfit.exceptions.UserDoesNotExistsException;
import com.flipfit.helper.LoginCredentials;
import com.flipfit.helper.PasswordUpdateData;
import com.flipfit.helper.RegisterData;
import java.util.List;

public class GymUserService implements GymUserServiceInterface {

  GymUserDAO gymUserDAO = new GymUserDAOImpl();

  public void login(LoginCredentials loginDetails) throws AuthenticationException {

    try {
      GymUser user = gymUserDAO.getUserByEmail(loginDetails.getUsername());
      if(user == null || !user.getPassword().equals(loginDetails.getPassword())){
        throw new AuthenticationException("Authentication Failed");
      }

      List<String> roles = user.getRole().stream().map(Role::getRoleName).toList();

      if(!roles.contains(loginDetails.getRole())){
        throw new AuthenticationException("Authentication Failed");
      }

    } catch (UserDoesNotExistsException e) {
      throw new AuthenticationException("Authentication Failed");
    }
  }

  public boolean registerCustomer(RegisterData regCustomerData) throws RegistrationException {
    boolean result = true;

    if(!result){
      throw new RegistrationException("Registration Failed");
    }
    System.out.println("[+] Registration successful for Customer");
    return true;
  }

  public boolean registerOwner(RegisterData regOwnerData) throws RegistrationException {
    boolean result = true;

    if(!result){
      throw new RegistrationException("Registration Failed");
    }
    System.out.println("[+] Registration successful for Owner");
    return true;
  }

  public boolean updatePassword(PasswordUpdateData passwordUpdateData) {
    boolean result = true;
    return result;
  }

  public void logoutUser() {
  }
}
