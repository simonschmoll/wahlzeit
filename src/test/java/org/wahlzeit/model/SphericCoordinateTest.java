package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SphericCoordinateTest {
	SphericCoordinate sphericCoor;
	CartesianCoordinate cartesianCoor;
	
	/**
	 * @throws SphericParametersInvalidException 
	 * 
	 */
	@Before
	public void setUp(){
		sphericCoor = SphericCoordinate.getInstance(90, 0 , 6000);
		cartesianCoor = CartesianCoordinate.getInstance(1,1, 6371);
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
		Assert.assertEquals(sphericCoor.getLatitude(),90, 0);
		Assert.assertEquals(sphericCoor.getLongitude(),0, 0);
		Assert.assertEquals(sphericCoor.getRadius(),6000, 0);
	}
	
	/**
	 * @throws IllegalArgumentException 
	 * 
	 */
	@Test (expected = IllegalArgumentException.class)
	public void invalidParameterSphericLatitude() throws IllegalArgumentException {
		sphericCoor = SphericCoordinate.getInstance(91, 0);
	}
	
	/**
	 * @throws IllegalArgumentException 
	 * 
	 */
	@Test (expected = IllegalArgumentException.class)
	public void invalidParameterSphericLatitude2() throws IllegalArgumentException {
		sphericCoor = SphericCoordinate.getInstance(-91, 0);
	}	
	
	/**
	 * @throws IllegalArgumentException 
	 * 
	 */
	@Test (expected = IllegalArgumentException.class)
	public void invalidParameterSphericLongitude() throws IllegalArgumentException {
		sphericCoor = SphericCoordinate.getInstance(0, 181);
	}
	
	/**
	 * @throws IllegalArgumentException 
	 * 
	 */
	@Test (expected = IllegalArgumentException.class)
	public void invalidParameterSphericLongitude2() throws IllegalArgumentException {
		sphericCoor = SphericCoordinate.getInstance(0, -181);
	}
		
	/**
	 * @throws IllegalArgumentException 
	 * 
	 */
	@Test
	public void conversionSphericToCartesianTest() throws IllegalArgumentException {
		sphericCoor = SphericCoordinate.getInstance(90, 0);
		cartesianCoor = CartesianCoordinate.getInstance(0, 0, 6371);
		Assert.assertEquals(cartesianCoor.getCartesianX(), sphericCoor.getCartesianX(), 0.001);
		Assert.assertEquals(cartesianCoor.getCartesianY(), sphericCoor.getCartesianY(), 0.001);
		Assert.assertEquals(cartesianCoor.getCartesianZ(), sphericCoor.getCartesianZ(), 0.001);
	}
	
	/**
	 * @throws IllegalArgumentException
	 * @throws NullPointerException
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void nullArgumentForDistanceTest() throws IllegalArgumentException, NullPointerException {
		sphericCoor = SphericCoordinate.getInstance(90, 0);
		sphericCoor.getDistance(null);
	}
	
	/**
	 * @throws IllegalArgumentException
	 * @throws NullPointerException
	 * 
	 */
	@Test
	public void distanceTestSameCoordinateType() throws IllegalArgumentException, NullPointerException  {
		SphericCoordinate sphericCoor = SphericCoordinate.getInstance(-90, 0);
		SphericCoordinate sphericCoor2 = SphericCoordinate.getInstance(90, 0);
		CartesianCoordinate cartesianCoor = CartesianCoordinate.getInstance(0,0, 6371);
		CartesianCoordinate cartesianCoor2 = CartesianCoordinate.getInstance(0,0, -6371);
		double resultExpected = 2*6371;
		double resultCalculatedSpheric = sphericCoor.getDistance(sphericCoor2);
		double resultCalculatedCartesian = cartesianCoor.getDistance(cartesianCoor2);
		Assert.assertEquals( resultExpected, resultCalculatedSpheric, 0.001);
		Assert.assertEquals( resultExpected, resultCalculatedCartesian, 0.001);
	}
	
	/**
	 * @throws IllegalArgumentException
	 * @throws NullPointerException
	 * 
	 */
	@Test
	public void distanceTestDifferentCoordinateTypes() throws IllegalArgumentException, NullPointerException  {
		SphericCoordinate sphericCoor = SphericCoordinate.getInstance(90, 0);
		CartesianCoordinate cartesianCoor = CartesianCoordinate.getInstance(0,0, -6371);
		double resultExpected = 2*6371;
		double resultCalculatedSpheric = sphericCoor.getDistance(cartesianCoor);
		double resultCalculatedCartesian = cartesianCoor.getDistance(sphericCoor);
		Assert.assertEquals( resultExpected, resultCalculatedSpheric, 0.001);
		Assert.assertEquals( resultExpected, resultCalculatedCartesian, 0.001);
				
	}	
	
	/**
	 * @throws IllegalArgumentException
	 * @throws NullPointerException
	 * 
	 */
	@Test
	public void isEqualTest() throws IllegalArgumentException, NullPointerException  {
		SphericCoordinate sphericCoor = SphericCoordinate.getInstance(-90, 0);
		SphericCoordinate sphericCoor2 = SphericCoordinate.getInstance(90, 0);
		CartesianCoordinate cartesianCoor = CartesianCoordinate.getInstance(0,0, -6371);
		CartesianCoordinate cartesianCoor2 = CartesianCoordinate.getInstance(0,0, 6371);
		Assert.assertTrue(sphericCoor.isEqual(cartesianCoor));
		Assert.assertTrue(sphericCoor2.isEqual(cartesianCoor2));
	}
	
	/**
	 * @throws IllegalArgumentException
	 * @throws NullPointerException
	 * 
	 */
	@Test
	public void isEqualTest2() throws IllegalArgumentException, NullPointerException  {
		SphericCoordinate sphericCoor = SphericCoordinate.getInstance(-90, 0);
		SphericCoordinate sphericCoor2 = SphericCoordinate.getInstance(90, 0);
		CartesianCoordinate cartesianCoor = CartesianCoordinate.getInstance(0,0, -6371);
		CartesianCoordinate cartesianCoor2 = CartesianCoordinate.getInstance(0,0, 6371);
		Assert.assertFalse(sphericCoor.isEqual(cartesianCoor2));
		Assert.assertFalse(sphericCoor2.isEqual(cartesianCoor));
	}
	
	/**
	 * @throws IllegalArgumentException
	 * @throws NullPointerException
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void isEqualWithNullObject () throws IllegalArgumentException, NullPointerException  {
		SphericCoordinate sphericCoor = SphericCoordinate.getInstance(-90, 0);
		sphericCoor.isEqual(null);
	}
	
	/**
	 * 
	 */
	@Test
	public void equalsTest() {
		SphericCoordinate sphericCoor = SphericCoordinate.getInstance(90, 0);
		SphericCoordinate sphericCoor2 = SphericCoordinate.getInstance(90, 0);
		SphericCoordinate sphericCoor3 = SphericCoordinate.getInstance(90, -15);
		Assert.assertTrue(sphericCoor.equals(sphericCoor2));
		Assert.assertTrue(sphericCoor.equals(sphericCoor));
		Assert.assertFalse(sphericCoor.equals(sphericCoor3));
	}
	
	
}
