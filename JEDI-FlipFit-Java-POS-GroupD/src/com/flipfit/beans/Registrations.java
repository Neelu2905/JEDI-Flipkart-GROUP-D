package com.flipfit.beans;

public class Registrations {
    private long regId;
    private String username;
    private String password;
    private Enum usertype;


    public long getRegId() {
        return regId;
    }

    public void setRegId(long regId) {
        this.regId = regId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Enum getUsertype() {
        return usertype;
    }

    public void setUsertype(Enum usertype) {
        this.usertype = usertype;
    }


}
