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
 * abstract Superclass for Coordinates
 *
 */
public abstract class AbstractCoordinate implements Coordinate {

	/**
	 * calculates the distance between to Coordinates
	 * 
	 * @param comparisonCoordinate,
	 * @param comparisonCoordinate2
	 * @return direct distance between two Coordinates
	 * 
	 */
	public double getDistance(Coordinate comparisonCoordinate) throws IllegalArgumentException, NullPointerException {
		double distance = 0;
		assertIsNonNullArgument(comparisonCoordinate);
		distance = doCalculateDistance(comparisonCoordinate);
		assertDistanceNotNegative(distance);
		assertClassInvariants();
		return distance;
	}
	
	/**
	 * 
	 * @param comparisonCoordinate
	 * @return distance between two Coordinates
	 */
	private double doCalculateDistance(Coordinate comparisonCoordinate) {
		return Math.sqrt((Math.pow(comparisonCoordinate.getCartesianX() - this.getCartesianX(), 2))
				+ (Math.pow(comparisonCoordinate.getCartesianY() - this.getCartesianY(), 2))
				+ (Math.pow(comparisonCoordinate.getCartesianZ() - this.getCartesianZ(), 2)));
	}
	
	/**
	 * @param comparisonCoordinate
	 * @return boolean
	 * @methodtype boolean query
	 */
	public boolean isEqual(Coordinate comparisonCoordinate) throws NullPointerException {
		assertIsNonNullArgument(comparisonCoordinate);
		if (comparisonCoordinate == this) {
			return true;
		} else if (doIsEqualWithDeviation(comparisonCoordinate.getCartesianX(), this.getCartesianX())
			&& doIsEqualWithDeviation(comparisonCoordinate.getCartesianY(), this.getCartesianY())
			&& doIsEqualWithDeviation(comparisonCoordinate.getCartesianZ(), this.getCartesianZ()))
			return true;
		return false;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return boolean
	 */
	private boolean doIsEqualWithDeviation(double x, double y) {
		assertIsValidDoubleRange(x);
		assertIsValidDoubleRange(y);
		double deviation = 0.00001;
		if (Math.abs(x - y) <= deviation)
			return true;
		return false;
	}
	
	/**
	 * 
	 * @param distance
	 * @throws IllegalArgumentException
	 * @methodtype assertion
	 */
	public void assertDistanceNotNegative(double distance) throws IllegalArgumentException{
		if(distance < 0) 
			throw new IllegalArgumentException ("Distance must not be negativ!" + distance);
	}
	
	/**
	 * 
	 * @param value
	 * @throws IllegalArgumentException
	 * @methodtype assertion
	 */
	public void assertIsValidDoubleRange(double value) throws IllegalArgumentException{
		if(Double.isInfinite(value) || Double.isNaN(value))
			throw new IllegalArgumentException ("Double value is not valid" + value);
		}
	
	
	/**
	 * 
	 * @param comparisonCoordinate
	 * @throws NullPointerException
	 * @methodtype assertion
	 */
	public static void assertIsNonNullArgument(Coordinate comparisonCoordinate)throws NullPointerException{
		if (comparisonCoordinate == null) {
			throw new NullPointerException("Coordinate must not be null!");
		}
	}
	
	/**
	 * assertion method for Invariants
	 * currently not used --> might be needed for future implementations  
	 */
	public void assertClassInvariants() {
		
	}	
}
