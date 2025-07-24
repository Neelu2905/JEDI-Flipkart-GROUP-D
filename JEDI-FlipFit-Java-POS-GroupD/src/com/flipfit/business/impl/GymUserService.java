package com.flipfit.business.impl;

import com.flipfit.beans.GymCustomer;
import com.flipfit.beans.GymOwner;
import com.flipfit.beans.GymUser;
import com.flipfit.beans.Role;
import com.flipfit.business.GymUserServiceInterface;
import com.flipfit.constants.Constants;
import com.flipfit.dao.GymCustomerDAO;
import com.flipfit.dao.GymCustomerDAOImpl;
import com.flipfit.dao.GymOwnerDAO;
import com.flipfit.dao.GymOwnerDAOImpl;
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
  GymCustomerDAO gymCustomerDAO = new GymCustomerDAOImpl();
  GymOwnerDAO gymOwnerDAO = new GymOwnerDAOImpl();

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
    GymUser gymUser = new GymUser();

    gymUser.setName(regCustomerData.getName());
    gymUser.setEmail(regCustomerData.getEmail());
    gymUser.setPassword(regCustomerData.getPassword());

    gymCustomer.setAddress(regCustomerData.getAddress());
    gymCustomer.setAge(regCustomerData.getAge());
    gymCustomer.setGender(regCustomerData.getGender());

    try {
      gymUser.addRole(gymUserDAO.getRoleByName(Constants.CUSTOMER));
      long new_id = gymUserDAO.addUser(gymUser).getUserId();

      gymCustomer.setUserId(new_id);
      gymCustomerDAO.addCustomer(gymCustomer);

      System.out.println("Customer added with ID " + new_id);

    } catch (RoleDoesNotExistsException | UserAlreadyExistsException | UserDoesNotExistsException e) {
      throw new RegistrationException("Registration Failed : "  + e.getMessage() );
    }
    return true;
  }

  public boolean registerOwner(RegisterData regOwnerData) throws RegistrationException {
    GymOwner gymOwner = new GymOwner();
    GymUser gymUser = new GymUser();


    gymUser.setName(regOwnerData.getName());
    gymUser.setEmail(regOwnerData.getEmail());
    gymUser.setPassword(regOwnerData.getPassword());

    gymOwner.setAadharNo(regOwnerData.getAadhar());
    gymOwner.setPanNo(regOwnerData.getPan());

    try{
      gymUser.addRole(gymUserDAO.getRoleByName(Constants.OWNER));
      long new_id = gymUserDAO.addUser(gymUser).getUserId();

      gymOwner.setUserId(new_id);
      gymOwnerDAO.addGymOwner(gymOwner);

      System.out.println("Gym Owner Added with ID " + new_id);
    } catch (Exception e) {
      throw new RegistrationException("Registration Failed: "   + e.getMessage() );
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
