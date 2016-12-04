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

import org.junit.Test;

public class LocationTest {

	Location testLocationSpheric;
	Location testLocationCartesian;
	
	/**
	 *
	 */
	@Test
	public void constructorTestSpheric() {
		SphericCoordinate testCoordinate = new SphericCoordinate(0,0);
		testLocationSpheric = new Location(testCoordinate);
		assertNotNull(testLocationSpheric);
	}
	
	/**
	 *
	 */
	@Test
	public void constructorTestCartesian() {
		CartesianCoordinate testCoordinate = new CartesianCoordinate(0,0, 6371);
		testLocationCartesian = new Location(testCoordinate);
		assertNotNull(testLocationCartesian);
	}
	
	
}