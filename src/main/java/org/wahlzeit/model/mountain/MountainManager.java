package org.wahlzeit.model.mountain;

import java.util.HashMap;
import java.util.Map;

import org.wahlzeit.model.Continent;
import org.wahlzeit.services.ObjectManager;

/**
 * 
 * Mountain Manager to create new objects of MountainTypes and Mountain and manage them
 *
 */
public class MountainManager extends ObjectManager {

	private static MountainManager manager = new MountainManager();

	protected static final Map<Integer, MountainType> mountainTypesCollection = new HashMap<>(500);

	protected static final Map<Integer, Mountain> mountainCollection = new HashMap<>(500);

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
	public synchronized Mountain createMountain(MountainType type, String mountainName, double height, Continent continent) {
		assertIsNonNullArgument(type);
		if((!findMountainType(type))) {
			doAddMountainType(type);
		}
		Mountain newMountain = new Mountain (type, mountainName, height, continent);
		if(findMountain(newMountain)) {
			throw new IllegalArgumentException(newMountain.toString()+ "is already existing!");
		}
		doAddMountain(newMountain) ;
		return newMountain;
	}
	
	/**
	 * 
	 * @param typeName
	 * @param parent
	 * @param altitudinalBelt
	 * @return MountainType
	 */
	public synchronized MountainType createMountainType(String typeName,
			MountainAltitudinalBelt altitudinalBelt) {
		MountainType newType = new MountainType(typeName, altitudinalBelt);
		if(findMountainType(newType)){
			throw new IllegalArgumentException(newType.toString()+ "is already existing!");
		}
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
		MountainType newType = new MountainType(typeName, altitudinalBelt);
		assertIsNonNullArgument(superType);
		if(findMountainType(newType)) {
			throw new IllegalArgumentException("type is already existent" + newType.toString());
		}
		if(!(findMountainType(superType))) {
			doAddMountainType(superType);
		}
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
		assert(mountainCollection.put(addObject.hashCode(), addObject) != null);
	}
	
	/**
	 * 
	 * @param addType
	 * @return
	 */
	private void doAddMountainType(MountainType addType) {
		assert(mountainTypesCollection.put(addType.hashCode(), addType) != null);
	}
	
	/**
	 * 
	 * @param mountainName
	 * @return boolean
	 */
	public boolean findMountain(Mountain mountain) {
		Mountain lookUp = mountainCollection.get(mountain.hashCode());
		if(lookUp != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param mountainTypeName
	 * @return boolean
	 */
	public boolean findMountainType(MountainType mountainType) {
		MountainType lookUp = mountainTypesCollection.get(mountainType.hashCode());
		if(lookUp != null) {
			return true;
		}
		return false;
	}
	
		
}
