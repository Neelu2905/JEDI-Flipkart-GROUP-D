package com.flipfit.helper;

public class RegisterData {
  private String name;
  private String email;
  private String password;
  private String role;
  private String address;
  private String phone;
  private int age;
  private String gender;
  private String aadhar;
  private String pan;

  private RegisterData(Builder builder) {
    this.name = builder.name;
    this.email = builder.email;
    this.password = builder.password;
    this.role = builder.role;
    this.address = builder.address;
    this.phone = builder.phone;
    this.age = builder.age;
    this.gender = builder.gender;
    this.aadhar = builder.aadhar;
    this.pan = builder.pan;
  }

  public static class Builder {
    private String name;
    private String email;
    private String password;
    private String role;
    private String address;
    private String phone;
    private int age;
    private String gender;
    private String aadhar;
    private String pan;

    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    public Builder setEmail(String email) {
      this.email = email;
      return this;
    }

    public Builder setPassword(String password) {
      this.password = password;
      return this;
    }

    public Builder setRole(String role) {
      this.role = role;
      return this;
    }

    public Builder setAddress(String address) {
      this.address = address;
      return this;
    }

    public Builder setPhone(String phone) {
      this.phone = phone;
      return this;
    }

    public Builder setAge(int age) {
      this.age = age;
      return this;
    }

    public Builder setGender(String gender) {
      this.gender = gender;
      return this;
    }

    public Builder setAadhar(String aadhar) {
      this.aadhar = aadhar;
      return this;
    }

    public Builder setPan(String pan) {
      this.pan = pan;
      return this;
    }

    public RegisterData build() {
      return new RegisterData(this);
    }
  }
}
