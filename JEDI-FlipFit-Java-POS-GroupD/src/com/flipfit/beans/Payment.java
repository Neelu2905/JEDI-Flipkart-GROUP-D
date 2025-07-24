package com.flipfit.beans;

import java.time.LocalDate; // Import for LocalDate

public class Payment {
  private long customerid;
  private double amount;          // Added: To store the payment amount
  private LocalDate paymentDate;  // Added: To store the date of payment
  private String paymentStatus;   // Added: To store the status (e.g., SUCCESS, FAILED)
  private String paymentMethod;   // Added: To store the method (e.g., "card", "upi")
  private String cardNumber;
  private String upiId;

  // Default constructor (recommended for JavaBeans)
  public Payment() {
  }

  // Parameterized constructor for convenience (optional but useful)
  public Payment(long customerid, double amount, LocalDate paymentDate, String paymentStatus, String paymentMethod, String cardNumber, String upiId) {
    this.customerid = customerid;
    this.amount = amount;
    this.paymentDate = paymentDate;
    this.paymentStatus = paymentStatus;
    this.paymentMethod = paymentMethod;
    this.cardNumber = cardNumber;
    this.upiId = upiId;
  }

  // --- Getters ---
  public long getCustomerid() {
    return customerid;
  }

  public double getAmount() {
    return amount;
  }

  public LocalDate getPaymentDate() {
    return paymentDate;
  }

  public String getPaymentStatus() {
    return paymentStatus;
  }

  public String getPaymentMethod() {
    return paymentMethod;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public String getUpiId() {
    return upiId;
  }

  // --- Setters ---
  public void setCustomerid(long customerid) {
    this.customerid = customerid;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public void setPaymentDate(LocalDate paymentDate) {
    this.paymentDate = paymentDate;
  }

  public void setPaymentStatus(String paymentStatus) {
    this.paymentStatus = paymentStatus;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public void setUpiId(String upiId) {
    this.upiId = upiId;
  }

  @Override
  public String toString() {
    // Mask card number for security in toString, show only last 4 digits if present
    String maskedCardNumber = (cardNumber != null && cardNumber.length() > 4) ?
            "XXXX-XXXX-XXXX-" + cardNumber.substring(cardNumber.length() - 4) :
            (cardNumber != null ? cardNumber : "N/A");

    return "Payment{" +
            "customerid=" + customerid +
            ", amount=" + amount +
            ", paymentDate=" + paymentDate +
            ", paymentStatus='" + paymentStatus + '\'' +
            ", paymentMethod='" + paymentMethod + '\'' +
            ", cardNumber='" + maskedCardNumber + '\'' +
            ", upiId='" + (upiId != null ? upiId : "N/A") + '\'' +
            '}';
  }
}