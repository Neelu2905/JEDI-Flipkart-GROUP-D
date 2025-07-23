package com.flipfit.business;

import com.flipfit.beans.Slot;
import com.flipfit.beans.GymCentre;
import java.util.List;

public interface GymCentreServiceInterface {
    List<Slot> viewAvailableSlots(List<Slot> slots, GymCentre gymCentre);
}
