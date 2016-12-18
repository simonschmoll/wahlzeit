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

/**
 * 
 * a class to represent a Cartesian Coordinate
 *
 */
public class CartesianCoordinate extends AbstractCoordinate{
	
	private static final Logger LOG = Logger.getLogger(CartesianCoordinate.class.getName());
	
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
	
	@Override
	public double getDistance(Coordinate comparisonCoordinate) throws NullPointerException, IllegalArgumentException{
		double distance = 0;
		try {
			distance = super.getDistance(comparisonCoordinate);
		} catch (NullPointerException nullObject) {
			LOG.log(SEVERE, nullObject.getMessage());
			throw new NullPointerException(nullObject.getMessage());
		} catch (IllegalArgumentException illegalArgument) { 
			LOG.log(SEVERE, illegalArgument.getMessage());
			throw new IllegalArgumentException(illegalArgument.getMessage());
		} 
		return distance;
	}
	
	@Override
	public boolean isEqual(Coordinate comparisonCoordinate) throws NullPointerException{
		boolean equal = false;
		try { 
			equal = super.isEqual(comparisonCoordinate);
		} catch (NullPointerException illegalArgument) {
			LOG.log(SEVERE, illegalArgument.getMessage());
			throw new NullPointerException(illegalArgument.getMessage());
		}
		return equal;
	}
	
	/**
	 * 
	 * @return x
	 * @methodytype getter
	 */
	public double getCartesianX() {
		return x;
	}

	/**
	 * 
	 * @return y
	 * @methodytype getter
	 */
	public double getCartesianY() {
		return y;
	}

	/**
	 * 
	 * @return z
	 * @methodytype getter
	 */
	public double getCartesianZ() {
		return z;
	}

	/**
	 * 
	 * @param x
	 * @throws DoubleOutOfRangeException 
	 * @methodtype setter
	 */
	public void setX(double x) throws IllegalArgumentException {
		try {
			assertIsValidDoubleRange(x);
		} catch (IllegalArgumentException illegalArgument) {
			LOG.log(SEVERE, illegalArgument.getMessage());
			throw new IllegalArgumentException(illegalArgument.getMessage());
		}
		this.x = x;
	}

	/**
	 * 
	 * @param y
	 * @throws DoubleOutOfRangeException 
	 * @methodtype setter
	 */
	public void setY(double y) throws IllegalArgumentException {
		try {
			assertIsValidDoubleRange(y);
		} catch (IllegalArgumentException illegalArgument) {
			LOG.log(SEVERE, illegalArgument.getMessage());
			throw new IllegalArgumentException(illegalArgument.getMessage());
		}
		this.y = y;
	}

	/**
	 * 
	 * @param z
	 * @throws DoubleOutOfRangeException 
	 * @methodtype setter
	 */
	public void setZ(double z) throws IllegalArgumentException {
		try {
			assertIsValidDoubleRange(z);
		} catch (IllegalArgumentException illegalArgument) {
			LOG.log(SEVERE, illegalArgument.getMessage());
			throw new IllegalArgumentException(illegalArgument.getMessage());
		}
		this.z = z;
	}	 
}
