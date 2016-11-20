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

import static org.junit.Assert.assertNotNull;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.model.mountain.MountainPhoto;
import org.wahlzeit.model.mountain.MountainPhotoFactory;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

/**
 * 
 * test class for the MountainPhotoFactory
 *
 */
public class MountainPhotoFactoryTest {

	/**
	 * 
	 */
	@ClassRule
	public static RuleChain ruleChain = RuleChain.outerRule(new LocalDatastoreServiceTestConfigProvider())
			.around(new RegisteredOfyEnvironmentProvider());

	/**
	 * 
	 */
	@Test
	public void constructorTest() {
		MountainPhotoFactory.initialize();
		assertNotNull(MountainPhotoFactory.getInstance());
	}

	/**
	 * 
	 */
	@Test
	public void createPhotoTest() {
		MountainPhoto testPhoto = MountainPhotoFactory.getInstance().createPhoto();
		assertNotNull(testPhoto);
	}

	/**
	 * 
	 */
	@Test
	public void createPhotowithIdTest() {
		PhotoId id = PhotoId.getNextId();
		MountainPhoto testPhoto = MountainPhotoFactory.getInstance().createPhoto(id);
		assertNotNull(testPhoto);
	}
}
