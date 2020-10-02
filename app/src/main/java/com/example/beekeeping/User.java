package com.example.beekeeping;

import android.media.Image;
import java.util.ArrayList;
import java.util.List;

// Each beekeeper is represented as a user
public class User {
    public int id;
    public String name;
    public String pswd; // look into other classes for password handling
    public String phoneNumber;
    public String email;
    public Image profilePic;
    public List <Apiary> apiaryList; //Can be implemented as an arrayList since beekeepers can have mutiple Apiaries


    public User(String name, String pswd) {
        generateId();
        this.name = name;
        this.pswd = pswd;
        this.apiaryList = new ArrayList<Apiary>();
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
        this.id = 321; //TODO generate unique id
    }


    public void addApiary(Apiary a){
        apiaryList.add(a);
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pswd='" + pswd + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", profilePic=" + profilePic +
                ", apiaryList=" + apiaryList +
                '}';
    }
}
