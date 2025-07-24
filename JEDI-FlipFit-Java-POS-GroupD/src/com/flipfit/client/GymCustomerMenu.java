
package com.flipfit.client;

import java.util.Scanner;
import com.flipfit.beans.GymCustomer;
import com.flipfit.business.impl.GymCustomerService;


public class GymCustomerMenu implements GymClient {
    public void Menu() {

        GymCustomerService customerService = new GymCustomerService();
        GymCustomer customer = new GymCustomer();
        Scanner in = new Scanner(System.in);
        int option;

        do {
//       System.out.println(customerService.getCustomerDetails());
        System.out.println("\n--- Gym Application Menu ---");
        System.out.println("1. Search Gym");
        System.out.println("2. Book Slot");
        System.out.println("3. Edit Slot");
        System.out.println("4. View Booked slots");
        System.out.println("5. Cancel Booking");
        System.out.println("6. Make Payment");
        System.out.println("7. Edit Profile");
        System.out.println("8. Logout");

        option = in.nextInt();

            switch (option) {
                case 1:
                    // Search gym according to location
                    searchGyms();
                    break;

                case 2:
                    // Book Slot
                    bookSlot();
                    break;

                case 3:
                    // Edit slot
                    editSlot();
                    break;

                case 4:
                    // View Booked slots
                    viewBookedSlots();
                    break;
                case 5:
                    // Cancel Booking
                    cancelBooking();
                    break;
                case 6:
                    // Make Payment
                    makePayment();
                    break;

                case 7:

                    editProfile();
                    System.out.println("1. Edit name");
                    System.out.println("2. Edit email address");
                    System.out.println("3. Edit phone");

                    int subOption = in.nextInt();

                    switch (subOption) {
                        case 1:
                            System.out.print("Enter new name: ");
                            String name = in.next();
                            customer.setName(name);
                            System.out.println("Name changed successfully");
                            break;
                        case 2:
                            System.out.print("Enter new email address: ");
                            String email = in.next();
                            customer.setEmail(email);
                            System.out.println("Email changed successfully");
                            break;
                        case 3:
                            System.out.print("Enter new phone number: ");
                            int phone = in.nextInt();
                            customer.setCustomerPhone(phone);
                            System.out.println("Contact number changed successfully");
                            break;
                        default:
                            System.out.println("Invalid sub-option.");
                            break;
                    }
                    break;
                case 8:
                    // Exit and go back to gym application page
                    System.out.println("Logging you out...");
                    break;
                default:
                    System.out.println("Invalid option selected.");
                    break;
            }
        }
        while ( option != 8);
//        in.close();
      }

    private void searchGyms() {
        System.out.println("\n--- Search Gym ---");
        System.out.print("Enter location to search for gyms (e.g., Koramangala): ");
        String location = in.nextLine(); // Read location input

        System.out.println("Searching for gyms in " + location + "...");

        // Call the service method. As per GymCustomerService, this is a placeholder.
        // It's assumed viewGymCenters() might conceptually filter by location,
        // but the current service implementation just returns null.
        List<GymCentre> gymCenters = customerService.viewGymCenters();

        if (gymCenters != null && !gymCenters.isEmpty()) {
            System.out.println("Found Gym Centers (displaying all, not filtered by location in this demo):");
            for (GymCentre center : gymCenters) {
                System.out.println("ID: " + center.getCentreId() +
                        ", Name: " + center.getCentreName() +
                        ", Location: " + center.getLocation());
                // In a full application, you'd display more details like available slots, pricing etc.
            }
        } else {
            System.out.println("No gym centers found or the search functionality is not fully implemented yet.");
        }
    }

}