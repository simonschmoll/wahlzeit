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
public class CartesianCoordinate extends AbstractCoordinate{
	
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
}
