package com.esdemo.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Entity
@Document(indexName = "building")
public class Building implements Serializable {

    // latitude,longitude,TIV,occupancy,year built,square footage,number of stories,construction
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;    

    @Transient
    @GeoPointField
    private GeoPoint location;
    
    private double latitude;
    
    private double longitude;
    
    @Column(name = "totalinsuredvalue")
    private long totalInsuredValue;
    
    private String occupancy;
    
    @Column(name = "yearbuilt")
    private int yearBuilt;
    
    @Column(name = "squarefootage")
    private long squareFootage;
    
    @Column(name = "numberofstories")
    private int numberOfStories;
    
    private String construction;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
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
