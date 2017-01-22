package org.wahlzeit.model.mountain;

/**
 * Documentation of the creation process of Mountain
 * 1. Sequence of calls until creation
 * 	1.1 MountainManager is called through createMountain(MountainType type, String mountainName, double height, Continent continent)
 * 	1.2 This method creates a new Mountain through: Mountain newMountain = new Mountain (type, mountainName, height, continent);
 * (1.3 The new Mountain is returned to the caller)
 * 
 * 2. Object creation table
 * 	2.1 Delegation: separate-object (MountainManager is creating objects of Mountain)
 * 	2.2 On-the-Spot: concrete class is selected through hard coded new Mountain
 * 	2.3 N/A
 * 	2.4 Instantiation: in code (constructor of Mountain is called directly)
 * 	2.5 Initialization: default (constructor of Mountain has fixed argument list)
 * 	2.6 Building: Default (Mountain is creating structure by itself)
 * 
 */

import org.wahlzeit.model.Continent;
import org.wahlzeit.model.Location;
import org.wahlzeit.services.DataObject;
import org.wahlzeit.utils.PatternInstance;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;

/**
 * 
 * representation of Mountain object
 *
 */

@PatternInstance(
		name = "type object",
		participants = {"Mountain", "MountainType"}
		)
@Entity
public class Mountain extends DataObject {
	


	private MountainType type = null;
	
	@Id
	long mountainID;
	
	private String mountainName = "Default";
	
	private Continent continent = Continent.Default;
	
	private double height = 0;
	
	Location location = Location.LOCATION_UNDEFINED;
	
	
	/**
	 * 
	 * @param type
	 * @param mountainName
	 * @param height
	 * @param continent
	 */
	
	//Step 1.2 in creation process of Mountain 
	public Mountain(MountainType type, String mountainName, double height, Continent continent) {
		assertIsValidHeight(height);
		assertIsNonNullMountainName(mountainName);
		this.type = type;
		this.mountainName = mountainName;
		this.continent = continent;
		this.height = height;
	}

	/**
	 * 
	 * @param newHeight
	 * @throws IllegalHeightMountainException 
	 * 
	 * @methodtype set
	 */
	public void setMountainHeight(double newHeight) throws IllegalArgumentException {
		assertIsValidHeight(newHeight);
		this.height = newHeight;
	}
	/**
	 * 
	 * @param mountainName
	 * @throws IllegalArgumentException
	 * 
	 * @methodtype set
	 */
	public void setName(String mountainName) throws IllegalArgumentException {
		assertIsNonNullMountainName(mountainName);
		this.mountainName = mountainName;
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
	 * @return Continent
	 * 
	 * @methodtype get
	 */
	public Continent getContinent() {
		return this.continent;
	}
	
	/**
	 * @return mountainName
	 * 
	 * @methodtype get
	 */
	public String getMountainName() {
		return this.mountainName;
	}
	
	
	
	/**
	 * 
	 * @param height
	 * @throws IllegalHeightMountainException
	 * 
	 * @metodtype assertion
	 */
	public void assertIsValidHeight(double height) throws IllegalArgumentException {
		// this is necessary because the highest mountain - regarding the
		// elevation above sea level-
		// is Mount Everest with 8850 metres and there is no mountain defined
		// under 0 metres
		if (height < 0 || height > 8850) {
			String exceptionMsg = "Illegal parameter for Mountain Height!";
			throw new IllegalArgumentException(exceptionMsg + height);
		}
	}
	
	/**
	 * 
	 * @param mountainName
	 */
	public void assertIsNonNullMountainName(String mountainName) {
		if (mountainName == null) {
			throw new IllegalArgumentException("Type Name must not be null");
		}
	}
	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((continent == null) ? 0 : continent.hashCode());
		long temp;
		temp = Double.doubleToLongBits(height);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((mountainName == null) ? 0 : mountainName.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Mountain other = (Mountain) obj;
		if (continent != other.continent)
			return false;
		if (Double.doubleToLongBits(height) != Double.doubleToLongBits(other.height))
			return false;
		if (mountainName == null) {
			if (other.mountainName != null)
				return false;
		} else if (!mountainName.equals(other.mountainName))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Mountain [type=" + type.toString() + ", mountainName=" + mountainName + ", height=" + height + "]";
	}
	
	
	
	
}
