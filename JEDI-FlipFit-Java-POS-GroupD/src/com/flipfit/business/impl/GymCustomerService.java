package com.flipfit.business.impl;

import com.flipfit.beans.GymCustomer;

public class GymCustomerService {

  GymCustomer customer = new GymCustomer();

  public void createCustomer(Long userId, String name, String email, String address,int age, String gender, int customerPhone, String password) {

    customer.setEmail(email);
    customer.setName(name);
    customer.setUserId(userId);
    customer.setCustomerPhone(customerPhone);
    customer.setPassword(password);

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
