package com.example.beekeeping;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ApiariesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiaries);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ArrayList<Apiary> apiaries = getApiaries(user.getUid());
    }

    public ArrayList<Apiary> getApiaries(String id) {
        return null;
    }
}