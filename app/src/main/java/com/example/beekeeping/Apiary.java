package com.example.beekeeping;

import java.util.ArrayList;

public class Apiary {
        private int aid;
        private int numHives;
        private ArrayList<Hive> hives;

        public Apiary() {
            hives = new ArrayList<Hive>();
        }

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }

        public int getNumHives() {
            return numHives;
        }

        public void setNumHives(int numHives) {
            this.numHives = numHives;
        }

        public ArrayList<Hive> getHives() {
            return hives;
        }

        public void setHives(ArrayList<Hive> hives) {
            this.hives = hives;
        }
}
