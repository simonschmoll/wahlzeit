package org.wahlzeit.model;

import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Test;
import org.wahlzeit.model.mountain.Mountain;
import org.wahlzeit.model.mountain.MountainAltitudinalBelt;
import org.wahlzeit.model.mountain.MountainType;


/**
 * 
 * class for testing Mountain class
 *
 */
public class MountainTest {
	private final double TEST_HEIGHT = 200;
	Continent continent = Continent.Default;
	MountainAltitudinalBelt testAltitudinal = MountainAltitudinalBelt.DEFAULT;
	MountainType testType = new MountainType("default", testAltitudinal);
	Mountain testPlainMountain = new Mountain(testType, "Test", TEST_HEIGHT, continent );
	


	/**
	 * 
	 */
	@Test
	public void getterTest() {
		Assert.assertEquals(TEST_HEIGHT, testPlainMountain.getMountainHeight(), 0.001);
		Assert.assertEquals(continent, testPlainMountain.getContinent());
	}

	/**
	 * 
	 */
	@Test
	public void constructorTest() {
		assertNotNull(testPlainMountain);
	}

	/**
	 * @throws IllegalHeightMountainException 
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void invalidParameterNegativHeightTest() throws IllegalArgumentException {
		testPlainMountain.setMountainHeight(-1);
	}

	/**
	 * @throws IllegalHeightMountainException 
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void invalidParameterTooBigHeightTest() throws IllegalArgumentException {
		testPlainMountain.setMountainHeight(8851);
	}
	
	/**
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void invalidName() {
		String test = null;
		testPlainMountain.setName(test);
	}
	
}
