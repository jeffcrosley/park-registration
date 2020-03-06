package com.techelevator.parks.model;

import java.time.LocalDate;

public class Campground {
	
	private long campgroundId;
	private long parkId;
	private String name;
	private int openDate;
	private int closeDate;
	
	public long getCampgroundId() {
		return campgroundId;
	}


	public void setCampgroundId(long campgroundId) {
		this.campgroundId = campgroundId;
	}


	public long getParkId() {
		return parkId;
	}


	public void setParkId(long parkId) {
		this.parkId = parkId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getOpenDate() {
		return openDate;
	}


	public void setOpenDate(String openDate) {
		this.openDate = Integer.parseInt(openDate);
				
	}


	public int getCloseDate() {
		return closeDate;
	}


	public void setCloseDate(String closeDate) {
		this.closeDate = Integer.parseInt(closeDate);
	}


	public int getFee() {
		return fee;
	}


	public void setFee(int fee) {
		this.fee = fee;
	}


	private int fee;
	

	public Campground() {
		
	}

}
