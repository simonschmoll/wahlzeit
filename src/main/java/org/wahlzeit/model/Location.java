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

import java.util.logging.Logger;
import static java.util.logging.Level.SEVERE;

import org.wahlzeit.exceptionhandling.DoubleOutOfRangeException;
import org.wahlzeit.exceptionhandling.IllegalParameterDistanceException;
import org.wahlzeit.exceptionhandling.NullCoordinateException;

import com.googlecode.objectify.annotation.Ignore;

/**
 * 
 * class to represent a location
 *
 */

public class Location{
	
	private static final Logger LOG = Logger.getLogger(Location.class.getName());
	
	@Ignore
	private Coordinate coordinate;
	public static final Location LOCATION_UNDEFINED = new Location(null);
	
	/**
	 * @throws NullCoordinateException 
	 * @methodtype constructor
	 */
	public Location(Coordinate coordinate){
		this.coordinate = coordinate;
	}
	
	/**
	 * 
	 * @param location
	 * @return distance
	 * @throws DoubleOutOfRangeException 
	 * @throws IllegalParameterDistanceException 
	 * @throws NullCoordinateException 
	 * @methodtype constructor
	 */
	public double getDistance(Location location) throws NullCoordinateException, IllegalParameterDistanceException, DoubleOutOfRangeException {
		try {
			return coordinate.getDistance(location.coordinate);
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
	}
	
	
	
}
