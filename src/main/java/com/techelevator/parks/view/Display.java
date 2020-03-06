package com.techelevator.parks.view;

import java.util.List;

import com.techelevator.parks.model.Park;

public class Display {

	private static String divider = "------------------------------------------------------------";
	
	public static void displayAllParks(List<Park> parks) {
		System.out.println("Select a Park for Further Details");
		System.out.println(divider);
		for (Park park : parks) {
			System.out.println(park.getId() + " " + park.getName());
		}
	}
}
