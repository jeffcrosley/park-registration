package com.techelevator.parks.view;

import java.util.List;

import com.techelevator.parks.model.Park;

public class Menu {
	
	public static void displayAllParks(List<Park> parks) {
		for (Park park : parks) {
			System.out.println(park.getId() + " " + park.getName());
		}
	}
}