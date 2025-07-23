package com.flipfit.business;

import com.flipfit.beans.Booking;
import com.flipfit.beans.GymCustomer;
import java.util.List;

public class GymCustomerService {

  GymCustomer customer = new GymCustomer();

  public void createCustomer(int id, String name, String emailAddress, int phone, String password) {

    customer.setCustomerEmailAddress(emailAddress);
    customer.setCustomerName(name);
    customer.setCustomerId(id);
    customer.setCustomerPhone(phone);
    customer.setPassword(password);
    //Baad me is customer ko database me add karna hai

    System.out.println("customer details added");
  }

  public String getCustomerDetails(){
    return null;
  }

  public void viewGymCenters(){

  }

  public void bookSlot(){

  }

  public void cancelSlot(){

  }
}
