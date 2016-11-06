package org.wahlzeit.TestSuite;

import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;
import org.junit.runner.Result;

/**
 * 
 * test Runner class for starting all necessary tests and for showing results on console
 *
 */

public class TestRunnerJUnit {
	public static void main(String[] args) {
		Result cache = JUnitCore.runClasses(TestSuiteJUnit.class);
		
		for (Failure fail : cache.getFailures()) {
			System.out.println(fail.toString());
		}
		
		System.out.println(cache.wasSuccessful());
	}
	
}
