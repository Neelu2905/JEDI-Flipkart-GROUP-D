
package com.flipfit.client;

import java.util.Scanner;

import com.flipfit.beans.Booking;
import com.flipfit.beans.GymCustomer;
import com.flipfit.business.impl.GymCustomerService;
import com.flipfit.beans.GymCentre;
import com.flipfit.beans.Payment;
import java.util.List;
import java.time.LocalDate;

public class GymCustomerMenu implements GymClient {
    Scanner in = new Scanner(System.in);
    GymCustomerService customerService;
    GymCustomer customer = new GymCustomer();
    private GymCustomer loggedInCustomer;

    public GymCustomerMenu() {
        this.in = new Scanner(System.in);
        this.customerService = new GymCustomerService();

        System.out.print("Enter your Gym Customer ID (Long): ");
        Long ownerIdToFetch = null;
        ownerIdToFetch = in.nextLong();
        in.nextLine();
        // In a real application, this would come from a login method.
        this.loggedInCustomer = customerService.getCustomerDetails(ownerIdToFetch);
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
        System.out.println("\n--- Gym Application Menu ---");
        System.out.println("1. Search Gym");
        System.out.println("2. Book Slot");
        System.out.println("3. View Booked slots");
        System.out.println("4. Cancel Booking");
        System.out.println("5. Make Payment");
        System.out.println("6. Edit Profile");
        System.out.println("7. Logout");

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
                    // View Booked slots
                    viewBookedSlots();
                    break;
                case 4:
                    // Cancel Booking
                    cancelBooking();
                    break;
                case 5:
                    // Make Payment
                   makePayment();
                    break;

                case 6:

                    editProfile();
                    break;
                case 7:
                    // Exit and go back to gym application page
                    System.out.println("Logging you out...");
                    break;
                default:
                    System.out.println("Invalid option selected.");
                    break;
            }
        }
        while ( option != 7);
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
                    ", Slot ID=" + booked.getSlotId() + // Assuming getSlotId() from Booking bean
                    ", Transaction ID=" + booked.getTransactionId() +
                    ", Status=" + (booked.isWaitlistStatus()?"Booked":"Waitlisted"));
        } else {
            System.out.println("Failed to book slot. Please check inputs, ensure gym/slot exist, or try again.");
        }
    }


    private void viewBookedSlots() {
        System.out.println("\n--- View Booked Slots ---");
        // Ensure a customer is logged in
        if (loggedInCustomer == null) {
            System.out.println("No customer logged in to view booked slots.");
            return;
        }

        // Call the service to get all bookings for the logged-in customer
        List<Booking> bookings = customerService.getAllBookingsByCustomerId(loggedInCustomer.getUserId());

        if (bookings.isEmpty()) {
            System.out.println("You have no booked slots.");
        } else {
            System.out.println("Your booked slots:");
            // Iterate and display details of each booking
            for (Booking booking : bookings) {
                System.out.println("------------------------------------");
                System.out.println("Booking ID: " + booking.getBookingId());   // Assuming getGymId() exists in Booking bean
                System.out.println("Slot ID:    " + booking.getSlotId());
                System.out.println("Transaction ID: " + booking.getTransactionId());
                System.out.println("Status:     " + (booking.isWaitlistStatus()?"Booked":"Waitlisted"));
                System.out.println("------------------------------------");
            }
        }
    }



    private void cancelBooking() {
        System.out.println("\n--- Cancel Booking ---");
        // Ensure a customer is logged in
        if (loggedInCustomer == null) {
            System.out.println("No customer logged in to cancel booking.");
            return;
        }

        System.out.print("Enter Booking ID to cancel: ");
        long bookingId = in.nextLong();
        in.nextLine(); // Consume newline after reading long

        // Optional: Verify the booking exists and belongs to the logged-in customer
        // This adds an extra layer of security/validation at the UI level.
        Booking bookingToCancel = customerService.getBookingById(bookingId);
        if (bookingToCancel == null) {
            System.out.println("Booking with ID " + bookingId + " not found.");
            return;
        }
        if (bookingToCancel.getCustomerId() != loggedInCustomer.getUserId()) {
            System.out.println("You do not have permission to cancel booking ID " + bookingId + ".");
            return;
        }

        System.out.println("Attempting to cancel booking " + bookingId + "...");
        boolean cancelled = customerService.cancelSlot(bookingId); // Call the service method

        if (cancelled) {
            System.out.println("Booking " + bookingId + " cancelled successfully!");
        } else {
            System.out.println("Failed to cancel booking " + bookingId + ". Please check details or try again.");
        }
    }



    private void makePayment() {
        System.out.println("\n--- Make Payment ---");
        if (loggedInCustomer == null) {
            System.out.println("No customer logged in to make a payment.");
            return;
        }

        System.out.print("Enter amount to pay: ");
        double amount = in.nextDouble();
        in.nextLine(); // Consume newline

        if (amount <= 0) {
            System.out.println("Payment amount must be positive.");
            return;
        }

        System.out.print("Enter payment method (card/upi): ");
        String paymentMethod = in.nextLine().trim();

        String details = "";
        if (paymentMethod.equalsIgnoreCase("card")) {
            System.out.print("Enter Card Number: ");
            details = in.nextLine().trim();
            if (details.isEmpty()) {
                System.out.println("Card number cannot be empty.");
                return;
            }
        } else if (paymentMethod.equalsIgnoreCase("upi")) {
            System.out.print("Enter UPI ID: ");
            details = in.nextLine().trim();
            if (details.isEmpty()) {
                System.out.println("UPI ID cannot be empty.");
                return;
            }
        } else {
            System.out.println("Invalid payment method. Only 'card' or 'upi' are accepted.");
            return;
        }

        // Create a Payment object to send to the service layer
        Payment newPayment = new Payment();
        newPayment.setCustomerid(loggedInCustomer.getUserId());
        newPayment.setAmount(amount);
        newPayment.setPaymentMethod(paymentMethod);
        newPayment.setPaymentDate(LocalDate.now()); // Set current date
        newPayment.setPaymentStatus("PENDING"); // Initial status

        if (paymentMethod.equalsIgnoreCase("card")) {
            newPayment.setCardNumber(details);
            newPayment.setUpiId(null);
        } else { // upi
            newPayment.setUpiId(details);
            newPayment.setCardNumber(null);
        }

        System.out.println("Proceeding to process payment...");
        boolean success = customerService.makePayment(newPayment); // Call the service method

        if (success) {
            System.out.println("Payment processed successfully!");
            // In a real app, you might update a booking status here if payment is linked to a booking
            // newPayment.setPaymentStatus("SUCCESS"); // Update status if service confirms
        } else {
            System.out.println("Payment failed. Please check details or try again.");
            // newPayment.setPaymentStatus("FAILED"); // Update status if service confirms
        }
    }

    private void editProfile() {
        System.out.println("\n--- Edit Profile ---");
        if (loggedInCustomer == null) {
            System.out.println("No user logged in to edit profile.");
            return;
        }

        System.out.println("Current Profile Details:");
        System.out.println("Name:  " + loggedInCustomer.getName());
        System.out.println("Email: " + loggedInCustomer.getEmail());
        System.out.println("Phone: " + loggedInCustomer.getCustomerPhone());

        System.out.println("\nWhat would you like to edit?");
        System.out.println("1. Name");
        System.out.println("2. Email Address");
        System.out.println("3. Phone Number");
        System.out.print("Enter your choice (1-3): ");

        int subOption = in.nextInt();
        in.nextLine(); // Consume newline

        boolean profileChanged = false; // Flag to check if any field was actually updated

        switch (subOption) {
            case 1:
                System.out.print("Enter new name: ");
                String newName = in.nextLine();
                loggedInCustomer.setName(newName); // Update the name on the local object
                profileChanged = true;
                break;
            case 2:
                System.out.print("Enter new email address: ");
                String newEmail = in.nextLine();
                loggedInCustomer.setEmail(newEmail); // Update the email on the local object
                profileChanged = true;
                break;
            case 3:
                System.out.print("Enter new phone number: ");
                int newPhone = in.nextInt();
                in.nextLine(); // Consume newline
                loggedInCustomer.setCustomerPhone(newPhone); // Update the phone on the local object
                profileChanged = true;
                break;
            default:
                System.out.println("Invalid sub-option. No changes made.");
                break;
        }

        if (profileChanged) {
            // Call the service's general update method with the modified loggedInCustomer object
            customerService.updateCustomerProfile(loggedInCustomer);
            System.out.println("Profile updated successfully!");
            // It's good practice to refresh the local object from the DAO after update
            this.loggedInCustomer = customerService.getCustomerDetails(loggedInCustomer.getUserId());
        } else {
            System.out.println("Profile not updated.");
        }
    }

}