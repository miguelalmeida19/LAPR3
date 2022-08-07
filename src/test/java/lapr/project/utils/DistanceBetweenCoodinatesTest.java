package lapr.project.utils;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DistanceBetweenCoodinatesTest {

    @Test
    public void distanceBetweenCoordinatesRight() {
        double lat1 = 41.173917974419794;
        double lon1 = -8.549532908229658;
        double lat2 = 41.17885556027196;
        double lon2 =   -8.546897234258287;
        assert DistanceBetweenCoodinates.distanceBetweenCoordinates(lat1,lon1,lat2,lon2) == 0.5917                ;
    }
    @Test
    public void distanceBetweenCoordinatesWrong() {
        double lat1 = 41.112356106509144;
        double lon1 = -18.54365240550001;
        double lat2 = 44.178861125079889;
        double lon2 =  8.546863123523182;
        assertNotEquals(DistanceBetweenCoodinates.distanceBetweenCoordinates(lat1,lon1,lat2,lon2),0.36459);
    }

    @Test
    public void distanceBetweenCoordinatesImpossibleValues1() {
        double lat1 = 190.14553246574312;
        double lon1 = -8.543693720550001;
        double lat2 = 41.17886176079889;
        double lon2 =  -8.546863812473182;
        assertThrows(
                IllegalArgumentException.class,()->{
                    DistanceBetweenCoodinates.distanceBetweenCoordinates(lat1,lon1,lat2,lon2);

                });
    }
    @Test
    public void distanceBetweenCoordinatesImpossibleValues2() {
        double lat1 = 41.176613106509144;
        double lon1 = -190.17886176079889;
        double lat2 = 41.17886176079889;
        double lon2 =  -10.256414421350;
        assertThrows(
                IllegalArgumentException.class,()->{
                    DistanceBetweenCoodinates.distanceBetweenCoordinates(lat1,lon1,lat2,lon2);

                });
    }
 @Test
    public void distanceBetweenCoordinatesImpossibleValues3() {
     double lat1 = 100.0;
     double lon1 = -8.543693720550001;
     double lat2 = 190.17886176079889;
     double lon2 =  -10.256414421350;
     assertThrows(
             IllegalArgumentException.class,()->{
                 DistanceBetweenCoodinates.distanceBetweenCoordinates(lat1,lon1,lat2,lon2);

             });
    }
    @Test
    public void distanceBetweenCoordinatesImpossibleValues4() {
        double lat1 = 41.176613106509144;
        double lon1 = -19.17886176079889;
        double lat2 = 41.17886176079889;
        double lon2 =  -181.0;
        assertThrows(
                IllegalArgumentException.class,()->{
                    DistanceBetweenCoodinates.distanceBetweenCoordinates(lat1,lon1,lat2,lon2);

                });


    }

}