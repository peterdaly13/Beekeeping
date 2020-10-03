package com.example.beekeeping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class ApiaryActivity extends AppCompatActivity {
    Apiary apiary = null;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiary);


        FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
        Intent intent = getIntent();
        final String aid = intent.getStringExtra("aid");

        listView = (ListView) findViewById(R.id.listview);
        MainActivity.pullData(new DataCallback() {
            @Override
            public void onCallback(User user) {
                final List<Apiary> aList = user.getApiaryList();
                int temp = -1;
                for(int i = 0; i< aList.size(); i++) {
                    if (aList.get(i).getAid().equals(aid)) {
                       temp =i;
                    }
                }
                if(temp != -1) {
                    final List<Hive> hives = aList.get(temp).getHives();
                    List<String> nameList = new ArrayList<String>();
                    for (int i = 0; i < hives.size(); i++) {
                        nameList.add(hives.get(i).getName());
                    }
                    ArrayAdapter arrayAdapter = new ArrayAdapter(ApiaryActivity.this,
                            android.R.layout.simple_list_item_1, nameList);
                    listView.setAdapter(arrayAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View v, int i, long l) {
                            onHiveClick(hives, i);
                        }
                    });
                }
            }
        },fUser.getUid());
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

    public void onHiveClick(List<Hive> hives, int i) {
        Intent intent = new Intent(ApiaryActivity.this, HiveActivity.class);
        intent.putExtra("hid", hives.get(i).getHiveID());
        startActivity(intent);

    }


}