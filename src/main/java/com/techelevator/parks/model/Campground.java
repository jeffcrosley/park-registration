package com.techelevator.parks.model;

import java.math.BigDecimal;
import java.text.DateFormatSymbols;

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
    
    private String getMonth(int month) {
    	return new DateFormatSymbols().getMonths()[month-1];
    }
    
    public String toString() {
    	String tabsAfterName = "\t";
    	String tabsAfterOpenMonth = "\t";
    	String tabsAfterCloseMonth = "\t";
    	
    	if (getName().length() < 10) {
    		tabsAfterName += "\t";
    	}
    	
    	if (getName().length() < 15) {
    		tabsAfterName += "\t";
    	}
    	
    	if (getName().length() < 30) {
    		tabsAfterName += "\t";
    	}
    	
    	if (getMonth(getOpenDate()).length() < 8) {
    		tabsAfterOpenMonth += "\t";
    	}
    	
    	if (getMonth(getCloseDate()).length() < 8) {
    		tabsAfterCloseMonth += "\t";
    	}
        return "\t" + getCampgroundId() + "\t" + getName() + tabsAfterName + getMonth(getOpenDate()) + 
        		tabsAfterOpenMonth + getMonth(getCloseDate()) + tabsAfterCloseMonth + String.format("$%.2f", getFee());
    }
}
