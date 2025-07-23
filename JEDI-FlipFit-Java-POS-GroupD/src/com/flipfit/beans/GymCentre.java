package com.flipfit.beans;

import java.util.List;

public class GymCentre {
    private Long gymid;
    private String name;
    private String address;
    private String GSTIN;
    private int equipments;
    private List<Slot> slotList;

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

}
