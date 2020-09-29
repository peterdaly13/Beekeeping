package com.example.beekeeping;

import java.util.ArrayList;
import java.util.List;

public class Apiary {
        public int aid;
        public List<Hive> hives;

        public Apiary(int aid) {
            hives = new ArrayList<Hive>();
            generateAID();
        }

        public Apiary() {
        }

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }

        public List<Hive> getHives() {
            return hives;
        }

        public void setHives(ArrayList<Hive> hives) {
            this.hives = hives;
        }

        private void generateAID() {
            this.aid = 0; //TODO
        }
}
