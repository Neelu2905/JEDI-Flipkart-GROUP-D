package com.flipfit.beans;

public class GymCustomer implements GymUser {

  private long id;
  private String name;
  private String email;
  private String address;
  private int age;
  private Enum gender;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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
