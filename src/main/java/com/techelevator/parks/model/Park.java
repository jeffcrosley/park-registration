package com.techelevator.parks.model;

import java.time.LocalDate;

public class Park {

	private long id;
	private String name;
	private String location;
	private LocalDate establishedDate;
	private int area;
	private int annualVisitors;
	private String description;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public LocalDate getEstablishedDate() {
		return establishedDate;
	}
	
	public void setEstablishedDate(LocalDate establishedDate) {
		this.establishedDate = establishedDate;
	}
	
	// RETURN STATEMENT SHOULD BE IN STRING FORMAT, SPECIFYING TO THE USER THAT THIS IS IN SQ KM
	public String getArea() {
		return String.valueOf(area) + " sq km";
	}
	
	public void setArea(int area) {
		this.area = area;
	}
	
	public int getAnnualVisitors() {
		return annualVisitors;
	}
	
	public void setAnnualVisitors(int annualVisitors) {
		this.annualVisitors = annualVisitors;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return getName();
	}

}
