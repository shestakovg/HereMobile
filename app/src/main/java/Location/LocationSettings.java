package Location;

/**
 * Created by gshestakov on 11/9/2017.
 */

public class LocationSettings {
      public static LocationObject getCenterPoint() {
          //return new LocationObject(36.14246903430814,  -115.15458120648195);
          return new LocationObject( 37.6967563833721,  -95.96284827897907);
      }

      public static  LocationObject getTestMarker() {
          return new LocationObject(36.136573114814716,-115.16292601519251);
      }

      public static  LocationObject[] getDefaultMarkers()
      {
          return new LocationObject[]
                  {
                          new LocationObject(36.1707123233295,  -115.15050433088545),
                          new LocationObject(38.898742767829596,  -77.0348121922136),
                          new LocationObject(40.837417102736644,  -73.91388986941594)
                  };
      }
}
