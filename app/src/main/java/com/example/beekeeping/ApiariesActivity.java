package com.example.beekeeping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApiariesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    Apiary[] apiaryList;
    String apiaryNames[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiaries);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        MainActivity.pullData(new DataCallback() {
            @Override
            public void onCallback(User user) {
                List<Apiary> aList = user.getApiaryList();
                displayApiary(aList);
            }
        },user.getUid());
    }

    private void displayApiary(List<Apiary> a){
        int size = a.size();

        ArrayList<String> apiariesInList = new ArrayList<>();
        Apiary[] apiaryArray = new Apiary[size];

        for (int i = 0; i < size; i++) {
            Apiary mApiary = a.get(i);
            String name = mApiary.getName();
            apiariesInList.add(name);
            apiaryArray[i] = mApiary;
        }

        recyclerView = (RecyclerView) findViewById(R.id.display_apiaries);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new DisplayApiaryAdapter(this, apiariesInList, apiaryArray);
        recyclerView.setAdapter(mAdapter);

    }

    public ArrayList<Apiary> getApiaries(String id) {
        return null;
    }

    public void onAddApiaryClick(View v) {
        startActivity(new Intent(ApiariesActivity.this, AddApiaryActivity.class));
    }

    public void pickApiary(Apiary a){

    }
}