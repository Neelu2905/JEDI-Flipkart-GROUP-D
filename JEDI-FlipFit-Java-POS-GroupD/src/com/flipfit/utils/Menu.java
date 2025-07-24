package com.flipfit.utils;

import com.flipfit.helper.LoginCredentials;
import com.flipfit.helper.PasswordUpdateData;
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
    System.out.println("\n\t\t 1. ADMIN");
    System.out.println("\t\t 2. OWNER");
    System.out.println("\t\t 3. CUSTOMER");

    System.out.print("\n\t[+] Enter your choice: ");

    int choice = sc.nextInt();

    String role = "";
    switch (choice) {
      case 1:
        role = "ADMIN";
        break;
      case 2:
        role = "OWNER";
        break;
      case 3:
        role = "CUSTOMER";
        break;
      default:
        System.err.println("Invalid choice");
    }

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

    System.out.print("\t Address: ");
    String address = sc.nextLine();

    System.out.print("\t Gender: ");
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
    System.out.println("\n\n[+] Register for the Gym Owner: ");
    System.out.print("\t Name: ");
    String name = sc.nextLine();

    System.out.print("\t Email: ");
    String email = sc.nextLine();

    System.out.print("\t Password: ");
    String password = sc.nextLine();

    System.out.print("\t Aadhaar Number: ");
    String aadhar = sc.nextLine();

    System.out.print("\t Pan Number: ");
    String pan = sc.nextLine();

    return new RegisterData.Builder()
        .setName(name)
        .setEmail(email)
        .setPassword(password)
        .setAadhar(aadhar)
        .setPan(pan)
        .build();
  }

  public static PasswordUpdateData passwordUpdateMenu() {
    Scanner sc = new Scanner(System.in);
    System.out.println("\n\n[+] Change Password: ");
    System.out.println("\t UserId: ");
    long userId = sc.nextLong();
    System.out.print("\t Old Password: ");
    String newPassword = sc.nextLine();
    System.out.print("\t New Password: ");
    String oldPassword = sc.nextLine();

    return new PasswordUpdateData(userId, oldPassword, newPassword);

  }
}
