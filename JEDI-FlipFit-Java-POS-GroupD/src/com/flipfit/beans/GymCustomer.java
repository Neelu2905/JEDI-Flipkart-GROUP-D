package com.flipfit.beans;

public class GymCustomer extends GymUser{

  private String address;
  private int age;

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

  public int getCustomerPhone() {
    return customerPhone;
  }

  public void setCustomerPhone(int customerPhone) {
    this.customerPhone = customerPhone;
  }

  private Enum gender;
  private int customerPhone;





}
