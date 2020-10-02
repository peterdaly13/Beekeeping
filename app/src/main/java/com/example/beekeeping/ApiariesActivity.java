package com.example.beekeeping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApiariesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiaries);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        listView = (ListView) findViewById(R.id.listview);
        MainActivity.pullData(new DataCallback() {
            @Override
            public void onCallback(User user) {
                List<Apiary> aList = user.getApiaryList();
                List<String> nameList = new ArrayList<String>();
                for(int i = 0; i< aList.size(); i++){
                    nameList.add(aList.get(i).getName());
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(ApiariesActivity.this, android.R.layout.simple_list_item_1, nameList);
                listView.setAdapter(arrayAdapter);
            }
        },user.getUid());
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