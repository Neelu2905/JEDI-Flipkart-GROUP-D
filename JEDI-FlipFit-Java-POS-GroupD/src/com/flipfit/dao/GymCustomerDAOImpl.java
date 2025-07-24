package com.flipfit.dao;
import com.flipfit.beans.*;

import com.flipfit.beans.GymCustomer;
import com.flipfit.dao.GymCustomerDAO;
import com.flipfit.beans.Booking;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import com.flipfit.beans.Payment;
import java.util.Iterator;


public class GymCustomerDAOImpl implements GymCustomerDAO {
    // Customer
    private final Map<Long, GymCustomer> customers = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(0);

    // Bookings
    private static final Map<Long, Booking> bookingsStore = new HashMap<>();
    private static final AtomicLong bookingIdCounter = new AtomicLong(0);

    // Bookings
    private static List<Payment> payments = new ArrayList<>();

    public GymCustomerDAOImpl() {

    }


    @Override
    public void addCustomer(GymCustomer customer) {
        // Assign a new ID if it's a new customer (ID is 0 or not set)
        if (customer.getUserId() == 0) {
            customer.setUserId(idCounter.incrementAndGet());
        }
        customers.put(customer.getUserId(), customer);
        System.out.println("Added customer: " + customer.getName() + " with ID: " + customer.getUserId());
    }

    @Override
    public GymCustomer getCustomerById(long customerId) {
        return customers.get(customerId);
    }

    @Override
    public void updateCustomer(GymCustomer customer) {
        if (customers.containsKey(customer.getUserId())) {
            customers.put(customer.getUserId(), customer); // Overwrites the existing customer
            System.out.println("Updated customer: " + customer.getName() + " (ID: " + customer.getUserId() + ")");
        } else {
            System.out.println("Customer with ID " + customer.getUserId() + " not found for update.");
        }
    }

    @Override
    public void deleteCustomer(long customerId) {
        GymCustomer removedCustomer = customers.remove(customerId);
        if (removedCustomer != null) {
            System.out.println("Deleted customer with ID: " + customerId + " (" + removedCustomer.getName() + ")");
        } else {
            System.out.println("Customer with ID " + customerId + " not found for deletion.");
        }
    }

    @Override
    public List<GymCustomer> getAllCustomers() {
        return new ArrayList<>(customers.values()); // Return a copy of the list
    }


    // Bookings
    @Override
    public Booking addBooking(Booking booking) {
        // Assign a new ID if it's a new booking (ID is 0 or not set)
        if (booking.getBookingId() == 0) {
            booking.setBookingId(bookingIdCounter.incrementAndGet());
        }
        bookingsStore.put(booking.getBookingId(), booking);
        System.out.println("[DAO] Added Booking: " + booking.getBookingId() + " for Customer: " + booking.getCustomerId());
        return booking;
    }

    @Override
    public Booking getBookingById(long bookingId) {
        return bookingsStore.get(bookingId);
    }

    @Override
    public Booking updateBooking(Booking booking) {
        if (bookingsStore.containsKey(booking.getBookingId())) {
            bookingsStore.put(booking.getBookingId(), booking);
            System.out.println("[DAO] Updated Booking: " + booking.getBookingId());
            return booking;
        }
        System.out.println("[DAO] Booking with ID " + booking.getBookingId() + " not found for update.");
        return null;
    }

    @Override
    public boolean deleteBooking(long bookingId) {
        if (bookingsStore.containsKey(bookingId)) {
            bookingsStore.remove(bookingId);
            System.out.println("[DAO] Deleted Booking: " + bookingId);
            return true;
        }
        System.out.println("[DAO] Booking with ID " + bookingId + " not found for deletion.");
        return false;
    }

    @Override
    public List<Booking> getBookingsByCustomerId(long customerId) {
        return bookingsStore.values().stream()
                .filter(booking -> booking.getCustomerId() == customerId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookingsStore.values());
    }

    @Override
    public void clearAllBookings() {
        bookingsStore.clear();
        bookingIdCounter.set(0); // Reset ID counter
        System.out.println("[DAO] All bookings cleared.");
    }


    // Payments
    @Override
    public boolean addPayment(Payment payment) {
        // Since there's no unique ID, we simply add it.
        // If you need to prevent duplicates based on customerId + card/upi,
        // you'd have to implement a search before adding.
        payments.add(payment);
        System.out.println("Added payment: " + payment);
        return true;
    }

    @Override
    public List<Payment> getPaymentsByCustomerId(long customerId) {
        List<Payment> result = new ArrayList<>();
        for (Payment payment : payments) {
            if (payment.getCustomerid() == customerId) {
                result.add(payment);
            }
        }
        return result;
    }

    @Override
    public boolean updatePayment(Payment payment) {
        // This is tricky without a unique ID. We'll try to find an EXACT match
        // based on customerId, cardNumber, and upiId.
        // If multiple matches exist (unlikely with card/upi if they are unique per customer),
        // it will replace the first one found.
        boolean updated = false;
        for (int i = 0; i < payments.size(); i++) {
            Payment existingPayment = payments.get(i);
            // Check if this existing payment is the one we want to update.
            // This assumes the combination of customerId, cardNumber, and upiId uniquely identifies a payment.
            // If cardNumber or upiId can be null, equals() method on Payment handles null comparisons.
            if (existingPayment.getCustomerid() == payment.getCustomerid() &&
                    java.util.Objects.equals(existingPayment.getCardNumber(), payment.getCardNumber()) &&
                    java.util.Objects.equals(existingPayment.getUpiId(), payment.getUpiId())) {

                // Found a match, replace it.
                payments.set(i, payment);
                updated = true;
                System.out.println("Updated payment (matched by full details): " + payment);
                // If you want to update ALL matching payments, continue loop.
                // If only the first match, break. For an "update single record" behavior, break.
                break;
            }
        }
        if (!updated) {
            System.out.println("Payment (matched by full details) not found for update: " + payment);
        }
        return updated;
    }

    @Override
    public boolean deletePaymentByCustomerId(long customerId) {
        boolean removed = false;
        // Using Iterator to safely remove elements while iterating
        Iterator<Payment> iterator = payments.iterator();
        while (iterator.hasNext()) {
            Payment payment = iterator.next();
            if (payment.getCustomerid() == customerId) {
                iterator.remove();
                removed = true;
                System.out.println("Removed payment: " + payment + " for customer " + customerId);
            }
        }
        return removed;
    }

}