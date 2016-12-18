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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * class to test the Coordinate classes
 *
 */
public class CartesianCoordinateTest {
	
	SphericCoordinate sphericCoor;
	CartesianCoordinate cartesianCoor;
	
	/**
	 * 
	 */
	@Before
	public void setUp() {
		cartesianCoor = new CartesianCoordinate(1,1, 6371);
	}
	
	/**
	 * 
	 */
	@Test
	public void constructorTestCartesianCoordinate() {
		Assert.assertNotNull(cartesianCoor);
	}
	

	
	/**
	 * @throws DoubleOutOfRangeException 
	 * 
	 */
	@Test
	public void setterGetterTestCartesianCoordinate() throws IllegalArgumentException {
		//Cartesian Coordinates of the North Pole
		cartesianCoor.setX(0);
		cartesianCoor.setY(0);
		cartesianCoor.setZ(6371);
		Assert.assertEquals(cartesianCoor.getCartesianX(), 0.0, 0);
		Assert.assertEquals(cartesianCoor.getCartesianY(), 0.0, 0);
		Assert.assertEquals(cartesianCoor.getCartesianZ(), 6371, 0);
	}
	
	
	/**
	 * @throws SphericParametersInvalidException 
	 * 
	 */
	@Test
	public void conversionSphericToSphericTest() throws IllegalArgumentException {
		sphericCoor = new SphericCoordinate(90, 0);
		cartesianCoor = new CartesianCoordinate(0, 0, 6371);
		Assert.assertEquals(sphericCoor.getCartesianX(), cartesianCoor.getCartesianX(), 0.001);
		Assert.assertEquals(sphericCoor.getCartesianY(), cartesianCoor.getCartesianY(), 0.001);
		Assert.assertEquals(sphericCoor.getCartesianZ(), cartesianCoor.getCartesianZ(),0.001);
	}
	
	/**
	 * @throws SphericParametersInvalidException 
	 * @throws DoubleOutOfRangeException 
	 * @throws IllegalParameterDistanceException 
	 * @throws NullCoordinateException 
	 * 
	 */
	@Test
	public void distanceTestSameCoordinateType() throws IllegalArgumentException, NullPointerException {
		SphericCoordinate sphericCoor = new SphericCoordinate(-90, 0);
		SphericCoordinate sphericCoor2 = new SphericCoordinate (90, 0);
		CartesianCoordinate cartesianCoor = new CartesianCoordinate(0,0, 6371);
		CartesianCoordinate cartesianCoor2 = new CartesianCoordinate(0,0, -6371);
		double resultExpected = 2*6371;
		double resultCalculatedSpheric = sphericCoor.getDistance(sphericCoor2);
		double resultCalculatedCartesian = cartesianCoor.getDistance(cartesianCoor2);
		Assert.assertEquals( resultExpected, resultCalculatedSpheric, 0.001);
		Assert.assertEquals( resultExpected, resultCalculatedCartesian, 0.001);
	}
	
	/**
	 * @throws SphericParametersInvalidException 
	 * @throws DoubleOutOfRangeException 
	 * @throws IllegalParameterDistanceException 
	 * @throws NullCoordinateException 
	 * 
	 */
	@Test
	public void distanceTestDifferentCoordinateTypes() throws IllegalArgumentException, NullPointerException  {
		SphericCoordinate sphericCoor = new SphericCoordinate (90, 0);
		CartesianCoordinate cartesianCoor = new CartesianCoordinate(0,0, -6371);
		double resultExpected = 2*6371;
		double resultCalculatedSpheric = sphericCoor.getDistance(cartesianCoor);
		double resultCalculatedCartesian = cartesianCoor.getDistance(sphericCoor);
		Assert.assertEquals( resultExpected, resultCalculatedSpheric, 0.001);
		Assert.assertEquals( resultExpected, resultCalculatedCartesian, 0.001);
				
	}	
	
	/**
	 * @throws NullCoordinateException 
	 * 
	 */
	@Test
	public void isEqualTest() throws NullPointerException  {
		CartesianCoordinate cartesianCoor = new CartesianCoordinate(0,0, 6371);
		CartesianCoordinate cartesianCoor2 = new CartesianCoordinate(0,0, 6371);
		Assert.assertTrue(cartesianCoor.isEqual(cartesianCoor2));
	}
	
	@Test (expected = NullPointerException.class)
	public void distanceWithNullParameter () throws IllegalArgumentException, NullPointerException  {
		CartesianCoordinate cartesianCoor = new CartesianCoordinate(0,0, 6371);
		cartesianCoor.getDistance(null);
	}
}
