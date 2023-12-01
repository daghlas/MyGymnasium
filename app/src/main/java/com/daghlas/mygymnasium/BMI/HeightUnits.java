package com.daghlas.mygymnasium.BMI;

import java.util.ArrayList;

public class HeightUnits {

    private static ArrayList<HeightUnits> unitsArrayList;
    private int id;
    private String name;

    public HeightUnits(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static void initUnits() {

        unitsArrayList = new ArrayList<>();

        HeightUnits centimeters = new HeightUnits(0, "cm");
        unitsArrayList.add(centimeters);

    }

    public static ArrayList<HeightUnits> getUnitsArrayList() {
        return unitsArrayList;
    }

    public static String[] unitNames() {
        String[] units = new String[unitsArrayList.size()];
        for (int i = 0; i < unitsArrayList.size(); i++) {
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
