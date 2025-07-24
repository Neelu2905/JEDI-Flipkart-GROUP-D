package com.flipfit.beans;

public class GymOwner extends GymUser{
    private String panNo;
    private String aadharNo;

    public GymOwner(long ownerId, String ownerName, String ownerEmail, String ownerPhone, String ownerPanNo, String ownerAadharNo) {
        super();
    }

    public GymOwner() {

    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getAadharNo() {
        return aadharNo;
    }

    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }



}
