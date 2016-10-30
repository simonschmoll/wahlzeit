package org.wahlzeit.model;

public class Coordinate{
	private double latitude;
	private double longitude;
	public final int earthRadius = 6371;
	
	/**
	 *
	 */
	public Coordinate(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	/**
	 *
	 */
	public double getDistance (double latitude, double longitude) {
		
		if(latitude < Location.MINLATITUDE || latitude > Location.MAXLATITUDE) {
			throw new IllegalArgumentException("Latitude is out of acceptable Range" + latitude);
		}
		
		if(longitude < Location.MINLONGITUDE || longitude > Location.MAXLONGITUDE) {
			throw new IllegalArgumentException("Longitude is out of acceptable Range" + longitude);
		}
		
		double latitudeRadian1 = Math.toRadians(latitude);
		double latitudeRadian2 = Math.toRadians(this.latitude);
		double absoluteDistanceLatitudeInRadian = Math.toRadians(Math.abs(latitude-this.latitude));
		double absoluteDistanceLongitudeInRadian = Math.toRadians(Math.abs(longitude-this.longitude));
		
		double centralAngle = 2 * Math.asin(Math.sqrt(Math.pow((Math.sin(absoluteDistanceLatitudeInRadian/2)),2) + 
						(Math.cos(latitudeRadian1) * Math.cos(latitudeRadian2) * Math.pow(Math.sin(absoluteDistanceLongitudeInRadian/2), 2))));
		double result = earthRadius * centralAngle;
		return result;
	
	}

	
	
}
