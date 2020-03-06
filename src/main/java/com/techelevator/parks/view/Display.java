package com.techelevator.parks.view;

import java.util.ArrayList;
import java.util.List;

import com.techelevator.parks.model.Campground;
import com.techelevator.parks.model.Park;
import com.techelevator.parks.model.Site;

public class Display {

	private static final String divider = "------------------------------------------------------------";
	
	private static final String PARK_MENU_1 = "View Campgrounds";
	private static final String PARK_MENU_2 = "Search for Reservation";
	private static final String PARK_MENU_3 = "Return to Previous Screen";

	private static final String CAMPGROUND_MENU_1 = "Search for Available Reservations";
	private static final String CAMPGROUND_MENU_2 = "Return to Previous Screen";
		
	private static final String ARRIVAL_DATE_PROMPT = "What is the arrival date? _/_/__";
	private static final String DEPARTURE_DATE_PROMPT = "What is the departure date? _/_/__";

	private static final String RESERVATION_NAME_PROMPT = "What name should the reservation be made under?";
	
	private static final String RESERVATION_MADE = "The reservation has been made and the confirmation id is ";
	
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

	public static String getCampgroundMenu1() {
		return CAMPGROUND_MENU_1;
	}

	public static String getCampgroundMenu2() {
		return CAMPGROUND_MENU_2;
	}

	public static String getArrivalDatePrompt() {
		return ARRIVAL_DATE_PROMPT;
	}

	public static String getDepartureDatePrompt() {
		return DEPARTURE_DATE_PROMPT;
	}

	public static String getReservationNamePrompt() {
		return RESERVATION_NAME_PROMPT;
	}

	public static String getReservationMade() {
		return RESERVATION_MADE;
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
	
	public static void printCampgrounds(List<Campground> campgrounds) {
		// TODO DEFINE THIS METHOD; FIX TOSTRING METHOD IN OBJECT
	}
	
	public static List<String> getParkMenu() {
		List<String> output = new ArrayList<String>();
		output.add(PARK_MENU_1);
		output.add(PARK_MENU_2);
		output.add(PARK_MENU_3);
		return output;
	}

	public static List<String> getCampgroundMenu() {
		List<String> output = new ArrayList<String>();
		output.add(CAMPGROUND_MENU_1);
		output.add(CAMPGROUND_MENU_2);
		return output;
	}
}
