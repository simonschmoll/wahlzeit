package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SphericCoordinateTest {
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
	public void constructorTestSphericalCoordinate() {
		Assert.assertNotNull(sphericCoor);
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
	@Test (expected = NullPointerException.class)
	public void nullArgumentForDistanceTest() {
		sphericCoor = new SphericCoordinate(90, 0);
		sphericCoor.getDistance(null);
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
