package com.example.beekeeping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
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
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //these are references to the objects in the UML view
        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.Button);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //just an animation
                Animation animation = new AlphaAnimation(1.0f, 0.6f);
                animation.setDuration(500); //miliseconds
                button.startAnimation(animation);


                final User[] u = {null};
                final String uid = "0";

                pullData(new DataCallback() {
                    @Override
                    public void onCallback(User data) {
                        //here the User "data" is the User found with the uid provided
                        textView.setText(data.getName());




                        //LoginActivity activity =
                    }
                }, uid);
            }
        });
    }

    /*
    private User pullUser(String uid) {

        // DataCallback dbc = null;
        final User val[] = {null};

        pullData(new DataCallback() {
            @Override
            public void onCallback(User value) {

                Log.d("pull", u[0].toString());
                val[0] = value;
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




    private void pushData(User user) {
        // A HashMap is used to upload information to firebase, the String is the location in
        // firebase and the Object is the SongQueue to be put in firebase
        HashMap<String, Object> map = new HashMap<>();
        String folder = "/users/" + Integer.toString(user.getId());
        map.put(folder, user);
        mDatabase.updateChildren(map)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("pushData", "Error Adding User", e);
                    }
                });
    }




}




