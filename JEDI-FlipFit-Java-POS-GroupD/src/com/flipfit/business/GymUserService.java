package com.flipfit.business;

import com.flipfit.exceptions.AuthenticationException;
import com.flipfit.helper.LoginCredentials;

public class GymUserService {

  public boolean login(LoginCredentials loginDetails) throws AuthenticationException {
    // Connect with Database for further check
    boolean result = true;

    if(!result){
      throw new AuthenticationException("Login Failure");
    }

    return result;
  }

  public void registerUser() {
  }
  public void logoutUser() {
  }
}
