package com.flipfit.client;

import java.util.Scanner;
import com.flipfit.beans.GymOwner;
import com.flipfit.beans.GymCentre;
import com.flipfit.beans.Slot;
import com.flipfit.beans.GymUser;
import com.flipfit.beans.Role; // Import the Role class
import com.flipfit.business.GymOwnerServiceInterface;
import com.flipfit.business.impl.GymOwnerService;
import com.flipfit.dao.GymUserDAOImpl;
import com.flipfit.exceptions.RoleAlreadyExistsException;
import com.flipfit.exceptions.RoleDoesNotExistsException;
import com.flipfit.exceptions.UserAlreadyExistsException;
import com.flipfit.exceptions.UserDoesNotExistsException;
import com.flipfit.constants.Constants; // IMPORTANT: Ensure this import points to your Constants class

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet; // For Set<Role>
import java.util.List;
import java.util.Set;   // For Set<Role>

public class GymOwnerMenu implements GymClient {

    @Override
    public void Menu() throws RoleDoesNotExistsException, RoleAlreadyExistsException {
        Scanner scanner = new Scanner(System.in);
        GymOwnerServiceInterface gymOwnerService = new GymOwnerService();
        GymUserDAOImpl gymUserDAO = new GymUserDAOImpl();
        System.out.println("--- Gym Owner Login/Registration ---");

        GymOwner currentGymOwner = null;
        System.out.println(gymUserDAO);

        // Loop until a valid GymOwner is logged in or registered
        while (currentGymOwner == null) {
            System.out.print("Do you have an existing Gym Owner account? (yes/no): ");
            String existingUserChoice = scanner.nextLine().trim().toLowerCase();

            if (existingUserChoice.equals("yes")) {
                System.out.print("Enter your Gym Owner ID (Long): ");
                Long ownerIdToFetch = null;
                if (scanner.hasNextLong()) {
                    ownerIdToFetch = scanner.nextLong();
                    scanner.nextLine(); // Consume newline
                } else {
                    System.out.println("Invalid input. Please enter a valid number for Owner ID.");
                    scanner.next(); // Consume invalid input
                    scanner.nextLine(); // Consume newline
                    continue; // Restart the loop
                }

                try {
                    GymUser fetchedUser = gymUserDAO.getUserById(ownerIdToFetch);
                    // FIX 1: REINSTATE instanceof GymOwner AND use Constants.OWNER
                    if (fetchedUser.hasRole(Constants.OWNER)) {
                        currentGymOwner = (GymOwner) fetchedUser;
                        System.out.println("Welcome back, " + currentGymOwner.getName() + "!");
                    } else {
                        // FIX 2: Make the message consistent with the constant
                        System.out.println("User with ID " + ownerIdToFetch + " is not registered as an " + Constants.OWNER + " or does not have the correct role.");
                    }
                } catch (UserDoesNotExistsException e) {
                    System.out.println(e.getMessage());
                } catch (ClassCastException e) {
                    System.out.println("Error: The fetched user is not a GymOwner. Please ensure GymOwner extends GymUser correctly.");
                }

            } else if (existingUserChoice.equals("no")) {
                System.out.println("\n--- Register New Gym Owner ---");

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

                // FIX 3: Get the "OWNER" Role object from the DAO using Constants.OWNER
                Role gymOwnerRole = gymUserDAO.getRoleByName(Constants.OWNER);
                if (gymOwnerRole == null) {
                    // This scenario indicates a critical setup error in GymUserDAOImpl if roles aren't pre-populated correctly
                    System.out.println("Critical error: '" + Constants.OWNER + "' role not found in the system. Please check system configuration.");
                    continue; // Restart the loop or exit
                }

                Set<Role> rolesForOwner = new HashSet<>();
                rolesForOwner.add(gymOwnerRole);

                // Create the GymOwner object without an ID (it will be set by addUser)
                // Assuming GymOwner constructor takes (String name, String email, String phone, String pan, String aadhar)
                GymOwner newGymOwner = new GymOwner(ownerName, ownerEmail, ownerPhone, ownerPanNo, ownerAadharNo);
                newGymOwner.setRoles(rolesForOwner); // Assign the roles to the new GymOwner object

                try {
                    // addUser returns GymUser, so cast it to GymOwner
                    currentGymOwner = (GymOwner) gymUserDAO.addUser(newGymOwner);
                    System.out.println("New Gym Owner registered successfully: " + currentGymOwner.getName() + " with ID: " + currentGymOwner.getUserId());
                } catch (UserAlreadyExistsException e) {
                    System.out.println("Registration failed: " + e.getMessage());
                } catch (UserDoesNotExistsException e) {
                    // This exception is for getUserById, less likely for addUser unless there's an internal check
                    System.out.println("Registration failed unexpectedly: " + e.getMessage());
                } catch (ClassCastException e) {
                    System.out.println("Error registering user: User type mismatch. Ensure GymOwner extends GymUser correctly and Role handling is consistent.");
                }

            } else {
                System.out.println("Invalid choice. Please enter 'yes' or 'no'.");
            }
        }

        // Now that currentGymOwner is set, proceed to the detailed menu
        System.out.println("\n" + gymUserDAO); // Print the entire DAO state (includes userMap and roleMap for debugging)

        runDetailedMenu(scanner, gymOwnerService, currentGymOwner);

        scanner.close();
        System.out.println("Thank you for using FlipFit. Goodbye!");
    }


