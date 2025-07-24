package com.flipfit.dao; // A common convention for implementations

import com.flipfit.beans.GymOwner;
import com.flipfit.beans.GymCentre;
import com.flipfit.beans.Slot;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GymOwnerDAOImpl implements GymOwnerDAO {
    private Map<Long, GymOwner> gymOwners = new HashMap<>();
    private Map<Long, GymCentre> gymCentres = new HashMap<>();
    private Map<String, Slot> slots = new HashMap<>();

    // Helper method to generate a unique key for a slot
    private String getSlotKey(int gymId, Date date, Time time) {
        return gymId + "_" + date.getTime() + "_" + time.getTime();
    }

    private String getSlotKey(Slot slot) {
        return slot.getGymID() + "_" + slot.getDate().getTime() + "_" + slot.getTime().getTime();
    }

    // --- GymOwner Operations ---

    @Override
    public boolean addGymOwner(GymOwner gymOwner) {
        if (gymOwners.containsKey(gymOwner.getUserId())) {
            System.out.println("Error: Gym Owner with ID " + gymOwner.getUserId() + " already exists.");
            return false;
        }
        gymOwners.put(gymOwner.getUserId(), gymOwner);
        System.out.println("Gym Owner added: " + gymOwner.getName());
        return true;
    }

    @Override
    public GymOwner getGymOwnerById(long ownerId) {
        return gymOwners.get(ownerId);
    }

    @Override
    public boolean updateGymOwner(GymOwner gymOwner) {
        if (!gymOwners.containsKey(gymOwner.getUserId())) {
            System.out.println("Error: Gym Owner with ID " + gymOwner.getUserId() + " not found for update.");
            return false;
        }
        gymOwners.put(gymOwner.getUserId(), gymOwner); // Overwrites existing entry
        System.out.println("Gym Owner updated: " + gymOwner.getName());
        return true;
    }

    @Override
    public boolean deleteGymOwner(long ownerId) {
        if (!gymOwners.containsKey(ownerId)) {
            System.out.println("Error: Gym Owner with ID " + ownerId + " not found for deletion.");
            return false;
        }
        gymOwners.remove(ownerId);
        // In a real scenario, you might also delete associated gym centres and slots
        System.out.println("Gym Owner with ID " + ownerId + " deleted.");
        return true;
    }

    @Override
    public List<GymOwner> getAllGymOwners() {
        return new ArrayList<>(gymOwners.values());
    }


    // --- GymCentre Operations ---

    @Override
    public boolean addGymCentre(GymCentre gymCentre) {
        if (gymCentres.containsKey(gymCentre.getGymId())) {
            System.out.println("Error: Gym Centre with ID " + gymCentre.getGymId() + " already exists.");
            return false;
        }
        gymCentres.put(gymCentre.getGymId(), gymCentre);
        System.out.println("Gym Centre added: " + gymCentre.getName());
        return true;
    }

    @Override
    public List<GymCentre> getGymCentreByOwnerId(long ownerId) {
        return gymCentres.values().stream()
                .filter(gym -> gym.getOwnerId() == ownerId)
                .collect(Collectors.toList());
    }

    @Override
    public List<GymCentre> getAllGymCentres() {
        return new ArrayList<>(gymCentres.values());
    }

    @Override
    public GymCentre getGymCentreById(long centreId) { // Added this method as it's needed by GymCustomerService
        return gymCentres.get(centreId);
    }

    @Override
    public boolean deleteGymCentre(Long gymCentreId) {
        if (!gymCentres.containsKey(gymCentreId)) {
            System.out.println("Error: Gym Centre with ID " + gymCentreId + " not found for deletion.");
            return false;
        }
        gymCentres.remove(gymCentreId);
        // In a real scenario, you might also delete associated slots
        System.out.println("Gym Centre with ID " + gymCentreId + " deleted.");
        return true;
    }


    // --- Slot Operations ---

    @Override
    public boolean addSlot(Slot slot) {
        String key = getSlotKey(slot);
        if (slots.containsKey(key)) {
            System.out.println("Error: Slot for Gym ID " + slot.getGymID() + " at " + slot.getDate() + " " + slot.getTime() + " already exists.");
            return false;
        }
        slots.put(key, slot);
        System.out.println("Slot added for Gym ID " + slot.getGymID() + " at " + slot.getTime());
        return true;
    }

    @Override
    public List<Slot> getSlotsByGymIdAndDate(int gymId, Date date) {
        return slots.values().stream()
                .filter(s -> s.getGymID() == gymId && s.getDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public Slot getSlotByDetails(int gymId, Date date, Time time) {
        String key = getSlotKey(gymId, date, time);
        return slots.get(key);
    }

    @Override
    public boolean updateSlot(Slot slot) {
        String key = getSlotKey(slot);
        if (!slots.containsKey(key)) {
            System.out.println("Error: Slot for Gym ID " + slot.getGymID() + " at " + slot.getDate() + " " + slot.getTime() + " not found for update.");
            return false;
        }
        slots.put(key, slot); // Overwrites existing entry
        System.out.println("Slot updated for Gym ID " + slot.getGymID() + " at " + slot.getTime());
        return true;
    }

    @Override
    public boolean deleteSlot(int gymId, Date date, Time time) {
        String key = getSlotKey(gymId, date, time);
        if (!slots.containsKey(key)) {
            System.out.println("Error: Slot for Gym ID " + gymId + " at " + date + " " + time + " not found for deletion.");
            return false;
        }
        slots.remove(key);
        System.out.println("Slot for Gym ID " + gymId + " at " + time + " on " + date + " deleted.");
        return true;
    }

    @Override
    public List<Slot> getAllSlots() {
        return new ArrayList<>(slots.values());
    }
}
