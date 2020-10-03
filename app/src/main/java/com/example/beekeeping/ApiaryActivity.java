package com.example.beekeeping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class ApiaryActivity extends AppCompatActivity {
    Apiary apiary = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiary);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Intent intent = getIntent();
        final String aid = intent.getStringExtra("aid");

        MainActivity.pullData(new DataCallback() {
            @Override
            public void onCallback(User user) {
                final List<Apiary> aList = user.getApiaryList();
                setApiary(aList, aid);
            }
        },user.getUid());

        TextView displayApiaryName = findViewById(R.id.display_apiary_name);
       // displayApiaryName.setText(this.apiary.name); ERROR HERE
    }

    public void setApiary(List<Apiary> aList, String aid) {
        for(int i = 0; i< aList.size(); i++){
            if (aList.get(i).getAid().equals(aid)) {
                this.apiary = aList.get(i);
                Toast.makeText(this, "found match: " + this.apiary.name
                        , Toast.LENGTH_SHORT).show();
            }
        }
    }
}