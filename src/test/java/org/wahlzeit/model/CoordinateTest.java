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
public class CoordinateTest {
	
	SphericCoordinate sphericCoor;
	CartesianCoordinate cartesianCoor;
	
	/**
	 * 
	 */
	@Before
	public void setUp() {
		sphericCoor = new SphericCoordinate(1,1);
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
	 * 
	 */
	@Test
	public void constructorTestSphericalCoordinate() {
		Assert.assertNotNull(sphericCoor);
	}
	
	/**
	 * 
	 */
	@Test
	public void setterGetterTestCartesianCoordinate() {
		//Cartesian Coordinates of the North Pole
		cartesianCoor.setX(0);
		cartesianCoor.setY(0);
		cartesianCoor.setZ(6371);
		Assert.assertEquals(cartesianCoor.getCartesianX(), 0.0, 0);
		Assert.assertEquals(cartesianCoor.getCartesianY(), 0.0, 0);
		Assert.assertEquals(cartesianCoor.getCartesianZ(), 6371, 0);
	}
	
	/**
	 * 
	 */
	@Test
	public void setterGetterTestSphericCoordinate() {
		sphericCoor.setLatitude(90);
		sphericCoor.setLongitude(0);
		sphericCoor.setRadius(6000);
		Assert.assertEquals(sphericCoor.getLatitude(),90, 0);
		Assert.assertEquals(sphericCoor.getLongitude(),0, 0);
		Assert.assertEquals(sphericCoor.getRadius(),6000, 0);
	}
	
	/**
	 * 
	 */
	@Test (expected = IllegalArgumentException.class)
	public void constructorInvalidParameterSphericLatitude() {
		sphericCoor = new SphericCoordinate(91, 0);
	}
	
	/**
	 * 
	 */
	@Test (expected = IllegalArgumentException.class)
	public void constructorInvalidParameterSphericLatitude2() {
		sphericCoor = new SphericCoordinate(-91, 0);
	}	
	
	/**
	 * 
	 */
	@Test (expected = IllegalArgumentException.class)
	public void constructorInvalidParameterSphericLongitude() {
		sphericCoor = new SphericCoordinate(0, 181);
	}
	
	/**
	 * 
	 */
	@Test (expected = IllegalArgumentException.class)
	public void constructorInvalidParameterSphericLongitude2() {
		sphericCoor = new SphericCoordinate(0, -181);
	}
	
	/**
	 * 
	 */
	@Test (expected = IllegalArgumentException.class)
	public void setterInvalidParameterSphericLatitude() {
		sphericCoor.setLatitude(91);
	}
	
	/**
	 * 
	 */
	@Test (expected = IllegalArgumentException.class)
	public void setterInvalidParameterSphericLatitude2() {
		sphericCoor.setLatitude(-91);
	}
	
	/**
	 * 
	 */
	@Test (expected = IllegalArgumentException.class)
	public void setterInvalidParameterSphericLongitude() {
		sphericCoor.setLongitude(181);
	}
	
	/**
	 * 
	 */
	@Test (expected = IllegalArgumentException.class)
	public void setterInvalidParameterSphericLongitude2() {
		sphericCoor.setLatitude(-181);
	}
	
	/**
	 * 
	 */
	@Test
	public void conversionSphericToCartesianTest() {
		sphericCoor = new SphericCoordinate(90, 0);
		cartesianCoor = new CartesianCoordinate(0, 0, 6371);
		Assert.assertEquals(cartesianCoor.getCartesianX(), sphericCoor.getCartesianX(), 0.001);
		Assert.assertEquals(cartesianCoor.getCartesianY(), sphericCoor.getCartesianY(), 0.001);
		Assert.assertEquals(cartesianCoor.getCartesianZ(), sphericCoor.getCartesianZ(), 0.001);
	}
	
	/**
	 * 
	 */
	@Test
	public void distanceTestSameCoordinateType() {
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
	 * 
	 */
	@Test
	public void distanceTestDifferentCoordinateTypes() {
		SphericCoordinate sphericCoor = new SphericCoordinate (90, 0);
		CartesianCoordinate cartesianCoor = new CartesianCoordinate(0,0, -6371);
		double resultExpected = 2*6371;
		double resultCalculatedSpheric = sphericCoor.getDistance(cartesianCoor);
		double resultCalculatedCartesian = cartesianCoor.getDistance(sphericCoor);
		Assert.assertEquals( resultExpected, resultCalculatedSpheric, 0.001);
		Assert.assertEquals( resultExpected, resultCalculatedCartesian, 0.001);
				
	}	
	
	/**
	 * 
	 */
	@Test
	public void isEqualTest() {
		SphericCoordinate sphericCoor = new SphericCoordinate(-90, 0);
		SphericCoordinate sphericCoor2 = new SphericCoordinate (90, 0);
		CartesianCoordinate cartesianCoor = new CartesianCoordinate(0,0, -6371);
		CartesianCoordinate cartesianCoor2 = new CartesianCoordinate(0,0, 6371);
		Assert.assertTrue(sphericCoor.isEqual(cartesianCoor));
		Assert.assertTrue(sphericCoor2.isEqual(cartesianCoor2));
	}
	
	/**
	 * 
	 */
	@Test
	public void isEqualTest2() {
		SphericCoordinate sphericCoor = new SphericCoordinate(-90, 0);
		SphericCoordinate sphericCoor2 = new SphericCoordinate (90, 0);
		CartesianCoordinate cartesianCoor = new CartesianCoordinate(0,0, -6371);
		CartesianCoordinate cartesianCoor2 = new CartesianCoordinate(0,0, 6371);
		Assert.assertFalse(sphericCoor.isEqual(cartesianCoor2));
		Assert.assertFalse(sphericCoor2.isEqual(cartesianCoor));
	}
}
