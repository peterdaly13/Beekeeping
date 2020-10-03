package com.example.beekeeping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DeleteApiaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_apiary);
    }

    public void onDeleteApiaryClick(View v) {
        // somehow select apiary object itself, then MainActivity.deleteApiary(uid, apiary)
        FirebaseUser FBuser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = FBuser.getUid();
    }

    public void onBackToApiariesClick(View v) {
        startActivity(new Intent(DeleteApiaryActivity.this, ApiariesActivity.class));
    }
}