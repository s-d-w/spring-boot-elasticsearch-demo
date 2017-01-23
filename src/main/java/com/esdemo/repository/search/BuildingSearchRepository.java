package com.esdemo.repository.search;

import com.esdemo.domain.Building;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BuildingSearchRepository extends ElasticsearchRepository<Building, Long> {   
    
    List<Building> findByLocationNear(GeoPoint geoPoint, String distance, Pageable pageable);  //doesn't sort by euclidean by default
   
    /* The sort directive is ignored - does't sort on closest Euclidean distance how it should. This query sorts properly using 'Sense' */ 
    String findNearestQuery = "{\"query\": {" + 
                                    "\"filtered\" : {" +
                                        "\"query\" : {" +
                                            "\"match_all\" : {}}," +
                                        "\"filter\" : {" +
                                            "\"geo_distance\" : {" +
                                                "\"distance\" : \"30km\"," +
                                                "\"location\" : {" +
                                                    "\"lat\" : ?0," +
                                                    "\"lon\" : ?1" +
                                                "}}}}}," +
                                "\"sort\" : [" +
                                    "{\"_geo_distance\" : {" + 
                                        "\"location\" : {" +
                                            "\"lat\" : \"?0\"," +
                                            "\"lon\" : \"?1\"" +
                                        "}," + 
                                        "\"order\" : \"asc\"," + 
                                        "\"unit\" : \"km\"}}]}";  
    
    @Query(findNearestQuery)
    List<Building> findNearest(double lat, double lon); // doesn't sort properly, needed to create a custom repository with concrete methods
}                                                       // see BuildingSearchCustomRepository
