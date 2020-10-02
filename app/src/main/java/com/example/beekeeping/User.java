package com.example.beekeeping;

import android.media.Image;
import java.util.ArrayList;
import java.util.List;

// Each beekeeper is represented as a user
public class User {
    public String id;
    public String name;
    public String phoneNumber;
    public String email;
    public Image profilePic;
    public List <Apiary> apiaryList; //Can be implemented as an arrayList since beekeepers can have mutiple Apiaries


    public User(String name, String id) {

        this.name = name;
        this.apiaryList = new ArrayList<Apiary>();
        Apiary apiary = new Apiary("stub");
        this.apiaryList.add(apiary);
        this.id = id;
    }
    public User() {
    }

    String getName() {
        return this.name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getId() {
        return this.id;
    }

    void setId(String id){
        this.id = id;
    }

    void addApiary(Apiary a){
        apiaryList.add(a);
    }

    List<Apiary> getApiaryList(){
        return apiaryList;
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
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", profilePic=" + profilePic +
                ", apiaryList=" + apiaryList +
                '}';
    }

    public Image getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Image profilePic) {
        this.profilePic = profilePic;
    }
}
