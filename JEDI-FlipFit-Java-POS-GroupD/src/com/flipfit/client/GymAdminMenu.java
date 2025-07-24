package com.flipfit.client;

import com.flipfit.beans.GymCentre;
import com.flipfit.beans.GymCustomer;
import com.flipfit.beans.GymOwner;
import com.flipfit.business.impl.GymAdminService;
import java.util.List;
import java.util.Scanner;

public class GymAdminMenu implements GymClient {

    private static final GymAdminService gymAdminService = new GymAdminService();
    private static final Scanner in = new Scanner(System.in);

    public void Menu() {
        int choice;
        do {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Approve Gym Centre");
            System.out.println("2. Cancel Gym Approval");
            System.out.println("3. View Registered Gyms");
            System.out.println("4. View Registered Gym Customers");
            System.out.println("5. View Registered Gym Owners");
            System.out.println("6. Add Gym Owner");
            System.out.println("7. Remove Gym Owner");
            System.out.println("8. Remove Gym Customer");
            System.out.println("9. Remove Gym Centre");
            // System.out.println("10. Check Payment Status"); // This function has no implementation yet
            System.out.println("11. Log out");
            System.out.print("Enter your choice: ");

            choice = in.nextInt();
            in.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter Gym Centre ID to approve: ");
                    long approvalId = in.nextLong();
                    gymAdminService.approveGym(approvalId);
                    break;
                case 2:
                    System.out.print("Enter Gym Centre ID to cancel approval: ");
                    long cancelId = in.nextLong();
                    gymAdminService.cancelGymApproval(cancelId);
                    break;
                case 3:
                    System.out.println("--- Registered Gyms ---");
                    List<GymCentre> gyms = gymAdminService.viewRegisteredGyms();
                    gyms.forEach(gym -> System.out.println("ID: " + gym.getGymId() + ", Name: " + gym.getName()));
                    break;
                case 4:
                    System.out.println("--- Registered Gym Customers ---");
                    List<GymCustomer> customers = gymAdminService.viewRegisteredGymCustomers();
                    customers.forEach(customer -> System.out.println("ID: " + customer.getUserId() + ", Name: " + customer.getName()));
                    break;
                case 5:
                    System.out.println("--- Registered Gym Owners ---");
                    List<GymOwner> owners = gymAdminService.getAllRegisteredGymOwners(); // Assuming you add this to the service/DAO
                    owners.forEach(owner -> System.out.println("ID: " + owner.getUserId() + ", Name: " + owner.getName()));
                    break;
                case 6:
                    System.out.print("Enter new Owner User ID: ");
                    long id = in.nextLong();
                    in.nextLine(); // Consume newline
                    System.out.print("Enter new Owner Name: ");
                    String name = in.nextLine();
                    System.out.print("Enter new Owner Email: ");
                    String email = in.nextLine();
                    gymAdminService.addGymOwner(id, name, email);
                    break;
                case 7:
                    System.out.print("Enter Gym Owner ID to remove: ");
                    long removeOwnerId = in.nextLong();
                    gymAdminService.removeGymOwner(removeOwnerId);
                    break;
                case 8:
                    System.out.print("Enter Gym Customer ID to remove: ");
                    long removeCustomerId = in.nextLong();
                    gymAdminService.removeGymCustomer(removeCustomerId);
                    break;
                case 9:
                    System.out.print("Enter Gym Centre ID to remove: ");
                    long removeGymId = in.nextLong();
                    gymAdminService.removeGym(removeGymId);
                    break;
                case 10:
                    System.out.println("Check Payment Status - Not implemented.");
                    break;
                case 11:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 11); // Corrected loop condition
    }
}
