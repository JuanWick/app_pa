package fr.esgi.services.util;

 public class GpsUtil {

    private  Double degreToRadian(double valueToConvert) {
        return Math.PI * valueToConvert / 180;
    }

    public  Double getDistanceBetweenCoordinates(Double lat1, Double long1, Double lat2, Double long2) {

        if(null == lat1 || null == long1 || null == lat2 || null == long2){ return -1.0; }

        Double earthRadius = 6378137.0;   // Terre 6378km de rayon

        lat1 = degreToRadian(lat1);    // CONVERSION
        long1 = degreToRadian(long1);
        lat2 = degreToRadian(lat2);
        long2 = degreToRadian(long2);

        Double distanceLon = (long2 - long1) / 2;
        Double distanceLat = (lat2 - lat1) / 2;

        Double a = (Math.sin(distanceLat) * Math.sin(distanceLat)) + Math.cos(lat1) * Math.cos(lat2) * (Math.sin(distanceLon) * Math.sin(distanceLon));
        Double b = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return (earthRadius * b);
    }
}
