
package com.flipfit.client;

import java.util.Scanner;
import com.flipfit.beans.GymCustomer;
import com.flipfit.business.GymCustomerService;


public class GymCustomerClient implements GymClient {
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
                    System.out.println("Searching for gyms based on location...");
                    break;

                case 2:
                    // Search gym according to location
                    System.out.println("Booking your slot...");
                    break;

                case 3:
                    // Search gym according to location
                    System.out.println("Editing your slot...");
                    break;

                case 4:
                    // View Booked slots
                    System.out.println("Viewing booked slots...");
                    break;
                case 5:
                    // Cancel Booking
                    System.out.println("Cancelling booking...");
                    break;
                case 6:
                    // Make Payment
                    System.out.println("Proceeding to payment...");
                    break;

                case 7:
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

}