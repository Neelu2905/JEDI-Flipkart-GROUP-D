package com.flipfit.client;
import java.util.Scanner;
import com.flipfit.beans.GymCentre;
import com.flipfit.beans.GymOwner;
import com.flipfit.business.GymOwnerService;
import com.flipfit.business.GymCentreService;
import com.flipfit.business.GymUserService;
import com.flipfit.client.GymClient;

public class GymOwnerClient implements GymClient {
    public static void Menu() {
        GymOwnerService gymOwnerService = new GymOwnerService();
        GymOwner gymOwner = new GymOwner();
        GymUserService gymUserService = new GymUserService();
        Scanner in = new Scanner(System.in);
        int option;

        System.out.println("1.Gym Centre Registration");
        System.out.println("2.Edit Slots");
        System.out.println("3.View Registered Gyms");
        System.out.println("4.View Registered and free slots");
        System.out.println("5.Edit Profile");
        System.out.println("6.Exit");
        option = in.nextInt();

        switch(option){
            case 1:
                // Registers a Gym Centre
                break;
            case 2:
                int gymCenterId;
                Scanner inputScanner = new Scanner(System.in);
                System.out.println("Enter Gym Centre Id:");
                gymCenterId = inputScanner.nextInt();
                break;
            case 3:
                // Returns a list of registered gyms by the gym owner
                break;
            case 4:
                // Returns a list of registered and free slots

                break;
            case 5:
                // Edit profile
                System.out.println("1. Edit name");
                System.out.println("2. Edit email address");

                int subOption = in.nextInt();

                if(subOption == 1) {
                    String name = in.next();
                    gymOwner.setName(name);
                    System.out.println("Name changed successfully");
                }

                if(subOption == 2) {
                    String email = in.next();
                    gymOwner.setEmail(email);
                    System.out.println("Email changed successfully");
                }

                break;
            default:
                //GymApplicationClient gymApplication = new GymApplicationClient();
                //gymApplication.main(null);
                break;
        }
    }
}
