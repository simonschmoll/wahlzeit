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

package org.wahlzeit.model.mountain;

import org.wahlzeit.utils.EnumValue;

/**
 * 
 * The enum MountainHeight gives the MountainPhoto an attribute
 *
 */
public enum MountainAltitudinalBelt implements EnumValue {

	DEFAULT(0), FLAT_COUNTRY(1), HILL_COUNTRY(2), LOW_MOUNTAIN_RANGE(3), MOUNTAIN_RANGE(4), HIGH_MOUNTAIN_RANGE(5);

	/**
	 * All possible states of the mountain altitudinal belt and one additional
	 * default value
	 */
	private static MountainAltitudinalBelt[] allAltitudinalBelts = { DEFAULT, FLAT_COUNTRY, HILL_COUNTRY,
			LOW_MOUNTAIN_RANGE, MOUNTAIN_RANGE, HIGH_MOUNTAIN_RANGE };

	/**
	 * 
	 * @param value
	 * @return value as Integer
	 * @throws IllegalArgumentException
	 * @methodtype conversion
	 */
	public static MountainAltitudinalBelt getAsInt(int value) throws IllegalArgumentException {
		assertIsValidMountainHeight(value);
		return allAltitudinalBelts[value];
	}

	/**
	 * 
	 * @param value
	 * @throws IllegalArgumentException
	 * @methodtype assertion
	 * 
	 */
	protected static void assertIsValidMountainHeight(int value) throws IllegalArgumentException {
		if ((value < 0) || (value > 5)) {
			throw new IllegalArgumentException("invalid height: " + value);
		}
	}

	/**
	 *
	 */
	private static String[] AltitudinalBeltsNames = { "default", "flat country", "hill country", "low mountain range",
			"mountain range", "high mountain range" };

	/**
	 *
	 */
	public static MountainAltitudinalBelt getFromString(String sValue) throws IllegalArgumentException {
		for (MountainAltitudinalBelt value : MountainAltitudinalBelt.values()) {
			if (AltitudinalBeltsNames[value.asInt()].equals(sValue)) {
				return value;
			}
		}

		throw new IllegalArgumentException("invalid AltitudinalBeltsName string: " + sValue);
	}

	private int value = 0;

	/**
	 * 
	 * @param newValue
	 */
	MountainAltitudinalBelt(int newValue) {
		value = newValue;
	}

	/**
	 * 
	 */
	public int asInt() {
		return value;
	}

	/**
	 *
	 */
	public String asString() {
		return AltitudinalBeltsNames[value];
	}

	/**
	 *
	 */
	public MountainAltitudinalBelt[] getAllValues() {
		return allAltitudinalBelts;
	}

	/**
	 *
	 */
	public String getTypeName() {
		return "MountainHeight";
	}

	/**
	 *
	 */
	public boolean isCreated() {
		return true;
	}

}
