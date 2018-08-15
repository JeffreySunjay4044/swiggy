package jacryd.dev.swggy.autoassignment.utils;

public class HaversineDistance {

	
	public static int getDistance(String latitude1, String longitude1, String latitude2, String longitude2) {
        final int R = 6371; // Radious of the earth
        Double lat1 = Double.valueOf(latitude1);
        Double lon1 = Double.valueOf(longitude1);
        Double lat2 = Double.valueOf(latitude2);
        Double lon2 = Double.valueOf(longitude2);
        Double latDistance = toRad(lat2-lat1);
        Double lonDistance = toRad(lon2-lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + 
                   Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) * 
                   Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double distance = (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)) * R );
        return distance.intValue();
         
       // System.out.println("The distance between two lat and long is::" + distance);
	}
	
	 private static Double toRad(Double value) {
	        return value * Math.PI / 180;
	    }
}
