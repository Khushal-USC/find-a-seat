package com.example.gridlayout;

import java.util.List;

public class Building {
    private int buildingID;
    private String name;
    private List<List<Integer>> availabilities = null;
    private String description;
    private int indoorMax;
    private int outdoorMax;

    public Building(int buildingID, String name, List<List<Integer>> availabilities, String description, int indoorMax, int outdoorMax) {
        this.buildingID = buildingID;
        this.name = name;
        try {
            this.setAvailabilities(availabilities);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.description = description;
        this.indoorMax = indoorMax;
        this.outdoorMax = outdoorMax;
    }

    public int getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(int buildingID) {
        this.buildingID = buildingID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<List<Integer>> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<List<Integer>> availabilities) throws Exception{
        if(availabilities.size() % 2 != 0){
            throw new Exception("availabilties must even");
        }
        this.availabilities = availabilities;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIndoorMax() {
        return indoorMax;
    }

    public void setIndoorMax(int indoorMax) {
        this.indoorMax = indoorMax;
    }

    public int getOutdoorMax() {
        return outdoorMax;
    }

    public void setOutdoorMax(int outdoorMax) {
        this.outdoorMax = outdoorMax;
    }

    public boolean isAvailableForDayAndTime(String day, int startTime, int endTime) {

        int dayNum = Day.getDay(day);
        for (int i = 0; i < availabilities.get(dayNum).size(); i += 2) {
            if (availabilities.get(dayNum).get(i) <= startTime && availabilities.get(dayNum).get(i + 1) >= endTime) {
                return true;
            }
        }
        return false;
    }

    public void bookDayAndTime(String day, List<Integer> time) {
        // TODO: Implement this method to book a day and time for the building
    }
}
