package com.esdemo.service;

import com.esdemo.domain.Building;
import com.esdemo.repository.BuildingRepository;
import com.esdemo.repository.search.BuildingSearchCustomRepository;
import com.esdemo.repository.search.BuildingSearchRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BuildingService {

    private final Logger log = LoggerFactory.getLogger(BuildingService.class);
    
    private final BuildingRepository buildingRepository;
    private final BuildingSearchRepository buildingSearchRepository;
    private final BuildingSearchCustomRepository buildingSearchCustomRepository;
    
    public BuildingService(BuildingRepository buildingRepository, BuildingSearchRepository buildingSearchRepository, BuildingSearchCustomRepository buildingSearchCustomRepository) {
        this.buildingRepository = buildingRepository;
        this.buildingSearchRepository = buildingSearchRepository;
        this.buildingSearchCustomRepository = buildingSearchCustomRepository;
    }
    
    public Building findNearest(GeoPoint geoPoint) {
        List<Building> results = buildingSearchCustomRepository.findNearest(geoPoint);
        return results != null ? results.size() > 0 ? results.get(0) : null : null; 
    }
}
