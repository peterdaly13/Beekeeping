package com.example.beekeeping;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = findViewById(R.id.Button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                /*
                 * Below are ways to write a message to the firbase database
                 * and to read a message from the database
                 */
                // [START write_message]
                // Write a message to the database

                //Retrieve an instance of your database using getInstance() and reference the location you want to write to
                FirebaseDatabase dbInstance = FirebaseDatabase.getInstance();
                // Then make a reference to the database location called myRef
                System.out.println(dbInstance);
                DatabaseReference myRef = dbInstance.getReference("users");



                /*
                 * You can save a range of data types to the database this way,
                 * including Java objects. When you save an object the responses
                 * from any getters will be saved as children of this location.
                 */
                // Change the reference's value
                myRef.setValue("1234");
                // [END write_message]

                //Database: https://beekeeping-83194.firebaseio.com/

                // [START read_message]
                // To make your app data update in realtime, you should
                // add a ValueEventListener to the reference you just created.
                myRef.addValueEventListener(new ValueEventListener() { //In this case, the Ref we just created

                    // The onDataChange() method in this class is triggered
                    // once when the listener is attached and again every
                    // time the data changes, including the children if this is an object.
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) { //If a dataChange Event occurs, this method will run
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        Log.d(TAG, "Value is: " + value);
                        //If you go to the Logcat in Android studios and then type the TAG which is MainActivity, you will see this message
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });
                // [END read_message]

            }
        });
    }

}


