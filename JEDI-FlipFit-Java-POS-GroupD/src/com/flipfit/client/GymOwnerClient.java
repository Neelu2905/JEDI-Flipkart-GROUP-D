package com.flipfit.client;
import java.util.Scanner;
import com.flipfit.beans.GymCentre;
import com.flipfit.beans.GymOwner;
import com.flipfit.business.GymOwnerService;
import com.flipfit.business.GymCentreService;
import com.flipfit.business.GymUserService;
import com.flipfit.client.GymClient;
import com.flipfit.exceptions.AuthenticationException;

public class GymOwnerClient implements GymClient {
/*
    public static void main(String[] args) throws AuthenticationException {
        Menu();
    }

*/
    public void Menu() {
        GymOwnerService gymOwnerService = new GymOwnerService();
        GymOwner gymOwner = new GymOwner();
        GymUserService gymUserService = new GymUserService();
        int option=0;
        while(option!=6) {
            Scanner in = new Scanner(System.in);

            System.out.println("--- Gym Owner Menu ---");
            System.out.println("1.Gym Centre Registration");
            System.out.println("2.Edit Slots");
            System.out.println("3.View Registered Gyms");
            System.out.println("4.View Registered and Free Slots");
            System.out.println("5.Edit Profile");
            System.out.println("6.Logout");
            System.out.print("Enter your choice: ");
            option = in.nextInt();
            in.nextLine();


            switch (option) {
                case 1:
                    // Registers a Gym Centre
                    System.out.println("Option 1: Gym Centre Registration - (Not implemented)");
                    break;
                case 2:
                    System.out.println("Option 2: Edit Slots");
                    System.out.println("Enter Gym Centre Id:");
                    int gymCenterId = in.nextInt(); // Use 'in' scanner
                    in.nextLine(); // Consume newline after nextInt()
                    System.out.println("Entered Gym Centre Id for editing slots: " + gymCenterId);
                    break;
                case 3:
                    System.out.println("Option 3: View Registered Gyms - (Not implemented)");
                    // Returns a list of registered gyms by the gym owner
                    break;
                case 4:
                    System.out.println("Option 4: View Registered and Free Slots - (Not implemented)");
                    // Returns a list of registered and free slots
                    break;
                case 5:
                    // Edit profile
                    System.out.println("Option 5: Edit Profile");
                    System.out.println("1. Edit name");
                    System.out.println("2. Edit email address");

                    int subOption = in.nextInt();

                    if (subOption == 1) {
                        System.out.print("Enter new name: ");
                        String name = in.nextLine();
                        gymOwner.setName(name);
                        System.out.println("Name changed successfully to: " + gymOwner.getName());
                    }

                    if (subOption == 2) {
                        System.out.print("Enter new email: ");
                        String email = in.nextLine();
                        gymOwner.setEmail(email);
                        System.out.println("Email changed successfully to: " + gymOwner.getEmail());
                    }

                    break;
                case 6:
                    System.out.println("Logging Out. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
