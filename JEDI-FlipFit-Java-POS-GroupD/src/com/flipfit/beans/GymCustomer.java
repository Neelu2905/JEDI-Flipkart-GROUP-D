package com.flipfit.beans;

public class GymCustomer extends GymUser{

  private String address;
  private int age;
  private Enum gender;
  private int customerId;
  private String customerName;
  private String customerEmailAddress;
  private int customerPhone;
  private String password;


  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getCustomerEmailAddress() {
    return customerEmailAddress;
  }

  public void setCustomerEmailAddress(String customerEmailAddress) {
    this.customerEmailAddress = customerEmailAddress;
  }

  public int getCustomerPhone() {
    return customerPhone;
  }

  public void setCustomerPhone(int customerPhone) {
    this.customerPhone = customerPhone;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public void setPassword(String password) {
    this.password = password;
  }
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public Enum getGender() {
    return gender;
  }

  public void setGender(Enum gender) {
    this.gender = gender;
  }
}
