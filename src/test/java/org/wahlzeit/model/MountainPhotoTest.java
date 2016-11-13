/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;

import org.junit.Before;
import org.junit.rules.RuleChain;
import org.wahlzeit.model.mountain.MountainPhoto;
import org.wahlzeit.model.mountain.MountainPhotoManager;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;

import static org.junit.Assert.assertNotNull;

import org.junit.*;

/**
 * 
 * test class for MountainPhoto
 *
 */
public class MountainPhotoTest {

	MountainPhoto testPlainMountain = new MountainPhoto();
	private final double HEIGHT_PLAIN_MOUNTAIN = 200;

	/**
	 * necessary for Google API
	 */
	@ClassRule
	public static RuleChain ruleChain = RuleChain.outerRule(new LocalDatastoreServiceTestConfigProvider());

	/**
	 * 
	 */
	@Before
	public void setUp() {
		MountainPhotoManager.getInstance();
		testPlainMountain.setAltitudinalBelt("flat country");
		testPlainMountain.setMountainHeight(HEIGHT_PLAIN_MOUNTAIN);
	}

	/**
	 * 
	 */
	@Test
	public void getterTest() {
		Assert.assertEquals(HEIGHT_PLAIN_MOUNTAIN, testPlainMountain.getMountainHeight(), 0.001);
		Assert.assertEquals("flat country", testPlainMountain.getAltitudinalBelt());
	}

	/**
	 * 
	 */
	@Test
	public void constructorTest() {
		assertNotNull(testPlainMountain);
		assertNotNull(testPlainMountain.getAltitudinalBelt());
		assertNotNull(testPlainMountain.id);
		assertNotNull(testPlainMountain.getHeight());
	}

	/**
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void invalidParameterNegativHeightTest() {
		testPlainMountain.setMountainHeight(-1);
	}

	/**
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void invalidParameterTooBigHeightTest() {
		testPlainMountain.setMountainHeight(8851);
	}

	/**
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void invalidParameterAltitudinalBeltTest() {
		testPlainMountain.setAltitudinalBelt("");
	}

}
