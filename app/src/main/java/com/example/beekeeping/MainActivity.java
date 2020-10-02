package com.example.beekeeping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static DatabaseReference mDatabase;
    Button button;
    Button login_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // if user is already logged in, go directly to "me" page
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            startActivity(new Intent(MainActivity.this, MeActivity.class));
        }

        login_button = (Button) findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    //int uID, final String action
    static void pullData(final DataCallback dbc, final String uid) {
        // Somehow this allows the action String to work as intended
        DatabaseReference qReference = mDatabase.child("users").child(uid);
        Log.d("Fetch", qReference.toString());

        qReference.child(String.format("users/%s", uid));
        qReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User data = dataSnapshot.getValue(User.class);
                dbc.onCallback(data);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting User failed, log a message
                Log.w("PullData", "Failed to Load User from Firebase", databaseError.toException());
            }
        });
        // Use the uID to access the correct user
        //   return null;
    }

    static void pushData(User user) {
        // A HashMap is used to upload information to firebase, the String is the location in
        // firebase and the Object is the SongQueue to be put in firebase
        HashMap<String, Object> map = new HashMap<>();
        String folder = "/users/" + user.getId();
        map.put(folder, user);
        mDatabase.updateChildren(map)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("pushData", "Error Adding User", e);
                    }
                });
    }

    static void addApiary(String uid, final Apiary a) {
        pullData(new DataCallback() {
            @Override
            public void onCallback(User user) {
                user.getApiaryList().add(a);
                pushData(user);
            }
        },uid);
    }

    void addHive(String uid, final String aid, final Hive h) {
        pullData(new DataCallback() {
            @Override
            public void onCallback(User user) {
                List<Apiary> aList = user.getApiaryList();
                for(int i = 0; i < aList.size(); i++){
                    if(aList.get(i).getAid().equals(aid)){
                        user.getApiaryList().get(i).addHive(h);
                    }
                }

                pushData(user);
            }
        },uid);
    }

    void updateHive(String uid, final String aid, final Hive h) {
        pullData(new DataCallback() {
            @Override
            public void onCallback(User user) {
                List<Apiary> aList = user.getApiaryList();
                for(int i = 0; i < aList.size(); i++){
                    if(aList.get(i).getAid().equals(aid)){
                        List<Hive> hList = user.getApiaryList().get(i).getHives();
                        for(int j =0; j< hList.size(); j++){
                            if(hList.get(j).getHiveID().equals(h.getHiveID())){
                                user.getApiaryList().get(i).getHives().set(j, h);
                            }
                        }
                    }
                }

                pushData(user);
            }
        },uid);
    }

    boolean userExists(String uid) {
        pullData(new DataCallback() {
            @Override
            public void onCallback(User user) {

            }
        },uid);
        return false;
    }
    /*
    void updateApiary(String uid, final Apiary a) {
        pullData(new DataCallback() {
            @Override
            public void onCallback(User user) {
                List<Apiary> aList = user.getApiaryList();
                for(int i = 0; i < aList.size(); i++){
                    if(aList.get(i).getAid().equals(a.getAid())){
                        user.getApiaryList().set(i, a);
                    }
                }
                pushData(user);
            }
        },uid);
    }
    */
    void updateUsername(String uid, final String name){
        pullData(new DataCallback() {
            @Override
            public void onCallback(User user) {
                user.setName(name);
                pushData(user);
            }
        },uid);
    }
    void updatePhone(String uid, final String phone){
        pullData(new DataCallback() {
            @Override
            public void onCallback(User user) {
                user.setPhoneNumber(phone);
                pushData(user);
            }
        },uid);
    }
    void updateEmail(String uid, final String email){
        pullData(new DataCallback() {
            @Override
            public void onCallback(User user) {
                user.setEmail(email);
                pushData(user);
            }
        },uid);
    }
    void updateImage(String uid, final Image img){
        pullData(new DataCallback() {
            @Override
            public void onCallback(User user) {
                user.setProfilePic(img);
                pushData(user);
            }
        },uid);
    }

    void updateApiaryName(String uid, final String aid, final String name){
        pullData(new DataCallback() {
            @Override
            public void onCallback(User user) {
                List<Apiary> aList = user.getApiaryList();
                for(int i = 0; i< aList.size(); i++){
                    if(aList.get(i).getAid().equals(aid)){
                        user.getApiaryList().get(i).setName(name);
                    }
                }
                pushData(user);
            }
        },uid);
    }
    void displayApiaryList(String uid){
        pullData(new DataCallback() {
            @Override
            public void onCallback(User user) {
                List<Apiary> aList = user.getApiaryList();
                //YOUR CODE HERE TRESSA
                pushData(user);
            }
        },uid);
    }



}


