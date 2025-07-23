package com.flipfit.dao;

import com.flipfit.beans.GymCentre;
import com.flipfit.beans.GymCustomer;
import com.flipfit.beans.GymOwner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GymAdminDAOImpl implements GymAdminDAO {

    // Using static maps to simulate a simple in-memory database for demonstration
    private final static Map<Long, GymCentre> gymCentres = new HashMap<>(); // Stores GymCentre objects by ID
    private final static Map<Long, GymCustomer> customers = new HashMap<>(); // Stores GymCustomer objects by userId
    private final static Map<Long, GymOwner> gymOwners = new HashMap<>(); // Stores GymOwner objects by userId
    private final static Map<Long, String> gymCentreStatuses = new HashMap<>(); // Stores status by GymCentre ID
    private final static Map<Long, String> gymOwnerStatuses = new HashMap<>();

    public boolean approveGym (Long gymCentreId){
        if (gymCentres.containsKey(gymCentreId)) {
            gymCentreStatuses.put(gymCentreId, "APPROVED");
            System.out.println("Gym Centre " + gymCentreId + " approved.");
            return true;
        }
        System.out.println("Gym Centre " + gymCentreId + " not found for approval.");
        return false;
    }

    public boolean cancelGymApproval(Long gymId) {
        if (gymCentres.containsKey(gymId)) {
            gymCentreStatuses.put(gymId, "CANCELLED"); // Or "PENDING", "REJECTED" based on your workflow
            System.out.println("Gym Centre " + gymId + " approval cancelled.");
            return true;
        }
        System.out.println("Gym Centre " + gymId + " not found for cancellation.");
        return false;
    }

    public boolean approveGymOwner(Long gymOwnerId) {
        if (gymOwners.containsKey(gymOwnerId)) {
            gymOwnerStatuses.put(gymOwnerId, "APPROVED");
            System.out.println("Gym Owner " + gymOwnerId + " approved.");
            return true;
        }
        System.out.println("Gym Owner " + gymOwnerId + " not found for approval.");
        return false;
    }


    public boolean cancelGymOwnerApproval(Long gymOwnerId) {
        if (gymOwners.containsKey(gymOwnerId)) {
            gymOwnerStatuses.put(gymOwnerId, "CANCELLED");
            System.out.println("Gym Owner " + gymOwnerId + " approval cancelled.");
            return true;
        }
        System.out.println("Gym Owner " + gymOwnerId + " not found for cancellation.");
        return false;
    }

    public boolean addGymOwner (GymOwner gymOwner){
        if (gymOwner == null) {
            System.out.println("Error: Cannot add a null Gym Owner.");
            return false;
        }
        // Check if a gym owner with the same ID already exists
        if (gymOwners.containsKey(gymOwner.getUserId())) {
            System.out.println("Error: Gym Owner with ID " + gymOwner.getUserId() + " already exists.");
            return false;
        }
        gymOwners.put(gymOwner.getUserId(), gymOwner);
        System.out.println("Gym Owner added: " + gymOwner.getName() + " (ID: " + gymOwner.getUserId() + ")");
        return true;
    }

    public boolean removeGym (Long gymId){
        if (!gymCentres.containsKey(gymId)) {
            System.out.println("Error: Gym Centre with ID " + gymId + " not found for removal.");
            return false;
        }
        gymCentres.remove(gymId);
        gymCentreStatuses.remove(gymId); // Remove status entry as well
        System.out.println("Gym Centre " + gymId + " removed.");
        return true;
    }

    public boolean removeGymOwner ( long userId){
        if (!gymOwners.containsKey(userId)) {
            System.out.println("Error: Gym Owner with ID " + userId + " not found for removal.");
            return false;
        }
        gymOwners.remove(userId);
        System.out.println("Gym Owner with ID " + userId + " removed.");
        return true;
    }

    public boolean removeGymCustomer ( long userId){
        if (!customers.containsKey(userId)) {
            System.out.println("Error: Gym Customer with ID " + userId + " not found for removal.");
            return false;
        }
        customers.remove(userId);
        System.out.println("Gym Customer with ID " + userId + " removed.");
        return true;
    }

    public List<GymCentre> getAllRegisteredGyms () {
        System.out.println("Fetching all registered gym centres.");
        return new ArrayList<>(gymCentres.values());
    }

    public List<GymCustomer> getAllRegisteredGymCustomers () {
        System.out.println("Fetching all registered gym customers.");
        return new ArrayList<>(customers.values());
    }

    public List<GymOwner> getAllRegisteredGymOwners () {
        System.out.println("Fetching all registered gym owners.");
        return new ArrayList<>(gymOwners.values());
    }
}
