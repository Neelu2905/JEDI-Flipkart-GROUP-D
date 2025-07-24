package com.flipfit.client;

import java.util.Scanner;
import com.flipfit.beans.GymOwner;
import com.flipfit.beans.GymCentre;
import com.flipfit.beans.Slot;
import com.flipfit.business.GymOwnerServiceInterface; // Use the interface
import com.flipfit.business.impl.GymOwnerService; // Use the implementation
import com.flipfit.dao.GymUserDAOImpl;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GymOwnerMenu implements GymClient {

    // This is the new entry point Menu method as requested.
    // It sets up the required objects and then calls the detailed menu logic.
    @Override // Implementing the Menu method from GymClient if it exists, otherwise remove @Override
    public void Menu() {
        Scanner scanner = new Scanner(System.in);
        GymOwnerServiceInterface gymOwnerService = new GymOwnerService(); // Use the interface type
        GymUserDAOImpl gymUserDAO = new GymUserDAOImpl();

        // --- Take GymOwner details as input from the user ---
        System.out.println("--- Gym Owner Login/Registration ---");
//        System.out.println("User Map: " + gymUserDAO.getUserMap());
        System.out.println(gymUserDAO);
        System.out.print("Enter your Gym Owner ID (Long): ");
        long ownerId = 0L;
        while (!scanner.hasNextLong()) {
            System.out.println("Invalid input. Please enter a valid number for Owner ID.");
            scanner.next(); // Consume invalid input
            System.out.print("Enter your Gym Owner ID (Long): ");
        }
        ownerId = scanner.nextLong();
        scanner.nextLine(); // Consume the leftover newline

        System.out.print("Enter your Name: ");
        String ownerName = scanner.nextLine();

        System.out.print("Enter your Email: ");
        String ownerEmail = scanner.nextLine();

        System.out.print("Enter your Phone Number: ");
        String ownerPhone = scanner.nextLine();

        System.out.print("Enter your PAN Number: ");
        String ownerPanNo = scanner.nextLine();

        System.out.print("Enter your Aadhar Number: ");
        String ownerAadharNo = scanner.nextLine();

        // Create the GymOwner object with user-provided data
        GymOwner currentGymOwner = new GymOwner(ownerId, ownerName, ownerEmail, ownerPhone, ownerPanNo, ownerAadharNo);

        // Call the detailed menu logic, passing the initialized objects
        runDetailedMenu(scanner, gymOwnerService, currentGymOwner);

        // Close the scanner when the application loop finishes
        scanner.close();
        System.out.println("Thank you for using FlipFit. Goodbye!");
    }


    // This method now contains the detailed menu loop and logic.
    // It is called by the new public void Menu() method.
    public void runDetailedMenu(Scanner scanner, GymOwnerServiceInterface gymOwnerService, GymOwner currentGymOwner) {

        // This part would typically be handled by a login process in the calling application.
        // Display welcome message for the current owner.
        //System.out.println("\nWelcome, " + currentGymOwner.getName() + " (Gym Owner ID: " + currentGymOwner.getUserId() + ")");

        boolean running = true;
        while (running) {
            System.out.println("\n--- Gym Owner Menu ---");
            System.out.println("1. Gym Centre Registration");
            System.out.println("2. Add Slots");
            System.out.println("3. Edit Slots");
            System.out.println("4. View Registered Gyms");
            System.out.println("5. View Booked and Available Slots");
            System.out.println("6. Edit Profile");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");

            int option;
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume invalid input
                System.out.print("Enter your choice: ");
            }
            option = scanner.nextInt();
            scanner.nextLine(); // Consume the leftover newline

            switch (option) {
                case 1:
                    // Registers a Gym Centre
                    System.out.println("\n--- Gym Centre Registration ---");
                    System.out.print("Enter Gym Centre ID (Long): ");
                    Long gymId = null;
                    while (!scanner.hasNextLong()) {
                        System.out.println("Invalid input. Please enter a valid number for Gym ID.");
                        scanner.next();
                        System.out.print("Enter Gym Centre ID (Long): ");
                    }
                    gymId = scanner.nextLong();
                    scanner.nextLine();

                    System.out.print("Enter Gym Centre Name: ");
                    String gymName = scanner.nextLine();

                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();

                    System.out.print("Enter GSTIN: ");
                    String gstin = scanner.nextLine();

                    System.out.print("Enter number of equipments (int): ");
                    int equipments = 0;
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a number for equipments.");
                        scanner.next();
                        System.out.print("Enter number of equipments (int): ");
                    }
                    equipments = scanner.nextInt();
                    scanner.nextLine();

                    // For simplicity, slotList is empty initially or can be passed as null/empty list
                    GymCentre newGymCentre = new GymCentre(gymId, gymName, address, gstin, equipments, new ArrayList<>(), currentGymOwner.getUserId());

                    if (gymOwnerService.registerGym(newGymCentre)) {
                        System.out.println("Gym Centre registered successfully!");
                    } else {
                        System.out.println("Failed to register Gym Centre.");
                    }
                    break;

                case 2:
                    System.out.println("\n--- Add Slots ---");
                    System.out.print("Enter Gym Centre ID (int) for adding slots: ");
                    int slotGymId = 0;
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a valid number for Gym ID.");
                        scanner.next();
                        System.out.print("Enter Gym Centre ID (int) for adding slots: ");
                    }
                    slotGymId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter Slot Capacity: ");
                    int capacity = 0;
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a number for capacity.");
                        scanner.next();
                        System.out.print("Enter Slot Capacity: ");
                    }
                    capacity = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter Vacant slots: ");
                    int vacant = 0;
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a number for vacant slots.");
                        scanner.next();
                        System.out.print("Enter Vacant slots: ");
                    }
                    vacant = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter Date (YYYY-MM-DD): ");
                    String dateStr = scanner.nextLine();
                    Date date = null;
                    try {
                        date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                        break;
                    }

                    System.out.print("Enter Time (HH:MM:SS): ");
                    String timeStr = scanner.nextLine();
                    Time time = null;
                    try {
                        time = Time.valueOf(timeStr);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid time format. Please use HH:MM:SS.");
                        break;
                    }

                    Slot newSlot = new Slot(slotGymId, capacity, vacant, date, time);
                    if (gymOwnerService.addSlot(newSlot)) {
                        System.out.println("Slot added successfully!");
                    } else {
                        System.out.println("Failed to add slot.");
                    }
                    break;

                case 3:
                    System.out.println("\n--- Edit Slots ---");
                    System.out.print("Enter Gym Centre ID (int) of slot to edit: ");
                    int editSlotGymId = 0;
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a valid number for Gym ID.");
                        scanner.next();
                        System.out.print("Enter Gym Centre ID (int) of slot to edit: ");
                    }
                    editSlotGymId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter Date of slot to edit (YYYY-MM-DD): ");
                    String editDateStr = scanner.nextLine();
                    Date editDate = null;
                    try {
                        editDate = new SimpleDateFormat("yyyy-MM-dd").parse(editDateStr);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                        break;
                    }

                    System.out.print("Enter Time of slot to edit (HH:MM:SS): ");
                    String editTimeStr = scanner.nextLine();
                    Time editTime = null;
                    try {
                        editTime = Time.valueOf(editTimeStr);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid time format. Please use HH:MM:SS.");
                        break;
                    }

                    // For simplicity, we'll create a new Slot object with updated values.
                    System.out.print("Enter new Capacity: ");
                    int newCapacity = 0;
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a number for capacity.");
                        scanner.next();
                        System.out.print("Enter new Capacity: ");
                    }
                    newCapacity = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter new Vacant slots: ");
                    int newVacant = 0;
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a number for vacant slots.");
                        scanner.next();
                        System.out.print("Enter new Vacant slots: ");
                    }
                    newVacant = scanner.nextInt();
                    scanner.nextLine();

                    Slot updatedSlot = new Slot(editSlotGymId, newCapacity, newVacant, editDate, editTime);
                    if (gymOwnerService.editSlot(updatedSlot)) {
                        System.out.println("Slot updated successfully!");
                    } else {
                        System.out.println("Failed to update slot.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- View Registered Gyms ---");
                    List<GymCentre> registeredGyms = gymOwnerService.viewRegisteredGyms(currentGymOwner.getUserId());
                    if (registeredGyms != null && !registeredGyms.isEmpty()) {
                        registeredGyms.forEach(System.out::println);
                    } else {
                        System.out.println("No gyms registered by this owner.");
                    }
                    break;

                case 5:
                    System.out.println("\n--- View Booked and Available Slots ---");
                    System.out.print("Enter Gym Centre ID (Long) to view slots: ");
                    Long viewSlotGymCentreId = null;
                    while (!scanner.hasNextLong()) {
                        System.out.println("Invalid input. Please enter a valid number for Gym Centre ID.");
                        scanner.next();
                        System.out.print("Enter Gym Centre ID (Long) to view slots: ");
                    }
                    viewSlotGymCentreId = scanner.nextLong();
                    scanner.nextLine();

                    System.out.print("Enter Date (YYYY-MM-DD) to view slots: ");
                    String viewDateStr = scanner.nextLine();
                    Date viewDate = null;
                    try {
                        viewDate = new SimpleDateFormat("yyyy-MM-dd").parse(viewDateStr);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                        break;
                    }

                    List<Slot> slots = gymOwnerService.viewBookedAndAvailableSlots(viewSlotGymCentreId, viewDate);
                    if (slots != null && !slots.isEmpty()) {
                        slots.forEach(System.out::println);
                    } else {
                        System.out.println("No slots found for the specified gym and date.");
                    }
                    break;

                case 6:
                    System.out.println("\n--- Edit Profile ---");
                    System.out.println("1. Edit name");
                    System.out.println("2. Edit email address");
                    System.out.println("3. Edit phone number"); // Added phone number edit option

                    int subOption = 0;
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.next();
                        System.out.print("Enter your choice: ");
                    }
                    subOption = scanner.nextInt();
                    scanner.nextLine();

                    switch (subOption) {
                        case 1:
                            System.out.print("Enter new name: ");
                            String newName = scanner.nextLine();
                            currentGymOwner.setName(newName); // Update the in-memory object
                            if (gymOwnerService.editProfile(currentGymOwner)) { // Call service to persist
                                System.out.println("Name changed successfully to: " + currentGymOwner.getName());
                            } else {
                                System.out.println("Failed to update name.");
                            }
                            break;
                        case 2:
                            System.out.print("Enter new email: ");
                            String newEmail = scanner.nextLine();
                            currentGymOwner.setEmail(newEmail); // Update the in-memory object
                            if (gymOwnerService.editProfile(currentGymOwner)) { // Call service to persist
                                System.out.println("Email changed successfully to: " + currentGymOwner.getEmail());
                            } else {
                                System.out.println("Failed to update email.");
                            }
                            break;
                        default:
                            System.out.println("Invalid sub-option.");
                            break;
                    }
                    break;

                case 7:
                    System.out.println("Logging Out. Goodbye!");
                    running = false; // Exit the loop
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        // The scanner is not closed here as it's passed in from the caller.
        // The caller (e.g., main application) is responsible for closing it.
    }
}
