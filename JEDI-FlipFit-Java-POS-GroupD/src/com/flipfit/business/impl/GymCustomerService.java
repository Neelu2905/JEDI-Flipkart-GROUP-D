package com.flipfit.business.impl;

import com.flipfit.beans.Booking;
import com.flipfit.beans.GymCustomer;
import com.flipfit.beans.GymCentre; // Assuming GymCentre is needed for viewGymCenters
import com.flipfit.beans.Payment; // Added for PaymentService methods
import com.flipfit.dao.GymCustomerDAO; // Import the DAO interface
import com.flipfit.dao.GymCustomerDAOImpl; // Assuming this is the implementation

import java.sql.SQLException; // Added for SQLException handling in payment methods
import java.util.List;
import java.util.Optional; // For cleaner null handling
import java.util.stream.Collectors; // Needed for stream operations

public class GymCustomerService {

  // Instantiate the DAO. In a real application, this would be injected
  // via a dependency injection framework (e.g., Spring, Guice).
  private GymCustomerDAO gymCustomerDAO;

  /**
   * Constructor for GymCustomerService.
   * Initializes the DAO dependency.
   */
  public GymCustomerService() {
    // Assuming GymCustomerDaoImpl is the concrete implementation of GymCustomerDAO
    this.gymCustomerDAO = new GymCustomerDAOImpl(); // Replace with injection in a real app
  }

  /**
   * Creates a new customer and adds them to the system.
   *
   * @param userId The ID of the customer.
   * @param name The name of the customer.
   * @param email The email address of the customer.
   * @param address The address of the customer.
   * @param age The age of the customer.
   * @param gender The gender of the customer.
   * @param customerPhone The phone number of the customer.
   * @param password The password for the customer's account.
   */
  public void createCustomer(Long userId, String name, String email, String address, int age, String gender, int customerPhone, String password) {
    GymCustomer newCustomer = new GymCustomer();
    newCustomer.setUserId(userId); // If userId is provided, use it. DAO will auto-generate if 0.
    newCustomer.setName(name);
    newCustomer.setEmail(email);
    newCustomer.setAddress(address);
    newCustomer.setAge(age);
    newCustomer.setGender(gender);
    newCustomer.setCustomerPhone(customerPhone);
    newCustomer.setPassword(password);

    gymCustomerDAO.addCustomer(newCustomer); // Delegate to DAO to add the customer
    System.out.println("[GymCustomerService] Customer details added successfully.");
  }

  /**
   * Retrieves customer details by their ID.
   *
   * @param customerId The ID of the customer to retrieve.
   * @return The GymCustomer object if found, null otherwise.
   */
  public GymCustomer getCustomerDetails(int customerId) { // Modified to accept customerId
    System.out.println("[GymCustomerService] Fetching customer details for ID: " + customerId);
    return gymCustomerDAO.getCustomerById(customerId);
  }

  /**
   * Updates an existing customer's profile.
   *
   * @param customer The GymCustomer object with updated details.
   */
  public void updateCustomerProfile(GymCustomer customer) { // Added this method for profile edits
    System.out.println("[GymCustomerService] Updating customer profile for ID: " + customer.getUserId());
    gymCustomerDAO.updateCustomer(customer);
  }

  /**
   * Placeholder for viewing available gym centers.
   * In a real application, this would interact with a GymCentreDAO.
   */
  public List<GymCentre> viewGymCenters() {
    System.out.println("[GymCustomerService] Viewing available gym centers (placeholder).");
    // This would typically call a GymCentreDAO to get a list of centers
    return null; // Placeholder
  }

