package com.techelevator.parks.model;

import java.math.BigDecimal;

public class Campground {
	
	private long campgroundId;
    private long parkId;
    private String name;
    private int openDate;
    private int closeDate;
    private BigDecimal fee;
    
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


    public BigDecimal getFee() {
        return fee;
    }


    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
}
