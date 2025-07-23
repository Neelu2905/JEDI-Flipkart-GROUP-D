package com.flipfit.business;

import com.flipfit.beans.Slot;

import java.util.List;

public interface SlotServiceInterface {
    public List<Slot> viewAvailableSlots(List<Slot> s);
    public void bookSlot(List<Slot> s);
    public void cancelSlot(List<Slot> s);
}
