// In your GymOwner.java file
package com.flipfit.beans;

public class GymOwner extends GymUser {
    private String Phone;
    private String PanNo;
    private String AadharNo;
    private String approvalStatus; 

    
    public GymOwner() {
        super(); // Calls GymUser's no-argument constructor
        this.approvalStatus = "PENDING"; // Default status
    }

    
    public GymOwner(
            long Id,
            String Name,
            String Email,
            String Phone,
            String PanNo,
            String AadharNo
    ) {
        
        super(); 

        // Set inherited properties from GymUser
        this.setUserId(Id); // Set the userId inherited from GymUser
        this.setName(Name);   // Set the name inherited from GymUser
        this.setEmail(Email); // Set the email inherited from GymUser
        // You MUST also set a password for GymUser, even if it's a temporary one
        this.setPassword("temp_password_for_new_owner"); // IMPORTANT: Set a password!
        this.addRole(new Role("OWNER","owner"));// Add the OWNER role

        // Set GymOwner specific properties
        this.Phone = Phone;
        this.PanNo = PanNo;
        this.AadharNo = AadharNo;
        this.approvalStatus = "PENDING"; // Default status
    }

    // Getters and Setters for GymOwner-specific fields
    public String getPhone() { return Phone; }
    public void setPhone(String ownerPhone) { this.Phone = ownerPhone; }
    public String getPanNo() { return PanNo; }
    public void setPanNo(String ownerPanNo) { this.PanNo = ownerPanNo; }
    public String getAadharNo() { return AadharNo; }
    public void setAadharNo(String aadharNo) { this.AadharNo = aadharNo; }
    public String getApprovalStatus() { return approvalStatus; }
    public void setApprovalStatus(String approvalStatus) { this.approvalStatus = approvalStatus; }

    
}
