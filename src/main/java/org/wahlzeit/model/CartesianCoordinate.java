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
	public void setX(double x) throws DoubleOutOfRangeException {
		try {
			assertIsValidDoubleRange(x);
		} catch (DoubleOutOfRangeException illegalArgument) {
			LOG.log(SEVERE, illegalArgument.getMessage());
			throw new DoubleOutOfRangeException(illegalArgument.getMessage());
		}
		this.x = x;
	}

	/**
	 * 
	 * @param y
	 * @throws DoubleOutOfRangeException 
	 * @methodtype setter
	 */
	public void setY(double y) throws DoubleOutOfRangeException {
		try {
			assertIsValidDoubleRange(y);
		} catch (DoubleOutOfRangeException illegalArgument) {
			LOG.log(SEVERE, illegalArgument.getMessage());
			throw new DoubleOutOfRangeException(illegalArgument.getMessage());
		}
		this.y = y;
	}

	/**
	 * 
	 * @param z
	 * @throws DoubleOutOfRangeException 
	 * @methodtype setter
	 */
	public void setZ(double z) throws DoubleOutOfRangeException {
		try {
			assertIsValidDoubleRange(z);
		} catch (DoubleOutOfRangeException illegalArgument) {
			LOG.log(SEVERE, illegalArgument.getMessage());
			throw new DoubleOutOfRangeException(illegalArgument.getMessage());
		}
		this.z = z;
	}	 
}
