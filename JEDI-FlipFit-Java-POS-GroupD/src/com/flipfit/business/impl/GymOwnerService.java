package com.flipfit.business.impl;

import com.flipfit.beans.GymCentre;
import com.flipfit.beans.GymOwner;
import com.flipfit.beans.Slot;
import com.flipfit.business.GymOwnerServiceInterface;
import com.flipfit.dao.GymOwnerDAO;
import com.flipfit.dao.GymOwnerDAOImpl; // Assuming this is the implementation you want to use

import java.util.List;
import java.util.Date;
import java.sql.Time;

public class GymOwnerService implements GymOwnerServiceInterface {

    private GymOwnerDAO gymOwnerDAO;

    public GymOwnerService() {
        this.gymOwnerDAO = new GymOwnerDAOImpl();
    }
    public GymOwnerService(GymOwnerDAO gymOwnerDAO) {
        this.gymOwnerDAO = gymOwnerDAO;
    }

    @Override
    public boolean registerGym(GymCentre gymCentre) {
        // Business logic: Validate gymCentre data before adding
        if (gymCentre == null || gymCentre.getName() == null || gymCentre.getName().trim().isEmpty() ||
                gymCentre.getAddress() == null || gymCentre.getAddress().trim().isEmpty()) {
            System.out.println("Service Error: Gym Centre name or address cannot be empty.");
            return false;
        }
        // Add more complex validation here (e.g., check for duplicate GSTIN, unique ID generation if not provided)

        boolean result = gymOwnerDAO.addGymCentre(gymCentre);
        if (result) {
            System.out.println("Service: Gym Centre '" + gymCentre.getName() + "' registered successfully.");
        } else {
            System.out.println("Service: Failed to register Gym Centre '" + gymCentre.getName() );
        }
        return result;
    }

    @Override
    public boolean addSlot(Slot slot) {
        // Business logic: Validate slot data
        if (slot == null || slot.getGymID() <= 0 || slot.getCapacity() <= 0 || slot.getDate() == null || slot.getTime() == null) {
            System.out.println("Service Error: Invalid slot data provided.");
            return false;
        }
        boolean result = gymOwnerDAO.addSlot(slot);
        if (result) {
            System.out.println("Service: Slot added for Gym ID " + slot.getGymID() + " at " + slot.getTime() + " on " + slot.getDate());
        } else {
            System.out.println("Service: Failed to add slot. It might already exist or data is invalid.");
        }
        return result;
    }

    @Override
    public boolean editSlot(Slot slot) {
        // Business logic: Validate slot data and ensure it exists before editing
        if (slot == null || slot.getGymID() <= 0 || slot.getDate() == null || slot.getTime() == null) {
            System.out.println("Service Error: Invalid slot data for editing.");
            return false;
        }
        boolean result = gymOwnerDAO.updateSlot(slot);
        if (result) {
            System.out.println("Service: Slot for Gym ID " + slot.getGymID() + " at " + slot.getTime() + " updated successfully.");
        } else {
            System.out.println("Service: Failed to update slot.");
        }
        return result;
    }

    @Override
    public List<GymCentre> viewRegisteredGyms(long ownerId) {
        // Business logic: Now fetching gyms specifically by owner ID using the DAO method
        List<GymCentre> registeredGyms = gymOwnerDAO.getGymCentreByOwnerId(ownerId);
        if (registeredGyms.isEmpty()) {
            System.out.println("Service: No gym centres found for owner ID " + ownerId + ".");
        } else {
            System.out.println("Service: Displaying registered gym centres for owner ID " + ownerId + ":");
        }
        return registeredGyms;
    }

    @Override
    public List<Slot> viewBookedAndAvailableSlots(Long gymCentreId, Date date) {
        // Business logic: Retrieve slots for the given gym and date
        if (gymCentreId == null || date == null) {
            System.out.println("Service Error: Gym Centre ID and Date are required to view slots.");
            return null;
        }
        // Convert Long gymCentreId to int gymId for Slot operations
        int gymIdInt = gymCentreId.intValue();

        List<Slot> slots = gymOwnerDAO.getSlotsByGymIdAndDate(gymIdInt, date);
        if (slots.isEmpty()) {
            System.out.println("Service: No slots found for Gym Centre ID " + gymCentreId + " on " + date);
        } else {
            System.out.println("Service: Retrieved " + slots.size() + " slots for Gym Centre ID " + gymCentreId + " on " + date);
        }
        return slots;
    }

    @Override
    public boolean editProfile(GymOwner gymOwner) {
        // Business logic: Validate owner profile data before updating
        if (gymOwner == null || gymOwner.getUserId() <= 0 || gymOwner.getName().trim().isEmpty()) {
            System.out.println("Service Error: Invalid Gym Owner profile data for editing.");
            return false;
        }
        boolean result = gymOwnerDAO.updateGymOwner(gymOwner);
        if (result) {
            System.out.println("Service: Gym Owner profile for " + gymOwner.getName() + " updated successfully.");
        } else {
            System.out.println("Service: Failed to update Gym Owner profile.");
        }
        return result;
    }

    @Override
    public GymOwner getGymOwnerProfile(long ownerId) {
        // Business logic: Retrieve owner profile
        GymOwner owner = gymOwnerDAO.getGymOwnerById(ownerId);
        if (owner == null) {
            System.out.println("Service: Gym Owner with ID " + ownerId + " not found.");
        } else {
            System.out.println("Service: Retrieved profile for Gym Owner " + owner.getName());
        }
        return owner;
    }
}
