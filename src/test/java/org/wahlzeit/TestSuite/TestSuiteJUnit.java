package org.wahlzeit.TestSuite;

import org.junit.runners.Suite;
import org.wahlzeit.handlers.TestSuiteHandler;
import org.wahlzeit.model.TestSuiteModel;
import org.wahlzeit.model.persistence.TestSuiteModelPersistence;
import org.wahlzeit.services.TestSuiteServices;
import org.wahlzeit.services.mailing.TestSuiteServicesMailing;
import org.wahlzeit.utils.TestSuiteUtils;
import org.junit.runner.RunWith;

@RunWith(Suite.class)
	
	@Suite.SuiteClasses({
		TestSuiteHandler.class,
		TestSuiteModel.class,
		TestSuiteModelPersistence.class,
		TestSuiteServices.class,
		TestSuiteServicesMailing.class,
		TestSuiteUtils.class
		
	})

/**
 * 
 * class to encapsulate all tests of the Test Suites from the different packages
 *
 */
public class TestSuiteJUnit {

}
