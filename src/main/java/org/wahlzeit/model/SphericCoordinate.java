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

import java.util.logging.Logger;

import org.wahlzeit.exceptionhandling.DoubleOutOfRangeException;
import org.wahlzeit.exceptionhandling.IllegalParameterDistanceException;
import org.wahlzeit.exceptionhandling.NullCoordinateException;
import org.wahlzeit.exceptionhandling.SphericParametersInvalidException;

/**
 * 
 * class to represent a Spheric Coordinate
 *
 */
public class SphericCoordinate extends AbstractCoordinate {

	private static final Logger LOG = Logger.getLogger(SphericCoordinate.class.getName());
	
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
	 * @throws SphericParametersInvalidException 
	 * @methodtype constructor
	 */
	public SphericCoordinate(double latitude, double longitude) throws SphericParametersInvalidException {
		this(latitude, longitude, EARTHRADIUS_KM);
	}

	/**
	 * @throws SphericParametersInvalidException 
	 * @methodtype constructor
	 */
	public SphericCoordinate(double latitude, double longitude, double radius) throws SphericParametersInvalidException {
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
	 * @throws SphericParametersInvalidException
	 */
	public void assertClassSphericInvariants() throws SphericParametersInvalidException{
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
	public static void assertIsValidLatitude(double latitude) throws SphericParametersInvalidException {
		if (( latitude < MINLATITUDE ) || ( latitude > MAXLATITUDE)) {
			String exceptionMsg = "Illegal latitude Parameter for Spheric Coordinate";
			LOG.log(SEVERE, exceptionMsg);
			throw new SphericParametersInvalidException(exceptionMsg);
		} 
	}
	
	
	
	/**
	 * 
	 * @param longitude
	 * @throws IllegalArgumentException
	 * @methodtype assertion
	 */
	public static void assertIsValidLongitude(double longitude) throws SphericParametersInvalidException {
		if((longitude < MINLONGITUDE ) || (longitude > MAXLONGITUDE)) {
			String exceptionMsg = "Illegal longitude Parameter for Spheric Coordinate";
			LOG.log(SEVERE, exceptionMsg);
			throw new SphericParametersInvalidException(exceptionMsg);
		}
	}
	
	/**
	 * 
	 * @param radius
	 * @throws IllegalArgumentException
	 * @methodtype assertion
	 */
	public static void assertIsValidRadius (double radius) throws SphericParametersInvalidException{
		if (radius <= 0) {
			String exceptionMsg = "Illegal radius Parameter for Spheric Coordinate";
			LOG.log(SEVERE, exceptionMsg);
			throw new SphericParametersInvalidException(exceptionMsg);
		}
	}
	
	@Override
	public double getDistance(Coordinate comparisonCoordinate) throws NullCoordinateException, IllegalParameterDistanceException, DoubleOutOfRangeException {
		double distance = 0;
		try {
			distance = super.getDistance(comparisonCoordinate);
		} catch (NullCoordinateException nullObject) {
			LOG.log(SEVERE, nullObject.getMessage());
			throw new NullCoordinateException(nullObject.getMessage());
		} catch (IllegalParameterDistanceException illegalArgument) { 
			LOG.log(SEVERE, illegalArgument.getMessage());
			throw new IllegalParameterDistanceException(illegalArgument.getMessage());
		} catch (DoubleOutOfRangeException illegalArgument) { 
			LOG.log(SEVERE, illegalArgument.getMessage());
			throw new DoubleOutOfRangeException(illegalArgument.getMessage());
		}
		return distance;
	}
	
	@Override
	public boolean isEqual(Coordinate comparisonCoordinate) throws NullCoordinateException{
		boolean equal = false;
		try { 
			equal = super.isEqual(comparisonCoordinate);
		} catch (NullCoordinateException illegalArgument) {
			LOG.log(SEVERE, illegalArgument.getMessage());
			throw new NullCoordinateException(illegalArgument.getMessage());
		}
		return equal;
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
	 * @throws SphericParametersInvalidException 
	 * @methodtype setter
	 */
	public void setLatitude(double latitude) throws SphericParametersInvalidException{
		assertIsValidLatitude(latitude);
		this.latitude = latitude;
	}

	/**
	 * 
	 * @param longitude
	 * @throws SphericParametersInvalidException 
	 * @methodtype setter
	 */
	public void setLongitude(double longitude) throws SphericParametersInvalidException{
		assertIsValidLongitude(longitude);
		this.longitude = longitude;
	}

	/**
	 * 
	 * @param radius
	 * @throws DoubleOutOfRangeException 
	 * @throws SphericParametersInvalidException 
	 * @methodtype setter
	 */
	public void setRadius(double radius) throws DoubleOutOfRangeException, SphericParametersInvalidException {
		assertIsValidDoubleRange(radius);
		assertIsValidRadius(radius);
		this.radius = radius;
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
}
