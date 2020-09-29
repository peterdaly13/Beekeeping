package com.example.beekeeping;

import android.media.Image;

// Each beekeeper is represented as a user
public class User {
    int id;
    String name;
    String pswd; // look into other classes for password handling
    Image profilePic;
    Apiary apiary;
    String phoneNumber;
    String email;

    User(String name, String pswd) {
        generateId();
        this.name = name;
        this.pswd = pswd;
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
        this.id = 0; //TODO
    }

    void deleteUser() {
        // TODO
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
