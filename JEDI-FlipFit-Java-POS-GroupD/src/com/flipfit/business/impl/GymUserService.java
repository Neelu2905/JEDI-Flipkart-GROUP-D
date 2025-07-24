package com.flipfit.business.impl;

import com.flipfit.beans.GymCustomer;
import com.flipfit.beans.GymOwner;
import com.flipfit.beans.GymUser;
import com.flipfit.beans.Role;
import com.flipfit.business.GymUserServiceInterface;
import com.flipfit.constants.Constants;
import com.flipfit.dao.GymUserDAO;
import com.flipfit.dao.GymUserDAOImpl;
import com.flipfit.exceptions.AuthenticationException;
import com.flipfit.exceptions.RegistrationException;
import com.flipfit.exceptions.RoleDoesNotExistsException;
import com.flipfit.exceptions.UserAlreadyExistsException;
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
    GymCustomer gymCustomer = new GymCustomer();
    gymCustomer.setName(regCustomerData.getName());
    gymCustomer.setEmail(regCustomerData.getEmail());
    gymCustomer.setPassword(regCustomerData.getPassword());
    gymCustomer.setAddress(regCustomerData.getAddress());
    gymCustomer.setAge(regCustomerData.getAge());
    gymCustomer.setGender(regCustomerData.getGender());

    try {
      gymCustomer.addRole(gymUserDAO.getRoleByName(Constants.CUSTOMER));
      gymUserDAO.addUser(gymCustomer);
    } catch (RoleDoesNotExistsException | UserAlreadyExistsException | UserDoesNotExistsException e) {
      throw new RegistrationException("Registration Failed");
    }
    return true;
  }

  public boolean registerOwner(RegisterData regOwnerData) throws RegistrationException {
    GymOwner gymOwner = new GymOwner();
    gymOwner.setName(regOwnerData.getName());
    gymOwner.setEmail(regOwnerData.getEmail());
    gymOwner.setPassword(regOwnerData.getPassword());
    gymOwner.setAadharNo(regOwnerData.getAadhar());
    gymOwner.setPanNo(regOwnerData.getPan());

    try{
      gymOwner.addRole(gymUserDAO.getRoleByName(Constants.OWNER));
      gymUserDAO.addUser(gymOwner);
    } catch (Exception e) {
      throw new RegistrationException("Registration Failed");
    }
    return true;
  }

  public boolean updatePassword(PasswordUpdateData passwordUpdateData) {
    boolean result = true;
    return result;
  }

  public void logoutUser() {
  }
}
