package com.example.beekeeping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public DatabaseReference mDatabase;
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


        /*
        button = (Button) findViewById(R.id.Button);


        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                User user1 = new User("Ted", "123");
                Apiary a = new Apiary(123);
                Apiary b = new Apiary(456);
                Hive h = new Hive("1", "2", "3", "4", "5", "6", "7", "8");
                Hive i = new Hive("1", "2", "3", "4", "5", "6", "7", "8");
                Log.i("Button", h.getInspectionResults());
                a.addHive(h);
                b.addHive(i);
                user1.addApiary(a);
                user1.addApiary(b);
                pushData(user1);
                User u = pullData(5555 , "return");
                Log.i("Pull", u.toString() );


            }
        }, uid);

        return val[0];
    }
*/
    //int uID, final String action
    private void pullData(final DataCallback dbc, final String uid) {
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
    }

    private void pushData(User user){
        // A HashMap is used to upload information to firebase, the String is the location in
        // firebase and the Object is the SongQueue to be put in firebase
        HashMap<String, Object> map = new HashMap<>();
        String folder = "/users/"+ Integer.toString(user.getId());
        map.put(folder, user);
        mDatabase.updateChildren(map)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("pushData", "Error Adding User", e);
                    }
                });
    }

    private User pullData(int uID, final String action) {
        // Somehow this allows the action String to work as intented
        final String[] actionRef = {action};
        // Use the partyLeaderID to access the correct database
        DatabaseReference qReference = mDatabase.child("users").child(Integer.toString(uID));
        final ArrayList<User> uList = new ArrayList<User>();
        uList.add(null);
        // The onDataChange will be called once to perform an action, and then once again if the
        // data was changed. The action var is set to "" so that no action repeats in one call of
        // pullData
        ValueEventListener postListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get the User from Firebase
                User user = dataSnapshot.getValue(User.class);
                user = dataSnapshot.getValue(User.class);

                if (user != null) {
                    Log.i("User" , "Not null");
                    // Perform the desired action
                    if (actionRef[0].equals("return")) {
                        uList.add(user);
                    }
                }
                else{
                    Log.i("User" , "Null");
                }
                // Set action to "" so that the action doesn't repeat
                actionRef[0] ="";

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting SongQueue failed, log a message
                Log.w("PullData", "Failed to Load User from Firebase", databaseError.toException());
            }
        };
        qReference.addValueEventListener(postListener);
        uList.remove(0);
        return uList.get(0);
    }


}


