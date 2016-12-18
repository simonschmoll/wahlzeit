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

import static java.util.logging.Level.SEVERE;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 * 
 * class to represent a Spheric Coordinate
 *
 */
public class SphericCoordinate extends AbstractCoordinate {

	private static final Logger LOG = Logger.getLogger(SphericCoordinate.class.getName());
	
	private final double latitude;
	private final double longitude;
	private final double radius;
	public static final int MAXLATITUDE = 90;
	public static final int MINLATITUDE = -90;
	public static final int MAXLONGITUDE = 180;
	public static final int MINLONGITUDE= -180;
	private static final double EARTHRADIUS_KM = 6371.0;
	private static final int MAXIMUM_SIZE = 500;
	protected static final HashMap<Integer, SphericCoordinate> sphCoordinates = new HashMap<>(MAXIMUM_SIZE); 

	/**
	 * @methodtype constructor
	 */
	public SphericCoordinate() {
		this.latitude = 0;
		this.longitude = 0;
		this.radius = EARTHRADIUS_KM;
	}

	/**
	 * 
	 * @return SphericCoordinate
	 */
	public static synchronized SphericCoordinate getInstance() {
		return getInstance(0, 0, EARTHRADIUS_KM);	
	}
	
	/**
	 * 
	 * @param latitude
	 * @param longitude
	 * @return SphericCoordinate
	 */
	public static synchronized SphericCoordinate getInstance(double latitude, double longitude) {
		return getInstance( latitude, longitude, EARTHRADIUS_KM);
	}
	
	/**
	 * 
	 * @param latitude
	 * @param longitude
	 * @param radius
	 * @return SphericCoordinate
	 */
	public static synchronized SphericCoordinate getInstance(double latitude, double longitude, double radius) {
		SphericCoordinate sphericCoor = new SphericCoordinate (latitude, longitude, radius);
		SphericCoordinate lookUpCoordinate = sphCoordinates.get(sphericCoor.hashCode());
		if(lookUpCoordinate == null) {
			sphCoordinates.put(sphericCoor.hashCode(), sphericCoor);
			return sphericCoor;
		}
		return lookUpCoordinate;
	}
	
	/**
	 * @throws IllegalArgumentException 
	 * @methodtype constructor
	 */
	private SphericCoordinate(double latitude, double longitude, double radius) throws IllegalArgumentException {
			assertIsValidLatitude(latitude);
			assertIsValidLongitude(longitude);
			assertIsValidRadius(radius);	
			this.latitude = latitude;
			this.longitude = longitude;
			this.radius = radius;	
			assertClassSphericInvariants();
	}
	
	/**
	 * 
	 * @throws IllegalArgumentException
	 */
	public void assertClassSphericInvariants() throws IllegalArgumentException{
			assertIsValidLatitude(latitude);
			assertIsValidLongitude(longitude);
			assertIsValidRadius(radius);
	}
	
	/**
	 * 
	 * @param latitude
	 * @throws IllegalArgumentException
	 * @methodtype assertion
	 */
	public static void assertIsValidLatitude(double latitude) throws IllegalArgumentException {
		if (( latitude < MINLATITUDE ) || ( latitude > MAXLATITUDE)) {
			String exceptionMsg = "Illegal latitude Parameter for Spheric Coordinate";
			LOG.log(SEVERE, exceptionMsg);
			throw new IllegalArgumentException(exceptionMsg);
		} 
	}
	
	
	
	/**
	 * 
	 * @param longitude
	 * @throws IllegalArgumentException
	 * @methodtype assertion
	 */
	public static void assertIsValidLongitude(double longitude) throws IllegalArgumentException {
		if((longitude < MINLONGITUDE ) || (longitude > MAXLONGITUDE)) {
			String exceptionMsg = "Illegal longitude Parameter for Spheric Coordinate";
			LOG.log(SEVERE, exceptionMsg);
			throw new IllegalArgumentException(exceptionMsg);
		}
	}
	
	/**
	 * 
	 * @param radius
	 * @throws IllegalArgumentException
	 * @methodtype assertion
	 */
	public static void assertIsValidRadius (double radius) throws IllegalArgumentException{
		if (radius <= 0) {
			String exceptionMsg = "Illegal radius Parameter for Spheric Coordinate";
			LOG.log(SEVERE, exceptionMsg);
			throw new IllegalArgumentException(exceptionMsg);
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
	 * @return value representing the x-coordinate in a Cartesian Coordinate system
	 * @methodtype conversion
	 */
	@Override
	public double getCartesianX() {
		return this.radius * Math.cos(Math.toRadians(this.latitude)) *
		 Math.cos(Math.toRadians(this.longitude));
	}

	/**
	 * @return value representing the y-coordinate in a Cartesian Coordinate system
	 * @methodtype conversion
	 */
	@Override
	public double getCartesianY() {
		return this.radius * Math.cos(Math.toRadians(this.latitude)) *
		Math.sin(Math.toRadians(this.longitude));
	}

	/**
	 * @return value representing the z-coordinate in a Cartesian Coordinate system
	 * @methodtype conversion
	 */
	@Override
	public double getCartesianZ() {
		return this.radius * Math.sin(Math.toRadians(this.latitude));
	}	
	 /**
	  * 
	  */
	@Override
	public int hashCode() {
		int result = 1;
		long hashLatitude = Double.doubleToLongBits(latitude);
		long hashLongitude = Double.doubleToLongBits(longitude);
		long hashRadius = Double.doubleToLongBits(radius);
		return result * 37 + (int)(hashLatitude ^ (hashLatitude >>> 32)) + (int)(hashLongitude ^ (hashLongitude >>> 32)) + (int)(hashRadius ^ (hashRadius >>> 32));
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object sphCoordinate) {
		if(this == sphCoordinate) {
			return true;
		}
		if(sphCoordinate == null || !(sphCoordinate instanceof SphericCoordinate)) {
			return false;
		}
		SphericCoordinate equalsTest = (SphericCoordinate) sphCoordinate;
		if( (Double.compare(equalsTest.getLatitude(), this.latitude) != 0) &&
		(Double.compare(equalsTest.getLongitude(), this.longitude) != 0) &&
		(Double.compare(equalsTest.getRadius(), this.radius) != 0) ) return true;
		
	return false;
			
	}
	
}
