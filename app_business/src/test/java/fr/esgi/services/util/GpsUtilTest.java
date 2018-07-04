package fr.esgi.services.util;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class GpsUtilTest extends TestCase {

    public GpsUtilTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( GpsUtilTest.class );
    }

    public void test_should_returnDistanceBetweenCoordinates()
    {
        //base location : 242 Rue du Faubourg Saint-Antoine, 75012 Paris, France
        Double baseLat = 48.8491666;
        Double baseLon = 2.3897342999999864;

        //100m : 252 Rue du Faubourg Saint-Antoine, 75012 Paris, France
        Double lat1 = 48.8490124;
        Double lon1 = 2.390833799999996;
        assertTrue( GpsUtil.getDistanceBetweenCoordinates(baseLat, baseLon, lat1,  lon1)<100);

        //2915m : Parvis de l'hotel de ville de Paris, France
        Double lat2 = 48.857376;
        Double lon2 = 2.351932;
        Double distance2 = GpsUtil.getDistanceBetweenCoordinates(baseLat, baseLon, lat2,  lon2);
        assertTrue( distance2 < 2920 && distance2 > 2910);

        //13km : Boulevard de Champy Nesles, Champs-sur-Marne, France
        Double lat3 = 48.8476106;
        Double lon3 = 2.5788645999999744;
        assertTrue( GpsUtil.getDistanceBetweenCoordinates(baseLat, baseLon, lat3,  lon3)<14000);
    }
}
