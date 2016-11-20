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
 * a class to represent a Cartesian Coordinate
 *
 */
public class CartesianCoordinate implements Coordinate{
	
	private double x;
	private double y;
	private double z;

	/**
	 * @methodtype constructor
	 */
	public CartesianCoordinate() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @methodtype constructor
	 */
	public CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * 
	 * @return x
	 * @methodytype getter
	 */
	public double getX() {
		return x;
	}

	/**
	 * 
	 * @return y
	 * @methodytype getter
	 */
	public double getY() {
		return y;
	}

	/**
	 * 
	 * @return z
	 * @methodytype getter
	 */
	public double getZ() {
		return z;
	}

	/**
	 * 
	 * @param x
	 * @methodtype setter
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * 
	 * @param y
	 * @methodtype setter
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * 
	 * @param z
	 * @methodtype setter
	 */
	public void setZ(double z) {
		this.z = z;
	}
	
	/**
	 * 
	 * @param comparisonCoordinate
	 * @return distance
	 * @throws IllegalArgumentException
	 */
	public double getDistance(Coordinate comparisonCoordinate) throws IllegalArgumentException {
		if (comparisonCoordinate instanceof SphericCoordinate) {
			SphericCoordinate coordinate = (SphericCoordinate) comparisonCoordinate;
			return calculateDistance(convertFromSphericToCartesian(coordinate));
		} else if (comparisonCoordinate instanceof CartesianCoordinate) {
			return this.calculateDistance((CartesianCoordinate) comparisonCoordinate);
		} else {
			throw new IllegalArgumentException("Not a defined subtyp of Coordinate" + comparisonCoordinate);
		}
	}
	
	/**
	 * 
	 * @param coordinate
	 * @return distance between two Spheric Coordinates
	 * @methodtype helper
	 * retrieved from http://mathsfirst.massey.ac.nz/Algebra/PythagorasTheorem/pythapp.htm
	 */
	public double calculateDistance(CartesianCoordinate coordinate) {
		return Math.abs(Math.sqrt( ( Math.pow(this.x - coordinate.getX(),2)) + ( Math.pow(this.y - coordinate.getY(),2)) + 
				( Math.pow(this.z - coordinate.getZ(),2))));
	}
	
	 /**
	 * converts a Spheric Coordinate to a Cartesian Coordinate under the
	 * premise that the earth is spherical and not ellipsoid
	 *
	 * @param coordinate
	 * @return Cartesian Coordinate
	 * @methodtype helper
	 * retrieved from https://rbrundritt.wordpress.com/2008/10/14/conversion-between-spherical-and-cartesian-coordinates-systems/
	 */
	 public CartesianCoordinate convertFromSphericToCartesian(SphericCoordinate coordinate) {
	 double lat = Math.toRadians(coordinate.getLatitude());
	 double longi = Math.toRadians(coordinate.getLongitude());
	 double x = coordinate.getRadius() * Math.cos(lat) *
	 Math.cos(longi);
	 double y = coordinate.getRadius() * Math.cos(lat) *
	 Math.sin(longi);
	 double z = coordinate.getRadius() * Math.sin(lat);
	 return new CartesianCoordinate(x, y, z);
	 }

}
