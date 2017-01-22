package org.wahlzeit.model;

import static org.junit.Assert.assertNotNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.model.mountain.Mountain;
import org.wahlzeit.model.mountain.MountainAltitudinalBelt;
import org.wahlzeit.model.mountain.MountainManager;
import org.wahlzeit.model.mountain.MountainType;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;

/**
 * 
 * test class for the MountainManager
 */
public class MountainManagerTest {

	/**
	 * necessary for Google API
	 */
	@ClassRule
	public static RuleChain ruleChain = RuleChain.outerRule(new LocalDatastoreServiceTestConfigProvider());
	

	
	/**
	 * 
	 */
	@Test
	public void singletonTest() {
		MountainManager manager = new MountainManager();
		assertNotNull(manager);
		assertNotNull(MountainManager.getInstance());
	}

	/**
	 * 
	 */
	@Test
	public void createMountainTest() {
		assertNotNull(MountainManager.getInstance().createMountain(new MountainType("TestForInstance", MountainAltitudinalBelt.DEFAULT), "Test6", 0, Continent.Default));
	}
	
	/**
	 * 
	 */
	@Test
	public void createMountainTypeTest() {
		MountainManager.getInstance().createMountainType("TestType1", MountainAltitudinalBelt.DEFAULT);
		MountainManager.getInstance().createMountainTypeWithSuperType("TestType2", MountainAltitudinalBelt.DEFAULT, new MountainType("Test2", MountainAltitudinalBelt.DEFAULT));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void createMountainTypeDuplicatesTest(){
		MountainManager.getInstance().createMountainType("Test", MountainAltitudinalBelt.DEFAULT);
		MountainManager.getInstance().createMountainType("Test", MountainAltitudinalBelt.DEFAULT);
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void createMountainDuplicateTest() {
		MountainType testMountainType = new MountainType("Test", MountainAltitudinalBelt.DEFAULT);
		MountainManager.getInstance().createMountain(testMountainType, "Test", 0, Continent.Default);
		MountainManager.getInstance().createMountain(testMountainType, "Test", 0, Continent.Default);
	}
		
}
