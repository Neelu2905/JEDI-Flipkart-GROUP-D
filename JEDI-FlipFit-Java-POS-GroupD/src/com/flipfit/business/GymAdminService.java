package com.flipfit.business;
import com.flipfit.beans.GymAdmin;
import com.flipfit.beans.GymCentre;
import com.flipfit.beans.GymCustomer;

import java.util.List;
public class GymAdminService {
    public static void approveGym(Long approvalId){
    }
    public void cancelApproval(Long approvalId){
    }
    public List<GymCentre> viewRegisteredGyms(){
        return null;
    }
    public List<GymCustomer> viewRegisteredGymCustomers(){
        return null;
    }
    public void addGymOwner(long userId, long name, long email){
    }
    public void removeGymOwner(long userId){
    }
    public void removeGymCustomer(long userId){
    }
    public void removeGym(Long id){
    }
}
