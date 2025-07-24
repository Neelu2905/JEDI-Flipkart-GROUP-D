
package com.flipfit.client;

import java.util.Scanner;

import com.flipfit.beans.Booking;
import com.flipfit.beans.GymCustomer;
import com.flipfit.business.impl.GymCustomerService;
import com.flipfit.beans.GymCentre;
import java.util.List;

public class GymCustomerMenu implements GymClient {
    Scanner in = new Scanner(System.in);
    GymCustomerService customerService;
    GymCustomer customer = new GymCustomer();
    private GymCustomer loggedInCustomer;

    public GymCustomerMenu() {
        this.in = new Scanner(System.in);
        this.customerService = new GymCustomerService();

        // --- DEMO LOGIN ---
        // For demonstration, fetch a customer with ID 1 as the "logged-in" user.
        // In a real application, this would come from a login method.
        this.loggedInCustomer = customerService.getCustomerDetails(1);
        if (this.loggedInCustomer == null) {
            System.out.println("Warning: No customer found with ID 1 for demonstration. " +
                    "Please ensure sample data is loaded in GymCustomerDAOImpl " +
                    "or implement a proper login/registration flow first.");
            // You might want to handle this more robustly, e.g., prompt for signup or exit.
        }
    }

    public void Menu() {


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
                    //bookSlot();
                    break;

                case 3:
                    // Edit slot
                    //editSlot();
                    break;

                case 4:
                    // View Booked slots
                    //viewBookedSlots();
                    break;
                case 5:
                    // Cancel Booking
                    //cancelBooking();
                    break;
                case 6:
                    // Make Payment
                   // makePayment();
                    break;

                case 7:

                    //editProfile();
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
        List<GymCentre> gymCenters = customerService.viewAllGymCenters();

        if (gymCenters != null && !gymCenters.isEmpty()) {
            System.out.println("Found Gym Centers (displaying all, not filtered by location in this demo):");
            for (GymCentre center : gymCenters) {
                System.out.println("ID: " + center.getGymid() +
                        ", Name: " + center.getName() +
                        ", Location: " + center.getAddress());
                // In a full application, you'd display more details like available slots, pricing etc.
            }
        } else {
            System.out.println("No gym centers found or the search functionality is not fully implemented yet.");
        }
    }


    private void bookSlot() {
        System.out.println("\n--- Book Slot ---");

        System.out.println("Please view available gyms and their slots first (e.g., via Search Gym and then a 'View Slots' option).");
        // For this demo, we'll ask directly for IDs

        System.out.print("Enter Gym Centre ID where you want to book: ");
        long gymCentreId = in.nextLong();
        in.nextLine(); // Consume newline after reading long

        System.out.print("Enter Slot ID (e.g., 1 for 7-8 AM, consult gym schedule): ");
        long slotTimeId = in.nextLong();
        in.nextLine(); // Consume newline after reading long

        System.out.println("Attempting to book slot...");
        Booking booked = customerService.bookSlot(loggedInCustomer.getUserId(), gymCentreId, slotTimeId);

        if (booked != null) {
            System.out.println("Slot booked successfully!");
            System.out.println("Booking Details: ID=" + booked.getBookingId() +
//                    ", Gym ID=" + booked.getGymId() + // Assuming getGymId() from Booking bean
//                    ", Slot ID=" + booked.getSlotId() + // Assuming getSlotId() from Booking bean
//                    ", Date=" + booked.getBookingDate() +
//                    ", Status=" + booked.getBookingStatus());
        } else {
            System.out.println("Failed to book slot. Please check inputs, ensure gym/slot exist, or try again.");
        }
    }
}