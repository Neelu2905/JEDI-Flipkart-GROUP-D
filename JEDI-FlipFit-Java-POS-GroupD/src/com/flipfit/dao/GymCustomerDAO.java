package com.flipfit.dao;

import com.flipfit.beans.GymCustomer;
import com.flipfit.beans.Booking;
import java.util.List;
import com.flipfit.beans.Payment;
import java.sql.SQLException;

public interface GymCustomerDAO {
    // Customers
    void addCustomer(GymCustomer customer);
    GymCustomer getCustomerById(long customerId);
    void updateCustomer(GymCustomer customer);
    void deleteCustomer(long customerId);
    List<GymCustomer> getAllCustomers();


    // Bookings
    Booking addBooking(Booking booking);
    Booking getBookingById(long bookingId);
    Booking updateBooking(Booking booking);
    boolean deleteBooking(long bookingId);
    List<Booking> getBookingsByCustomerId(long customerId);
    List<Booking> getAllBookings();
    void clearAllBookings();


    //Payments
    boolean addPayment(Payment payment) throws SQLException;
    List<Payment> getPaymentsByCustomerId(long customerId) throws SQLException;
    boolean updatePayment(Payment payment) throws SQLException;
    boolean deletePaymentByCustomerId(long customerId) throws SQLException;

}