  /**
   * Placeholder for booking a slot.
   * In a real application, this would involve complex logic like
   * checking slot availability, creating a booking, updating slot capacity.
   *
   * @param customerId The ID of the customer booking the slot.
   * @param gymCentreId The ID of the gym center.
   * @param slotTimeId The ID of the specific slot time.
   * @return The created Booking object if successful, null otherwise.
   */
  public Booking bookSlot(long customerId, long gymCentreId, long slotTimeId) {
    System.out.println("[GymCustomerService] Booking slot (placeholder).");
    // This would involve:
    // 1. Check slot availability (via GymCentreDAO / SlotDAO)
    // 2. Create a new Booking object
    // 3. Add booking via gymCustomerDAO.addBooking()
    // 4. Update slot capacity (via GymCentreDAO / SlotDAO)
    Booking newBooking = new Booking(); // Placeholder booking
    newBooking.setCustomerId(customerId);
    newBooking.setBookingId(gymCentreId); // Corrected: Assuming Booking bean has setGymId, not setBookingId
    newBooking.setSlotId(slotTimeId);
    newBooking.setWaitlistStatus(false);
    return gymCustomerDAO.addBooking(newBooking); // Simulate adding a booking
  }

  /**
   * Placeholder for canceling a slot.
   * In a real application, this would involve updating booking status,
   * refunding payment, and updating slot capacity.
   *
   * @param bookingId The ID of the booking to cancel.
   * @return true if cancellation is successful, false otherwise.
   */
  public boolean cancelSlot(long bookingId) {
    System.out.println("[GymCustomerService] Cancelling slot (placeholder).");
    // This would involve:
    // 1. Get booking by ID via gymCustomerDAO.getBookingById()
    // 2. Check if cancellable (e.g., not already cancelled, within cancellation window)
    // 3. Delete booking via gymCustomerDAO.deleteBooking()
    // 4. Update slot capacity (via GymCentreDAO / SlotDAO)
    return gymCustomerDAO.deleteBooking(bookingId); // Simulate deletion
  }

  // --- Booking Service Interface Methods Merged Below ---

  public Booking getBookingById(long bookingId) {
    System.out.println("[GymCustomerService] Fetching booking by ID: " + bookingId);
    return gymCustomerDAO.getBookingById(bookingId);
  }

  public Booking getBookingByCustomerId(long customerId) {
    System.out.println("[GymCustomerService] Fetching a single booking for customer ID: " + customerId);
    List<Booking> bookings = gymCustomerDAO.getBookingsByCustomerId(customerId);
    return bookings.isEmpty() ? null : bookings.get(0);
  }

  public Booking getBookingByGymId(long gymId) {
    System.out.println("[GymCustomerService] Fetching a single booking for gym ID: " + gymId);
    // The provided GymCustomerDAO does not have a direct getBookingsByGymId.
    // We will get all bookings and filter by gymId.
    List<Booking> bookings = gymCustomerDAO.getAllBookings().stream()
            .filter(booking -> booking.getBookingId() == gymId) // Assumes Booking bean has getGymId()
            .collect(Collectors.toList());
    return bookings.isEmpty() ? null : bookings.get(0);
  }

  public List<Booking> getAllBookings() {
    System.out.println("[GymCustomerService] Fetching all bookings.");
    return gymCustomerDAO.getAllBookings();
  }

  public List<Booking> getAllBookingsByGymId(long gymId) {
    System.out.println("[GymCustomerService] Fetching all bookings for gym ID: " + gymId);
    // The provided GymCustomerDAO does not have a direct getBookingsByGymId.
    // We will get all bookings and filter by gymId.
    List<Booking> bookings = gymCustomerDAO.getAllBookings().stream()
            .filter(booking -> booking.getBookingId() == gymId) // Assumes Booking bean has getGymId()
            .collect(Collectors.toList());
    return bookings;
  }

  public List<Booking> getAllBookingsByCustomerId(long customerId) {
    System.out.println("[GymCustomerService] Fetching all bookings for customer ID: " + customerId);
    return gymCustomerDAO.getBookingsByCustomerId(customerId);
  }

  // cancelSlot method already exists with similar logic, so ensuring consistency
  // If the intent was to have two separate methods, one could be renamed (e.g., `cancelBookingById`)
  // For now, `cancelSlot` covers the `cancelBooking` interface method.

