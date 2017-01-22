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

import java.util.Map;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.model.UserSession;
import com.google.appengine.api.images.Image;
import com.googlecode.objectify.annotation.Subclass;

/**
 * Documentation of the creation process of MountainPhoto
 * 1. Sequence of calls until creation
 * 	1.1 MountainPhotoManager is initialized in ModelMain (startUP)
 * 	1.2 MountainPhotoManager is called in UploadPhotoFormHandler (doHandlePost(UserSession us, Map args))
 * 		with the call createPhoto(fileName, uploadedImage)
 * 	1.2.1	MountainPhotoManager inherits this method from PhotoManager
 * 	1.3 Next PhotoUtil is called with the method createPhoto(String filename, PhotoId id, Image uploadedImage)
 * 	1.4 In PhotoUtil the MountainPhotoFactory.getInstance().createPhoto(id) method is called 
 * 	1.5 In createPhoto(id) from MountainPhotoFactory a new MountainPhoto is created through: return new MountainPhoto(id)
 * (1.6 This calls the constructor from MountainPhoto
 * 	1.7 The constructor is calling the inherited constructor from Photo through super(id)
 * 	1.8 Lastly the id is set and the counter from DataObject is incremented through incWriteCount();)
 * 
 * 2. Object creation table
 * 	2.1 Delegation: separate-object (MountainPhotoFactory is creating the MountainPhoto)
 * 	2.2 Selection: by-subclassing (MountainPhotoFactory is a subclass from PhotoFactory)
 * 	2.3 N/A
 * 	2.4 Instantiation: in code (constructor of MountainPhoto is called directly)
 * 	2.5 Initialization: By-key-value-pair (constructor of MountainPhoto has variable argument list)
 * 	2.6 Building: Default (MountainPhoto is creating structure by itself)
 * 
 */

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
