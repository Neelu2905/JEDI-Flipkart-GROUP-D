package com.flipfit.client;
import java.util.Scanner;
import com.flipfit.business.GymOwnerService;
import com.flipfit.business.GymCentreService;
import com.flipfit.business.GymUserService;
public class GymOwnerClient {
    public static void main(String[] args) {
        GymOwnerService gymOwnerService = new GymOwnerService();
        Scanner in = new Scanner(System.in);
        int option;

        System.out.println("1.Registration");
        System.out.println("2.Login");
        System.out.println("3.Gym Registration");
        System.out.println("4.Edit Slots");
        System.out.println("5.View Registered Gyms");
        System.out.println("6.View Registered and free slots");
        System.out.println("7.Edit Profile");
        System.out.println("8.Exit");
        option = in.nextInt();
        switch(option){
            case 1:
                GymUserService gymUserService = new GymUserService();
                gymUserService.registerUser();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            default:
                break;
        }
    }
}
