package com.flipfit.business;

public interface GymCustomerServiceInterface {
    public void createCustomer(int id, String name, String emailAddress, int phone, String password);
    public void getCustomerDetails();
    public void viewGymCenters();
    public void bookSlot();
    public void cancelSlot();
}