    public void runDetailedMenu(Scanner scanner, GymOwnerServiceInterface gymOwnerService, GymOwner currentGymOwner) {

        System.out.println("\nWelcome, " + currentGymOwner.getName() + " (Gym Owner ID: " + currentGymOwner.getUserId() + ")");

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
                    System.out.println("\n--- Gym Centre Registration ---");
                    System.out.print("Enter Gym Centre ID (Long): ");
                    Long gymId = null;
                    while (gymId == null) {
                        if (scanner.hasNextLong()) {
                            gymId = scanner.nextLong();
                            scanner.nextLine();
                        } else {
                            System.out.println("Invalid input. Please enter a valid number for Gym ID.");
                            scanner.next();
                            scanner.nextLine();
                            System.out.print("Enter Gym Centre ID (Long): ");
                        }
                    }

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

                    GymCentre newGymCentre = new GymCentre(gymId, gymName, address, gstin, equipments, new ArrayList<>(), currentGymOwner.getUserId());

                    if (gymOwnerService.registerGym(newGymCentre)) {
                        System.out.println("Gym Centre registered successfully!");
                    } else {
                        System.out.println("Failed to register Gym Centre.");
                    }
                    break;

                case 2:
                    System.out.println("\n--- Add Slots ---");
                    System.out.print("Enter Gym Centre ID (Long) for adding slots: ");
                    Long slotGymId = null;
                    while (slotGymId == null) {
                        if (scanner.hasNextLong()) {
                            slotGymId = scanner.nextLong();
                            scanner.nextLine();
                        } else {
                            System.out.println("Invalid input. Please enter a valid number for Gym ID.");
                            scanner.next();
                            scanner.nextLine();
                            System.out.print("Enter Gym Centre ID (Long) for adding slots: ");
                        }
                    }

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

                    // FIX 4: Pass Long directly to Slot constructor (removed Math.toIntExact)
                    Slot newSlot = new Slot(Math.toIntExact(slotGymId), capacity, vacant, date, time); // Changed from Math.toIntExact
                    if (gymOwnerService.addSlot(newSlot)) {
                        System.out.println("Slot added successfully!");
                    } else {
                        System.out.println("Failed to add slot.");
                    }
                    break;

                case 3:
                    System.out.println("\n--- Edit Slots ---");
                    System.out.print("Enter Gym Centre ID (Long) of slot to edit: ");
                    Long editSlotGymId = null;
                    while (editSlotGymId == null) {
                        if (scanner.hasNextLong()) {
                            editSlotGymId = scanner.nextLong();
                            scanner.nextLine();
                        } else {
                            System.out.println("Invalid input. Please enter a valid number for Gym ID.");
                            scanner.next();
                            scanner.nextLine();
                            System.out.print("Enter Gym Centre ID (Long) of slot to edit: ");
                        }
                    }

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

                    // FIX 5: Pass Long directly to Slot constructor (removed Math.toIntExact)
                    Slot updatedSlot = new Slot(Math.toIntExact(editSlotGymId), newCapacity, newVacant, editDate, editTime); // Changed from Math.toIntExact
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
                        System.out.println("Gyms registered by " + currentGymOwner.getName() + ":");
                        registeredGyms.forEach(System.out::println);
                    } else {
                        System.out.println("No gyms registered by this owner.");
                    }
                    break;

                case 5:
                    System.out.println("\n--- View Booked and Available Slots ---");
                    System.out.print("Enter Gym Centre ID (Long) to view slots: ");
                    Long viewSlotGymCentreId = null;
                    while (viewSlotGymCentreId == null) {
                        if (scanner.hasNextLong()) {
                            viewSlotGymCentreId = scanner.nextLong();
                            scanner.nextLine();
                        } else {
                            System.out.println("Invalid input. Please enter a valid number for Gym Centre ID.");
                            scanner.next();
                            scanner.nextLine();
                            System.out.print("Enter Gym Centre ID (Long) to view slots: ");
                        }
                    }

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
                            currentGymOwner.setName(newName);
                            if (gymOwnerService.editProfile(currentGymOwner)) {
                                System.out.println("Name changed successfully to: " + currentGymOwner.getName());
                            } else {
                                System.out.println("Failed to update name.");
                            }
                            break;
                        case 2:
                            System.out.print("Enter new email: ");
                            String newEmail = scanner.nextLine();
                            currentGymOwner.setEmail(newEmail);
                            if (gymOwnerService.editProfile(currentGymOwner)) {
                                System.out.println("Email changed successfully to: " + currentGymOwner.getEmail());
                            } else {
                                System.out.println("Failed to update email.");
                            }
                            break;
                    }
                    break;

                case 7:
                    System.out.println("Logging Out. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}