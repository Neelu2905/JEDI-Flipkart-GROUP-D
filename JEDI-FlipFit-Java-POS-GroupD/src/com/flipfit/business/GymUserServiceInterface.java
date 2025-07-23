package com.flipfit.business;

import com.flipfit.exceptions.AuthenticationException;
import com.flipfit.exceptions.RegistrationException;
import com.flipfit.helper.LoginCredentials;
import com.flipfit.helper.PasswordUpdateData;
import com.flipfit.helper.RegisterData;

public interface GymUserServiceInterface {
  boolean login(LoginCredentials loginDetails) throws AuthenticationException;

  boolean registerCustomer(RegisterData regCustomerData) throws RegistrationException;

  boolean registerOwner(RegisterData regOwnerData) throws RegistrationException;

  boolean updatePassword(PasswordUpdateData passwordUpdateData);

  void logoutUser();
}
