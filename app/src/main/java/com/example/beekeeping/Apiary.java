package com.example.beekeeping;

import java.util.ArrayList;
import java.util.List;

public class Apiary {
        public int aid;
        public List<Hive> hives;

        public Apiary() {
            hives = new ArrayList<Hive>();
            generateAID();
        }

        public int getAid() { return aid; }

        public void setAid(int aid) { this.aid = aid; }

        public List<Hive> getHives() { return hives; }

        public void setHives(ArrayList<Hive> hives) { this.hives = hives; }

        public String toString(){
            //String info = "Apiary #" + aid + "manages the following hives: \n";
            String info = "Apiary #" + aid + "manages " + hives.size() + " hives.";
            return info;
        }

        private void generateAID() {
            this.aid = 0; //TODO
        }


    }
