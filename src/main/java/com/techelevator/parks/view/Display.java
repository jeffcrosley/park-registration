package com.techelevator.parks.view;

import java.util.ArrayList;
import java.util.List;


import com.techelevator.parks.model.Park;

public class Display {

	private static final String divider = "------------------------------------------------------------";
	
	private static final String PARK_MENU_1 = "View Campgrounds";
	private static final String PARK_MENU_2 = "Search for Reservation";
	private static final String PARK_MENU_3 = "Return to Previous Screen";
	
	
	public static String getDivider() {
		return divider;
	}

	public static String getParkMenu1() {
		return PARK_MENU_1;
	}

	public static String getParkMenu2() {
		return PARK_MENU_2;
	}

	public static String getParkMenu3() {
		return PARK_MENU_3;
	}

	public static void printParkInfo(Park selectedPark) {
		// TODO FORMAT THIS
        System.out.println(selectedPark.getName());
        System.out.println(selectedPark.getLocation());
        System.out.println(selectedPark.getEstablishedDate());
        System.out.println(selectedPark.getArea());
        System.out.println(selectedPark.getAnnualVisitors());
        System.out.println(selectedPark.getDescription());
    }
	
	public static List<String> getParkMenu() {
		List<String> output = new ArrayList<String>();
		output.add(PARK_MENU_1);
		output.add(PARK_MENU_2);
		output.add(PARK_MENU_3);
		return output;

	}
	
} 
