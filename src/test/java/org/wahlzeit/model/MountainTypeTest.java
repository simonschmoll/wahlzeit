package org.wahlzeit.model;

import org.wahlzeit.model.mountain.MountainAltitudinalBelt;
import org.wahlzeit.model.mountain.MountainType;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * class to test the MountainType class
 *
 */
public class MountainTypeTest {

	private MountainAltitudinalBelt altitudinalBelt = MountainAltitudinalBelt.DEFAULT;
	MountainType testType = new MountainType("test", altitudinalBelt);
	MountainType testSubType = new MountainType("testSub", altitudinalBelt);
	
	@Before
	public void SetUp() {
		testType.addSubType(testSubType);
	}
	
	/**
	 * 
	 */
	@Test
	public void testConstructor() {
		assertNotNull(testType);
	}
	
	/**
	 * 
	 */
	@Test (expected = IllegalArgumentException.class)
	public void invalidParameterConstructor() {
		MountainType testType2 = new MountainType(null, altitudinalBelt);
	}
	
	/**
	 * 
	 */
	@Test
	public void getterTest() {
		assertEquals("test", testType.getTypeName());
		assertNotNull(testType.getSubtypes());
		assertEquals(altitudinalBelt, testType.getAltitudinalBelt());
	}
	
	/**
	 * 
	 */
	@Test 
	public void setterTest() {
		testType.setMountainAltitudinal(MountainAltitudinalBelt.FLAT_COUNTRY);
		assertEquals(testType.getAltitudinalBelt(), MountainAltitudinalBelt.FLAT_COUNTRY);
		testType.setTypeName("newType");
		assertEquals(testType.getTypeName(), "newType");
	}
	
	/**
	 * 
	 */
	@Test (expected = IllegalArgumentException.class)
	public void invalidParameterTypeName() {
		testType.setTypeName(null);
	}
	
	/**
	 * 
	 */
	@Test
	public void isSubtypeTest() {
		assertTrue(testType.isSubtype(testSubType));
	}
	
	/**
	 * 
	 */
	@Test
	public void isSubSubtypeTest() {
		MountainType testSubSubType = new MountainType("testSubSub", altitudinalBelt);
		testSubType.addSubType(testSubSubType);
		assertTrue(testType.isSubtype(testSubSubType));
	}
	
}
