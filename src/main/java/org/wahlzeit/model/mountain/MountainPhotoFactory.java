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

import java.util.logging.Logger;

import org.wahlzeit.model.PhotoFactory;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.services.LogBuilder;

/**
 * 
 * MountainPhotoFactory: class to create mountain photos
 *
 */
public class MountainPhotoFactory extends PhotoFactory {

	private static MountainPhotoFactory instance = null;

	private static final Logger log = Logger.getLogger(MountainPhotoFactory.class.getName());

	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	public static void initialize() {
		getInstance();
	}

	/**
	 * Public singleton access method.
	 * 
	 * @methodtype getter
	 */
	public static synchronized MountainPhotoFactory getInstance() {
		if (instance == null) {
			log.config(LogBuilder.createSystemMessage().addAction("setting generic MountainPhotoFactory").toString());
			setInstance(new MountainPhotoFactory());
		}

		return instance;
	}

	/**
	 * Method to set the singleton instance of MountainPhotoFactory.
	 * 
	 * @param MountainPhotoFactory
	 */
	protected static synchronized void setInstance(MountainPhotoFactory MountainPhotoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize MountainPhotoFactory twice");
		}

		instance = MountainPhotoFactory;
	}

	/**
	 * 
	 * @methodtype creation
	 */
	public MountainPhoto createPhoto() {
		return new MountainPhoto();
	}

	/**
	 * Creates a new photo with the specified id
	 * 
	 * @param id
	 * @return MountainPhoto
	 * 
	 * @methodtype creation
	 */
	public MountainPhoto createPhoto(PhotoId id) {
		return new MountainPhoto(id);
	}

	/**
	 * Creates a new photo of mountains with id, height, altitudinalBelt
	 * 
	 * @param id
	 * @param height
	 * @param altitudinalBelt
	 * @return MountainPhoto
	 * 
	 * @methodtype creation
	 */
	public MountainPhoto createPhoto(PhotoId id, double height, String altitudinalBelt) {
		return new MountainPhoto(id, height, altitudinalBelt);
	}

}
