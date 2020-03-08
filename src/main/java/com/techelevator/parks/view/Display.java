package com.techelevator.parks.view;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.techelevator.parks.model.Campground;
import com.techelevator.parks.model.Park;

public class Display {

	private static final String DIVIDER = "--------------------------------------------------------------------------------------------------------------\n";
	
	private static final String SELECT_PARK_PROMPT = "Select a Park for Further Details\n";
	private static final String SELECT_COMMAND_PROMPT = "Select a Command\n";
	
	private static final String CAMPGROUNDS_HEADER = "\tId\tName\t\t\t\tOpen\t\tClose\t\tDaily Fee";
	private static final String SITES_HEADER = "\tSite No.\tMax Occup.\tAccessible?\tRV Len\tUtility\tCost";
	
	private static final String VALID_YN_INPUT_PROMPT = "Please input 'Y' or 'N'";

	private static final String PARK_MENU_1 = "View Campgrounds";
	private static final String PARK_MENU_2 = "Search for Reservation";
	private static final String PARK_MENU_3 = "Return to Previous Screen";

	private static final String CAMPGROUND_MENU_1 = "Search for Available Reservations";
	private static final String CAMPGROUND_MENU_2 = "Return to Previous Screen";

	private static final String ARRIVAL_DATE_PROMPT = "What is the arrival date? mm/dd/yyyy";
	private static final String DEPARTURE_DATE_PROMPT = "What is the departure date? mm/dd/yyyy";
	private static final String RESBUMIT_DATES_PROMPT = "No sites available for that date range.  Would you like to submit an alternate date range (Y/N)?";

	private static final String RESERVATION_NAME_PROMPT = "What name should the reservation be made under?";
	
	private static final String RESERVATION_MADE = "The reservation has been made and the confirmation id is ";
	
	public static String getDivider() {
		return DIVIDER;
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

	public static String getCampgroundsHeader() {
		return CAMPGROUNDS_HEADER;
	}

	public static String getSitesHeader() {
		return SITES_HEADER;
	}
	
	public static String getValidYnInputPrompt() {
		return VALID_YN_INPUT_PROMPT;
	}
	
	public static String getArrivalDatePrompt() {
		return ARRIVAL_DATE_PROMPT;
	}

	public static String getDepartureDatePrompt() {
		return DEPARTURE_DATE_PROMPT;
	}

	public static String getResbumitDatesPrompt() {
		return RESBUMIT_DATES_PROMPT;
	}

	public static String getReservationNamePrompt() {
		return RESERVATION_NAME_PROMPT;
	}

	public static String getReservationMade() {
		return RESERVATION_MADE;
	}
	
	public static String getSelectParkPrompt() {
		return SELECT_PARK_PROMPT;
	}
	
	public static String getSelectCommandPrompt() {
		return SELECT_COMMAND_PROMPT;
}

	
	private static String formatTextForConsole(String text) {
		String output = "";
		final int WORDS_PER_LINE = 10;
		String[] arr = text.split(" ");
		String[] line = new String[WORDS_PER_LINE];
		int wordCounter = 0;
			
		for (int i = 0; i < arr.length; i++) {
			line[wordCounter] = arr[i];
			wordCounter++;
			if (wordCounter == WORDS_PER_LINE || i == arr.length - 1) {
				output += String.join(" ", line);
				output += "\n";
				wordCounter = 0;
				for (int j = 0; j < WORDS_PER_LINE; j++) {
					line[j] = "";
				}
			}
		}
		
		return output;
	}
	
	public static void printParkInfo(Park selectedPark) {
		System.out.println(DIVIDER);
        System.out.println(selectedPark.getName() + " National Park");
        System.out.println("Location: \t\t" + selectedPark.getLocation());
        System.out.println("Established: \t\t" + selectedPark.getEstablishedDate());
        System.out.println("Area: \t\t\t" + NumberFormat.getNumberInstance(Locale.US).format(selectedPark.getArea()) + " sq km");
        System.out.println("Annual Visitors: \t" + NumberFormat.getNumberInstance(Locale.US).format(selectedPark.getAnnualVisitors()));
        System.out.println("\n" + formatTextForConsole(selectedPark.getDescription()));
        System.out.println(DIVIDER);
    }
	
	public static void printCampgrounds(Park selectedPark, List<Campground> campgrounds) {
		System.out.println(DIVIDER);
		System.out.println(selectedPark.getName() + " National Park Campgrounds\n");
		System.out.println(CAMPGROUNDS_HEADER);
		for (Campground grounds : campgrounds) {
			System.out.println(grounds);
		}
		System.out.println("\n" + DIVIDER);
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

