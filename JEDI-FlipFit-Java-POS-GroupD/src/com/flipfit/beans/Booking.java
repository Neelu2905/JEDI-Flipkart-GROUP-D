package com.flipfit.beans;

public class Booking {
  private long bookingId;
  private long customerId;
  private long slotId;
  private boolean waitlistStatus;
  private String transactionId;

  public long getBookingId() {
    return bookingId;
  }

  public void setBookingId(long bookingId) {
    this.bookingId = bookingId;
  }

  public long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(long customerId) {
    this.customerId = customerId;
  }

  public long getSlotId() {
    return slotId;
  }

  public void setSlotId(long slotId) {
    this.slotId = slotId;
  }

  public boolean isWaitlistStatus() {
    return waitlistStatus;
  }

  public void setWaitlistStatus(boolean waitlistStatus) {
    this.waitlistStatus = waitlistStatus;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }
}
