package com.flipfit.dao;

import com.flipfit.beans.GymOwner;
import com.flipfit.beans.GymCentre;
import com.flipfit.beans.Slot;

import java.util.List;
import java.util.Date; // Required for Slot's date field
import java.sql.Time; // Required for Slot's time field

public interface GymOwnerDAO {

    boolean addGymOwner(GymOwner gymOwner);
    GymOwner getGymOwnerById(long ownerId);
    boolean updateGymOwner(GymOwner gymOwner);
    boolean deleteGymOwner(long ownerId);
    List<GymOwner> getAllGymOwners();

    boolean addGymCentre(GymCentre gymCentre);
    List<GymCentre> getGymCentreByOwnerId(long ownerId);

    List<GymCentre> getAllGymCentres();
    boolean deleteGymCentre(Long gymCentreId);
    GymCentre getGymCentreById(long gymCentreId);
    boolean addSlot(Slot slot);
    List<Slot> getSlotsByGymIdAndDate(int gymId, Date date);
    Slot getSlotByDetails(int gymId, Date date, Time time);
    boolean updateSlot(Slot slot);
    boolean deleteSlot(int gymId, Date date, Time time);
    List<Slot> getAllSlots();
}
