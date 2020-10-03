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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class HiveActivity extends AppCompatActivity {

    TextView name;
    TextView inspectionResults;
    TextView health;
    TextView honeyStores;
    TextView queenProduction;
    TextView equipmentOnHive;
    TextView equipmentInventory;
    TextView losses;
    TextView gains;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hive);

        FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
        Intent intent = getIntent();
        final String aid = intent.getStringExtra("aid");
        final String hid = intent.getStringExtra("hid");


        name = (TextView) findViewById(R.id.hive_header);
        inspectionResults = (TextView) findViewById(R.id.hive_inspection_results);
        health = (TextView) findViewById(R.id.hive_health);
        honeyStores = (TextView) findViewById(R.id.hive_honey_stores);
        queenProduction = (TextView) findViewById(R.id.hive_queen_production);
        equipmentOnHive = (TextView) findViewById(R.id.hive_equip_on_hive);
        equipmentInventory = (TextView) findViewById(R.id.hive_inventory);
        losses = (TextView) findViewById(R.id.hive_losses);
        gains = (TextView) findViewById(R.id.hive_gains);

        MainActivity.pullData(new DataCallback() {
            @Override
            public void onCallback(User user) {
                final List<Apiary> aList = user.getApiaryList();
                int temp = -1;
                for (int i = 0; i < aList.size(); i++) {
                    if (aList.get(i).getAid().equals(aid)) {
                        temp = i;
                    }
                }
                if (temp != -1) {
                    List<Hive> hives = aList.get(temp).getHives();
                    int temp2 = -1;
                    for (int k = 0; k < hives.size(); k++) {
                        if (hives.get(k).getHiveID().equals(hid)) {
                            temp2 = k;
                        }
                    }
                    if (temp2 != -1) {
                        Hive h = user.getApiaryList().get(temp).getHives().get(temp2);
                        /*
                        name.setText(h.getName());
                        inspectionResults.setText(h.getInspectionResults());
                        health.setText(h.getHealth());
                        honeyStores.setText(h.getHoneyStores());
                        queenProduction.setText(h.getQueenProduction());
                        equipmentOnHive.setText(h.getEquipmentOnHive());
                        equipmentInventory.setText(h.getEquipmentInventory());
                        losses.setText(h.getLosses());
                        gains.setText(h.getGains());

                         */

                    }
                }
            }
        }, fUser.getUid());

    }
}