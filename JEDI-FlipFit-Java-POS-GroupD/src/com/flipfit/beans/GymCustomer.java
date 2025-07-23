package com.flipfit.beans;

public class GymCustomer extends GymUser{

  private String address;
  private int age;
  private String gender;
  public String getAddress() {
    return address;
  }
  private int customerPhone;
  public GymCustomer() {}
  public GymCustomer(long userId, String name, String email, String address, int age, String gender, int customerPhone) {
    this.setUserId(userId);
    this.setName(name);
    this.setEmail(email);
    this.setAddress(address);
    this.setAge(age);
    this.setGender(gender);
    this.setCustomerPhone(customerPhone);
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

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public int getCustomerPhone() {
    return customerPhone;
  }

  public void setCustomerPhone(int customerPhone) {
    this.customerPhone = customerPhone;
  }




}
