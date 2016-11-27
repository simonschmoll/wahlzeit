package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {

	/**
	 * calculates the distance between to Coordinates
	 * @param comparisonCoordinate,
	 *            comparisonCoordinate2
	 * @return direct distance between two Coordinates
	 * 
	 */
	public double getDistance(Coordinate comparisonCoordinate) {
		return Math.sqrt((Math.pow(comparisonCoordinate.asCartesian().getX() - this.asCartesian().getX(), 2))
				+ (Math.pow(comparisonCoordinate.asCartesian().getY() - this.asCartesian().getY(), 2))
				+ (Math.pow(comparisonCoordinate.asCartesian().getZ() - this.asCartesian().getZ(), 2)));
	}

	/**
	 * @param comparisonCoordinate
	 * @return boolean
	 */
	public boolean isEqual(Coordinate comparisonCoordinate) {
		if (this == null || comparisonCoordinate == null) {
			return false;
		} else if (comparisonCoordinate == this) {
			return true;
		} else if (doIsEqualWithDeviation(comparisonCoordinate.asCartesian().getX(), this.asCartesian().getX())
				&& doIsEqualWithDeviation(comparisonCoordinate.asCartesian().getY(), this.asCartesian().getY())
				&& doIsEqualWithDeviation(comparisonCoordinate.asCartesian().getZ(), this.asCartesian().getZ()))
			return true;
		return false;
	}

	public boolean doIsEqualWithDeviation(double x, double y) {
		double deviation = 0.00001;
		if (Math.abs(x - y) <= deviation)
			return true;
		return false;
	}
}
