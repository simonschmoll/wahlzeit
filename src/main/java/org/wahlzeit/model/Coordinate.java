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

import org.wahlzeit.exceptionhandling.DoubleOutOfRangeException;
import org.wahlzeit.exceptionhandling.IllegalParameterDistanceException;
import org.wahlzeit.exceptionhandling.NullCoordinateException;

/**
 * 
 * interface for the Coordinate classes
 *
 */
interface Coordinate {


	/**
	 * 
	 * @param coordinate
	 * @return distance
	 * @throws IllegalParameterDistanceException 
	 * @throws NullCoordinateException 
	 * @throws DoubleOutOfRangeException 
	 */
	public double getDistance(Coordinate comparisonCoordinate) throws NullCoordinateException, IllegalParameterDistanceException, DoubleOutOfRangeException;
	
	/**
	 * 
	 * @param coordinate
	 * @return boolean
	 * @throws NullCoordinateException 
	 */
	public boolean isEqual(Coordinate comparisonCoordinate) throws NullCoordinateException;
	
	/**
	 * 
	 * @return value x of the Cartesian Coordinate representation
	 */
	public double getCartesianX();
	
	/**
	 * 
	 * @return value y of the Cartesian Coordinate representation
	 */
	public double getCartesianY();
	
	/**
	 * 
	 * @return value z of the Cartesian Coordinate representation
	 */
	public double getCartesianZ();
	
}
