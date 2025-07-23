package com.flipfit.utils;

import com.flipfit.helper.LoginCredentials;
import com.flipfit.helper.RegisterData;
import java.util.Scanner;

public class Menu {
  public static void displayMainMenu() {
    System.out.println("\n\nWelcome to Flip Fit Application\n");
    System.out.println("\t1. Login");
    System.out.println("\t2. Register for the Gym Customer");
    System.out.println("\t3. Register for the Gym Owner");
    System.out.println("\t4. Change Password");
    System.out.println("\t5. Exit");

    System.out.print("\n[+] Enter your choice: ");
  }

  public static LoginCredentials loginMenu() {
    Scanner sc = new Scanner(System.in);
    System.out.println("\n\n[+] Login: ");
    System.out.print("\t Username: ");
    String username = sc.nextLine();
    System.out.print("\t Password: ");
    String password = sc.nextLine();
    System.out.print("\t Role: ");
    String role = sc.nextLine();

    return new LoginCredentials(username, password, role);
  }

  public static void changePasswordMenu() {

  }

  public static RegisterData registerCustomerMenu() {
    Scanner sc = new Scanner(System.in);
    System.out.println("\n\n[+] Register for the Gym Customer: ");
    System.out.print("\t Name: ");
    String name = sc.nextLine();

    System.out.print("\t Email: ");
    String email = sc.nextLine();

    System.out.print("\t Password: ");
    String password = sc.nextLine();

    System.out.println("\t Address: ");
    String address = sc.nextLine();

    System.out.println("\t Gender: ");
    String gender = sc.nextLine();

    return new RegisterData.Builder()
        .setName(name)
        .setEmail(email)
        .setPassword(password)
        .setAddress(address)
        .setGender(gender)
        .build();
  }

  public static RegisterData registerOwnerMenu() {
    Scanner sc = new Scanner(System.in);
    System.out.println("\n\n[+] Register for the Gym Customer: ");
    System.out.print("\t Name: ");
    String name = sc.nextLine();

    System.out.print("\t Email: ");
    String email = sc.nextLine();

    System.out.print("\t Password: ");
    String password = sc.nextLine();

    System.out.print("\t Aadhaar Number: ");
    String aadhar = sc.nextLine();

    System.out.println("\t Pan Number: ");
    String pan = sc.nextLine();

    return new RegisterData.Builder()
        .setName(name)
        .setEmail(email)
        .setPassword(password)
        .setAadhar(aadhar)
        .setPan(pan)
        .build();
  }
}
