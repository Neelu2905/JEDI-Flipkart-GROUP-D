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
        int choice = 0;
        do {
            Scanner in = new Scanner(System.in);

            System.out.println("1. Approve Gym Centre");
            System.out.println("2. Cancel Gym Approval");
            System.out.println("3. View Registered Gyms");
            System.out.println("4. View Registered Gym Customers");
            System.out.println("5. View Registered Gym Owners");
            System.out.println("6. Add Gym Owner");
            System.out.println("7. Remove Gym Owner");
            System.out.println("8. Remove Gym Customer");
            System.out.println("9. Remove Gym Centre");
            System.out.println("10. Check Payment Status");
            System.out.println("11. Log out");
            System.out.print("Enter your choice: ");

            choice = in.nextInt();
            System.out.println(choice);

            switch (choice) {
                case 1:
                    System.out.print("Approving Gym Owner: ");
//                    long approvalId = in.nextLong();
//                    gymAdminService.approveGym(approvalId);
                    break;
                case 2:
                    System.out.println("Cancelled approval");
//                    long Id = in.nextLong();
//                    gymAdminService.cancelApproval(Id);

                    break;
                case 3:
                    System.out.println("View registered gyms");
//                    gymAdminService.viewRegisteredGyms();
                    break;

                case 4:
                    System.out.println("View registered gym customers");
//                    gymAdminService.viewRegisteredGymCustomers();
                    break;


                case 5:
                    System.out.println("View registered gym owners");
                    break;

                case 6:
                    System.out.println("Added owner to registered gyms");
//                    long id = in.nextLong();
//                    String name = in.next();
//                    String email = in.next();
//                    gymAdminService.addGymOwner(id, name, email);

                    break;
                case 7:
                    System.out.println("Removed owner from registered gyms");
//                    long removeId = in.nextLong();
//                    gymAdminService.removeGymOwner(removeId);

                    break;
                case 8:
                    System.out.println("Removed owner from registered gyms");
//                    long removeid = in.nextLong();
//                    gymAdminService.removeGymCustomer(removeid);

                    break;
                case 9:
                    System.out.println("Removed owner from registered gyms");
//                    long removegym = in.nextLong();
//                    gymAdminService.removeGym(removegym);

                    break;

                case 10:
                    System.out.println("Check Payment Status");
                    break;

            }
        }
        while(choice!=11);{
            System.out.println("Invalid choice. Please try again.");
        }
    }
}