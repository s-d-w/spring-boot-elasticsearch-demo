package com.esdemo.dto;

import com.esdemo.domain.Building;

public class BuildingDTO {
    
    private double latitude;
    
    private double longitude;
    
    private long totalInsuredValue;
    
    private String occupancy;
    
    private int yearBuilt;
    
    private long squareFootage;
    
    private int numberOfStories;
    
    private String construction;    
    
    public BuildingDTO() {}
    
    public BuildingDTO(Building b) {
        this.latitude = b.getLatitude();
        this.longitude = b.getLongitude();
        this.totalInsuredValue = b.getTotalInsuredValue();
        this.occupancy = b.getOccupancy();
        this.yearBuilt = b.getYearBuilt();
        this.squareFootage = b.getSquareFootage();
        this.numberOfStories = b.getNumberOfStories();
        this.construction = b.getConstruction();
    }
    
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getTotalInsuredValue() {
        return totalInsuredValue;
    }

    public void setTotalInsuredValue(long totalInsuredValue) {
        this.totalInsuredValue = totalInsuredValue;
    }

    public String getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(String occupancy) {
        this.occupancy = occupancy;
    }

    public int getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(int yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public long getSquareFootage() {
        return squareFootage;
    }

    public void setSquareFootage(long squareFootage) {
        this.squareFootage = squareFootage;
    }

    public int getNumberOfStories() {
        return numberOfStories;
    }

    public void setNumberOfStories(int numberOfStories) {
        this.numberOfStories = numberOfStories;
    }

    public String getConstruction() {
        return construction;
    }

    public void setConstruction(String construction) {
        this.construction = construction;
    }    
}
