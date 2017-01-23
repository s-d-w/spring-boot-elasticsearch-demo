package com.esdemo.repository.search;

import com.esdemo.domain.Building;
import java.util.List;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

public interface BuildingSearchCustom {
    
    List<Building> findNearest(GeoPoint geoPoint);
    
}
