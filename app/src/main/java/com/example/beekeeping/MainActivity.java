package com.example.beekeeping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;

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
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        button = (Button) findViewById(R.id.Button);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*
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
                pushData(user1);*/
                final User[] u = {null};
               // DataCallback dbc = null;
                pullData(new DataCallback() {
                    @Override
                    public void onCallback(User value) {
                        Log.d("pull", value.toString());
                    }
                }, 5555);

            }
        });
    }

    //int uID, final String action
    private void pullData(final DataCallback dbc, final int uid) {
        // Somehow this allows the action String to work as intended

        DatabaseReference qReference = mDatabase.child("users").child(Integer.toString(uid));
        Log.d("Fetch", qReference.toString());
        //        final ArrayList<User> uList = new ArrayList<User>();
        //        uList.add(null);

        // The onDataChange will be called once to perform an action, and then once again if the
        // data was changed. The action var is set to "" so that no action repeats in one call of
        //
        //User user;
        qReference.child(String.format("users/%s", uid));
        qReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User value = dataSnapshot.getValue(User.class);
                dbc.onCallback(value);
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




