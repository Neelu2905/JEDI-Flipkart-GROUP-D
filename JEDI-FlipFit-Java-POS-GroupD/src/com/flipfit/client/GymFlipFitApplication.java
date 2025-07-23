package com.flipfit.client;

import com.flipfit.constants.Constants;
import com.flipfit.helper.LoginCredentials;
import com.flipfit.utils.Menu;
import java.util.Scanner;

public class GymFlipFitApplication {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    String bannerText = Constants.BANNER;

    System.out.println(bannerText);
    int mainMenuChoice = 0;
    do{
      Menu.displayMainMenu();
      mainMenuChoice = scanner.nextInt();

      switch (mainMenuChoice) {
        case 1:
          LoginCredentials loginDetails = Menu.loginMenu();
          // Call user service for login options

          // Throws exceptions and return to main menu if there are any error
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
