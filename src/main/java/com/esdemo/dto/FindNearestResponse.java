package com.esdemo.dto;

import com.esdemo.domain.Building;

public class FindNearestResponse {
    
    private BuildingDTO nearestBuilding;
    
    private Double originalLatitude;
    
    private Double originalLongitude;
    
    private Double distanceBetweenInKilometers;
    
    public FindNearestResponse() {}
    
    public FindNearestResponse(Building b, Double originalLatitude, Double originalLongitude, Double distanceBetweenInKilometers) {
        this.nearestBuilding = new BuildingDTO(b);
        this.originalLatitude = originalLatitude;
        this.originalLongitude = originalLongitude;
        this.distanceBetweenInKilometers = distanceBetweenInKilometers;  
    }
    
    public BuildingDTO getNearestBuilding() {
        return nearestBuilding;
    }

    public void setNearestBuilding(BuildingDTO nearestBuilding) {
        this.nearestBuilding = nearestBuilding;
    }

    public Double getOriginalLatitude() {
        return originalLatitude;
    }

    public void setOriginalLatitude(Double originalLatitude) {
        this.originalLatitude = originalLatitude;
    }

    public Double getOriginalLongitude() {
        return originalLongitude;
    }

    public void setOriginalLongitude(Double originalLongitude) {
        this.originalLongitude = originalLongitude;
    }

    public Double getDistanceBetweenInKilometers() {
        return distanceBetweenInKilometers;
    }

    public void setDistanceBetweenInKilometers(Double distanceBetweenInKilometers) {
        this.distanceBetweenInKilometers = distanceBetweenInKilometers;
    }    

}
