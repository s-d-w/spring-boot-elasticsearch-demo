package com.esdemo.bootstrap;

import com.esdemo.domain.Building;
import com.esdemo.repository.BuildingRepository;
import com.esdemo.repository.search.BuildingSearchRepository;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Component;

@Component
public class BuildingsBootstrap implements Bootstrap {
    
    private static final Logger LOG = Logger.getLogger(BuildingsBootstrap.class.getName());    
    
    final private ResourceLoader resourceLoader;
    final private BuildingRepository buildingRepository;
    final private BuildingSearchRepository buildingSearchRepository;
    
    @Autowired
    public BuildingsBootstrap(ResourceLoader resourceLoader, BuildingRepository buildingRepository, BuildingSearchRepository buildingSearchRepository) {
        this.resourceLoader = resourceLoader;
        this.buildingRepository = buildingRepository;
        this.buildingSearchRepository = buildingSearchRepository;
    }

    // latitude,longitude,TIV,occupancy,year built,square footage,number of stories,construction    
    
    @Override
    public void bootstrap() {
        LOG.log(Level.INFO, "Loading bootstrap data for buildings..");

        InputStream is = null;
        Resource resource = resourceLoader.getResource("classpath:locations.csv");
        try {
            is = resource.getInputStream();
        } catch (IOException ex) {
            Logger.getLogger(BuildingsBootstrap.class.getName()).log(Level.SEVERE, null, ex);
        }
        Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
        Iterable<CSVRecord> records = null;
        try {
            records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
        } catch (IOException ex) {
            Logger.getLogger(BuildingsBootstrap.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (CSVRecord record : records) {
            Building b = new Building();
            b.setLatitude(Double.parseDouble(record.get("latitude")));
            b.setLongitude(Double.parseDouble(record.get("longitude")));
            b.setTotalInsuredValue(Long.parseLong(record.get("TIV")));
            b.setOccupancy(record.get("occupancy"));
            b.setYearBuilt(Integer.parseInt(record.get("year built")));
            b.setSquareFootage(Long.parseLong(record.get("square footage")));
            b.setNumberOfStories(Integer.parseInt(record.get("number of stories")));
            b.setConstruction(record.get("construction"));
            buildingRepository.save(b);
            b.setLocation(new GeoPoint(Double.parseDouble(record.get("latitude")), Double.parseDouble(record.get("longitude"))));
            buildingSearchRepository.save(b);   
        }
    }

    @Override
    public int getOrder() {
        return 100;
    }

}
