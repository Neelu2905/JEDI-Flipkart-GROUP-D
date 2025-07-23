package com.flipfit.business;

import com.flipfit.beans.Booking;

import java.util.List;

public interface BookingServiceInterface {
    public Booking getBookingById(long bookingId);
    public Booking getBookingByCustomerId(long customerId);
    public Booking getBookingByGymId(long gymId);
    public List<Booking> getAllBookings();
    public List<Booking> getAllBookingsByGymId(long gymId);
    public List<Booking> getAllBookingsByCustomerId(long customerId);
    public boolean cancelBooking(long bookingId);
    public boolean checkWaitlistStatus(long bookingId);
}
