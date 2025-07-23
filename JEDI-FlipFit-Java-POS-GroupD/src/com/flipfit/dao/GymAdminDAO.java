package com.flipfit.dao;

import com.flipfit.beans.GymCentre;
import com.flipfit.beans.GymCustomer;
import com.flipfit.beans.GymOwner;

import java.util.List;
import java.util.Map;

/**
 * Interface for Data Access Object (DAO) operations related to Gym Administration.
 * This interface defines the contract for interacting with the persistence layer
 * for administrative tasks such as managing gyms, gym owners, and gym customers.
 */
public interface GymAdminDAO {

    /**
     * Approves a gym centre by updating its status in the persistence layer.
     * @param gymCentreId The unique identifier of the gym centre to be approved.
     * @return true if the approval was successful, false otherwise.
     */
    boolean approveGym(Long gymCentreId);

    /**
     * Cancels the approval of a gym centre, effectively setting its status to pending or rejected.
     * @param gymId The unique identifier of the gym centre whose approval is to be cancelled.
     * @return true if the cancellation was successful, false otherwise.
     */
    boolean cancelGymApproval(Long gymId);
    /**
     * Approves a gym owner by updating their status in the persistence layer.
     * @param gymOwnerId The unique identifier of the gym owner to be approved.
     * @return true if the approval was successful, false otherwise.
     */
    boolean approveGymOwner(Long gymOwnerId); // New method

    /**
     * Cancels the approval of a gym owner, effectively setting their status to pending or rejected.
     * @param gymOwnerId The unique identifier of the gym owner whose approval is to be cancelled.
     * @return true if the cancellation was successful, false otherwise.
     */
    boolean cancelGymOwnerApproval(Long gymOwnerId); // New method
    /**
     * Retrieves a list of all registered gym centres from the persistence layer.
     * @return A list of GymCentre objects, or an empty list if no gym centres are registered.
     */


    List<GymCentre> getAllRegisteredGyms();

    /**
     * Retrieves a list of all registered gym customers from the persistence layer.
     * @return A list of GymCustomer objects, or an empty list if no customers are registered.
     */
    List<GymCustomer> getAllRegisteredGymCustomers();

    /**
     * Retrieves a list of all registered gym owners from the persistence layer.
     * @return A list of GymOwner objects, or an empty list if no owners are registered.
     */
    List<GymOwner> getAllRegisteredGymOwners();

    /**
     * Adds a new gym owner to the persistence layer.
     * @param gymOwner The GymOwner object to be added.
     * @return true if the gym owner was added successfully, false otherwise.
     */
    boolean addGymOwner(GymOwner gymOwner);

    /**
     * Removes a gym owner from the persistence layer based on their user ID.
     * This might involve soft deletion (marking as inactive) or hard deletion.
     * @param userId The unique identifier of the gym owner to be removed.
     * @return true if the gym owner was removed successfully, false otherwise.
     */
    boolean removeGymOwner(long userId);

    /**
     * Removes a gym customer from the persistence layer based on their user ID.
     * This might involve soft deletion (marking as inactive) or hard deletion.
     * @param userId The unique identifier of the gym customer to be removed.
     * @return true if the gym customer was removed successfully, false otherwise.
     */
    boolean removeGymCustomer(long userId);

    /**
     * Removes a gym centre from the persistence layer based on its ID.
     * This might involve soft deletion (marking as inactive) or hard deletion.
     * @param gymId The unique identifier of the gym centre to be removed.
     * @return true if the gym centre was removed successfully, false otherwise.
     */
    boolean removeGym(Long gymId);

    // The checkPaymentStatus method was not explicitly in the DAO interface provided.
    // If needed, you would add a method signature here.
}
