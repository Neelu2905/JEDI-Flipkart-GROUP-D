package com.flipfit.business;

import com.flipfit.beans.GymCustomer;

public class GymCustomerService {

  GymCustomer customer = new GymCustomer();

  public void createCustomer(int id, String name, String emailAddress, int phone, String password) {

    customer.setEmail(emailAddress);
    customer.setName(name);
    customer.setUserId(id);
    customer.setCustomerPhone(phone);
    customer.setPassword(password);
    //Baad me is customer ko database me add karna hai

    System.out.println("customer details added");
  }

  public void getCustomerDetails(){

  }

  public void viewGymCenters(){

  }

  public void bookSlot(){

  }

  public void cancelSlot(){

  }
}
