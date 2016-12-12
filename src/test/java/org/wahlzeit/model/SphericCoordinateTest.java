package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.exceptionhandling.DoubleOutOfRangeException;
import org.wahlzeit.exceptionhandling.IllegalParameterDistanceException;
import org.wahlzeit.exceptionhandling.NullCoordinateException;
import org.wahlzeit.exceptionhandling.SphericParametersInvalidException;

public class SphericCoordinateTest {
	SphericCoordinate sphericCoor;
	CartesianCoordinate cartesianCoor;
	
	/**
	 * @throws SphericParametersInvalidException 
	 * 
	 */
	@Before
	public void setUp() throws SphericParametersInvalidException {
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
	 * @throws SphericParametersInvalidException 
	 * @throws DoubleOutOfRangeException 
	 * 
	 */
	@Test
	public void setterGetterTestSphericCoordinate() throws SphericParametersInvalidException, DoubleOutOfRangeException {
		sphericCoor.setLatitude(90);
		sphericCoor.setLongitude(0);
		sphericCoor.setRadius(6000);
		Assert.assertEquals(sphericCoor.getLatitude(),90, 0);
		Assert.assertEquals(sphericCoor.getLongitude(),0, 0);
		Assert.assertEquals(sphericCoor.getRadius(),6000, 0);
	}
	
	/**
	 * @throws SphericParametersInvalidException 
	 * 
	 */
	@Test (expected = SphericParametersInvalidException.class)
	public void constructorInvalidParameterSphericLatitude() throws SphericParametersInvalidException {
		sphericCoor = new SphericCoordinate(91, 0);
	}
	
	/**
	 * @throws SphericParametersInvalidException 
	 * 
	 */
	@Test (expected = SphericParametersInvalidException.class)
	public void constructorInvalidParameterSphericLatitude2() throws SphericParametersInvalidException {
		sphericCoor = new SphericCoordinate(-91, 0);
	}	
	
	/**
	 * @throws SphericParametersInvalidException 
	 * 
	 */
	@Test (expected = SphericParametersInvalidException.class)
	public void constructorInvalidParameterSphericLongitude() throws SphericParametersInvalidException {
		sphericCoor = new SphericCoordinate(0, 181);
	}
	
	/**
	 * @throws SphericParametersInvalidException 
	 * 
	 */
	@Test (expected = SphericParametersInvalidException.class)
	public void constructorInvalidParameterSphericLongitude2() throws SphericParametersInvalidException {
		sphericCoor = new SphericCoordinate(0, -181);
	}
	
	/**
	 * @throws SphericParametersInvalidException 
	 * 
	 */
	@Test (expected = SphericParametersInvalidException.class)
	public void setterInvalidParameterSphericLatitude() throws SphericParametersInvalidException {
		sphericCoor.setLatitude(91);
	}
	
	/**
	 * @throws SphericParametersInvalidException 
	 * 
	 */
	@Test (expected = SphericParametersInvalidException.class)
	public void setterInvalidParameterSphericLatitude2() throws SphericParametersInvalidException {
		sphericCoor.setLatitude(-91);
	}
	
	/**
	 * @throws SphericParametersInvalidException 
	 * 
	 */
	@Test (expected = SphericParametersInvalidException.class)
	public void setterInvalidParameterSphericLongitude() throws SphericParametersInvalidException {
		sphericCoor.setLongitude(181);
	}
	
	/**
	 * @throws SphericParametersInvalidException 
	 * 
	 */
	@Test (expected = SphericParametersInvalidException.class)
	public void setterInvalidParameterSphericLongitude2() throws SphericParametersInvalidException {
		sphericCoor.setLatitude(-181);
	}
	
	/**
	 * @throws SphericParametersInvalidException 
	 * 
	 */
	@Test
	public void conversionSphericToCartesianTest() throws SphericParametersInvalidException {
		sphericCoor = new SphericCoordinate(90, 0);
		cartesianCoor = new CartesianCoordinate(0, 0, 6371);
		Assert.assertEquals(cartesianCoor.getCartesianX(), sphericCoor.getCartesianX(), 0.001);
		Assert.assertEquals(cartesianCoor.getCartesianY(), sphericCoor.getCartesianY(), 0.001);
		Assert.assertEquals(cartesianCoor.getCartesianZ(), sphericCoor.getCartesianZ(), 0.001);
	}
	
	/**
	 * @throws SphericParametersInvalidException 
	 * @throws DoubleOutOfRangeException 
	 * @throws IllegalParameterDistanceException 
	 * @throws NullCoordinateException 
	 * 
	 */
	@Test (expected = NullCoordinateException.class)
	public void nullArgumentForDistanceTest() throws SphericParametersInvalidException, NullCoordinateException, IllegalParameterDistanceException, DoubleOutOfRangeException {
		sphericCoor = new SphericCoordinate(90, 0);
		sphericCoor.getDistance(null);
	}
	
	/**
	 * @throws SphericParametersInvalidException 
	 * @throws DoubleOutOfRangeException 
	 * @throws IllegalParameterDistanceException 
	 * @throws NullCoordinateException 
	 * 
	 */
	@Test
	public void distanceTestSameCoordinateType() throws SphericParametersInvalidException, NullCoordinateException, IllegalParameterDistanceException, DoubleOutOfRangeException {
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
	public void distanceTestDifferentCoordinateTypes() throws SphericParametersInvalidException, NullCoordinateException, IllegalParameterDistanceException, DoubleOutOfRangeException {
		SphericCoordinate sphericCoor = new SphericCoordinate (90, 0);
		CartesianCoordinate cartesianCoor = new CartesianCoordinate(0,0, -6371);
		double resultExpected = 2*6371;
		double resultCalculatedSpheric = sphericCoor.getDistance(cartesianCoor);
		double resultCalculatedCartesian = cartesianCoor.getDistance(sphericCoor);
		Assert.assertEquals( resultExpected, resultCalculatedSpheric, 0.001);
		Assert.assertEquals( resultExpected, resultCalculatedCartesian, 0.001);
				
	}	
	
	/**
	 * @throws SphericParametersInvalidException 
	 * @throws NullCoordinateException 
	 * 
	 */
	@Test
	public void isEqualTest() throws SphericParametersInvalidException, NullCoordinateException {
		SphericCoordinate sphericCoor = new SphericCoordinate(-90, 0);
		SphericCoordinate sphericCoor2 = new SphericCoordinate (90, 0);
		CartesianCoordinate cartesianCoor = new CartesianCoordinate(0,0, -6371);
		CartesianCoordinate cartesianCoor2 = new CartesianCoordinate(0,0, 6371);
		Assert.assertTrue(sphericCoor.isEqual(cartesianCoor));
		Assert.assertTrue(sphericCoor2.isEqual(cartesianCoor2));
	}
	
	/**
	 * @throws SphericParametersInvalidException 
	 * @throws NullCoordinateException 
	 * 
	 */
	@Test
	public void isEqualTest2() throws SphericParametersInvalidException, NullCoordinateException {
		SphericCoordinate sphericCoor = new SphericCoordinate(-90, 0);
		SphericCoordinate sphericCoor2 = new SphericCoordinate (90, 0);
		CartesianCoordinate cartesianCoor = new CartesianCoordinate(0,0, -6371);
		CartesianCoordinate cartesianCoor2 = new CartesianCoordinate(0,0, 6371);
		Assert.assertFalse(sphericCoor.isEqual(cartesianCoor2));
		Assert.assertFalse(sphericCoor2.isEqual(cartesianCoor));
	}
	
	/**
	 * 
	 * @throws SphericParametersInvalidException
	 * @throws NullCoordinateException
	 */
	@Test (expected = NullCoordinateException.class)
	public void isEqualWithNullObject () throws SphericParametersInvalidException, NullCoordinateException {
		SphericCoordinate sphericCoor = new SphericCoordinate(-90, 0);
		sphericCoor.isEqual(null);
	}
	
	
}
