package com.techelevator.parks.model;

public class Site {

	private long id;
	private long campgroundId;
	private long site_number;
	private long maxOccupancy;
	private boolean isAccessible;
	private long maxRVLength;
	private boolean hasUtilities;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getCampgroundId() {
		return campgroundId;
	}
	
	public void setCampgroundId(long campgroundId) {
		this.campgroundId = campgroundId;
	}
	
	public long getSite_number() {
		return site_number;
	}
	
	public void setSite_number(long site_number) {
		this.site_number = site_number;
	}
	
	public long getMaxOccupancy() {
		return maxOccupancy;
	}
	
	public void setMaxOccupancy(long maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}
	
	public String isAccessible() {
		if (isAccessible) {
			return "Yes";
		} else {
			return "No";
		}
	}
	
	public void setAccessible(boolean isAccessible) {
		this.isAccessible = isAccessible;
	}
	
	public String getMaxRVLength() {
		if (maxRVLength == 0) {
			return "N/A";
		} else {
			return String.valueOf(maxRVLength);
		}
	}
	
	public void setMaxRVLength(long maxRVLength) {
		this.maxRVLength = maxRVLength;
	}
	
	public String isHasUtilities() {
		if (hasUtilities) {
			return "Yes";
		} else {
			return "N/A";
		}
	}
	
	public void setHasUtilities(boolean hasUtilities) {
		this.hasUtilities = hasUtilities;
	}

	public String toString() {
		return "\t" + getSite_number() + "\t\t" + getMaxOccupancy() + "\t\t" + isAccessible() 
		+ "\t\t" + getMaxRVLength() + "\t" + isHasUtilities();
    }
}
