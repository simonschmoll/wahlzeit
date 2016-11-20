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

private static final double deviationPercentageBetweenSphericalAndEllipsoid = 0.05;
	

	
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
		Assert.assertEquals(cartesianCoor.getX(), 0.0, 0.001);
		Assert.assertEquals(cartesianCoor.getY(), 0.0, 0.001);
		Assert.assertEquals(cartesianCoor.getZ(), 6371, 0.001);
	}
	
	/**
	 * 
	 */
	@Test
	public void setterGetterTestSphericCoordinate() {
		sphericCoor.setLatitude(90);
		sphericCoor.setLongitude(0);
		sphericCoor.setRadius(6000);
		Assert.assertEquals(sphericCoor.getLatitude(),90, 0.001);
		Assert.assertEquals(sphericCoor.getLongitude(),0, 0.001);
		Assert.assertEquals(sphericCoor.getRadius(),6000, 0.001);
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
		CartesianCoordinate testCoordinate = cartesianCoor.convertFromSphericToCartesian(sphericCoor);
		Assert.assertEquals(cartesianCoor.getX(), testCoordinate.getX(), 0.001);
		Assert.assertEquals(cartesianCoor.getY(), testCoordinate.getY(), 0.001);
		Assert.assertEquals(cartesianCoor.getZ(), testCoordinate.getZ(), 0.001);
	}
	
	/**
	 * 
	 */
	@Test
	public void conversionCartesianToSphericTest() {
		sphericCoor = new SphericCoordinate(90, 0);
		cartesianCoor = new CartesianCoordinate(0, 0, 6371);
		SphericCoordinate testCoordinate = sphericCoor.convertFromCartesianToSpheric(cartesianCoor);
		Assert.assertEquals(sphericCoor.getLatitude(), testCoordinate.getLatitude(), 0.001);
		Assert.assertEquals(sphericCoor.getLongitude(), testCoordinate.getLongitude(), 0.001);
		Assert.assertEquals(sphericCoor.getRadius(), testCoordinate.getRadius(), 0.001);
	}
	
	/**
	*
	*/
	@Test
	public void accuracyTestForFarLocations() {
		sphericCoor = new SphericCoordinate(23.684994, 90.356331); // polar coordinates of someplace in Bangladesh
		CartesianCoordinate cartesianBangladesh = cartesianCoor.convertFromSphericToCartesian(sphericCoor);
		SphericCoordinate tokyo = new SphericCoordinate(35.689487,139.691706);
		CartesianCoordinate cartesianTokyo = cartesianCoor.convertFromSphericToCartesian(tokyo);
		double distanceBetweenTokyoAndBangladeshEllipsoidCalculator = 4903.162222070491; 
		// calculated with http://www.fai.org/distance_calculation/ earth model: WGS84
		double deviationSphericalToEllipsoid = distanceBetweenTokyoAndBangladeshEllipsoidCalculator * 
													deviationPercentageBetweenSphericalAndEllipsoid;
		double resultSpheric = sphericCoor.getDistance(tokyo);
		double resultCartesian = cartesianBangladesh.getDistance(cartesianTokyo);

		Assert.assertEquals(resultSpheric, distanceBetweenTokyoAndBangladeshEllipsoidCalculator, deviationSphericalToEllipsoid);
		Assert.assertEquals(resultCartesian, distanceBetweenTokyoAndBangladeshEllipsoidCalculator, deviationSphericalToEllipsoid);
		// according to Wikipedia the deviation might add up to 5%
	}
	
	/**
	 *
	 */
	@Test
	public void accuracyTestForNearLocations() {
		sphericCoor = new SphericCoordinate(52.520007, 13.404954); // polar coordinates of somewhere in Berlin
		CartesianCoordinate cartesianBerlin = cartesianCoor.convertFromSphericToCartesian(sphericCoor);
		SphericCoordinate hamburg = new SphericCoordinate(53.551085, 9.993682);
		CartesianCoordinate cartesianHamburg = cartesianCoor.convertFromSphericToCartesian(hamburg);
		double distanceBetweenBerlinAndHamburgEllipsoidCalculator = 255.9556456597355; 
				// calculated with http://www.fai.org/distance_calculation/ earth model: WGS84
		double deviationSphericalToEllipsoid = 	(distanceBetweenBerlinAndHamburgEllipsoidCalculator * 
													deviationPercentageBetweenSphericalAndEllipsoid);
		double resultSpheric = sphericCoor.getDistance(hamburg);
		double resultCartesian = cartesianBerlin.getDistance(cartesianHamburg);
		
		Assert.assertEquals(resultSpheric, distanceBetweenBerlinAndHamburgEllipsoidCalculator, deviationSphericalToEllipsoid);
		Assert.assertEquals(resultCartesian, distanceBetweenBerlinAndHamburgEllipsoidCalculator, deviationSphericalToEllipsoid);
		// according to Wikipedia the deviation might add up to 5%
	}
	
	/**
	 * 
	 */
	@Test
	public void crossOverTestCartesianSpheric() {
		sphericCoor = new SphericCoordinate(52.520007, 13.404954); // polar coordinates of somewhere in Berlin
		CartesianCoordinate cartesianBerlin = cartesianCoor.convertFromSphericToCartesian(sphericCoor);
		SphericCoordinate hamburg = new SphericCoordinate(53.551085, 9.993682);
		double distanceBetweenBerlinAndHamburgEllipsoidCalculator = 255.9556456597355; 
				// calculated with http://www.fai.org/distance_calculation/ earth model: WGS84
		double deviationSphericalToEllipsoid = 	(distanceBetweenBerlinAndHamburgEllipsoidCalculator * 
													deviationPercentageBetweenSphericalAndEllipsoid);
		double resultCrossOver = cartesianBerlin.getDistance(hamburg);

		Assert.assertEquals(resultCrossOver, distanceBetweenBerlinAndHamburgEllipsoidCalculator, deviationSphericalToEllipsoid);
		// according to Wikipedia the deviation might add up to 5%
	}
	
	/**
	 * 
	 */
	@Test
	public void crossOverTestSphericCartesian() {
		sphericCoor = new SphericCoordinate(52.520007, 13.404954); // polar coordinates of somewhere in Berlin
		CartesianCoordinate cartesianBerlin = cartesianCoor.convertFromSphericToCartesian(sphericCoor);
		SphericCoordinate hamburgSpheric = new SphericCoordinate(53.551085, 9.993682);
		double distanceBetweenBerlinAndHamburgEllipsoidCalculator = 255.9556456597355; 
				// calculated with http://www.fai.org/distance_calculation/ earth model: WGS84
		double deviationSphericalToEllipsoid = 	(distanceBetweenBerlinAndHamburgEllipsoidCalculator * 
													deviationPercentageBetweenSphericalAndEllipsoid);
		double resultCrossOver = hamburgSpheric.getDistance(cartesianBerlin);

		Assert.assertEquals(resultCrossOver, distanceBetweenBerlinAndHamburgEllipsoidCalculator, deviationSphericalToEllipsoid);
		// according to Wikipedia the deviation might add up to 5%
	}
	
	
	
}
