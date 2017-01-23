package com.esdemo.controller;

import com.esdemo.domain.Building;

import com.esdemo.dto.FindNearestResponse;
import com.esdemo.service.BuildingService;
import com.esdemo.service.util.HaversineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/building")
public class BuildingController {
    
    private final Logger log = LoggerFactory.getLogger(BuildingController.class);
    
    final private BuildingService buildingService;
    final private HaversineService havensineService;
    
    @Autowired
    public BuildingController(BuildingService buildingService, HaversineService havensineService) {
        this.buildingService = buildingService;
        this.havensineService = havensineService;   
    }
    
    @RequestMapping(value = "/findnearest",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FindNearestResponse> findNearest(@RequestParam("lat") double lat, @RequestParam("lon") double lon) {
        log.debug("Recieved findNearest request. Latitude: " + lat + " Longitude: " + lon);
        Building result = buildingService.findNearest(new GeoPoint(lat, lon));
        return result != null ? new ResponseEntity<>(new FindNearestResponse(result, lat, lon, 
                havensineService.distanceBetween(lat, lon, result.getLatitude(), result.getLongitude())), HttpStatus.OK) 
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }     
}
