package org.wahlzeit.model;


import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;

/*
* LocationCoordinateTest
*
* V 1.1
*
* 31.10.16
*
* 
*/

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
	@Test
	public void accuracyTestForFarLocations() {
		testLocation = new Location(23.684994, 90.356331); // polar coordinates of someplace in Bangladesh
		Location tokyo = new Location(35.689487,139.691706);
		double distanceBetweenTokyoAndBangladeshEllipsoidCalculator = 4903.162222070491; 
		// calculated with http://www.fai.org/distance_calculation/ earth model: WGS84
		double deviationSphericalToEllipsoid = distanceBetweenTokyoAndBangladeshEllipsoidCalculator * 
													deviationPercentageBetweenSphericalAndEllipsoid;
		double result = testLocation.getDistance(tokyo);

		Assert.assertEquals(result, distanceBetweenTokyoAndBangladeshEllipsoidCalculator, deviationSphericalToEllipsoid);
		// according to Wikipedia the deviation might add up to 5%
	}
	
	/**
	 *
	 */

	@Test
	public void accuracyTestForNearLocations() {
		testLocation = new Location(52.520007, 13.404954); // polar coordinates of somewhere in Berlin
		Location hamburg = new Location(53.551085, 9.993682);
		double distanceBetweenBerlinAndHamburgEllipsoidCalculator = 255.9556456597355; 
				// calculated with http://www.fai.org/distance_calculation/ earth model: WGS84
		double deviationSphericalToEllipsoid = 	(distanceBetweenBerlinAndHamburgEllipsoidCalculator * 
													deviationPercentageBetweenSphericalAndEllipsoid);
		double result = testLocation.getDistance(hamburg);

		Assert.assertEquals(result, distanceBetweenBerlinAndHamburgEllipsoidCalculator, deviationSphericalToEllipsoid);
		// according to Wikipedia the deviation might add up to 5%
	}
	

	
	
}
