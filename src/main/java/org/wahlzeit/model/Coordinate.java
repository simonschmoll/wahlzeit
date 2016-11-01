package org.wahlzeit.model;

/*
* Coordinate
*
* V 1.1
*
* 31.10.16
*
* 
*/

public class Coordinate{
	private double latitude;
	private double longitude;
	public final double EARTHRADIUS_KM = 6371;
	
	/**
	 *
	 */
	public Coordinate(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public double getLatitude() {
		return this.latitude;
	}
	
	public double getLongitude() {
		return this.longitude;
	}

	
	/**
	 * calculates the distance between two coordinates
	 * @param coordinate
	 * @return distance
	 */
	public double getDistance (Coordinate coordinate) {
		
		
		double latitudeRadian1 = Math.toRadians(coordinate.latitude);
		double latitudeRadian2 = Math.toRadians(this.latitude);
		double absoluteDistanceLatitudeInRadian = Math.toRadians(Math.abs(coordinate.latitude-this.latitude));
		double absoluteDistanceLongitudeInRadian = Math.toRadians(Math.abs(coordinate.longitude-this.longitude));
		
		double centralAngle = 2 * Math.asin(Math.sqrt(Math.pow((Math.sin(absoluteDistanceLatitudeInRadian/2)),2) + 
						(Math.cos(latitudeRadian1) * Math.cos(latitudeRadian2) * Math.pow(Math.sin(absoluteDistanceLongitudeInRadian/2), 2))));
		double result = EARTHRADIUS_KM * centralAngle;
		return result;
	
	}

	
	
}
