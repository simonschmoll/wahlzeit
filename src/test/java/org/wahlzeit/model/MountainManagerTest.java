package org.wahlzeit.model;

import static org.junit.Assert.assertNotNull;
import org.junit.Assert;
import org.junit.ClassRule;
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
	
	
	MountainManager manager = new MountainManager();

	/**
	 * 
	 */
	@Test
	public void singletonTest() {
		assertNotNull(manager);
		assertNotNull(MountainManager.getInstance());
	}

	/**
	 * 
	 */
	@Test
	public void createMountainTest() {
		assertNotNull(MountainManager.getInstance().createMountain(new MountainType("Test", MountainAltitudinalBelt.DEFAULT), "Test", 0, Continent.Default));
	}
	
	/**
	 * 
	 */
	@Test
	public void createMountainTypeTest() {
		assertNotNull(MountainManager.getInstance().createMountainType("Test", MountainAltitudinalBelt.DEFAULT));
		assertNotNull(MountainManager.getInstance().createMountainTypeWithSuperType("Test", MountainAltitudinalBelt.DEFAULT, new MountainType("Test", MountainAltitudinalBelt.DEFAULT)));
	}
		
}
