
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


    public static void approveGym(Scanner in) {
        System.out.println("\n--- Approve Gym Centre ---");
        System.out.print("Enter the Gym Approval ID to approve: ");
        Long approvalId = in.nextLong();
        GymAdminService.approveGym(approvalId);
        System.out.println("Gym with Approval ID " + approvalId + " approved successfully.");
    }


    public static void cancelApproval(Scanner in) {
        System.out.println("\n Cancel Gym Approval");
        System.out.print("Enter the Gym Approval ID to cancel approval for: ");
        Long approvalId = in.nextLong();
        gymAdminService.cancelApproval(approvalId); // Call instance method
        System.out.println("Approval for Gym with ID " + approvalId + " cancelled.");
    }


    public static void viewRegisteredGyms() {
        System.out.println("\n Registered Gym Centres ");
        List<GymCentre> gymDetails = gymAdminService.viewRegisteredGyms();
        if (gymDetails == null || gymDetails.isEmpty()) {
            System.out.println("No registered gym centres found.");
            return;
        }

        System.out.printf("%s %s %s %s%n", "ID", "Name", "Address", "Approved");

        for (GymCentre gym : gymDetails) {
            System.out.printf("%-10d %-25s %-30s %-10s%n",
                    gym.getId(),// Assuming getGymId() returns int or long
                    gym.getName(),
                    gym.getAddress());

        }

    }


    public static void viewRegisteredGymCustomers() {
        System.out.println("\n Registered Gym Customers");
        List<GymCustomer> customerDetails = gymAdminService.viewRegisteredGymCustomers();
        if (customerDetails == null || customerDetails.isEmpty()) {
            System.out.println("No registered gym customers found.");
            return;
        }

        System.out.printf("%-10s %-25s %-20s%n", "ID", "Name", "Email");

        for (GymCustomer customer : customerDetails) {
            System.out.printf("%-10d %-25s %-20s%n",
                    customer.getUserId(),
                    customer.getName(),
                    customer.getEmail());
        }

    }


    public static void addGymOwner(Scanner in) {
        System.out.println("\n--- Add New Gym Owner ---");
        // In a real application, you'd collect details like name, email, password, etc.
        gymAdminService.addGymOwner(); // Call the service method
        System.out.println("Gym owner addition initiated. (Further details/confirmation needed)");
    }


    public static void removeGymOwner(Scanner in) {
        System.out.println("\n Remove Gym Owner");
        System.out.print("Enter the Gym Owner ID to remove: ");
        Long gymOwnerId = in.nextLong();
        gymAdminService.removeGymOwner(gymOwnerId);
        System.out.println("Gym owner with ID " + gymOwnerId + " removed.");
    }


    public static void removeGymCustomer(Scanner in) {
        System.out.println("\n Remove Gym Customer");
        System.out.print("Enter the Gym Customer ID to remove: ");
        Long gymCustomerId = in.nextLong();
        gymAdminService.removeGymCustomer(gymCustomerId);
        System.out.println("Gym customer with ID " + gymCustomerId + " removed.");
    }


    public static void removeGym(Scanner in) {
        System.out.println("\n Remove Gym Centre");
        System.out.print("Enter the Gym Centre ID to remove: ");
        Long gymId = in.nextLong();
        gymAdminService.removeGym(gymId);
        System.out.println("Gym centre with ID " + gymId + " removed.");
    }


    public static void Menu(Scanner in) throws Exception {
        while (true) {
            System.out.println("\n--- Gym Admin Menu ---");
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
                    approveGym(in);
                    break;
                case 2:
                    cancelApproval(in);
                    break;
                case 3:
                    viewRegisteredGyms();
                    break;
                case 4:
                    viewRegisteredGymCustomers();
                    break;
                case 5:
                    addGymOwner(in);
                    break;
                case 6:
                    removeGymOwner(in);
                    break;
                case 7:
                    removeGymCustomer(in);
                    break;
                case 8:
                    removeGym(in);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    
}
