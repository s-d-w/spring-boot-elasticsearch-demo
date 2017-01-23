package com.esdemo.repository.search;

import com.esdemo.domain.Building;
import java.util.List;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

@Repository
public class BuildingSearchCustomRepository implements BuildingSearchCustom {
    
    private final BuildingSearchRepository buildingSearchRepository;
    private final ElasticsearchTemplate elasticsearchTemplate;
    
    @Autowired
    public BuildingSearchCustomRepository(BuildingSearchRepository buildingSearchRepository, ElasticsearchTemplate elasticsearchTemplate) {
        this.buildingSearchRepository = buildingSearchRepository;
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    @Override
    /* The 'sort' feature (using geo_coords) doesn't work when using @Query, so here is a non spring-data way of searching ES */
    public List<Building> findNearest(GeoPoint geoPoint) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withSort(SortBuilders.geoDistanceSort("location").point(geoPoint.getLat(), geoPoint.getLon()).order(SortOrder.ASC).unit(DistanceUnit.KILOMETERS))
                .withQuery(QueryBuilders.matchAllQuery())
                .build();
        
        return elasticsearchTemplate.queryForList(searchQuery, com.esdemo.domain.Building.class);
    }
}
