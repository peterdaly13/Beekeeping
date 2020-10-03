package com.example.beekeeping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HiveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hive);

        FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
        Intent intent = getIntent();
        final String hid = intent.getStringExtra("hid");




    }
}