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



import java.util.HashMap;


/**
 * 
 * a class to represent a Cartesian Coordinate
 *
 */
public class CartesianCoordinate extends AbstractCoordinate{
	
	private final double x;
	private final double y;
	private final double z;
	private static final int MAXIMUM_SIZE = 500;
	protected static final HashMap<Integer, CartesianCoordinate> cartCoordinates = new HashMap<>(MAXIMUM_SIZE); 

	public static synchronized CartesianCoordinate getInstance() {
		return getInstance(0, 0, 0);
	}
	
	public static synchronized CartesianCoordinate getInstance(double x, double y, double z) {
		CartesianCoordinate cartCoor = new CartesianCoordinate (x, y, z);
		CartesianCoordinate lookUpCoordinate = cartCoordinates.get(cartCoor.hashCode());
		if(lookUpCoordinate == null) {
			cartCoordinates.put(cartCoor.hashCode(), cartCoor);
			return cartCoor;
		}
		return lookUpCoordinate;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @methodtype constructor
	 */
	private CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
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
	 */
	@Override
	public int hashCode() {
		int result = 1;
		long hashX = Double.doubleToLongBits(x);
		long hashY = Double.doubleToLongBits(y);
		long hashZ = Double.doubleToLongBits(z);
		return result * 37 + (int)(hashX ^ (hashX >>> 32)) + (int)(hashY ^ (hashY >>> 32)) + (int)(hashZ ^ (hashZ >>> 32));
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object cartCoordinate) {
		if(this == cartCoordinate) {
			return true;
		}
		if(cartCoordinate == null || !(cartCoordinate instanceof SphericCoordinate)) {
			return false;
		}
		CartesianCoordinate equalsTest = (CartesianCoordinate) cartCoordinate;
		if( (Double.compare(equalsTest.getCartesianX(), this.x) != 0) &&
		(Double.compare(equalsTest.getCartesianY(), this.y) != 0) &&
		(Double.compare(equalsTest.getCartesianZ(), this.z) != 0) ) return true;
		
	return false;
			
	}
	
}
