package com.example.beekeeping;

import android.media.Image;

// Each beekeeper is represented as a user
public class User {
    int id;
    String name;
    String pswd; // look into other classes for password handling
    Image profilePic;
    Apiary apiary;
    String phone;
    String email;

    User(String name, String pswd) {
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

    void deleteUser() {
        // how do we want to go about deleting users?
    }
}
