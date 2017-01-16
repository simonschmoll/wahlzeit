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

	private Mountain mountain;

	/**
	 * @methodtype constructor
	 */
	public MountainPhoto() {
		super();
	}

	/**
	 * 
	 * @param id
	 * 
	 * @methodtype constructor
	 */
	public MountainPhoto(PhotoId id){
		super(id);
	}

	/**
	 * 
	 * @param id
	 * @param height
	 * @throws IllegalHeightMountainException 
	 * 
	 * @methodtype constructor
	 */
	public MountainPhoto(PhotoId id, Mountain mountain) {
		super(id);
		this.mountain = mountain;
		
	}



}
