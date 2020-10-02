package com.example.beekeeping;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Apiary {
    public String aid;
    public List<Hive> hives;

    public Apiary(String s) {
        this.hives = new ArrayList<Hive>();
        this.aid = generateAID();
    }

    public Apiary() {
    }


    public String getAid() {
        return aid;
    }

    //public void setAid(int aid) { this.aid = aid; }

    public List<Hive> getHives() {
        return hives;
    }

    public void setHives(ArrayList<Hive> hives) {
        this.hives = hives;
    }

    public void addHive(Hive h){
        hives.add(h);
    }

    public String toString() {
        //String info = "Apiary #" + aid + "manages the following hives: \n";
        String info = "Apiary #" + aid + "manages " + hives.size() + " hives.";
        return info;
    }

    private String generateAID() {
        return UUID.randomUUID().toString();
    }


}
