package org.wahlzeit.model.mountain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.wahlzeit.model.Continent;
import org.wahlzeit.services.LogBuilder;
import org.wahlzeit.services.ObjectManager;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Work;
import com.googlecode.objectify.annotation.Entity;

/**
 * 
 * Mountain Manager to create new objects of MountainTypes and Mountain and manage them
 *
 */


public class MountainManager extends ObjectManager {

	private static final Logger log = Logger.getLogger(MountainManager.class.getName());
	
	private static MountainManager manager = new MountainManager();

	protected Map<String, MountainType> mountainTypesCollection = new HashMap<String, MountainType>();

	protected Map<String, Mountain> mountainCollection = new HashMap<String, Mountain>();

	/**
	 * 
	 * 
	 */
	public static synchronized MountainManager getInstance() {
		return manager;
	}
	
	/**
	 * 
	 * @param type
	 * @param mountainName
	 * @param height
	 * @param continent
	 */
	
	//Step 1.1 in creation process of Mountain 
	public synchronized Mountain createMountain(MountainType type, String mountainName, double height, Continent continent) {
		assertIsNonNullArgument(type);
		if((!findMountainType(type.getTypeName()))) {
			doAddMountainType(type);
		}
		if(findMountain(mountainName)) {
			throw new IllegalArgumentException(mountainName + " is already existing!");
		}
		Mountain newMountain = new Mountain (type, mountainName, height, continent);
		doAddMountain(newMountain) ;
		return newMountain;
	}
	
	/**
	 * 
	 * @param typeName
	 * @param altitudinalBelt
	 * @return MountainType
	 */
	public synchronized MountainType createMountainType(String typeName,
			MountainAltitudinalBelt altitudinalBelt) {
		if(findMountainType(typeName)){
			throw new IllegalArgumentException(typeName + " is already existing!");
		}
		MountainType newType = new MountainType(typeName, altitudinalBelt);
		doAddMountainType(newType);
		return newType;
	}
		
	/**
	 * 
	 * @param typeName
	 * @param altitudinalBelt
	 * @param SuperType
	 * @return MountainType
	 */
	public synchronized MountainType createMountainTypeWithSuperType(String typeName,
			MountainAltitudinalBelt altitudinalBelt, MountainType superType) {
		
		assertIsNonNullArgument(superType);
		if(findMountainType(typeName)) {
			throw new IllegalArgumentException( typeName + " is already existing!");
		}
		if(!(findMountainType(superType.getTypeName()))) {
			doAddMountainType(superType);
		}
		MountainType newType = new MountainType(typeName, altitudinalBelt);
		doAddMountainType(newType);
		superType.addSubType(newType);
		return newType;
	}
	
	/**
	 * 
	 * @param addObject
	 * @return added Mountain
	 */
	private void doAddMountain (Mountain addObject) {
		mountainCollection.put(addObject.getMountainName(), addObject);
	}
	
	/**
	 * 
	 * @param addType
	 * @return
	 */
	private void doAddMountainType(MountainType addType) {
		mountainTypesCollection.put(addType.getTypeName(), addType);
	}
	
	/**
	 * 
	 * @param mountainName
	 * @return boolean
	 */
	public boolean findMountain(String mountainName) {
		return mountainCollection.containsKey(mountainName);
	}
	
	/**
	 * 
	 * @param typeName
	 * @return boolean
	 */
	public boolean findMountainType(String typeName) {
		return mountainTypesCollection.containsKey(typeName);
	}
	
	/**
	 * 
	 */
	public void init() {
		Collection<MountainType> existingTypes = ObjectifyService.run(new Work<Collection<MountainType>>() {
			@Override
			public Collection<MountainType> run() {
				Collection<MountainType> existingTypes = new ArrayList<MountainType>();
				readObjects(existingTypes, MountainType.class);
				return existingTypes;
			}
		});

		for (MountainType type : existingTypes) {
			if (!findMountainType(type.getTypeName())) {
				log.config(LogBuilder.createSystemMessage().addParameter("Load MountainType", type.toString())
						.toString());
				doAddMountainType(type);
			} else {
				log.config(LogBuilder.createSystemMessage().addParameter("Already loaded Mountain Type", type.toString())
						.toString());
			}
		}

		log.info(LogBuilder.createSystemMessage().addMessage("All Mountain Types loaded.").toString());
		
		Collection<Mountain> existingMountains = ObjectifyService.run(new Work<Collection<Mountain>>() {
			@Override
			public Collection<Mountain> run() {
				Collection<Mountain> existingMountains = new ArrayList<Mountain>();
				readObjects(existingMountains, Mountain.class);
				return existingMountains;
			}
		});

		for (Mountain mountain : existingMountains) {
			if (!findMountain(mountain.getMountainName())) {
				log.config(LogBuilder.createSystemMessage().addParameter("Load Mountains", mountain.toString())
						.toString());
				doAddMountain(mountain);
			} else {
				log.config(LogBuilder.createSystemMessage().addParameter("Already loaded Mountains", mountain.toString())
						.toString());
			}
		}

		log.info(LogBuilder.createSystemMessage().addMessage("All Mountains loaded.").toString());
	}
	
		
}
