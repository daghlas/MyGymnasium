package com.daghlas.mygymnasium.BMI;

import java.util.ArrayList;

public class WeightUnits {

    private static ArrayList<WeightUnits> unitsArrayList;
    private int id;
    private String name;

    public WeightUnits(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static void initUnits(){

        unitsArrayList = new ArrayList<>();

        WeightUnits kilograms = new WeightUnits(0, "kg");
        unitsArrayList.add(kilograms);

        //WeightUnits pounds = new WeightUnits(0, "lbs");
        //unitsArrayList.add(pounds);
    }

    public static ArrayList<WeightUnits> getUnitsArrayList() {
        return unitsArrayList;
    }

    public static String[] unitNames(){
        String[] units = new String[unitsArrayList.size()];
        for(int i = 0; i<unitsArrayList.size(); i++){
            units[i] = unitsArrayList.get(i).name;
        }
        return units;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
