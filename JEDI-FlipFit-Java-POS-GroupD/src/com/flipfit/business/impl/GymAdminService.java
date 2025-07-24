package com.flipfit.business.impl;

//import com.flipfit.beans.Gym; // Assuming you have a Gym bean for viewRegisteredGyms
import com.flipfit.beans.GymCentre; // Import GymCentre
import com.flipfit.beans.GymCustomer;
import com.flipfit.beans.GymOwner; // Import GymOwner
import com.flipfit.dao.GymAdminDAO;
import com.flipfit.dao.GymAdminDAOImpl;

import java.util.List;

public class GymAdminService {
    // Corrected instantiation: private GymAdminDAO gymAdminDAO
    private GymAdminDAO gymAdminDAO = new GymAdminDAOImpl();

    // Changed to non-static as it uses an instance member gymAdminDAO
    public boolean approveGym(Long GymId) {
        System.out.println("[Admin Service] Attempting to approve Gym Centre with ID: " + GymId);
        return gymAdminDAO.approveGym(GymId);
    }

    public boolean cancelGymApproval(Long GymId) {
        System.out.println("[Admin Service] Attempting to cancel approval for Gym Centre with ID: " + GymId);
        return gymAdminDAO.cancelGymApproval(GymId);
    }

    public boolean approveGymOwner(Long gymOwnerId) { // New method
        System.out.println("[Admin Service] Attempting to approve Gym Owner with ID: " + gymOwnerId);
        return gymAdminDAO.approveGymOwner(gymOwnerId);
    }

    public boolean cancelGymOwnerApproval(Long gymOwnerId) { // New method
        System.out.println("[Admin Service] Attempting to cancel approval for Gym Owner with ID: " + gymOwnerId);
        return gymAdminDAO.cancelGymOwnerApproval(gymOwnerId);
    }

    public List<GymCentre> viewRegisteredGyms() {
        System.out.println("[Admin Service] Fetching all registered gym centres.");
        return gymAdminDAO.getAllRegisteredGyms();
    }

//    public void checkPaymentStatus() {
//        // This method's contract is not defined in GymAdminDAO interface
//        // You might consider adding a method to GymAdminDAO if payment status
//        // needs to be retrieved from the persistence layer.
//        // For now, it remains an empty placeholder or can be removed if not needed.
//        System.out.println("[Admin Service] Checking payment status (implementation not mapped to DAO).");
//    }
    public List<GymOwner> getAllRegisteredGymOwners() {
      System.out.println("[Admin Service] Fetching all registered gym owners."); // Corrected message
      return gymAdminDAO.getAllRegisteredGymOwners();
    }

    public List<GymCustomer> viewRegisteredGymCustomers() {
        System.out.println("[Admin Service] Fetching all registered gym customers.");
        return gymAdminDAO.getAllRegisteredGymCustomers();
    }

    public boolean addGymOwner(long userId, String name, String email) {
        System.out.println("[Admin Service] Attempting to add new Gym Owner: " + name + " (ID: " + userId + ")");
        // Create a GymOwner object as expected by the DAO
        GymOwner newOwner = new GymOwner();
        newOwner.setUserId(userId);
        newOwner.setName(name);
        newOwner.setEmail(email);
        // Assuming GymOwner class has constructors/setters for these fields
        return gymAdminDAO.addGymOwner(newOwner);
    }

    public boolean removeGymOwner(long userId) {
        System.out.println("[Admin Service] Attempting to remove Gym Owner with ID: " + userId);
        return gymAdminDAO.removeGymOwner(userId);
    }

    public boolean removeGymCustomer(long userId) {
        System.out.println("[Admin Service] Attempting to remove Gym Customer with ID: " + userId);
        return gymAdminDAO.removeGymCustomer(userId);
    }

    public boolean removeGym(Long id) {
        System.out.println("[Admin Service] Attempting to remove Gym Centre with ID: " + id);
        return gymAdminDAO.removeGym(id);
    }
}
