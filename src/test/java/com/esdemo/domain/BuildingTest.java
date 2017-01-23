package com.esdemo.domain;

import com.esdemo.ESDemoApp;
import com.esdemo.domain.Building;
import com.esdemo.repository.BuildingRepository;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ESDemoApp.class)
@Transactional
public class BuildingTest {
    
    @Autowired
    private BuildingRepository buildingRepository;
    
    @Before
    public void init() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void buildingPersistence() {
        Building b = new Building();
        b.setConstruction("marble");
        b.setLatitude(39.5D);
        b.setLongitude(-105.5D);
        b.setNumberOfStories(100);
        b.setOccupancy("commercial office space");
        b.setSquareFootage(150000L);
        b.setTotalInsuredValue(8000000000000L);
        b.setYearBuilt(2016);
        b = buildingRepository.saveAndFlush(b);

        Building building = buildingRepository.findOne(b.getId());        
        assertEquals("marble", building.getConstruction());
        assertEquals(8000000000000L, building.getTotalInsuredValue());
        assertEquals(39.5D, building.getLatitude(), 0);
        assertEquals(-105.5D, building.getLongitude(), 0.0D);
        
    }
    
}
