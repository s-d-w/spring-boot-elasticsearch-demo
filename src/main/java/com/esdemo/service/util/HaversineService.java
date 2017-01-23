package com.esdemo.service.util;

import org.springframework.stereotype.Service;

// reference: http://stackoverflow.com/questions/3694380/calculating-distance-between-two-points-using-latitude-longitude-what-am-i-doi
// modified to not figure in height above sea level and returns distance in Kilometers instead of meters

@Service
public class HaversineService {
    
    private final int R = 6371;
    
    public double distanceBetween(double lat1, double lon1, double lat2, double lon2) {
        Double latDistance = Math.toRadians(lat2 - lat1);
        Double lonDistance = Math.toRadians(lon2 - lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }    
}
