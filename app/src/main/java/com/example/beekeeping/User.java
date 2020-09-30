package com.example.beekeeping;

import android.media.Image;

// Each beekeeper is represented as a user
public class User {
    public int id;
    public String name;
    public String pswd; // look into other classes for password handling
    public String phoneNumber;
    public String email;
    public Image profilePic;
    public Apiary apiary; //Can be implemented as an arrayList since beekeepers can have multiple Apiaries


    public User(String name, String pswd) {
        generateId();
        this.name = name;
        this.pswd = pswd;
    }
    public User() {
    }

    String getName() {
        return this.name;
    }

    void setName(String name) {
        this.name = name;
    }

    int getId() {
        return this.id;
    }

    void generateId() {
        this.id = 0; //TODO generate unique id
    }

    void deleteUser() {
        // TODO make UserList class and implement deleteUser there
    }

    String getEmail() {
        return this.email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    String getPhoneNumber() {
        return this.phoneNumber;
    }

    void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
