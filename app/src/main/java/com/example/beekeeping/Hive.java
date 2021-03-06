package com.example.beekeeping;

import java.util.UUID;

public class Hive {
    public String name;
    public String hiveID;
    public String inspectionResults;
    public String health;
    public String honeyStores;
    public String queenProduction;
    public String equipmentOnHive;
    public String equipmentInventory;
    public String losses;
    public String gains;

    public Hive(){
    }

    public Hive(String name, String inspectionResults, String health, String honeyStores, String queenProduction,
                String equipmentOnHive, String equipmentInventory, String losses, String gains){
        this.name = name;
        this.inspectionResults = inspectionResults;
        this.health = health;
        this.honeyStores = honeyStores;
        this.queenProduction = queenProduction;
        this.equipmentOnHive = equipmentOnHive;
        this.equipmentInventory = equipmentInventory;
        this.losses = losses;
        this.gains = gains;
        setHiveID();
    }

    public Hive(String name, String inspectionResults, String health, String honeyStores, String queenProduction,
                String equipmentOnHive, String equipmentInventory, String losses, String gains, String hiveID){
        this.name = name;
        this.inspectionResults = inspectionResults;
        this.health = health;
        this.honeyStores = honeyStores;
        this.queenProduction = queenProduction;
        this.equipmentOnHive = equipmentOnHive;
        this.equipmentInventory = equipmentInventory;
        this.losses = losses;
        this.gains = gains;
        this.hiveID = hiveID;
    }


    public String getInspectionResults() {
        return inspectionResults;
    }

    public void setInspectionResults(String inspectionResults) {
        this.inspectionResults = inspectionResults;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getHoneyStores() {
        return honeyStores;
    }

    public void setHoneyStores(String honeyStores) {
        this.honeyStores = honeyStores;
    }

    public String getQueenProduction() {
        return queenProduction;
    }

    public void setQueenProduction(String queenProduction) {
        this.queenProduction = queenProduction;
    }

    public String getEquipmentOnHive() {
        return equipmentOnHive;
    }

    public void setEquipmentOnHive(String equipmentOnHive) {
        this.equipmentOnHive = equipmentOnHive;
    }

    public String getEquipmentInventory() {
        return equipmentInventory;
    }

    public void setEquipmentInventory(String equipmentInventory) {
        this.equipmentInventory = equipmentInventory;
    }

    public String getLosses() {
        return losses;
    }

    public void setLosses(String losses) {
        this.losses = losses;
    }

    public String getGains() {
        return gains;
    }

    public void setGains(String gains) {
        this.gains = gains;
    }

    public String getHiveID() {
        return hiveID;
    }

    public void setHiveID() {
        hiveID = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
