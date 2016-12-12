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

	DEFAULT, FLAT_COUNTRY, HILL_COUNTRY, LOW_MOUNTAIN_RANGE, MOUNTAIN_RANGE, HIGH_MOUNTAIN_RANGE;

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
	 * @methodtype conversion
	 */
	public static MountainAltitudinalBelt getAsInt(int value) {
		return allAltitudinalBelts[value];
	}

	/**
	 *
	 */
	private static String[] AltitudinalBeltsNames = { "default", "flat country", "hill country", "low mountain range",
			"mountain range", "high mountain range" };

	/**
	 *
	 */
	public static MountainAltitudinalBelt getFromString(String sValue) {
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
