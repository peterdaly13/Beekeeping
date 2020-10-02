package com.example.beekeeping;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Apiary {
    public String aid;
    public String name;
    public List<Hive> hives;

    public Apiary(String name) {
        this.hives = new ArrayList<Hive>();
        this.hives.add(new Hive("", "", "", "" , "" , "" , "" , ""));
        this.aid = generateAID();
        this.name = name;
    }
    public Apiary(String name, String aid) {
        this.hives = new ArrayList<Hive>();
        this.hives.add(new Hive());
        this.aid = aid;
        this.name = name;
    }

    /*
    public Apiary(String name, String aid) {
        this.hives = new ArrayList<Hive>();
        this.aid = aid;
        this.name = name;
    }
     */

    public Apiary() {
    }


    public String getAid() {
        return aid;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
