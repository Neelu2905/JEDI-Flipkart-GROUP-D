package com.flipfit.business;
import com.flipfit.beans.GymCustomer;
import com.flipfit.beans.GymCentre;
import java.util.List;

public interface GymCustomerServiceInterface {
    public void createCustomer(int id, String name, String emailAddress, int phone, String password);
    public void getCustomerDetails();
    public void viewGymCenters();
    public void bookSlot();
    public void cancelSlot();
    List<GymCentre> searchGymCenters(String location); // Search by location
    List<GymCentre> viewAllGymCenters();
}
