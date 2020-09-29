package com.example.beekeeping;

import java.util.ArrayList;

public class Apiary {
    private int aid;
    private int numHives;
    private ArrayList<Hive> hives;

    /**
     * constructor
     * @param id of the apiary
     */
    public Apiary(int id) {
        this.aid = id;
        numHives = 0;
        hives = new ArrayList<Hive>();
        }

    /**
     * getter
     * @return aid apiary id
     */
    public int getAid() {
        return aid;
    }

    /**
     * mutator
     * @param aid set the apiary id to aid
     */
    public void setAid(int aid) {
        this.aid = aid;
    }

    /**
     * getter
     * @return number of hives
     */
    public int getNumHives() {
        return numHives;
    }

    /**
     * mutator
     * @param numHives set the number of hives
     */
    public void setNumHives(int numHives) {
        this.numHives = numHives;
    }

    /**
     * getter
     * @return hives a array list of hives
     */
    public ArrayList<Hive> getHives() {
        return hives;
    }

    /**
     * mutator
     * @param hives give the value of a hive array list to the field hives
     */
        public void setHives(ArrayList<Hive> hives) {
            this.hives = hives;
        }
}
