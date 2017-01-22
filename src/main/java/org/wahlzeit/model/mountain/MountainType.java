package org.wahlzeit.model.mountain;

import java.util.HashSet;
import java.util.Set;

import org.wahlzeit.services.DataObject;
import org.wahlzeit.utils.PatternInstance;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Serialize;

/**
 * representation of the Mountain Type class
 * 
 */
@PatternInstance(
		name = "type object",
		participants = {"Mountain", "MountainType"}
		)
@Entity
public class MountainType extends DataObject {

	@Id
	long typeId;
	
	private String typeName = "";
	
	private MountainAltitudinalBelt altitudinalBelt = MountainAltitudinalBelt.DEFAULT;
	@Serialize
	private Set<MountainType> subType= new HashSet<MountainType>();
	
	/**
	 * 
	 * @param typeName
	 */
	public MountainType(String typeName, MountainAltitudinalBelt altitudinalBelt) {
		assertIsNonNullTypeName(typeName);
		this.typeName = typeName;
		this.altitudinalBelt = altitudinalBelt;
	}
		
	/**
	 * 
	 * @param searchType
	 * @return boolean
	 */
	public boolean isSubtype(MountainType searchType) {
		for (MountainType temp : subType){
			if(temp.equals(searchType)) {
				return true;
			}
			return temp.isSubtype(searchType);
		}
		return false;
	}

	/**
	 * 
	 * @return typeName
	 */
	public String getTypeName() {
		return this.typeName;
	}

	/**
	 * @return altitudinal belt as a string
	 * 
	 * @methodtype get
	 */
	public MountainAltitudinalBelt getAltitudinalBelt() {
		return altitudinalBelt;
	}
	/**
	 * 
	 * @return
	 * 
	 * @methodtype get
	 */
	public Set<MountainType> getSubtypes() {
		return subType;
	}
	
	/**
	 * 
	 * @param typeName
	 * @methodtype set
	 */
	public void setTypeName(String typeName){
		assertIsNonNullTypeName(typeName);
		this.typeName = typeName;
	}
	
	/**
	 * 
	 * @param typeName
	 * @methodtype set
	 */
	public void setMountainAltitudinal(MountainAltitudinalBelt altitudinal){
		this.altitudinalBelt = altitudinal;
	}

	
	/**
	 * 
	 * @param mountainType
	 */
	public void addSubType(MountainType mountainType) {
		subType.add(mountainType);
	}

	/**
	 * 
	 * @param typeName
	 */
	public void assertIsNonNullTypeName(String typeName) {
		if (typeName == null) {
			throw new IllegalArgumentException("Type Name must not be null");
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((altitudinalBelt == null) ? 0 : altitudinalBelt.hashCode());
		result = prime * result + ((subType == null) ? 0 : subType.hashCode());
		result = prime * result + ((typeName == null) ? 0 : typeName.hashCode());
		return result;
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MountainType other = (MountainType) obj;
		if (altitudinalBelt != other.altitudinalBelt)
			return false;
		if (subType == null) {
			if (other.subType != null)
				return false;
		} else if (!subType.equals(other.subType))
			return false;
		if (typeName == null) {
			if (other.typeName != null)
				return false;
		} else if (!typeName.equals(other.typeName))
			return false;
		return true;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "MountainType [typeName=" + typeName + "]";
	}


}
