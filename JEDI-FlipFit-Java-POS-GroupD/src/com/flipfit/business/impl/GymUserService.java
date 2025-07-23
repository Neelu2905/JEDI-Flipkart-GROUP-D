package com.flipfit.business.impl;

import com.flipfit.exceptions.AuthenticationException;
import com.flipfit.exceptions.RegistrationException;
import com.flipfit.helper.LoginCredentials;
import com.flipfit.helper.PasswordUpdateData;
import com.flipfit.helper.RegisterData;

public class GymUserService {

  public boolean login(LoginCredentials loginDetails) throws AuthenticationException {
    // Connect with Database for further check
    boolean result = true;

    if(!result){
      throw new AuthenticationException("Login Failure");
    }

    return result;
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
