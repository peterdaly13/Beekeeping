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
import java.util.List;

public class ApiariesActivity extends AppCompatActivity {

    private RecyclerView dqRecycleView;
    private DisplayApiaryAdapter dqAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiaries);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //ArrayList<Apiary> apiaries = getApiaries(user.getUid());
    }

    private void displayApiary(List<Apiary> a){
        int size = a.size();

        ArrayList<String> songsInQ = new ArrayList<>();
        Apiary[] apiaryArray = new Apiary[size];

        for (int i = 0; i < size; i++) {
            Apiary mApiary = a.get(i);
            String name = mApiary.getName();
            songsInQ.add(name);
            apiaryArray[i] = mApiary;
        }

        dqRecycleView = (RecyclerView) findViewById(R.id.display_apiaries);
        dqRecycleView.setLayoutManager(new LinearLayoutManager(this));
        dqAdapter = new DisplayApiaryAdapter(this, songsInQ, apiaryArray);
        dqRecycleView.setAdapter(dqAdapter);


    }
    void pickApiary(Apiary a){

    }

    public ArrayList<Apiary> getApiaries(String id) {
        return null;
    }

    public void onAddApiaryClick(View v) {
        startActivity(new Intent(ApiariesActivity.this, AddApiaryActivity.class));
    }
}