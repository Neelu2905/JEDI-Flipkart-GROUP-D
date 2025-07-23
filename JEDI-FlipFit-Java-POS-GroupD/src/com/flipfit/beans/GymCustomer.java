package com.flipfit.beans;

public class GymCustomer extends GymUser{

  private String address;
  private int age;
  private Enum gender;

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
