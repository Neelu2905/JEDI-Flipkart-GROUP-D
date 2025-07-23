/*package com.flipfit.business.impl;

import com.flipfit.beans.Booking;
import java.util.List;

public class BookingService {
  public Booking getBookingById(long bookingId) {
    return null;
  }

  public Booking getBookingByCustomerId(long customerId) {
    return null;
  }

  public Booking getBookingByGymId(long gymId) {
    return null;
  }

  public List<Booking> getAllBookings() {
    return null;
  }

  public List<Booking> getAllBookingsByGymId(long gymId) {
    return null;
  }

  public List<Booking> getAllBookingsByCustomerId(long customerId) {
    return null;
  }

  public boolean cancelBooking(long bookingId) {
    return false;
  }

  public boolean checkWaitlistStatus(long bookingId) {
    return false;
  }
}*/
package com.flipfit.business.impl;

import com.flipfit.beans.Booking;
import com.flipfit.dao.GymCustomerDAO; // Import the provided DAO interface
import com.flipfit.dao.GymCustomerDAOImpl; // Assuming an implementation of GymCustomerDAO exists in 'dao' package
// (You'd typically inject this, but for a simple skeleton, we'll instantiate)

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// This class implements the BookingServiceInterface
public class BookingService implements com.flipfit.business.BookingServiceInterface {

  // Instantiate the DAO. In a real application, this would be injected
  // via a dependency injection framework (e.g., Spring, Guice)
  private GymCustomerDAO gymCustomerDAO;

  /**
   * Constructor for BookingService.
   * Initializes the DAO dependency.
   */
  public BookingService() {
    // Assuming GymCustomerDaoImpl is the concrete implementation of GymCustomerDAO
    this.gymCustomerDAO = new GymCustomerDAOImpl(); // Replace with injection in a real app
  }

  @Override
  public Booking getBookingById(long bookingId) {
    System.out.println("[BookingService] Fetching booking by ID: " + bookingId);
    return gymCustomerDAO.getBookingById(bookingId);
  }

  @Override
  public Booking getBookingByCustomerId(long customerId) {
    System.out.println("[BookingService] Fetching a single booking for customer ID: " + customerId);
    // gymCustomerDAO.getBookingsByCustomerId returns a list, so we take the first one if found.
    List<Booking> bookings = gymCustomerDAO.getBookingsByCustomerId(customerId);
    return bookings.isEmpty() ? null : bookings.get(0);
  }

  @Override
  public Booking getBookingByGymId(long gymId) {
    System.out.println("[BookingService] Fetching a single booking for gym ID: " + gymId);
    // The provided GymCustomerDAO does not have a direct getBookingsByGymId.
    // We will get all bookings and filter by gymId.
    // In a real application, the DAO would typically provide a more efficient method.
    List<Booking> bookings = gymCustomerDAO.getAllBookings().stream()
            .filter(booking -> booking.getBookingId() == gymId) // Assumes Booking bean has getGymId()
            .collect(Collectors.toList());
    return bookings.isEmpty() ? null : bookings.get(0);
  }

  @Override
  public List<Booking> getAllBookings() {
    System.out.println("[BookingService] Fetching all bookings.");
    return gymCustomerDAO.getAllBookings();
  }

  @Override
  public List<Booking> getAllBookingsByGymId(long gymId) {
    System.out.println("[BookingService] Fetching all bookings for gym ID: " + gymId);
    // The provided GymCustomerDAO does not have a direct getBookingsByGymId.
    // We will get all bookings and filter by gymId.
    List<Booking> bookings = gymCustomerDAO.getAllBookings().stream()
            .filter(booking -> booking.getBookingId() == gymId) // Assumes Booking bean has getGymId()
            .collect(Collectors.toList());
    return bookings;
  }

  @Override
  public List<Booking> getAllBookingsByCustomerId(long customerId) {
    System.out.println("[BookingService] Fetching all bookings for customer ID: " + customerId);
    return gymCustomerDAO.getBookingsByCustomerId(customerId);
  }

  @Override
  public boolean cancelBooking(long bookingId) {
    System.out.println("[BookingService] Attempting to cancel booking ID: " + bookingId);
    // Add business logic here before delegating to DAO (e.g., check cancellation window, refund policy).
    // For now, just delegate to DAO to delete.
    return gymCustomerDAO.deleteBooking(bookingId);
  }

  @Override
  public boolean checkWaitlistStatus(long bookingId) {
    System.out.println("[BookingService] Checking waitlist status for booking ID: " + bookingId);
    Booking booking = gymCustomerDAO.getBookingById(bookingId);
    // If booking is found, return its waitlist status, otherwise, it's not on waitlist (or doesn't exist).
    return Optional.ofNullable(booking)
            .map(Booking::isWaitlistStatus)
            .orElse(false);
  }

  // You might also want to add a method to create a booking, as it's a common service operation
  // public Booking createBooking(Booking booking) {
  //   System.out.println("[BookingService] Creating new booking.");
  //   // Add business logic: e.g., validate slot availability, check customer eligibility
  //   return gymCustomerDAO.addBooking(booking);
  // }
}

