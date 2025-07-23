package com.flipfit.business;

import com.flipfit.beans.GymCustomer;

import java.util.List;

public interface GymAdminServiceInterface {
    public void approveGym(Long approvalId);
    public void cancelApproval(Long approvalId);
    public void viewRegisteredGyms();
    public void addGymOwners(long userId, String name, String email);
    public void checkPaymentStatus();
    public List<GymCustomer> viewRegisteredGymCustomers();
    public void addGymOwner(long userId, String name, String email);
    public void removeGymOwner(long userId);
    public void removeGymCustomer(long userId);
    public void removeGym(Long id);
}
