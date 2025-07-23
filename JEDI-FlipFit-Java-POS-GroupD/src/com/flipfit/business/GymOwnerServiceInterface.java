package com.flipfit.business;

import com.flipfit.beans.GymCentre;
import com.flipfit.beans.GymOwner;
import com.flipfit.beans.Slot;

import java.util.List;
import java.util.Date;
import java.sql.Time;

public interface GymOwnerServiceInterface {
    boolean registerGym(GymCentre gymCentre);
    boolean addSlot(Slot slot);
    boolean editSlot(Slot slot);
    List<GymCentre> viewRegisteredGyms(long ownerId);
    List<Slot> viewBookedAndAvailableSlots(Long gymCentreId, Date date);
    boolean editProfile(GymOwner gymOwner);
    GymOwner getGymOwnerProfile(long ownerId);
}