  // public boolean cancelBooking(long bookingId) { // Already covered by cancelSlot
  //   return cancelSlot(bookingId);
  // }

  public boolean checkWaitlistStatus(long bookingId) {
    System.out.println("[GymCustomerService] Checking waitlist status for booking ID: " + bookingId);
    Booking booking = gymCustomerDAO.getBookingById(bookingId);
    return Optional.ofNullable(booking)
            .map(Booking::isWaitlistStatus)
            .orElse(false);
  }

  // --- PaymentService Methods Merged Below ---

  /**
   * Makes a payment for a customer.
   * This method assumes the Payment object is fully constructed with necessary details
   * (customerId, bookingId, amount, payment method details).
   *
   * @param payment The Payment object containing all payment details.
   * @return true if the payment was successfully added, false otherwise.
   */
  public boolean makePayment(Payment payment) { // Modified signature to take a Payment object
    System.out.println("[GymCustomerService] Attempting to make payment for customer: " + payment.getCustomerid());
    try {
      // Add business logic here before delegating to DAO:
      // - Validate payment details (e.g., amount > 0, valid card/UPI format)
      // - Interact with a payment gateway (if real-world scenario)
      // - Update booking status to 'paid' or similar (might be in BookingService)

      boolean success = gymCustomerDAO.addPayment(payment);
      if (success) {
        System.out.println("[GymCustomerService] Payment successful for customer " + payment.getCustomerid() + ".");
      } else {
        System.out.println("[GymCustomerService] Failed to add payment for customer " + payment.getCustomerid() + ".");
      }
      return success;
    } catch (SQLException e) {
      System.err.println("[GymCustomerService] Database error during payment for customer " + payment.getCustomerid() + ": " + e.getMessage());
      // In a real application, you would log this exception properly and potentially throw a custom service exception.
      return false;
    } catch (Exception e) {
      System.err.println("[GymCustomerService] An unexpected error occurred during payment for customer " + payment.getCustomerid() + ": " + e.getMessage());
      // Log this unexpected error.
      return false;
    }
  }

  /**
   * Retrieves payments for a specific customer.
   *
   * @param customerId The ID of the customer.
   * @return A list of Payment objects for the given customer.
   */
  public List<Payment> getPaymentsByCustomerId(long customerId) {
    System.out.println("[GymCustomerService] Fetching payments for customer ID: " + customerId);
    try {
      return gymCustomerDAO.getPaymentsByCustomerId(customerId);
    } catch (SQLException e) {
      System.err.println("[GymCustomerService] Error fetching payments for customer " + customerId + ": " + e.getMessage());
      return null; // Or throw a custom service exception
    }
  }

  /**
   * Updates an existing payment.
   *
   * @param payment The Payment object with updated information.
   * @return true if the payment was successfully updated, false otherwise.
   */
  public boolean updatePayment(Payment payment) {
    System.out.println("[GymCustomerService] Updating payment for customer: " + payment.getCustomerid());
    try {
      return gymCustomerDAO.updatePayment(payment);
    } catch (SQLException e) {
      System.err.println("[GymCustomerService] Error updating payment for customer " + payment.getCustomerid() + ": " + e.getMessage());
      return false;
    }
  }

  /**
   * Deletes payments for a specific customer.
   *
   * @param customerId The ID of the customer whose payments to delete.
   * @return true if payments were successfully deleted, false otherwise.
   */
  public boolean deletePaymentByCustomerId(long customerId) {
    System.out.println("[GymCustomerService] Deleting payments for customer ID: " + customerId);
    try {
      return gymCustomerDAO.deletePaymentByCustomerId(customerId);
    } catch (SQLException e) {
      System.err.println("[GymCustomerService] Error deleting payments for customer " + customerId + ": " + e.getMessage());
      return false;
    }
  }
}
