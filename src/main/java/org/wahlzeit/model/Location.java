package org.wahlzeit.model;

/*
* Location
*
* V 1.1
*
* 31.10.16
*
* 
*/


public class Location{
	public Coordinate coordinate;
	public static final int MAXLATITUDE = 90;
	public static final int MINLATITUDE = -90;
	public static final int MAXLONGITUDE = 180;
	public static final int MINLONGITUDE= -180;
	
	public Location(double latitude, double longitude) {
		if(latitude < MINLATITUDE || latitude > MAXLATITUDE ) {
			throw new IllegalArgumentException("Latitude is out of acceptable Range" + latitude);
		}
		
		if(longitude < MINLONGITUDE || longitude > MAXLONGITUDE) {
			throw new IllegalArgumentException("Longitude is out of acceptable Range" + longitude);
		}
		
		this.coordinate = new Coordinate(latitude,longitude);
	}
	
	/**
	 * 
	 * @param coordinate
	 * @return distance
	 */
	public double getDistance(Location location) {
		return coordinate.getDistance(location.coordinate);
				
	}
	
	
	
}
