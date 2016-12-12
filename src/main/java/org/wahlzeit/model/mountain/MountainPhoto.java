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

import static java.util.logging.Level.SEVERE;

import java.util.logging.Logger;

import org.wahlzeit.exceptionhandling.IllegalHeightMountainException;
import org.wahlzeit.model.Location;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;

import com.googlecode.objectify.annotation.Subclass;

/**
 * 
 * class to save user-created MountainPhotos
 *
 */
@Subclass
public class MountainPhoto extends Photo {

	private static final Logger LOG = Logger.getLogger(MountainPhoto.class.getName());
	
	private MountainAltitudinalBelt altitudinalBelt;
	private double height = 0;
	
	Location location = Location.LOCATION_UNDEFINED;

	/**
	 * @methodtype constructor
	 */
	public MountainPhoto() {
		super();
		this.altitudinalBelt = MountainAltitudinalBelt.DEFAULT;
	}

	/**
	 * 
	 * @param id
	 * 
	 * @methodtype constructor
	 */
	public MountainPhoto(PhotoId id){
		super(id);
		this.altitudinalBelt = MountainAltitudinalBelt.DEFAULT;
	}

	/**
	 * 
	 * @param id
	 * @param height
	 * @throws IllegalHeightMountainException 
	 * 
	 * @methodtype constructor
	 */
	public MountainPhoto(PhotoId id, double height, String altitudinalBelt) throws IllegalHeightMountainException {
		super(id);
		assertIsValidHeight(height);
		this.altitudinalBelt = MountainAltitudinalBelt.getFromString(altitudinalBelt);
		this.height = height;
	}

	/**
	 * 
	 * @param altitudinalBelt
	 * 
	 * @methodtype set
	 */
	public void setAltitudinalBelt(String altitudinalBelt) {
		this.altitudinalBelt = MountainAltitudinalBelt.getFromString(altitudinalBelt);
	}

	/**
	 * 
	 * @param newHeight
	 * @throws IllegalHeightMountainException 
	 * 
	 * @methodtype set
	 */
	public void setMountainHeight(double newHeight) throws IllegalHeightMountainException {
		assertIsValidHeight(newHeight);
		this.height = newHeight;
	}
	
	/**
	 * @return altitudinal belt as a string
	 * 
	 * @methodtype get
	 */
	public String getAltitudinalBelt() {
		if (altitudinalBelt == null) {
			return null;
		}
		return altitudinalBelt.asString();
	}

	/**
	 * @return height
	 * 
	 * @methodtype get
	 */
	public double getMountainHeight() {
		return this.height;
	}

	/**
	 * 
	 * @param height
	 * @throws IllegalHeightMountainException
	 * 
	 * @metodtype assertion
	 */
	public void assertIsValidHeight(double height) throws IllegalHeightMountainException {
		// this is necessary because the highest mountain - regarding the
		// elevation above sea level-
		// is Mount Everest with 8850 metres and there is no mountain defined
		// under 0 metres
		if (height < 0 || height > 8850) {
			String exceptionMsg = "Illegal parameter for Mountain Height!";
			LOG.log(SEVERE, exceptionMsg);
			throw new IllegalHeightMountainException(exceptionMsg + height);
		}
	}
}
