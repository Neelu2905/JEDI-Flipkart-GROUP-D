package com.flipfit.client;

import com.flipfit.business.GymUserService;
import com.flipfit.constants.Constants;
import com.flipfit.exceptions.AuthenticationException;
import com.flipfit.helper.LoginCredentials;
import com.flipfit.utils.Menu;
import java.util.Scanner;

public class GymFlipFitApplication {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    String bannerText = Constants.BANNER;

    GymUserService gymUserService = new GymUserService();

    System.out.println(bannerText);
    int mainMenuChoice = 0;
    do{
      Menu.displayMainMenu();
      mainMenuChoice = scanner.nextInt();

      switch (mainMenuChoice) {
        case 1:
          LoginCredentials loginDetails = Menu.loginMenu();
          try {
            gymUserService.login(loginDetails);

            // Get Associated Menu for different roles
            try{
               GymClient client = GymClientFactory.getGymClient(loginDetails.getRole());
               client.Menu();
            } catch (IllegalArgumentException e) {
              System.err.println("[-] Error: " + e.getLocalizedMessage());
            }

          } catch (AuthenticationException e) {
            System.err.println("[-] Error: " + e.getLocalizedMessage());
          }
          break;

        case 2:

          // Register for the gym customer

          break;

        case 3:
          // Register for the gym owner

          break;

        case 4:
            // Change Password
          break;

        case 5:
          System.exit(0);
          break;

        default:
          System.err.println("Invalid menu choice. Please try again.");
          break;
      }

    } while(true);
  }
}
