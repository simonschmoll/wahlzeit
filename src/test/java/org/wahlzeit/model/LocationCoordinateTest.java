package org.wahlzeit.model;


import org.junit.Test;
import org.junit.Before;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class LocationCoordinateTest {
	
	Location testLocation;
	private static final double deviationPercentageBetweenSphericalAndEllipsoid = 0.05;
	
	/**
	 *
	 */
	@Before
	public void setUp() {
		testLocation = new Location(0,0);
	}
	
	/**
	 *
	 */
	@Test
	public void constructorTest() {
		assertNotNull(testLocation);
		assertNotNull(testLocation.coordinate);
	}
	
	/**
	 *
	 */
	@Test(expected = IllegalArgumentException.class)
	public void outOffUpperRangeLatitudeInLocationTest() {
		testLocation = new Location(Location.MAXLATITUDE + 1, 0 );
	}
	
	/**
	 *
	 */
	@Test(expected = IllegalArgumentException.class)
	public void outOfLowerRangeLatitudeInLocationTest() {
		testLocation = new Location(Location.MINLATITUDE - 1 , 0 );
	}
	
	/**
	 *
	 */
	@Test(expected = IllegalArgumentException.class)
	public void outOfLowerRangeLongitudeInLocationTest() {
		testLocation = new Location(0, Location.MINLONGITUDE - 1 );
	}
	
	/**
	 *
	 */
	@Test(expected = IllegalArgumentException.class)
	public void outOffUpperRangeLongitudeInLocationTest() {
		testLocation = new Location(0, Location.MAXLONGITUDE + 1);
	}
		
	/**
	*
	*/
	@Test(expected = IllegalArgumentException.class)
	public void outOffUpperRangeLatitudeInCoordinateTest() {
		testLocation.coordinate.getDistance(Location.MAXLATITUDE + 1, 0);
	}
	
	/**
	 *
	 */
	@Test(expected = IllegalArgumentException.class)
	public void outOfLowerRangeLatitudeInCoordinateTest() {
		testLocation.coordinate.getDistance(Location.MINLATITUDE - 1, 0);
	}
	
	/**
	 *
	 */
	@Test(expected = IllegalArgumentException.class)
	public void outOfLowerRangeLongitudeInCoordinateTest() {
		testLocation.coordinate.getDistance(0, Location.MINLONGITUDE - 1);
	}
	
	/**
	 *
	 */
	@Test(expected = IllegalArgumentException.class)
	public void outOffUpperRangeLongitudeInCoordinateTest() {
		testLocation.coordinate.getDistance(0, Location.MAXLONGITUDE + 1);
	}
		
	/**
	*
	*/
	@Test
	public void accuracyTestForFarLocations() {
		testLocation = new Location(23.684994, 90.356331); // polar coordinates of someplace in Bangladesh
		double latitudeTokyo = 35.689487;
		double longitudeTokyo = 139.691706;
		double distanceBetweenTokyoAndBangladeshEllipsoidCalculator = 4903.162222070491; 
		// calculated with http://www.fai.org/distance_calculation/ earth model: WGS84
		double minDeviationSphericalToEllipsoid = distanceBetweenTokyoAndBangladeshEllipsoidCalculator -
													(distanceBetweenTokyoAndBangladeshEllipsoidCalculator * 
													deviationPercentageBetweenSphericalAndEllipsoid);
		double maxDeviationSphericalToEllipsoid = distanceBetweenTokyoAndBangladeshEllipsoidCalculator +
													(distanceBetweenTokyoAndBangladeshEllipsoidCalculator * 
													deviationPercentageBetweenSphericalAndEllipsoid);
		double result = testLocation.coordinate.getDistance(latitudeTokyo, longitudeTokyo);
		assertTrue("Distance is not in the acceptable deviation limit: " + result, result > minDeviationSphericalToEllipsoid
						&& result < maxDeviationSphericalToEllipsoid);
		// according to Wikipedia the deviation might add up to 5%
	}
	
	/**
	 *
	 */
	@Test
	public void accuracyTestForNearLocations() {
		testLocation = new Location(52.520007, 13.404954); // polar coordinates of somewhere in Berlin
		double latitudeHamburg = 53.551085;
		double longitudeHamburg = 9.993682;
		double distanceBetweenBerlinAndHamburgEllipsoidCalculator = 255.9556456597355; 
				// calculated with http://www.fai.org/distance_calculation/ earth model: WGS84
		double minDeviationSphericalToEllipsoid =  distanceBetweenBerlinAndHamburgEllipsoidCalculator -
													(distanceBetweenBerlinAndHamburgEllipsoidCalculator * 
													deviationPercentageBetweenSphericalAndEllipsoid);
		double maxDeviationSphericalToEllipsoid = distanceBetweenBerlinAndHamburgEllipsoidCalculator +
													(distanceBetweenBerlinAndHamburgEllipsoidCalculator * 
													deviationPercentageBetweenSphericalAndEllipsoid);
		double result = testLocation.coordinate.getDistance(latitudeHamburg, longitudeHamburg);
		assertTrue("Distance is not in the acceptable deviation limit: " + result, result > minDeviationSphericalToEllipsoid
				&& result < maxDeviationSphericalToEllipsoid);
		// according to Wikipedia the deviation might add up to 5%
	}
	

	
	
}
