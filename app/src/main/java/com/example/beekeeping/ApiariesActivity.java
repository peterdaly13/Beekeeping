package com.example.beekeeping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
                final List<Apiary> aList = user.getApiaryList();
                List<String> nameList = new ArrayList<String>();
                for(int i = 0; i< aList.size(); i++){
                    nameList.add(aList.get(i).getName());
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(ApiariesActivity.this,
                        android.R.layout.simple_list_item_1, nameList);
                listView.setAdapter(arrayAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View v, int i, long l) {
                        onApiaryClick(aList, i);
                    }
                });
            }
        },user.getUid());
    }

    public void onApiaryClick(List<Apiary> aList, int i) {
        Intent intent = new Intent(ApiariesActivity.this, ApiaryActivity.class);
        intent.putExtra("aid", aList.get(i).getAid());
        startActivity(intent);

    }

    public void onAddApiaryClick(View v) {
        startActivity(new Intent(ApiariesActivity.this, AddApiaryActivity.class));
    }

    public void pickApiary(Apiary a){

    }
}