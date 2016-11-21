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
public class SphericCoordinate implements Coordinate {

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
	 * @param comüparisonCoordinate
	 * @return distance
	 */
	public double getDistance(Coordinate comparisonCoordinate) throws IllegalArgumentException {

		if (comparisonCoordinate instanceof SphericCoordinate) {
			return calculateDistance((SphericCoordinate) comparisonCoordinate);

		} else if (comparisonCoordinate instanceof CartesianCoordinate) {
			CartesianCoordinate helperCoordinate = (CartesianCoordinate) comparisonCoordinate;
			return calculateDistance(convertFromCartesianToSpheric(helperCoordinate));
		} else {
			throw new IllegalArgumentException("Not a defined subtyp of Coordinate" + comparisonCoordinate);
		}

	}

	/**
	 * 
	 * @param coordinate
	 * @return distance between two Spheric Coordinates
	 */
	public double calculateDistance(SphericCoordinate coordinate) {
		double latitudeRadian1 = Math.toRadians(coordinate.getLatitude());
		double latitudeRadian2 = Math.toRadians(this.latitude);
		double absoluteDistanceLatitudeInRadian = Math.toRadians(Math.abs(coordinate.getLatitude() - this.latitude));
		double absoluteDistanceLongitudeInRadian = Math.toRadians(Math.abs(coordinate.getLongitude() - this.longitude));

		double centralAngle = 2 * Math.asin(
				Math.sqrt(Math.pow((Math.sin(absoluteDistanceLatitudeInRadian / 2)), 2) + (Math.cos(latitudeRadian1)
						* Math.cos(latitudeRadian2) * Math.pow(Math.sin(absoluteDistanceLongitudeInRadian / 2), 2))));
		double result = radius * centralAngle;
		return result;
	}
	
	/**
	 * converts a Cartesian Coordinate to a Spheric Coordinate under the premise
	 * that the earth is spherical and not ellipsoid
	 * 
	 * retrieved from https://rbrundritt.wordpress.com/2008/10/14/conversion-between-spherical-and-cartesian-coordinates-systems/
	 * 
	 * @param coordinate
	 * @return SphericCoordinate
	 * @methodtype helper
	 */
	public SphericCoordinate convertFromCartesianToSpheric(CartesianCoordinate coordinate) {
		double radius = Math.sqrt(Math.pow(coordinate.getX(), 2) + Math.pow(coordinate.getY(), 2) + Math.pow(coordinate.getZ(), 2));
		double latitude = Math.toDegrees(Math.asin(coordinate.getZ()/ radius));
		double longitude = Math.toDegrees(Math.atan2(coordinate.getY(), coordinate.getX()));
		return new SphericCoordinate(latitude, longitude, radius);
	}
	
	
}
