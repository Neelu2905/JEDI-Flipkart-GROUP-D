package com.flipfit.client;

import java.util.List;
import java.util.Scanner;


import com.flipfit.beans.GymAdmin;
import com.flipfit.beans.GymCentre;
import com.flipfit.beans.GymCustomer;
import com.flipfit.beans.GymOwner;



import com.flipfit.business.GymAdminService;


public class GymAdminClient implements GymClient {


    public static GymAdminService gymAdminService = new GymAdminService();

    public void Menu()  {
        Scanner in = new Scanner(System.in);

        System.out.println("1. Approve Gym Centre");
        System.out.println("2. Cancel Gym Approval");
        System.out.println("3. View All Registered Gyms");
        System.out.println("4. View All Registered Gym Customers");
        System.out.println("5. Add Gym Owner");
        System.out.println("6. Remove Gym Owner");
        System.out.println("7. Remove Gym Customer");
        System.out.println("8. Remove Gym Centre");
        System.out.println("9. Exit");
        System.out.print("Enter your choice: ");

        int choice = in.nextInt();
        switch (choice) {
            case 1:
                Long approvalId = in.nextLong();
                System.out.print("Approving Gym Owner: ");
                gymAdminService.approveGym(approvalId);
                break;
            case 2:
                Long Id = in.nextLong();
                gymAdminService.cancelApproval(Id);
                System.out.println("Cancelled approval");
                break;
            case 3:
                gymAdminService.viewRegisteredGyms();
                System.out.println("View registered gyms");
                break;
            case 4:
                gymAdminService.viewRegisteredGymCustomers();
                System.out.println("View registered gym customers");
                break;
            case 5:
                long id=in.nextLong();
                String name=in.next();
                String email=in.next();
                gymAdminService.addGymOwner(id, name, email);
                System.out.println("Added owner to registered gyms");
                break;
            case 6:
                long removeId=in.nextLong();
                gymAdminService.removeGymOwner(removeId);
                System.out.println("Removed owner from registered gyms");
                break;
            case 7:
                long removeid=in.nextLong();
                gymAdminService.removeGymCustomer(removeid);
                System.out.println("Removed owner from registered gyms");
                break;
            case 8:
                long removegym=in.nextLong();
                gymAdminService.removeGym(removegym);
                System.out.println("Removed owner from registered gyms");
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
        }


    }



}