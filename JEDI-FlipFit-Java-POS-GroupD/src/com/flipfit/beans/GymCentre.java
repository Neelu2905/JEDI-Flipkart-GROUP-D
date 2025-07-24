package com.flipfit.beans;

import java.util.ArrayList;
import java.util.List;

public class GymCentre {
    private Long gymid;
    private Long ownerId;
    private String name;
    private String address;
    private String GSTIN;
    private int equipments;
    private List<Slot> slotList;

    public GymCentre(Long gymId, String gymName, String address, String gstin, int equipments, ArrayList<Slot> es, long userId) {
        this.gymid = gymId;
        this.ownerId = userId;
        this.name = gymName;
        this.address = address;
        this.GSTIN = gstin;
        this.equipments = equipments;
        this.slotList = es;

    }


    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getGymid() {
        return gymid;
    }

    public void setGymid(Long gymid) {
        this.gymid = gymid;
    }

    public Long getGymId() {
        return gymid;
    }

    public void setId(Long id) {
        this.gymid = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGSTIN() {
        return GSTIN;
    }

    public void setGSTIN(String GSTIN) {
        this.GSTIN = GSTIN;
    }

    public int getEquipments() {
        return equipments;
    }

    public void setEquipments(int equipments) {
        this.equipments = equipments;
    }

    public List<Slot> getSlotList() {
        return slotList;
    }

    public void setSlotList(List<Slot> slotList) {
        this.slotList = slotList;
    }

    @Override
    public String toString() {
        return "GymCentre{" +
                "ID=" + gymid +
                ", OwnerID=" + ownerId +
                ", Name='" + name + '\'' +
                ", Address='" + address + '\'' +
                ", GSTIN='" + GSTIN + '\'' +
                ", Equipments=" + equipments +
                // You might want to include a summary of slots, e.g., size
                // ", SlotsCount=" + (slotList != null ? slotList.size() : 0) +
                '}';
    }

}
