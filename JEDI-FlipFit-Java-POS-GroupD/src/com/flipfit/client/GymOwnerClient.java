package com.flipfit.client;
import java.util.Scanner;
import com.flipfit.business.GymOwnerService;
import com.flipfit.business.GymCentreService;
import com.flipfit.business.GymUserService;
public class GymOwnerClient {
    public static void main(String[] args) {
        GymOwnerService gymOwnerService = new GymOwnerService();
        GymUserService gymUserService = new GymUserService();
        Scanner in = new Scanner(System.in);
        int option;

        System.out.println("1.Registration");
        System.out.println("2.Login");
        System.out.println("3.Gym Centre Registration");
        System.out.println("4.Edit Slots");
        System.out.println("5.View Registered Gyms");
        System.out.println("6.View Registered and free slots");
        System.out.println("7.Edit Profile");
        System.out.println("8.Exit");
        option = in.nextInt();

        switch(option){
            case 1:
                gymUserService.registerUser();
                break;
            case 2:
                gymUserService.loginUser();
                break;
            case 3:
                // Registers a Gym Centre
                break;
            case 4:
                int gymCenterId;
                Scanner inputScanner = new Scanner(System.in);
                System.out.println("Enter Gym Centre Id:");
                gymCenterId = inputScanner.nextInt();
                break;
            case 5:
                // Returns a list of registered gyms by the gym owner
                break;
            case 6:
                // Returns a list of registered and free slots
                break;
            case 7:
                // Edit profile
                break;
            default:
                //GymApplicationClient gymApplication = new GymApplicationClient();
                //gymApplication.main(null);
                break;
        }
    }
}
