package com.example.beekeeping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AddApiaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_apiary);
    }

    public void onCreateApiaryClick(View v) {
        EditText nameTextField = (EditText) findViewById(R.id.apiary_name_input);
        String name = nameTextField.getText().toString();
        Apiary newApiary = new Apiary(name);
        FirebaseUser FBuser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = FBuser.getUid();

        MainActivity.addApiary(uid, newApiary);
    }
}