/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;

/**
 * 
 * class to represent a Spheric Coordinate
 *
 */
public class SphericCoordinate extends AbstractCoordinate {

	private double latitude;
	private double longitude;
	private double radius;
	public static final int MAXLATITUDE = 90;
	public static final int MINLATITUDE = -90;
	public static final int MAXLONGITUDE = 180;
	public static final int MINLONGITUDE= -180;
	private static final double EARTHRADIUS_KM = 6371.0;

	/**
	 * @methodtype constructor
	 */
	public SphericCoordinate() {
		this.latitude = 0;
		this.longitude = 0;
		this.radius = EARTHRADIUS_KM;
	}

	/**
	 * @methodtype constructor
	 */
	public SphericCoordinate(double latitude, double longitude) {
		this(latitude, longitude, EARTHRADIUS_KM);
	}

	/**
	 * @methodtype constructor
	 */
	public SphericCoordinate(double latitude, double longitude, double radius) {
		assertIsValidSphericCoordinate(latitude, longitude);
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
	}
	
	public static void assertIsValidSphericCoordinate(double latitude, double longitude) throws IllegalArgumentException {
		if((longitude < MINLONGITUDE ) || (longitude > MAXLONGITUDE)) {
			throw new IllegalArgumentException("Invalid Parameter for Longitude: " + longitude);
		}else if (( latitude < MINLATITUDE ) || ( latitude > MAXLATITUDE)) {
			throw new IllegalArgumentException("Invalid Parameter for Latitude: " + latitude);
		} 
	}

	/**
	 * 
	 * @return latitude
	 * @methodytype getter
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * 
	 * @return longitude
	 * @methodytype getter
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * 
	 * @return radius
	 * @methodytype getter
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * 
	 * @param latitude
	 * @methodtype setter
	 */
	public void setLatitude(double latitude) throws IllegalArgumentException{
		assertIsValidSphericCoordinate(latitude, 0);
		this.latitude = latitude;
	}

	/**
	 * 
	 * @param longitude
	 * @methodtype setter
	 */
	public void setLongitude(double longitude) throws IllegalArgumentException{
		assertIsValidSphericCoordinate(0, longitude);
		this.longitude = longitude;
	}

	/**
	 * 
	 * @param radius
	 * @methodtype setter
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	/**
	 * calculates the distance between two coordinates
	 * 
	 * @param comparisonCoordinate
	 * @return distance
	 */
	public double getDistance(Coordinate comparisonCoordinate) throws IllegalArgumentException {
		if(comparisonCoordinate instanceof SphericCoordinate || comparisonCoordinate instanceof CartesianCoordinate){
			return super.getDistance(comparisonCoordinate);
		} else {
			throw new IllegalArgumentException("wrong Coordinate type" + comparisonCoordinate);
		}
	}

	 /**
	  * 
	  * @return Cartesian Coordinate
	  * @methodtype conversion
	  * 
	  */
	 public CartesianCoordinate asCartesian(){
			 double lat = Math.toRadians(this.latitude);
			 double longi = Math.toRadians(this.longitude);
			 double x = this.radius * Math.cos(lat) *
			 Math.cos(longi);
			 double y = this.radius * Math.cos(lat) *
			 Math.sin(longi);
			 double z = this.radius * Math.sin(lat);
			 return new CartesianCoordinate(x, y, z);
	 }
	 
	 
	 
	
	
	
}
